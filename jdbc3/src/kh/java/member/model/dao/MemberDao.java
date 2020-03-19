package kh.java.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.java.member.model.vo.Member;

public class MemberDao {
	
	
	public int insertMember(Member m) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(?,?,?,?,?,?,?,sysdate)";
		//1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. statement 생성
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//6. 자원반환
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member nameSearch(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		String query = "select * from member where member_name =?";
		Member m = new Member();
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234"); 
			//3. Statement객체생성 - >
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
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("Enroll_date"));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//6. 자원 반환
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return m;
		
		

	}

	public ArrayList<Member> idSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mem = new ArrayList<>();
		Member m = new Member();
		String query = "select * from member where member_id=?";
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. Statement객체생성 - > 
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
				m.setGender(rset.getString("Gender"));
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				mem.add(m);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//6. 자원 반환
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return mem;
		
	}

	public ArrayList<Member> printAllmember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		Member m = new Member();
		ArrayList<Member> mem = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while( rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("Gender"));
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				mem.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mem;
		
	}

	public int modifyMember(Member fixm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, member_name=?, hobby=? where member_id=?";
		//1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. statement 객체 생성 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fixm.getMemberPw());
			pstmt.setString(2, fixm.getMemberName());
			pstmt.setString(3, fixm.getHobby());
			pstmt.setString(4, fixm.getMemberId());
			//4. 쿼리문 전송 및 결과 수신
			result = pstmt.executeUpdate();
			if( result ==0 ) {
				conn.rollback();
			}else {
				conn.commit();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
		
	}

	public int deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete member where member_id=?";
		//1. 드라이버 생성
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. 커넥션 객체 생성
			conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. statement 객체 생성 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			//4. 쿼리문 보내고 결과 받기
			result = pstmt.executeUpdate();
			//5. 결과 처리
			if (result ==0) {
				conn.rollback();
			}else {
				conn.commit();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
