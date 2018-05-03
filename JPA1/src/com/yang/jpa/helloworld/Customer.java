package com.yang.jpa.helloworld;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@Table(name="JPA_CUSTOMERS")//���������ʾ������Ϊ����
@Entity
public class Customer {
	private Integer id;
	private String lastName;
	private String email;
	private int age;
	
	private Date createdTime;
	private Date birth;
	
	private Set<Order> orders = new HashSet<>();
	
	
	//ӳ�䵥�� 1-n�Ĺ�����ϵ
	//ʹ��@OneToMany��ӳ��һ�Զ������ϵ
	//ʹ��@JoinColumn��ӳ���������
	//�����޸�@OneToMany��cascade�������޸�Ĭ�ϵ�ɾ������
//	@JoinColumn(name="customer_id")
	@OneToMany(fetch=FetchType.EAGER,cascade= {CascadeType.REMOVE},mappedBy="customer")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	//@Temporalע�����ڶ�ʱ��ľ�ȷ
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
	
	//@GeneratedValue���ڱ�ע�������ɲ��ԣ�ͨ����һ�¼���ѡ��
	// - identity���������ݿ�id�������ķ�ʽ���������ֶΣ�Oracle��֧��
	// - auto��jpa�Զ�ѡ����ʵĲ��ԣ���Ĭ��ѡ��
	// - sequence��ͨ�����в�������������ͨ��@SequenceGeneratorע��ָ��������MySql��֧��
	// - table��ͨ���������������ܽ��ɱ�ģ�����в���������ʹ�øò��Կ���ʹӦ�ø��������ݿ�һֱ
	
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
	
	//JavaBean��set����Ĭ������˴�ע�⣬��ʾ������ӳ��Ϊ���е�һ��
	@Basic
	public void setAge(int age) {
		this.age = age;
	}
	
	//���߷���������Ҫӳ��Ϊ˳����һ��
	//��@Transientע���ʾ���Ը����ԣ����Ѹ�����ӳ��Ϊ����һ��
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
