package kh.java.member.view;


import java.util.Scanner;

import kh.java.member.model.vo.Member;

public class MemberView {
	Scanner sc= new Scanner(System.in);
	public int mainMenu() {
		System.out.println("========회원관리 프로그램=========");
		System.out.println("1. 회원 전체 출력 ");
		System.out.println("2. 회원 아이디 검색");
		System.out.println("3. 회원 이름으로 검색");
		System.out.println("4. 회원 가입");
		System.out.println("5. 정보 수정");
		System.out.println("6. 회원 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print(" 선 택 >>>> ");
		int sel = sc.nextInt();
		return sel;
	}
	public Member insertMember() {
		System.out.println("\n------ 회원 가입 ---------");
		Member m = new Member();
		System.out.print("아이디 입력 : ");
		m.setMemberId(sc.next());
		System.out.print("비밀번호 입력 : ");
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
		return m;
	}
	public void printMsg(String msg) {
		System.out.println(msg);
	}
	
	public String nameSearch() {
		System.out.println("=== 회원 찾기 프로그램 ===");
		System.out.print("찾으실 이름 입력 : ");
		String name = sc.next();
		return name;
	}
	public String idSearch() {
		System.out.println("==== 회원 관리 프로그램 ====");
		System.out.print("찾으실 회원 id 입력  : ");
		String id = sc.next();
		return id;
	}
	public Member modifyMember() {
		Member m = new Member();
		System.out.println("=====회원 정보 수정 프로그램====");
		System.out.print("id를 입력해 주세요 : ");
		m.setMemberId(sc.next());
		System.out.print("수정하실 이름을 입력해 주세요 : ");
		m.setMemberName(sc.next());
		System.out.print("바꾸실 비밀번호 입력 : ");
		m.setMemberPw(sc.next());
		System.out.print("바꾸실 취미를 입력 : ");
		m.setHobby(sc.next());
		return m;
	}
	public String deleteMember() {
		System.out.println("===회원 삭제 프로그램 =====");
		System.out.print("삭제하실 id 입력 : ");
		String id = sc.next();
		return id;
	}
	

}
