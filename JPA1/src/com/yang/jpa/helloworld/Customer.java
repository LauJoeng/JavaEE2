package com.yang.jpa.helloworld;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@Table(name="JPA_CUSTOMERS")//不加这个表示类名即为表名
@NamedQuery(name="testNamedQuery",query="SELECT c From Customer c WHERE c.id = ?")
@Cacheable(true)
@Entity
public class Customer {
	private Integer id;
	private String lastName;
	private String email;
	private int age;
	
	private Date createdTime;
	private Date birth;
	
	private Set<Order> orders = new HashSet<>();
	
	public Customer(String lastName, int age) {
		this.lastName = lastName;
		this.age = age;
	}
	
	
	public Customer() {
		super();
	}

	public Customer(Integer id, String lastName, String email, int age, Date createdTime, Date birth,
			Set<Order> orders) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.createdTime = createdTime;
		this.birth = birth;
		this.orders = orders;
	}



	//映射单向 1-n的关联关系
	//使用@OneToMany来映射一对多关联关系
	//使用@JoinColumn来映射外键名称
	//可以修改@OneToMany的cascade属性来修改默认的删除策略
//	@JoinColumn(name="customer_id")
	@OneToMany(fetch=FetchType.LAZY,cascade= {CascadeType.REMOVE},mappedBy="customer")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	//@Temporal注解用于对时间的精确
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	//@GeneratedValue用于标注主键生成策略，通常有一下几个选项
	// - identity：采用数据库id自增长的方式来自增长字段，Oracle不支持
	// - auto：jpa自动选择合适的策略，是默认选项
	// - sequence：通过序列产生序列主键，通过@SequenceGenerator注解指定列名，MySql不支持
	// - table：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库一直
	
//	@TableGenerator(
//			name="id_generator",
//			table="jpa_id_generators",
//			pkColumnName="PK_NAME",
//			pkColumnValue="customer_id",
//			valueColumnName="pk_value",
//			allocationSize=100)
//	@GeneratedValue(strategy=GenerationType.TABLE,generator="id_generator")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="LAST_NAME",length=50,nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAge() {
		return age;
	}
	
	//JavaBean中set方法默认添加了此注解，表示该属性映射为表中的一列
	@Basic
	public void setAge(int age) {
		this.age = age;
	}
	
	//工具方法，不需要映射为顺序表的一列
	//加@Transient注解表示忽略该属性，不把该属性映射为表中一列
	@Transient
	public String getInfo() {
		return "lastName :" +lastName+",Email: "+email;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", createdTime="
				+ createdTime + ", birth=" + birth + "]";
	}
	
	
	
}
