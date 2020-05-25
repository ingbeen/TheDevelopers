// 아이디 체크
function IDCheck (joinform) {
	var member_id = joinform.member_id.value;
	
	// 자릿수 검사
	if (member_id.length < 5 || 15 < member_id.length) {
		alert("아이디는 최소 5 ~ 최대 15자리를 입력해주십시오")
		joinform.member_id.focus();
		return false;
	} else {
		// 유효값 검사
		for(var i = 0; i < member_id.length; i++){
			var getIDTexts = member_id.substr(i, 1);
			if (!(("0" <= getIDTexts && getIDTexts <= "9")
					|| ("A" <= getIDTexts && getIDTexts <= "Z")
					|| ("a" <= getIDTexts && getIDTexts <= "z"))) {
				alert("아이디는 숫자와 알파벳만 사용해주십시오")
				joinform.member_id.focus();
				return false;
			};
		};
	};
};


// 비밀번호 체크
function passwordCheck (joinform) {
	
	// '비밀번호' 자릿수 검사
	var member_pw = joinform.member_pw.value;
	if (member_pw.length < 6 || 12 < member_pw.length) {
		alert("비밀번호는 최소 6 ~ 최대 12자리를 입력해주십시오")
		joinform.member_pw.focus();
		return false;
	} else {
		// '비밀번호' 유효값 검사
		for(var i = 0; i < member_pw.length; i++){
			var getPasswordTexts = member_pw.substr(i, 1);
			if (!(("0" <= getPasswordTexts && getPasswordTexts <= "9")
					|| ("A" <= getPasswordTexts && getPasswordTexts <= "Z")
					|| ("a" <= getPasswordTexts && getPasswordTexts <= "z"))) {
				alert("비밀번호는 숫자와 알파벳만 사용해주십시오")
				joinform.member_pw.focus();
				return false;
			};
		};
	};
	
	// '비밀번호 확인' 자릿수 검사
	var repeat_pw = joinform.repeat_pw.value;
	if (repeat_pw.length < 6 || 12 < repeat_pw.length) {
		alert("비밀번호는 최소 6 ~ 최대 12자리를 입력해주십시오")
		joinform.repeat_pw.focus();
		return false;
	} else {
		// '비밀번호 확인' 유효값 검사
		for(var i = 0; i < repeat_pw.length; i++){
			var getPasswordTexts = repeat_pw.substr(i, 1);
			if (!(("0" <= getPasswordTexts && getPasswordTexts <= "9")
					|| ("A" <= getPasswordTexts && getPasswordTexts <= "Z")
					|| ("a" <= getPasswordTexts && getPasswordTexts <= "z"))) {
				alert("비밀번호는 숫자와 알파벳만 사용해주십시오")
				joinform.repeat_pw.focus();
				return false;
			};
		};
	};
	
	// 비밀번호 일치하는지 체크
	if (!(member_pw == repeat_pw)) {
		alert("비밀번호가 일치하지 않습니다")
		joinform.member_pw.focus();
		return false;
	}
};


// 이메일 체크
function emailCheck (joinform) {
	var member_email = joinform.member_email.value;
	for(var i = 0; i < member_email.length; i++){
		var getEmailTexts = member_email.substr(i, 1);
		if (!(("0" <= getEmailTexts && getEmailTexts <= "9")
				|| ("A" <= getEmailTexts && getEmailTexts <= "Z")
				|| ("a" <= getEmailTexts && getEmailTexts <= "z")
				|| (getEmailTexts == "@")
				|| (getEmailTexts == "."))) {
			alert("이메일주소는 숫자와 알파벳만 사용해주십시오");
			joinform.member_email.focus();
			return false;
		};
	};
};


// 버튼 클릭시 호출하는 함수
function check(joinform) {
	// 아이디 체크 결과
	var IDCheckResult = IDCheck(joinform)
	if (IDCheckResult == false) {
		return false;
	}
	
	// 비밀번호 체크 결과
	var passwordCheckResult = passwordCheck(joinform)
	if (passwordCheckResult == false) {
		return false;
	}
	
	// 비밀번호 체크 결과
	var emailCheckResult = emailCheck(joinform)
	if (emailCheckResult == false) {
		return false;
	}
}