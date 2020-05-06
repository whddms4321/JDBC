package kh.java.member.controller;



import java.util.ArrayList;

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
		MemberDao md = new MemberDao();
		String id =mv.deleteMember();
		int result = 0;
		result=md.deleteMember(id);
		if (result >0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("해당 정보가 없습니다.");
		}
	}

	private void modifyMember() {
		MemberDao md = new MemberDao();
		Member fixm =mv.modifyMember();
		int result =md.modifyMember(fixm);
		if (result >0) {
			System.out.println("수정 완료");
		}else {
			System.out.println("해당 정보가 없습니다.");
		}
	}

	private void printAllMember() {
		MemberDao md = new MemberDao();
		ArrayList<Member> mem = md.printAllmember();
		System.out.println(" =====================================  ");
		System.out.println("ID\t패스워드\t이름\t성별\t나이\t전화번호\t취미\t등록일자");
		for ( Member m : mem) {
			
			System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
					+m.getGender()+"\t"+m.getAge()+"\t"+m.getPhone()+"\t"+m.getHobby()+"\t"+m.getEnrollDate());
		}
	}

	private void idSearch() {
		String id =mv.idSearch();
		MemberDao md = new MemberDao();
		ArrayList<Member> mem = new ArrayList<Member>();
		mem = md.idSearch(id);
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
		
	}

	private void nameSearch() {
		String name =mv.nameSearch();
		MemberDao md = new MemberDao();
		Member m =md.nameSearch(name);
		if ( m.getMemberName() != null) {
			System.out.println(" =====================================  ");
			System.out.println("ID\t패스워드\t이름\t성별\t나이\t전화번호\t취미\t등록일자");
			System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
					+m.getGender()+"\t"+m.getAge()+"\t"+m.getPhone()+"\t"+m.getHobby()+"\t"+m.getEnrollDate());
			
		}else {
			System.out.println("찾는 정보가 없습니다. ");
		}
	}

	public void insertMember() {
		Member m = mv.insertMember();
		MemberDao md = new MemberDao();
		int result = md.insertMember(m);
		if (result>0) {
			//가입 성공한 경우
			mv.printMsg("회원가입 성공");
		}else {
			//가입 실패한 경우
			mv.printMsg("회원가입 실패");
		}
		
	}
}
