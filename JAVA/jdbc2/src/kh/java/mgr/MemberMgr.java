package kh.java.mgr;

import java.util.ArrayList;
import java.util.Scanner;

import kh.java.db.DBConnect;
import kh.java.db.Member;

public class MemberMgr {
	Scanner sc = new Scanner(System.in);
	
	public void main() {
		while (true ) {
			System.out.println("1. 회원 전체 출력 ");
			System.out.println("2. 회원 아이디 검색");
			System.out.println("3. 회원 이름으로 검색");
			System.out.println("4. 회원 가입");
			System.out.println("5. 정보 수정");
			System.out.println("6. 회원 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print(" 선 택 >>>> ");
			int sel = sc.nextInt();
			switch(sel) {
			case 0 : 
				return;
			case 1 : 
				printAllMember();
				break;
			case 2 :
				idSearch();
				break;
			case 3 :
				nameSearch();
				break;
			case 4 :
				insertMember();
				break;
			case 5 : modifyMember();
				break;
			case 6 :
				deleteMember();
				break;
				
				
			}
		}
	}
	
	private void insertMember() {
		DBConnect db = new DBConnect();
		Member m = new Member();
		int result = 0;
		System.out.println("===회원 등록 프로그램====");
		System.out.print("아이디 입력 : ");
		m.setMemberId(sc.next());
		System.out.print("패스워드 입력 : ");
		m.setMemberPw(sc.next());
		System.out.print("이름 입력 : ");
		m.setMemberName(sc.next());
		System.out.print("성별 입력 : ");
		m.setGender(sc.next());
		System.out.print("나이 입력 : ");
		m.setAge(sc.nextInt());
		System.out.print("전화번호 입력 : ");
		m.setPhone(sc.next());
		System.out.print("취미 입력 : ");
		m.setHobby(sc.next());
		result=db.insertMember(m);
		if( result >0) {
			System.out.println("입력 완료");
		}else {
			System.out.println("입력 실패");
		}
	}

	private void nameSearch() {
		System.out.println("=== 회원 정보 삭제 프로그램 ===");
		System.out.print("찾을 이름 입력 : ");
		String name = sc.next();
		DBConnect db = new DBConnect();
		Member mem = new Member();
		mem =db.NameSearch(name);
		if( mem.getMemberName() !=null) {
			System.out.println(mem.getMemberId());
			System.out.println(mem.getMemberPw());
			System.out.println(mem.getMemberName());
			System.out.println(mem.getGender());
			System.out.println(mem.getAge());
			System.out.println(mem.getPhone());
			System.out.println(mem.getHobby());
			System.out.println(mem.getEnrollDate());
		}else {
			System.out.println("찾으시는 데이터가 없습니다.");
		}
	}
	private void idSearch() {
		System.out.println("===아이디로 검색 하는 프로그램");
		System.out.print("아이디 입력 : ");
		String id = sc.next();
		DBConnect db = new DBConnect();
		Member mem = new Member();
		mem=db.idSearch(id);
		if(  mem != null) {
			System.out.println("===회원 전체 정보 출력 프로그램 ===");
			System.out.println("ID\tPW\tNAME\tGENDER\tAGE\tPHONE\tHOBBY\tDATE");
			System.out.println(mem);
			
		}else {
			System.out.println("조회 할수 없습니다.");
		}
	}
	
	private void deleteMember() {
		System.out.println("=== 회원 정보 삭제 프로그램 ===");
		System.out.print("지울 회원 아이디 삭제 : ");
		String id = sc.next();
		DBConnect db = new DBConnect();
		int result = 0;
		result =db.deleteMember(id);
		if( result !=0) {
			 System.out.println("삭제 완료");
		 }else {
			 System.out.println("조회 할수 없습니다.");
		 }
		
	}
	
	private void printAllMember() {
		ArrayList<Member> mem = new ArrayList<Member>();
		DBConnect dc = new DBConnect();
		System.out.println("===회원 전체 정보 출력 프로그램 ===");
		System.out.println("ID\tPW\tNAME\tGENDER\tAGE\tPHONE\tHOBBY\tDATE");
		mem = dc.printAllMember();
		for (Member g : mem) {
			System.out.println(g);
		}
		
		
	}
	private void modifyMember() {
		System.out.println("\n----- 정보 수정 ---------");
		Member m = new Member();
		DBConnect db = new DBConnect();
		int result = 0;
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		m.setMemberId(memberId);
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		m.setMemberPw(memberPw);
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		m.setPhone(phone);
		System.out.print("취미 입력 ");
		String hobby = sc.next();
		m.setHobby(hobby);
		result=db.modifyMember(m);
		if( result >0) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정되지 않았습니다.");
		}
		
	}
	
}
