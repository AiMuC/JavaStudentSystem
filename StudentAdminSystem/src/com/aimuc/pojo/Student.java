package com.aimuc.pojo;

public class Student implements Comparable {
	// public class Student extends Person implements Comparable {
	private String stuid;
	private String SID;
	private String password;
	private String sex;
	private String name;
	private int age;

	public void Student() {
	}

	public Student(String stuid, String password) {
		this.password = password;
		this.stuid = stuid;
	}

	public Student(String stuid, String password, String SID) {
		this.password = password;
		this.stuid = stuid;
		this.SID = SID;
	}

	public Student(String stuid, String name, String SID, String sex, String age) {
		this.stuid = stuid;
		this.name = name;
		this.sex = sex;
		this.SID = SID;
		int stuage = Integer.parseInt(age);
		this.age = stuage;
	}

	public Student(String stuid, String SID, String password, String sex, String name, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.stuid = stuid;
		this.SID = SID;
		this.password = password;
	}

	public String getStuid() {
		return stuid;
	}

	public String getPassword() {
		return password;
	}

	public String getSID() {
		return SID;
	}

	public String Getname() {
		return name;
	}

	public String Getsex() {
		return sex;
	}

	public int Getage() {
		return age;
	}

	public String toString() {
		return "学号:" + stuid + "\t姓名:" + Getname() + "\t性别:" + Getsex() + "\t身份证号:" + SID + "\t年龄:" + Getage();
	}

	public int hashCode() {
		return stuid.hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}

		Student student = (Student) obj;
		boolean b = this.stuid.equals(student.stuid);
		return b;
	}

	public int compareTo(Object obj) {
		Student student = (Student) obj;
		if (Integer.parseInt(this.stuid) - Integer.parseInt(student.stuid) > 0) {
			return 1;
		}
		if (Integer.parseInt(this.stuid) - Integer.parseInt(student.stuid) == 0) {
			return this.stuid.compareTo(student.stuid);
		}
		return -1;
	}
}
