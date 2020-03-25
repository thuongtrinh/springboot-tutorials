package com.txt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.txt.entity.Customer;
import com.txt.repository.CustomerRepository;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = { "/customer-list" })
	public String listCustomer(Model model) {
		model.addAttribute("listCustomer", customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		return "customer-list";
	}

	@RequestMapping("/customer-view/{id}")
	public String viewCustomer(@PathVariable int id, Model model) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			model.addAttribute("customer", customer.get());
		}
		return "customer-view";
	}

	@RequestMapping("/customer-save")
	public String insertCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-save";
	}

	@RequestMapping("/saveCustomer")
	public String doSaveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		customerRepository.save(customer);
		//model.addAttribute("listCustomer", customerRepository.findAll());
		return "forward:/customer-list";
	}

	@RequestMapping("/customer-update/{id}")
	public String updateCustomer(@PathVariable int id, Model model) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			model.addAttribute("customer", customer.get());
		}
		return "customer-update";
	}

	@RequestMapping(value="/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		if(customer != null && customer.getId() != null){
			customerRepository.setCustomerInfoById(customer.getName(), customer.getAddress(), customer.getId());
		} else {
			customerRepository.save(customer);
		}

		//model.addAttribute("listCustomer", customerRepository.findAll());
		return "forward:/customer-list";
	}

	@RequestMapping("/customerDelete/{id}")
	public String doDeleteCustomer(@PathVariable int id, Model model) {
		customerRepository.deleteById(id);
		//model.addAttribute("listCustomer", customerRepository.findAll());
		return "forward:/customer-list";
	}
}
