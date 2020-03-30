package com.txt.controller;

import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.txt.entity.Customer;
import com.txt.repository.CustomerRepository;

@Transactional
@Controller
public class CustomerController {

	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;

	@PostConstruct
	public void initData() {
		logger.info("initData");
		System.out.println("__________Reset and init data___________");
//		customerRepository.deleteAll();
//		for (int i = 0; i < 10; i++) {
//			customerRepository.save(new Customer("name" + i, "address" + i));
//		}
	}

	@RequestMapping(value = { "/customer-list" })
	public String listCustomer(Model model, 
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort
		) {

//		model.addAttribute("listCustomer", customerRepository.findAllOrderByNameDescNative());
//		model.addAttribute("listCustomer", customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
//		model.addAttribute("listCustomer", customerRepository.findAll(Sort.by("id").ascending()));

		Sort sortable = null;
		if("ASC".equals(sort)){
			sortable = Sort.by("id").ascending();
		} else if("DESC".equals(sort)){
			sortable = Sort.by("id").descending();
		}

		if(page < 0){
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, sortable);
		long countRecords = customerRepository.count();
		int totalPage = (int) countRecords / size + (countRecords % size > 0 ? 1 : 0);
		
		model.addAttribute("listCustomer", customerRepository.findCustomers(pageable));
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("total", totalPage);
		System.out.println(totalPage);
		
		Stream<Customer> streamCust = customerRepository.findByNameStream("thuongtx");
		if(streamCust != null){
			Customer customer2 = streamCust.findAny().get();
			System.out.println(customer2.getName() + " - " + customer2.getAddress());
		}
		
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
