package org.arthur.myframe.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.arthur.myframe.helper.DatabaseHelper;
import org.arthur.myframe.model.Customer;
import org.arthur.myframe.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerService {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(CustomerService.class);
	
	/**
	 * ��ȡ�ͻ��б�
	 */
	
	public List<Customer> getCustomerList() {
		String sql = "select * from customer";
		List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql);
		return customerList;
	}
	/**
	 * ��ȡ�ͻ�
	 * @return
	 */
	public Customer getCustomer(long id) {
		String sql = "select * from customer where id = ?";
		Customer customerList = DatabaseHelper.queryEntity(Customer.class, sql, id);
		return customerList;
	}
	/**
	 * �����ͻ�
	 * @param fieldMap
	 * @return
	 */
	public boolean createCustomer(Map<String,Object> fieldMap) {
		boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
		return result;
	}
	/**
	 * ���¿ͻ�
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
		boolean result = DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
		return result;
	}
	/**
	 * ɾ���ͻ�
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id) {
		boolean result = DatabaseHelper.deleteEntity(Customer.class, id);
		return result;
	}
	
}
