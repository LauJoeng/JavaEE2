package com.yang.jpa.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="orders")
@Entity
public class Order {
	private Integer id;
	private String orderName;
	
	private Customer customer;

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="order_name")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	//ӳ�䵥��n-1������ϵ
	//ʹ��@ManyToOne��ӳ����һ�Ĺ�����ϵ
	//ʹ��@JoinColumn��ӳ�����
	//����ʹ��@ManyToOne��fetch�������޸�Ĭ�ϵĹ������Եļ��ز���
	@JoinColumn(name="customer_id")
	@ManyToOne(fetch=FetchType.LAZY)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	 
}
