<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%@ page import="net.member.db.*" %>
<%
MemberBean member = (MemberBean) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
</head>
<body>
	<table border="1" align="center" width="300">
		<tr align="center">
			<td>아이디 : </td>
			<td><%=member.getMember_id()%></td>
		</tr>
		<tr align="center">
			<td>비밀번호 : </td>
			<td><%=member.getMember_pw()%></td>
		</tr>
		<tr align="center">
			<td>이메일주소 : </td>
			<td><%=member.getMember_email()%></td>
		</tr>
		<tr align="center">
			<td colspan="2"><a href="MemberListAction.me">리스트로 돌아가기</a></td>
		</tr>
	</table>
</body>
</html>