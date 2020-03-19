package kh.java.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.spec.PSource;

import common.JDBCTemplate;
import kh.java.member.model.vo.Member;

public class MemberDao {
	
	
	public int insertMember(Connection conn,Member m) {
		int result = 0;
//		Connection conn =JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		String query = "insert into member values(?,?,?,?,?,?,?,sysdate)";
		try {
		
			pstmt = conn.prepareStatement(query);
			//위치홀더 값 대체
			//4. 쿼리문전송 및 결과받기
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getHobby());
			//5. 결과처리
			result  = pstmt.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//6. 자원반환
			JDBCTemplate.close(pstmt);
//			JDBCTemplate.close(conn);
		}
		return result;
	}

	public Member nameSearch(Connection conn,String name) {
		String query = "select * from member where member_name =?";
//		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		Member m = new Member();
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			//4. 쿼리문 전송 및 결과 수신
			rset= pstmt.executeQuery();
			if(rset.next()) {
				//5. 결과 처리
				
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				if (rset.getString("gender").equals("M")) {
					m.setGender("남자");
				}else {
					m.setGender("여자");
				}
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("Enroll_date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//6. 자원 반환
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
//			JDBCTemplate.close(conn);
		}
		
		return m;
		
		

	}

	public ArrayList<Member> idSearch(Connection conn ,String id) {
//		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mem = new ArrayList<>();
		Member m = new Member();
		String query = "select * from member where member_id=?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			//4. 쿼리문 전송 및 결과 수신
			rset = pstmt.executeQuery();
			//5. 결과 처리
			while(rset.next()) {
				
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				if (rset.getString("gender").equals("M")) {
					m.setGender("남자");
				}else {
					m.setGender("여자");
				}
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				mem.add(m);
				
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				//6. 자원 반환
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
//				JDBCTemplate.close(conn);
			
		}
		
		return mem;
		
	}

	public ArrayList<Member> printAllmember(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		Member m = new Member();
		ArrayList<Member> mem = new ArrayList<>();
		try {
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while( rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				if (rset.getString("gender").equals("M")) {
					m.setGender("남자");
				}else {
					m.setGender("여자");
				}
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				mem.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
//				JDBCTemplate.close(conn);
		}
		
		return mem;
		
	}

	public int modifyMember(Connection conn, Member fixm) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, member_name=?, hobby=? where member_id=?";
		//1. 드라이버 등록
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fixm.getMemberPw());
			pstmt.setString(2, fixm.getMemberName());
			pstmt.setString(3, fixm.getHobby());
			pstmt.setString(4, fixm.getMemberId());
			//4. 쿼리문 전송 및 결과 수신
			result = pstmt.executeUpdate();
//			if( result ==0 ) {
//				JDBCTemplate.rollback(conn);
//			}else {
//				JDBCTemplate.commit(conn);
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
//			JDBCTemplate.close(conn);
			
		}
		
		return result;
		
	}

	public int deleteMember(Connection conn,String memberId) {
		//1. 드라이브
		String query = "delete from member where member_id=?";
//		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
//			if(result>0) {
//				JDBCTemplate.commit(conn);
//			}else {
//				JDBCTemplate.rollback(conn);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
//			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int insertDelMember(Connection conn,String memberId) {
//		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into del_member values(del_seq.nextval,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
//			if(result >0 ) {
//				JDBCTemplate.commit(conn);
//			}else {
//				JDBCTemplate.rollback(conn);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
//			JDBCTemplate.close(conn);
		}
		
		return result;
	}

}
