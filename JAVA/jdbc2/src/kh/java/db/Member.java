package kh.java.db;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String gender;
	private String hobby;
	private String phone;
	private int age;
	private Date enrollDate;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Member(String memberId, String memberPw, String memberName, String gender, String hobby, String phone,
			int age, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.gender = gender;
		this.hobby = hobby;
		this.phone = phone;
		this.age = age;
		this.enrollDate = enrollDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Member(String memberId, String memberPw, String memberName, String gender, String hobby, String phone,
			Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.gender = gender;
		this.hobby = hobby;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return getMemberId()+"\t"+getMemberPw()+"\t"+getMemberName()+"\t"+getGender()+"\t"+getAge()+"\t"
				+getHobby()+"\t"+getPhone()+"\t"+getEnrollDate();
	}
	
}
