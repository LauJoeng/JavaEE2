package com.yang.jpa.helloworld;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Department {
	private Integer id;
	private String deptName;
	
	private Manager manager;

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	//ʹ��@OneToOne��ӳ�� 1-1 ������ϵ
	//����Ҫ�ڵ�ǰ���ݱ��������������Ҫʹ��@JoinColumn������ӳ�䣬ע�� 1-1 ������ϵ��������Ҫ���unique=true
	@JoinColumn(name="MGR_ID",unique=true)
	@OneToOne(fetch=FetchType.LAZY)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
}
