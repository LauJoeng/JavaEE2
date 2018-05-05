package com.yang.jpa.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Manager {
	private Integer id;
	private String name;
	private Department department;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//���ڲ�ά��������ϵ��û�������һ����ʹ��@OneToOne������ӳ�䣬��������mappedBy
	@OneToOne(mappedBy="manager")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
