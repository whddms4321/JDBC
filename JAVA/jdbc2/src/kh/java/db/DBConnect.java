package kh.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {

	public int modifyMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, phone=?,hobby=? where member_id=?";

		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "1234");
			//3. PreparedStatement 객체 생성 후 위치홀더에 값을 대입
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getHobby());
			pstmt.setString(4, m.getMemberId());
			//4. 쿼리문 요청후 결과 받기 > 쿼리문 실행 및 
			result = pstmt.executeUpdate();
			//5. 결과 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//6. 자원 반환 코드
				
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public ArrayList<Member> printAllMember2() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from member ";
		ArrayList<Member> members = new ArrayList<Member>();
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. Statement객체생성 - > 
			stmt = conn.createStatement();
			//4. 쿼리문 전송 및 결과 수신
			rset = stmt.executeQuery(query);
			//5. 결과 처리
			while( rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_Date"));
				members.add(m);
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
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return members;
	}
	
	public ArrayList<Member> printAllMember() {
		Connection conn =null;
		PreparedStatement pstmt= null;
		ResultSet rset= null;
		ArrayList<Member> members = new ArrayList<Member>();
		String query = " select * from member ";
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. PrepareStatement 객체 생성 
			pstmt = conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_Date"));
				members.add(m);
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
		return members;	
		
	}


	public Member idSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = new Member();
		int result = 0;
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
			if(rset.next()) {
				member = new Member();
				String id1 = rset.getString("member_id");
				member.setMemberId(id1);
				String pw = rset.getString("member_pw");
				member.setMemberPw(pw);
				String name = rset.getString("member_name");
				member.setMemberName(rset.getString("member_name"));
				member.setGender(rset.getString("gender"));
				member.setAge(rset.getInt("age"));
				member.setPhone(rset.getString("phone"));
				member.setHobby(rset.getString("hobby"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				
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
		return member;
		
		
	}


	public int deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		String query = "delete from member where member_Id =?";
		
		//1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. Statement객체생성 - >
			pstmt = conn.prepareStatement(query);
			//4. 쿼리문 전송 및 결과 수신
			pstmt.setString(1,id);
			//5. 결과 처리
			result = pstmt.executeUpdate();
			if( result !=0) {
				conn.commit();
			}else {
				conn.rollback();
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}


	public Member NameSearch(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_name=?";
		Member m = new Member();
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");	
			//3. Statement객체생성 - > 
			pstmt = conn.prepareStatement(query);
			//4. 쿼리문 전송 및 결과 수신
			pstmt.setNString(1, name);
			rset=pstmt.executeQuery();
			//5. 결과 처리
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setGender(rset.getString("Gender"));
				m.setAge(rset.getInt("Age"));
				m.setPhone(rset.getString("Phone"));
				m.setHobby(rset.getString("Hobby"));
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


	public int insertMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "insert into member values(?,?,?,?,?,?,?,sysdate)";
		int result= 0;
		//1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. Statement객체생성 - > 
			pstmt = conn.prepareStatement(query);
			
			//4. 쿼리문 전송 및 결과 수신
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getHobby());
			result = pstmt.executeUpdate();// statement에 excuteupdate를 붙이면 업데이트가 된곳의 행 번호를 내보낸다.
			//5. 결과 처리
			if( result >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//6. 자원 반환
		catch (SQLException e) {
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
