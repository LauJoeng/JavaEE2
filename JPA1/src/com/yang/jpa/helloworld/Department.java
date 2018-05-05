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

	//使用@OneToOne来映射 1-1 关联关系
	//若需要在当前数据表中添加主键则需要使用@JoinColumn来进行映射，注意 1-1 关联关系，所以需要添加unique=true
	@JoinColumn(name="MGR_ID",unique=true)
	@OneToOne(fetch=FetchType.LAZY)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
}
