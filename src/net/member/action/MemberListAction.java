package net.member.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		
		List<MemberBean> memberlist = new ArrayList<MemberBean>();
		
		String id = (String) session.getAttribute("id");
		if (id == null) {
			forward.setRedirect(true);
			forward.setPath("./singInUp.me");
			return forward;
		} else if (!id.contentEquals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자가 아닙니다');"
					+ "location.href='./singInUp.me';</script>");
			out.close();
			return null;
		}
		
		memberlist = memberdao.getMemberList();
		if(memberlist == null) {
			System.out.println("회원 목록 읽기 실패");
			return null;
		}
		
		request.setAttribute("memberlist", memberlist);
		forward.setRedirect(false);
		forward.setPath("./member/member_list.jsp");
		return forward;
	}

}
