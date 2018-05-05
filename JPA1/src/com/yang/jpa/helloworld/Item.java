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

	//ʹ��@ManyToManyӳ���Զ������ϵ
	//ʹ��@JoinTable��ӳ���м��
	//1.name ָ���м�������
	//2. joinColumnsӳ�䵱ǰ�����ڵı���ӳ����е����
	//2.1 nameָ������е�����
	//2.2 referenceColumName ָ������й�����ǰ�����һ��
	//3. inverseJoinColumn ӳ��������������м�����
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
