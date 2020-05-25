package net.member.db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	// 로그인
	public int isMember(MemberBean member) {
		String sql = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		int result = -1;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("MEMBER_PW").equals(member.getMember_pw())) {
					result = 1; // 일치
				} else {
					result = 0; // 불일치
				}
			} else {
				result = -1; // 아이디 존재하지 않음
			}
		} catch (Exception ex) {
			System.out.println("isMember 에러 : " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}
		return result;
	}
	
	// 회원가입
	public boolean joinMember(MemberBean member, HttpServletResponse response) throws Exception {
		String sql = "INSERT INTO MEMBER VALUES (?, ? ,?)";
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pw());
			pstmt.setString(3, member.getMember_email());
			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}
		} catch(SQLIntegrityConstraintViolationException sqle) {
			String err = sqle.toString();
			if (err.contains("무결성")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디가 존재합니다');"
						+ "location.href='./singInUp.me';</script>");
				out.close();
			}
			System.out.println("joinMember 에러 : " + sqle);
		} catch (Exception ex) {
			System.out.println("joinMember 에러 : " + ex);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}
		return false;
	}
	
	// 회원목록 출력
	public List<MemberBean> getMemberList() {
		String sql = "SELECT * FROM MEMBER";
		List<MemberBean> memberlist = new ArrayList<>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_pw(rs.getString("member_pw"));
				mb.setMember_email(rs.getString("member_email"));

				memberlist.add(mb);
			}

			return memberlist;
		} catch (Exception ex) {
			System.out.println("getMemberList 에러 : " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}
		return null;
	}
	
	// 회원 삭제
	public MemberBean getDetailMember(String id) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			MemberBean mb = new MemberBean();
			mb.setMember_id(rs.getString("MEMBER_ID"));
			mb.setMember_pw(rs.getString("MEMBER_PW"));
			mb.setMember_email(rs.getString("MEMBER_EMAIL"));

			return mb;
		} catch (Exception ex) {
			System.out.println("getDetailMember 에러 : " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}
		return null;
	}
	
	public boolean deleteMember(String id) {
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		int res = 0;
		boolean result = false;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();
			
			if (res > 0) {
				result = true;
			}
		} catch (Exception ex) {
			System.out.println("deleteMember 에러 : " + ex);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}
		return result;
	}
}
