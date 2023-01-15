package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
//need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	//@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		System.out.println(currentSession);
		//create query 
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName",Customer.class);
		//execute query and get result list
		List<Customer> customers=theQuery.getResultList();
		//return list 	
		
		return customers;
	}
	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		//save to database	
		currentSession.saveOrUpdate(theCustomer);
		
	}
	@Override
	public Customer getCustomer(int theId) {
		//get current session
		Session currentSession= sessionFactory.getCurrentSession();
		//get the data
		Customer theCustomer=currentSession.get(Customer.class, theId);
		return theCustomer;
	}
	@Override
	public void deleteCustomer(int theId) {
		Session currentSession= sessionFactory.getCurrentSession();
		Customer theCustomer=currentSession.get(Customer.class,theId);
		currentSession.delete(theCustomer);
		
	}

}
