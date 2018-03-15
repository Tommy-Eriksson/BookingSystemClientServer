package models;

import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = -2722707222547925803L;
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + " " + age + " År"; 
	}
	

	

}
