package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//need to inject customer Dao
//	@Autowired
//	private CustomerDAO customerDAO;
	//need to inject customer service
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String customerList(Model theModel) {
		//get customer from DAO
		List<Customer> theCustomers=customerService.getCustomers();
		
		//add the customer to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind data
		Customer theCustomer= new Customer();
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		//get the customer to database
		//set customer as a model attribute to pre-populate the form
		//send over to our form
		Customer theCustomer=customerService.getCustomer(theId);
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theheModel) {
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	

}
