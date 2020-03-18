package kh.java.mgr;

import java.util.ArrayList;
import java.util.Scanner;

import kh.java.db.DBConnect;
import kh.java.db.Member;

public class MemberMgr {
	Scanner sc = new Scanner(System.in);
	ArrayList<Member> members =  new ArrayList<>();
	public void main() {
		while(true) {
			System.out.println("======회원 관리프로그램 ======");
			System.out.println("1. 회원 전체 정보 보기");//select 
			System.out.println("2. 회원 아이디 조회");// select 
			System.out.println("3. 회원 이름으로 조회");//select 
			System.out.println("4. 회원 가입");			//insert
			System.out.println("5. 회원 정보 변경");		//update
			System.out.println("6. 회원 삭제");			//delete 
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int sel = sc.nextInt();
			switch(sel) {
			case 1 : printAll();
				break;
			case 2 : idSearch();
				break;
			case 3 : nameSearch();
				break;
			case 0 : return;
			}
		}
	
	}
	
	private void printAll() {
		DBConnect db = new DBConnect();
		members = db.allPrint();
		System.out.println("======테이블===========");
		for(int i=0;i<members.size();i++) {
			System.out.println(members.get(i));
		}
		
	}

	private void nameSearch() {
		System.out.print("조회할 이름 입력 : ");
		String memberName = sc.next();
		DBConnect db = new DBConnect();
		Member member = db.nameSearch(memberName);
		if(member == null) {
			System.out.println("조회할수 없습니다.");
		}else {
			System.out.println("아이디 : "+member.getMemberId());
			System.out.println("비밀번호 : "+member.getMemberPw());
			System.out.println("이름 : "+member.getMemberName());
			System.out.println("성별 : "+member.getGender());
			System.out.println("나이 : "+member.getAge());
			System.out.println("전화번호 : "+member.getPhone());
			System.out.println("취미 : "+member.getHobby());
			System.out.println("가입일 : "+member.getEnrollDate());
		}
	}
	private void nameSearch2() {
		System.out.print("조회할 이름 입력 : ");
		String memberName = sc.next();
		DBConnect db = new DBConnect();
		members = db.nameSearch2(memberName);
		if(members.isEmpty()) {
			System.out.println("조회할수 없습니다.");
		}else {
			for(Member m : members) {
				System.out.println(m);
			}
		}
	}
	
	public void idSearch() {
		System.out.print("조회할 아이디 입력 : ");
		String memberId = sc.next();
		DBConnect db = new DBConnect();
		Member member
		=db.idSearch(memberId);
		if(member== null) {
			System.out.println("아이디를 조회할 수 없습니다.");
		}else {
			System.out.println("아이디 : "+member.getMemberId());
			System.out.println("비밀번호 : "+member.getMemberPw());
			System.out.println("이름 : "+member.getMemberName());
			System.out.println("성별 : "+member.getGender());
			System.out.println("나이 : "+member.getAge());
			System.out.println("전화번호 : "+member.getPhone());
			System.out.println("취미 : "+member.getHobby());
			System.out.println("가입일 : "+member.getEnrollDate());
		}
	}
	
	
	
}
	