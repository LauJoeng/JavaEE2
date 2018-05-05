package com.yang.jpa.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Item {
	private Integer id;
	private String name;
	
	private Set<Category>categories = new HashSet<>();

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

	//使用@ManyToMany映射多对多关联关系
	//使用@JoinTable来映射中间表
	//1.name 指向中间表的名字
	//2. joinColumns映射当前类所在的表在映射表中的外键
	//2.1 name指定外键列的列名
	//2.2 referenceColumName 指定外键列关联当前表的那一列
	//3. inverseJoinColumn 映射关联的列所在中间表的列
	@JoinTable(name="item_category",
			joinColumns= {@JoinColumn(name="item_id",referencedColumnName="id")},
			inverseJoinColumns= {@JoinColumn(name="category_id",referencedColumnName="id")})
	@ManyToMany
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
