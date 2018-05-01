package com.yang.jpa.helloworld;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
		//1.����EntityManagerFactory
				String persistenceUnitName = "JPA1";
				
				Map<String, Object> properties = new HashMap<>();
				properties.put("hibernate.show_sql", false);
				EntityManagerFactory entityManagerFactory =
						Persistence.createEntityManagerFactory(persistenceUnitName);
//						Persistence.createEntityManagerFactory(persistenceUnitName, properties);
				
				//2.����EntityManager
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				//3.��������
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				
				//4.���г־û�����
				Customer customer = new Customer();
				customer.setAge(12);
				customer.setEmail("1909227160@qq.com");
				customer.setLastName("Rachel");
				customer.setBirth(new Date());
				customer.setCreatedTime(new Date());
				
				entityManager.persist(customer);
				
				//5.�ύ����
				transaction.commit();
				
				//6.�ر�EntityManager
				entityManager.close();
				
				//7.�ر�EntityManagerFactory
				entityManagerFactory.close();
	}
	
}
