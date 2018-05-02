package com.yang.jpa.helloworld.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.yang.jpa.helloworld.Customer;
import com.yang.jpa.helloworld.Order;

class JPATest {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA1");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		System.out.println("init");
	}
	
	//������hibernate��session��get����
	//@Test
	void testFind() {
		init();
		System.out.println(entityManager==null);
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println("----------------------------");
		System.out.println(customer);
		destroy();
	}
	
	//������hibernate�е�load����,����ִ����getReference�������ȡ��ֻ��һ���������Ҫ�ȴ�����ʹ�øö���ʱ�Ŵ����ݿ��в�ѯȡ�����������ʹ�ö���֮ǰ
	//�ر���entityManager�ͻ�����������쳣
	//@Test
	public void testGetReference() {
		init();
		Customer customer = entityManager.getReference(Customer.class, 1);
		System.out.println(customer.getClass().getName());
		System.out.println("----------------------------");
		System.out.println(customer);
		destroy();
	}
	
	
	//������hibernate��save����
	//��֮��֮ͬ�����ڣ���������id������ִ��insert�����������׳��쳣
	//@Test
	public void testPersistence() {
		init();
		Customer customer = new Customer();
		customer.setAge(14);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("1909227160@qq.com");
		customer.setLastName("yang");
		entityManager.persist(customer);
		destroy();
	}
	
	//������hibernate��delete�������Ѷ�������ݿ����Ƴ�
	//��ע�⣬�÷���ֻ���Ƴ��־û����󣬶�hibernate�������Ƴ��������
	@Test
	public void testRemove() {
		init();
		Customer customer = new Customer();
		customer.setId(2);
		entityManager.remove(customer);//�޷�ɾ��
		
		customer = entityManager.find(Customer.class, 2);
//		entityManager.remove(customer);//��ɾ��
		destroy();
	}
	
	/**
	 * �ܵ���˵��������hibernate Session��savaOrUpdate����
	 * 1.���������һ����ʱ�Ķ���
	 * �ᴴ��һ���µĶ��󣬰���ʱ��������Ը��Ƶ��µĶ����У�Ȼ����µĶ���ִ�г־û������������µĶ�������id������ǰ����ʱ����û��id
	 */
	@Test
	public void testMerge1() {
		init();
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("aa123.com");
		customer.setLastName("aa");
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println("customer id:  "+customer.getId());
		System.out.println("customer2 id:  "+customer2.getId());
		destroy();
	}
	
	
	/**
	 * ���������һ������Ķ��󣬼�����Ķ�����id��
	 * 1.����EntityManager������û�иö��������ݿ���Ҳû�ж�Ӧ�ļ�¼��JPA�ᴴ��һ���¶���Ȼ��ѵ�ǰ�����������Ը��Ƶ��´����Ķ����У�Ȼ����¶���ִ��insert����
	 */
	@Test
	public void testMerge2() {
		init();
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("ss123.com");
		customer.setLastName("ss");
		
		customer.setId(100);
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println("customer id:  "+customer.getId());
		System.out.println("customer2 id:  "+customer2.getId());
		destroy();
	}
	
	/**
	 * ���������һ������Ķ��󣬼�����Ķ�����id��
	 * 1.����EntityManager������û�� �ö��󣬵������ݿ��ж�Ӧ�ļ�¼��JPA���ѯ��Ӧ�ļ�¼��Ȼ�󷵻ظü�¼��Ӧ�Ķ���Ȼ��������������Ը��Ƶ���ѯ�����С��ڶԲ�ѯ���Ķ���ִ��update 
	 */
	@Test
	public void testMerge3() {
		init();
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("ee123.com");
		customer.setLastName("ee");
		
		customer.setId(4);
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println(customer == customer2);
		System.out.println("customer2 id:  "+customer2.getId());
		destroy();
	}
	
	
	/**
	 * ���������һ������Ķ��󣬼�����Ķ�����id��
	 * 1.����EntityManager������  ��  �ö���JPA����������Ƶ���ѯ���Ķ����С��ٶԲ�ѯ���Ķ���ִ��update 
	 */
	@Test
	public void testMerge4() {
		init();
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("1909227160@qq.com");
		customer.setLastName("yang");
		
		customer.setId(4);
		
		Customer customer2 = entityManager.find(Customer.class,4);
		entityManager.merge(customer);
		
		System.out.println(customer == customer2);
		System.out.println("customer2 id:  "+customer2.getId());
		destroy();
	}
	
	@Test
	public void testFlush() {
		init();
		Customer customer = entityManager.find(Customer.class, 1);
		
		customer.setLastName("aa");
		entityManager.flush();//���û��ִ�д˷��������ύ����ʱִ��update�����÷���ǿ��ˢ��ִ��sql��䡣ͬhibernate��flush����
		destroy();
	}
	
	/**
	 * ͬhibernate��session��refresh����
	 */
	@Test
	public void testRefresh() {
		init();
		Customer customer = entityManager.find(Customer.class, 1);
		
		customer = entityManager.find(Customer.class, 1);
		
		entityManager.refresh(customer);
		destroy();
	}
	
	@Test
	public void testManyToOne() {
		init();
		Order o = new Order();
		Customer customer = entityManager.find(Customer.class, 1);
		o.setOrderName("test");
		o.setCustomer(customer);
		
		
		entityManager.persist(o);
		destroy();
	}
	
	
	
	@After
	public void destroy() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
