package com.comulynx.wallet.rest.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comulynx.wallet.rest.api.model.Account;
import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.repository.AccountRepository;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;

	public List<CustomerResponse> getCustomerDetails() {
		Account account;
		CustomerResponse customerResponse;
		List<CustomerResponse> response= new ArrayList<>();
		List<Customer> customers=customerRepository.findAll();
		for(Customer customer:customers) {
			account=accountRepository.findAccountByCustomerId(customer.getCustomerId()).get();
			customerResponse=new CustomerResponse(customer.getPin(), customer.getFirstName(),
					customer.getLastName(),customer.getEmail(), customer.getCustomerId(), 
					account.getAccountNo(), account.getBalance());
			response.add(customerResponse);
		}
		return response;
		
	}

}
