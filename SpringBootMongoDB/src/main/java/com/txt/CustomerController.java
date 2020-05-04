package com.txt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.txt.entity.Customer;
import com.txt.repository.CustomerRepository;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = { "/", "/customer-list" })
	public String listCustomer(Model model) {
		model.addAttribute("listCustomer", customerRepository.findAll());
		return "customer-list";
	}

	@RequestMapping("/customer-save")
	public String insertCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-save";
	}

	@RequestMapping("/customer-view/{id}")
	public String viewCustomer(@PathVariable String id, Model model) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			model.addAttribute("customer", customer.get());
		}
		return "customer-view";
	}

	@RequestMapping("/customer-update/{id}")
	public String updateCustomer(@PathVariable String id, Model model) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			model.addAttribute("customer", customer.get());
		}
		return "customer-update";
	}

	@RequestMapping("/saveCustomer")
	public String doSaveCustomer(@ModelAttribute("Customer") Customer customer, Model model) {
		customerRepository.save(customer);
		model.addAttribute("listCustomer", customerRepository.findAll());
		return "customer-list";
	}

	@RequestMapping("/updateCustomer")
	public String doUpdateCustomer(@ModelAttribute("Customer") Customer customer, Model model) {
		customerRepository.save(customer);
		model.addAttribute("listCustomer", customerRepository.findAll());
		return "customer-list";
	}

	@RequestMapping("/customerDelete/{id}")
	public String doDeleteCustomer(@PathVariable String id, Model model) {
		customerRepository.deleteById(id);
		model.addAttribute("listCustomer", customerRepository.findAll());
		return "customer-list";
	}
}
