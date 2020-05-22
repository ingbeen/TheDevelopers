package net.board.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action {
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		if (id == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}

		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<>();
		
		// 처음 보이는 페이지의 디폴트값
		int page = 1;
		
		// 한 화면에 보이는 글의 갯수
		int limit = 10;
		
		// 최초일때는 page는 1
		// 그전에 하단 페이지링크를 클릭했다면 그 페이지위치를 얻어옴
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 총 리스트 수를 받아옴
		int listcount = boarddao.getListCount();

		// 글 내용를 받아옴
		boardlist = boarddao.getBoardList(page, limit);

		// 0.9를 더해서 페이지를 총페이지수를 구함(글이 1개이면 1페이지, 11개면 2페이지, 101개면 11페이지)
		int maxpage = (int) ((double) listcount / limit + 0.9);
		
		// 하단 페이지링크 갯수[이전] [1] ... [10] [다음]의 [1]에 해당
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		
		// 하단 페이지링크 갯수[이전] [1] ... [10] [다음]의 [10]에 해당
		int endpage = maxpage;

		// 글이 1개이면 startpage는 1이 되고 maxpage는 1이 된다
		// 글이 11개이면 startpage는 1이 되고 maxpage는 2이 된다
		// 글이 101개이면 startpage는 1이 되고 maxpage는 11이 된다
		// 글이 101인 상태에서는 하단에 page링크는 1~10까지만 떠야되며 11은 뜨지 말아야 된다
		// 그걸 위해 startpage가 1일때는 10까지만 11일때는 20까지만 뜨게 한다
		if (endpage > startpage + 10 - 1) {
			endpage = startpage + 10 - 1;
		}

		// 현재 페이지 수
		request.setAttribute("page", page);
		// 최대 페이지 수
		request.setAttribute("maxpage", maxpage);
		// 현재 페이지에서 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		// 현재 페이지를 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		// 글수
		request.setAttribute("listcount", listcount);
		// 글내용
		request.setAttribute("boardlist", boardlist);
		
		// 위의 페이지수나 글수 등에 대한 정보를 세션에 담으면 세션객체가 꼬이므로
		// 리퀘스트에 담아서 setRedirect를 false로 지정하여
		// forward로 이동하게끔 하여 리퀘스트를 다음 페이지로 전달한다
		forward.setRedirect(false);
		forward.setPath("./board/qna_board_list.jsp");
		return forward;
	}
}