package kh.java.member.controller;



import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kh.java.member.model.dao.MemberDao;
import kh.java.member.model.vo.Member;
import kh.java.member.view.MemberView;

public class MemberController {
	MemberView mv = new MemberView();
	
	public void main() {
		while(true) {
			int sel =mv.mainMenu();
			switch(sel) {
			case 1:printAllMember();
				break;
			case 2 :idSearch();
				break;
			case 3 :nameSearch();
				break;
			case 4 :insertMember();
				break;
			case 5 : modifyMember();
				break;
			case 6 :deleteMember();
				break;
			case 0 :
				return;
			}
		}
	}
	
	private void deleteMember() {
		String memberId = mv.getId();
		MemberDao md = new MemberDao();
		//트랜잭션 작업을 위해서 Connection을 생성
		Connection conn = JDBCTemplate.getConnection();
		//멤버테이블에서 해당하는 row를 삭제
		int result = md.deleteMember(conn,memberId);
		int result2 = md.insertDelMember(conn,memberId);
		if (result>0 && result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
//		if( result > 0) {
//			//Del_member테이블에 추가
//			int result2 = md.insertDelMember(conn,memberId);
//			if( result2>0) {
//				JDBCTemplate.commit(conn);
//			}else {
//				JDBCTemplate.rollback(conn);
//			}
//			
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
	}

	
	private void modifyMember() {
		MemberDao md = new MemberDao();
		Member fixm =mv.modifyMember();
		Connection conn = JDBCTemplate.getConnection();
		int result =md.modifyMember(conn,fixm);
		if (result >0) {
			JDBCTemplate.commit(conn);
			System.out.println("수정 완료");
		}else {
			JDBCTemplate.commit(conn);
			System.out.println("해당 정보가 없습니다.");
		}
		JDBCTemplate.close(conn);
	}

	private void printAllMember() {
		MemberDao md = new MemberDao();
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> mem = md.printAllmember(conn);
		System.out.println(" =====================================  ");
		System.out.println("ID\t패스워드\t이름\t성별\t나이\t전화번호\t취미\t등록일자");
		for ( Member m : mem) {
			
			System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
					+m.getGender()+"\t"+m.getAge()+"\t"+m.getPhone()+"\t"+m.getHobby()+"\t"+m.getEnrollDate());
		}
		JDBCTemplate.close(conn);
	}

	private void idSearch() {
		String id =mv.idSearch();
		MemberDao md = new MemberDao();
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> mem = new ArrayList<Member>();
		mem = md.idSearch(conn,id);
		if( mem.isEmpty()) {
			System.out.println("해당 정보가 존재하지 않습니다.");
		}else {
			System.out.println(" =====================================  ");
			System.out.println("ID\t패스워드\t이름\t성별\t나이\t전화번호\t취미\t등록일자");
			for ( Member m : mem) {
				
				System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
						+m.getGender()+"\t"+m.getAge()+"\t"+m.getPhone()+"\t"+m.getHobby()+"\t"+m.getEnrollDate());
			}
			
		}
		JDBCTemplate.close(conn);
		
	}

	private void nameSearch() {
		Connection conn = JDBCTemplate.getConnection();
		String name =mv.nameSearch();
		MemberDao md = new MemberDao();
		Member m =md.nameSearch(conn,name);
		if ( m.getMemberName() != null) {
			System.out.println(" =====================================  ");
			System.out.println("ID\t패스워드\t이름\t성별\t나이\t전화번호\t취미\t등록일자");
			System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
					+m.getGender()+"\t"+m.getAge()+"\t"+m.getPhone()+"\t"+m.getHobby()+"\t"+m.getEnrollDate());
			
		}else {
			System.out.println("찾는 정보가 없습니다. ");
		}
		JDBCTemplate.close(conn);
	}

	public void insertMember() {
		Connection conn = JDBCTemplate.getConnection();
		Member m = mv.insertMember();
		MemberDao md = new MemberDao();
		int result = md.insertMember(conn,m);
		if (result>0) {
			//가입 성공한 경우
			mv.printMsg("회원가입 성공");
		}else {
			//가입 실패한 경우
			mv.printMsg("회원가입 실패");
		}
		JDBCTemplate.close(conn);
	}
}
