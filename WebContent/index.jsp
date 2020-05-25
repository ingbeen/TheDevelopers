<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.board.db.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
	List boardList = (List) request.getAttribute("boardlist");
	int listcount = ((Integer) request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	int maxpage = ((Integer) request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer) request.getAttribute("startpage")).intValue();
	int endpage = ((Integer) request.getAttribute("endpage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<title>TheDevelopers</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./css/indexStyle.css">
<link rel="stylesheet" href="./css/headerStyle.css">

</head>
<body>

	<!-- Start Header -->
	<header>
		<div class="container">
			<div class="row">
				<div class="brand-name">
					<a href="">TheDevelopers</a>
				</div>
				<div class="wrap">
					<div class="search">
						<input type="text" class="searchTerm">
						<button type="submit" class="searchButton">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
				<div class="login">
				<%
					if (id == null) {
				%>	
					<ul>
						<li><a href="./singInUp.me">로그인 / 회원가입</a></li>
					</ul>
				<%	
					} else {
						if (id.equals("admin")) {
				%>
						<ul>
							<li><a href="./MemberListAction.me">회원관리</a></li>
							<li><a href="./MemberLogoutAction.me">로그아웃</a></li>
						</ul>
				<% 
						} else {
				%>			
						<ul>
							<li><a href="./MemberLogoutAction.me">로그아웃</a></li>
						</ul>
				<% 
						}
					}
				%>
				</div>
			</div>
		</div>
	</header>
	<!-- End Header -->

	<!-- Start navbar -->
	<div class="navbar">
		<div class="container">
			<ul class="row">
				<li><a href="">공지사항</a></li>
				<li><a href="">팁과정보</a></li>
				<li><a href="">이모저모</a></li>
				<li><a href="">아무거나질문</a></li>
				<li><a href="">벼룩시장</a></li>
				<li><a href="">정기모임/스터디</a></li>
				<li><a href="">학원/홍보</a></li>
			</ul>
		</div>
	</div>
	<!-- End navbar -->

	<!-- Start Home -->
	<section class="Home">
		<div class="container">
			<table>
				<tr>
					<td class="left caption" colspan="4">최신글</td>
					<td class="right">
						<div class="write">
							<a href="./BoardWrite.bo">[글쓰기]</a>
						</div>
					</td>
				</tr>
				
				<tr class="center">
					<td width="80px">
						<div>번호</div>
					</td>
					<td width="450px">
						<div>제목</div>
					</td>
					<td width="130px">
						<div>작성자</div>
					</td>
					<td width="140px">
						<div>날짜</div>
					</td>
					<td width="100px">
						<div>조회수</div>
					</td>
				</tr>
				
				<%
				int num = listcount - ((nowpage - 1) * 10);
				for (int i = 0; i < boardList.size(); i++) {
					BoardBean bl = (BoardBean) boardList.get(i);
				%>
				<tr class="contents">
					<td class="center">
						<%=num %>
					</td>
					<td class="left">
						<div class="subject">
							<%if (bl.getBOARD_RE_LEV() != 0) { %>
								<%for (int a = 0; a <= bl.getBOARD_RE_LEV() * 2; a++) { %>
								&nbsp;
								<%} %>
								▶
							<%} else { %>
								▶
							<%} %>
							<a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM()%>"> 
								<%=bl.getBOARD_SUBJECT()%>
							</a>
						</div>
					</td>
					<td class="center">
						<div><%=bl.getBOARD_ID()%></div>
					</td>
					<td class="center">
						<div><%=bl.getBOARD_DATE()%></div>
					</td>
					<td class="center">
						<div><%=bl.getBOARD_READCOUNT()%></div>
					</td>
				</tr>
				<%
					num--;
				} 
				%>
				
				<tr class="center number">
					<td colspan="5">
						<%if (nowpage <= 1) { %> 
							[이전]
						<%} else { %> 
							<a href="./BoardList.bo?page=<%=nowpage - 1%>">[이전]</a>&nbsp; 
						<%} %> 
						<%for (int a = startpage; a <= endpage; a++) {
		 					if (a == nowpage) {%> 
		 						[<%=a%>]
		 					<%} else { %> 
		 						<a href="./BoardList.bo?page=<%=a%>">
		 							[<%=a%>]
								</a>
							<%} %> 
						<%} %>
						<%if (nowpage >= maxpage) { %> 
							[다음]
						<%} else { %>
							<a href="./BoardList.bo?page=<%=nowpage + 1%>">
							[다음]</a> 
							<%} %>
					</td>
				</tr>
			</table>
			<!-- 꼬리말 -->
			<br><br>
			구현 기능<br>
			우측 상단 : 로그인 / 회원가입 --- 'admin'으로 로그인하면 회원관리, 로그아웃 탭 생성(그외는 로그아웃 탭 생성)<br>
			그외 게시판 기능 동일
		</div>
	</section> 
	<!-- End Home -->

	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>