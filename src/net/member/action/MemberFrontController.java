// 리퀘스트 리스폰스 = 주소가 바뀔때마다 초기회된다
// 				(Redirect로 하면 주소창이 변경되면서 초기화)
//				(Forward로 하면 주소창이 고정되면서 유지)
// 세션 = 브라우저을 종료할때 초기회된다(Redirect와  Forward하고는 관계없다)
// 어플리케이션 = 서버를 종료할때 초기화된다

package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.*;

// 서블릿 파일은 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet를 상속받는다
public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	// 버전관리를 위한 값
	static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward memberForward = null;
		net.board.action.ActionForward boradForward = null;
		Action memberAction = null;
		net.board.action.Action boradAction = null;

		System.out.println("REquestURI :" + RequestURI);
		System.out.println("contextPath :" + contextPath);
		System.out.println("command :" + command);
		
		int check = 0;

		if (command.equals("/")) {
			boradAction = new BoardListAction();
			check = 1;
			try {
				boradForward = boradAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/singInUp.me")) {
			check = 2;
			memberForward = new ActionForward();
			memberForward.setRedirect(false);
			memberForward.setPath("./member/singInUp.jsp");
		} else if (command.equals("/MemberLoginAction.me")) {
			check = 2;
			memberAction = new MemberLoginAction();
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLogoutAction.me")) {
			check = 2;
			memberAction = new MemberLogoutAction();
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberJoinAction.me")) {
			check = 2;
			memberAction = new MemberJoinAction();
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberListAction.me")) {
			check = 2;
			System.out.println("list");
			memberAction = new MemberListAction();
			System.out.println("list11");
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberViewAction.me")) {
			check = 2;
			memberAction = new MemberViewAction();
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDeleteAction.me")) {
			check = 2;
			memberAction = new MemberDeleteAction();
			try {
				memberForward = memberAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if (check == 1) {
			System.out.println(boradForward.isRedirect());
			// 주소랑 페이지 바꿈
			if (boradForward.isRedirect()) {
				response.sendRedirect(boradForward.getPath());
			} else {
				// 페이지만 바꿈
				RequestDispatcher dispatcher = request.getRequestDispatcher(boradForward.getPath());
				dispatcher.forward(request, response);
			}
		} else if (check == 2) {
			System.out.println(memberForward.isRedirect());
			// 주소랑 페이지 바꿈
			if (memberForward.isRedirect()) {
				response.sendRedirect(memberForward.getPath());
			} else {
				// 페이지만 바꿈
				RequestDispatcher dispatcher = request.getRequestDispatcher(memberForward.getPath());
				dispatcher.forward(request, response);
			}
		} 
	}
	
	// servlet 클래스를 직접 만든 것
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
