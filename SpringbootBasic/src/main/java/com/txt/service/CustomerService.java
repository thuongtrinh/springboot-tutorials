package com.txt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.entity.Customer;
import com.txt.repository.CustomerRepository;
import com.txt.service.ServiceResult.Status;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public ServiceResult findAll() {
		ServiceResult result = new ServiceResult();
		List<Customer> list = customerRepository.findAll();
		result.setData(list);
		return result;
	}

	public ServiceResult findById(int id) {
		ServiceResult result = new ServiceResult();
		Customer customer = customerRepository.findById(id).orElse(null);
		result.setData(customer);
		return result;
	}

	public ServiceResult create(Customer customer) {
		ServiceResult result = new ServiceResult();
		result.setData(customerRepository.save(customer));
		return result;
	}

	public ServiceResult update(Customer customer) {
		ServiceResult result = new ServiceResult();

		if (!customerRepository.findById(customer.getId()).isPresent()) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			result.setData(customerRepository.save(customer));
		}
		return result;
	}

	public ServiceResult delete(int id) {
		ServiceResult result = new ServiceResult();

		Customer customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			customerRepository.delete(customer);
			result.setMessage("success");
		}
		return result;
	}

}
