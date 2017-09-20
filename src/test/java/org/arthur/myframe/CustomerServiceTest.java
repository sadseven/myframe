package org.arthur.myframe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arthur.myframe.model.Customer;
import org.arthur.myframe.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CustomerServiceTest {
	/**
	 * CustomerService ��Ԫ����
	 */
	private final CustomerService customerService;
	
	public CustomerServiceTest() {
		customerService = new CustomerService();
	}
	
	@Before
	public void init() {
		//TODO ��ʼ�����ݿ�
		
	}
	
	@Test
	public void getCustomerListTest() {
		// TODO Auto-generated method stub
		List<Customer> customerList = customerService.getCustomerList();
		for (Customer customer : customerList) {
			System.out.println(customer.toString());
		}
		Assert.assertEquals(2, customerList.size());
	}
	
	@Test
	public void getCustomerTest() {
		// TODO Auto-generated method stub
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		System.out.println(customer.toString());
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void createCustomerTest() {
		// TODO Auto-generated method stub
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "13512345678");
		boolean result = customerService.createCustomer(fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void updateCustomerTest() {
		// TODO Auto-generated method stub
		long id = 3;
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("email", "arthur@test.com");
		fieldMap.put("name", "arthur");
		boolean result = customerService.updateCustomer(id, fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() {
		// TODO Auto-generated method stub
		long id = 3;
		boolean result = customerService.deleteCustomer(id);
		System.out.println(result);
		Assert.assertTrue(result);
	}
}
