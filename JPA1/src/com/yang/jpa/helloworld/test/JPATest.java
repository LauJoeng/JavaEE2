package com.yang.jpa.helloworld.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import org.hibernate.ejb.QueryHints;
import org.hibernate.jpa.internal.EntityManagerMessageLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.yang.jpa.helloworld.Category;
import com.yang.jpa.helloworld.Customer;
import com.yang.jpa.helloworld.Department;
import com.yang.jpa.helloworld.Item;
import com.yang.jpa.helloworld.Manager;
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
	
	
	//˫��һ��һ������ϵ�������ȱ��治ά��������ϵ��һ������û�������һ�����������Լ���sql����ִ��
	@Test
	public void testOneToOne() {
		init();
		Manager manager = new Manager();
		manager.setName("m-aa");
		
		Department department = new Department();
		department.setDeptName("d-aa");
		
		//������ϵ
		manager.setDepartment(department);
		department.setManager(manager);
		
		//ִ�б���
		entityManager.persist(manager);
		entityManager.persist(department);
		destroy();
		
	}
	
	
	//Ĭ�������:1.��ά��������ϵ��һ�������ͨ���������ӻ�ȡ������ĵĶ���
	//������ͨ��@OneToOne��fetch�������޸ļ��ز���
	@Test
	public void testOneToOneFind() {
		init();
		Department department = entityManager.find(Department.class, 1);
		System.out.println(department.getDeptName());
		System.out.println(department.getManager().getClass().getName());
		destroy();
	}
	
	//ͬ���ģ���ȡ��ά��������ϵ��һ����Ҳ��ͨ���������ӻ�ȡ�����Ķ���
	//ͨ��fetch�޸ļ��ز���Ҳ��Ȼ����ˡ�
	@Test
	public void testOneToOneFind2() {
		init();
		Manager m = entityManager.find(Manager.class,1);
		System.out.println(m.getName());
		System.out.println(m.getDepartment().getDeptName());
		destroy();
	}
	
	
	@Test
	public void testManyToMany() {
		init();
		Item i1 = new Item();
		i1.setName("i1");
		
		Item i2 = new Item();
		i2.setName("i2");
		
		Category c1 = new Category();
		c1.setName("c1");
		
		Category c2= new Category();
		c2.setName("c2");
		
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		i2.getCategories().add(c2);
		i2.getCategories().add(c1);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(c1);
		entityManager.persist(c2);
		destroy();
	}
	
	
	//���ڹ����ļ��϶���Ĭ��ʹ�������ز���
	@Test
	public void testManyToManyFind() {
		init();
		Item item = entityManager.find(Item.class, 1);
		System.out.println(item.getName());
		System.out.println(item.getCategories().size());
		destroy();
	}
	
	
	@Test
	public void testSecondLevelCache() {
		init();
		Customer customer1= entityManager.find(Customer.class, 2);
		destroy();
		init();
		Customer customer2 = entityManager.find(Customer.class, 2);
		destroy();
	}
	
	
	
	@Test
	public void helloJPQL() {
		init();
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql);
		
		query.setParameter(1, 1);//ռλ��������1��ʼ
		List<Customer>customers = query.getResultList();
		System.out.println(customers.size());
		destroy();
	}
	
	
	//Ĭ������£���ֻ��ѯ�������ԣ��򷵻�Object[] ���ͽ��������Object[] ���͵�List
	//Ҳ������ʵ�����д�����Ӧ�Ĺ�������Ȼ����kpql��������ö�Ӧ�Ĺ���������ʵ����List
	@Test
	public void helloPartProperties() {
		init();
//		String jpql = "SELECT c.lastName,c.age FROM Customer c WHERE c.id > ?";
		String jpql = "SELECT new Customer(c.lastName,c.age) FROM Customer c WHERE c.id > ?";
		List result = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();
		System.out.println(result);
		destroy();
	}
	
	//createNamedQuery������ʵ����ǰʹ��@NamedQuery��ǵĲ�ѯ���
	@Test
	public void testNamedQuery() {
		init();
		
		Query query = entityManager.createNamedQuery("testNamedQuery").setParameter(1, 2);
		
		Customer customer = (Customer) query.getSingleResult();
		System.out.println(customer);
		
		destroy();
	}
	
	
	//createNativeQueryʹ���뱾��sql
	@Test
	public void testNativeQuery() {
		init();
		
		String sql = "select age from customer where id = ?";
		Query query = entityManager.createNativeQuery(sql).setParameter(1, 3);
		
		Object result = query.getSingleResult();
		System.out.println(result);
		
		destroy();
	}
	
	
	//ʹ��hibernate�Ĳ�ѯ���档
	@Test
	public void testQueryCache() {
		init();
		
		String jpql = "FROM Customer c WHERE c.age > ?";
		
	
		Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		query.setParameter(1, 1);//ռλ��������1��ʼ
		List<Customer>customers = query.getResultList();
		System.out.println(customers.size());
		
		query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		query.setParameter(1, 1);//ռλ��������1��ʼ
		customers = query.getResultList();
		System.out.println(customers.size());
		destroy();
	}
	
	
	@Test
	public void testQueryOrderBy() {
		init();
		
		String jpql = "FROM Customer c WHERE c.age > ? ORDER BY c.age DESC";
		Query query = entityManager.createQuery(jpql);
		
		query.setParameter(1, 1);//ռλ��������1��ʼ
		List<Customer>customers = query.getResultList();
		System.out.println(customers.size());
	
		
		destroy();
	}
	
	//��ѯOrder��������2����ЩCustomer
	@Test
	public void testQueryGroupBy() {
		init();
		
		String jpql = "select  o.customer FROM  Order o GROUP  BY o.customer HAVING count(o.id) > 2 ";
		Query query = entityManager.createQuery(jpql);
		
		List<Customer>customers = query.getResultList();
		System.out.println(customers.size());
	
		
		destroy();
	}
	
	
	//jpql�Ĺ�����ѯͬHQL�Ĺ�����ѯ
	@Test
	public void testLeftOuterJoinFetch() {
		init();
		
		String jpql = "FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?";
		Customer customer = (Customer) entityManager.createQuery(jpql).setParameter(1, 6).getSingleResult();
		System.out.println(customer.getLastName());
		
		System.out.println(customer.getOrders().size());
		
//		List<Object[]>result = entityManager.createQuery(jpql).setParameter(1, 6).getResultList();
//		System.out.println(result);
	
		
		destroy();
	}
	
	
	
	@Test
	public void testSubQuery() {
		init();
		
		//��ѯ����Customer��lastNameΪRoss��Order
		String jpql = "SELECT o FROM Order o WHERE o.customer IN (SELECT c FROM Customer c WHERE c.lastName = ?)";
	
		Query query = entityManager.createQuery(jpql).setParameter(1, "Ross");
		List<Order>orders = query.getResultList();
		System.out.println(orders.size());
		destroy();
	}
	
	
	//ʹ��jpql�ڽ���һЩ����.
	@Test
	public void testJPQLFuntion() {
		init();
		
		String jpql = "SELECT upper(c.email) FROM Customer c";//ȫ����д
		List<String>emails = entityManager.createQuery(jpql).getResultList();
		System.out.println(emails);
		destroy();
	}
	
	
	//����ʹ��jpql���update �� delete ����
	@Test
	public void testExecuteUpdate() {
		init();
		
		String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
		Query query = entityManager.createQuery(jpql).setParameter(1, "Joey").setParameter(2, 2);
		
		query.executeUpdate();
		destroy();
	}
	
	
	@After
	public void destroy() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
