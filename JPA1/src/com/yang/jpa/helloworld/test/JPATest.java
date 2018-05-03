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




/**     �����д�ӣ����ݿ��б�������ʹ�ùؼ��֣����磺order��order��order��������û�뵽order��Ȼ���ǹؼ��֡������key��foreignʲô���Ҷ�����                                **/





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
	@Test
	public void testPersistence() {
		init();
		Customer customer = new Customer();
		customer.setAge(14);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("Monica@qq.com");
		customer.setLastName("Monica");
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
	
	/**
	 * ������һʱ��Ӧ���ȱ���һ��һ�ˣ��ٱ�����һ�ˣ�����������������sql���
	 */
	/*@Test
	public void testManyToOne() {
		init();
		Customer customer = new Customer();
		customer.setAge(13);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("1909227160@163.com");
		customer.setLastName("Joey");
		
		Order order1 = new Order();
		order1.setOrderName("o-J-1");
		
		Order order2 = new Order();
		order2.setOrderName("o-J-2");
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//ִ�б������
		entityManager.persist(customer);
		entityManager.persist(order1);
		entityManager.persist(order2);
		destroy();
	}*/
	
	/**
	 * Ĭ������£�ʹ���������ӵķ�ʽ����ȡ �� ��һ�˶����������� һ ��һ�˵Ķ���
	 * //����ʹ��@ManyToOne��fetch�������޸�Ĭ�ϵĹ������Եļ��ز���
	 */
//	@Test
//	public void testManyToOneFind() {
//		init();
//		Order order = entityManager.find(Order.class, 2);
//		
//		System.out.println(order.getOrderName());
//		System.out.println(order.getCustomer().getLastName());
//		destroy();
//	}
	
	//����ֱ��ɾ�� һ ��һ�ˣ���Ϊ�����Լ����
	@Test
	public void testManyToOneRemove() {
		init();
//		Order order = entityManager.find(Order.class, 2);
//		entityManager.remove(2);;
		Customer customer = entityManager.find(Customer.class, 2);
		entityManager.remove(customer);
		destroy();
	}
	
	/*@Test
	public void testManyToOneUpdate() {
		init(); 
		Order o = entityManager.find(Order.class, 2);
		o.getCustomer().setLastName("Chandler");
		destroy();
	}*/
	
	
	//����˫�� 1-n �Ĺ�����ϵ��ִ�б���ʱ
	//���ȱ��� n ��һ�ˣ��ٱ��� 1 ��һ�ˣ����� 2*n ��update���
	//����������� n ��
	//�ڽ���˫��1-n������ϵʹ������ʹ��n��һ����ά��������ϵ����1��һ����ά��������ϵ����������Ч����sql���
	//ע�⣺����1��һ�˵�@OneToMany��ʹ��mappedBy���ԣ���@OneToMany�˾Ͳ���ʹ��@JoinColumn������
	
	/**
	 * ���� 1-n ������ϵִ�б���ʱ��һ������update���
	 *��Ϊ n ��һ���ڲ���ʱ����ͬʱ���������
	 */
	@Test
	public void testOneToManyPersist() {
		init();
		
		init();
		Customer customer = new Customer();
		customer.setAge(13);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("Ross@gmail.com");
		customer.setLastName("Ross");
		
		Order order1 = new Order();
		order1.setOrderName("o-P-1");
		
		Order order2 = new Order();
		order2.setOrderName("o-P-2");
		
		//����������ϵ
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//ִ�б������
		entityManager.persist(customer);
		entityManager.persist(order1);
		entityManager.persist(order2);
		
		destroy();
	}
	
	
	//Ĭ�϶Թ����Ķ��һ��ʹ�������ز���
	@Test
	public void testOneToManyFind() {
		init();
		
		Customer c = entityManager.find(Customer.class, 5);
		System.out.println(c.getLastName());
		
		System.out.println(c.getOrders().size());
		
		destroy();
	}
	
	//Ĭ������£���ɾ��1��һ�ˣ����ѹ�����n��һ�������Ϊ��,Ȼ�����ɾ��
	//�����޸�@OneToMany��cascade�������޸�Ĭ�ϵ�ɾ������
	@Test
	public void testOneToManyRemove() {
		init();
		
		Customer c = entityManager.find(Customer.class, 5);
		entityManager.remove(c);
		destroy();
		
	}
	
	@Test
	public void testOneToManyUpdate() {
		init();
		
		Customer c = entityManager.find(Customer.class, 2);
		c.getOrders().iterator().next().setOrderName("O-XX-2");
		destroy();
		
	}
	
	
	
	
	@After
	public void destroy() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
