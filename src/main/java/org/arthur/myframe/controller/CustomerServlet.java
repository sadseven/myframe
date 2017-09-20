package org.arthur.myframe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.myframe.model.Customer;
import org.arthur.myframe.service.CustomerService;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet{
	
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		customerService = new CustomerService();
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
