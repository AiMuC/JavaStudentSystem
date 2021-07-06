package com.aimuc.pojo;

public class Person {
	private String sex;
	private String name;
	private int age;

	public void name() {
	}

	public Person(String sex, String name, int age) {
		this.age = age;
		this.sex = sex;
		this.name = name;
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
}
