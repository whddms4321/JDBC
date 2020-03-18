package kh.java.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {

	public Member idSearch(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = "select * from member where member_id='"+memberId+"'";
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 객체 생성
			
			conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			//3. Statement 객체 생성
			stmt = conn.createStatement();
			//4. 쿼리문 전송 및 결과 수신
			rset = stmt.executeQuery(query);
			//5. 결과 처리
			if(rset.next()) {
				member = new Member();
						
				String id = rset.getString("member_id");
				member.setMemberId(id);
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
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}

	public Member nameSearch(String memberName) {
		Connection conn= null;
		Statement stmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "select * from member where member_name = '"+memberName+"'";
		System.out.println(query);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				member = new Member();
				String id = rset.getString("member_id");
				member.setMemberId(id);
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
				rset.close();
				conn.close();
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return member;
	}
	public ArrayList<Member> nameSearch2(String memberName) {
		Connection conn= null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> members = new ArrayList<Member>();
		String query = "select * from member where member_name like '%"+memberName+"%'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
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
		}
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				conn.close();
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return members;
	}
	public ArrayList<Member> allPrint() {
		Connection conn= null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> members = new ArrayList<Member>();
		String query = "select * from member";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc","1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
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
		}
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				conn.close();
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return members;
	}
				
}
	
	
