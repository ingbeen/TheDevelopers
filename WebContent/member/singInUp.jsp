<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<title>TheDevelopers</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/singInUpStyle.css">
</head>
<body>
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
			<label for="tab-1" class="tab">Sign In</label><!-- 
			--><input id="tab-2" type="radio" name="tab" class="sign-up"><!--
			--><label for="tab-2" class="tab">Sign Up</label>
			<div class="login-form">
				<form name="loginform" action="./MemberLoginAction.me" method="post">
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label">ID</label> <input type="text"
								class="input" name="member_id" maxlength="15">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input
								type="password" class="input" data-type="password"
								name="member_pw" maxlength="12">
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> Keep me
								Signed in</label>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign In">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="">Forgot Password?</a>
						</div>
					</div>
				</form>

				<div class="sign-up-htm">
					<form name="joinform" action="./MemberJoinAction.me" method="post"
						onsubmit="return check(this)">
						<div class="group">
							<label for="user" class="label">ID</label> <input type="text"
								class="input" name="member_id" maxlength="15">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input
								type="password" class="input" name="member_pw" maxlength="12">
						</div>
						<div class="group">
							<label for="pass" class="label">Repeat Password</label> <input
								type="password" class="input" name="repeat_pw" maxlength="12">
						</div>
						<div class="group">
							<label for="pass" class="label">Email Address</label> <input
								type="email" class="input" name="member_email">
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign Up">
						</div>
						<div class="hr"></div>
					</form>
					<div class="foot-lnk">
						<a href="">Already Member?</a>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="./js/jquery-3.5.1.js"></script>
	<script src="./js/singUpCheck.js"></script>
</body>
</html>
