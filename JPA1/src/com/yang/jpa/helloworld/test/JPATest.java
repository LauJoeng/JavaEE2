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
	
	//类似于hibernate中session的get方法
	//@Test
	void testFind() {
		init();
		System.out.println(entityManager==null);
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println("----------------------------");
		System.out.println(customer);
		destroy();
	}
	
	//类似于hibernate中的load方法,即在执行完getReference方法后获取的只是一个代理对象，要等待真正使用该对象时才从数据库中查询取出对象。如果在使用对象之前
	//关闭了entityManager就会出现懒加载异常
	//@Test
	public void testGetReference() {
		init();
		Customer customer = entityManager.getReference(Customer.class, 1);
		System.out.println(customer.getClass().getName());
		System.out.println("----------------------------");
		System.out.println(customer);
		destroy();
	}
	
	
	//类似于hibernate的save方法
	//与之不同之处在于：若对象有id，则不能执行insert操作，而会抛出异常
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
	
	//类似于hibernate的delete方法，把对象从数据库中移除
	//但注意，该方法只能移除持久化对象，而hibernate方法能移除游离对象
	@Test
	public void testRemove() {
		init();
		Customer customer = new Customer();
		customer.setId(2);
		entityManager.remove(customer);//无法删除
		
		customer = entityManager.find(Customer.class, 2);
//		entityManager.remove(customer);//能删除
		destroy();
	}
	
	/**
	 * 总的来说，类似于hibernate Session的savaOrUpdate方法
	 * 1.若传入的是一个临时的对象
	 * 会创建一个新的对象，把临时对象的属性复制到新的对象中，然后对新的对象执行持久化操作，所以新的对象中有id，但以前的临时对象没有id
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
	 * 若传入的是一个游离的对象，即传入的对象有id，
	 * 1.若在EntityManager缓存中没有该对象，在数据库中也没有对应的记录，JPA会创建一个新对象，然后把当前游离对象的属性复制到新创建的对象中，然后对新对象执行insert操作
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
	 * 若传入的是一个游离的对象，即传入的对象有id，
	 * 1.若在EntityManager缓存中没有 该对象，但在数据库有对应的记录，JPA会查询对应的记录，然后返回该记录对应的对象，然后把游离对象的属性复制到查询对象中。在对查询到的对象执行update 
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
	 * 若传入的是一个游离的对象，即传入的对象有id，
	 * 1.若在EntityManager缓存中  有  该对象，JPA把游离对象复制到查询到的对象中。再对查询到的对象执行update 
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
		entityManager.flush();//如果没有执行此方法会在提交事务时执行update，调用方法强制刷新执行sql语句。同hibernate的flush方法
		destroy();
	}
	
	/**
	 * 同hibernate中session的refresh方法
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
