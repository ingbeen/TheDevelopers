package net.member.db;

public class MemberBean {
	private String member_id;
	private String member_pw;
	private String member_email;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String mEMBER_ID) {
		member_id = mEMBER_ID;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String mEMBER_PW) {
		member_pw = mEMBER_PW;
	}
	
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String mEMBER_EMAIL) {
		member_email = mEMBER_EMAIL;
	}
	
}
