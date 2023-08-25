package com.amit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amit.entities.Account;
import com.amit.entities.Customer;
import com.amit.exceptions.NotFoundException;
import com.amit.repositories.AccountRepository;
import com.amit.repositories.CustomerRepository;
import com.amit.service.myService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@ComponentScan(basePackages="com.amit")
@RestController 
public class myController {
	
	
	@Autowired
	private myService service;
	
	
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private AccountRepository accRepo;
	
	
	@PostMapping("/addAccount")
    public ResponseEntity<Customer> addAccount(@RequestBody Customer cust) throws NotFoundException {
		Customer customer = service.addAcc(cust);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
	
    
	
	@GetMapping("/accounts")
    public ResponseEntity<List<Customer>> getAccounts(){
		List<Customer> accounts=  service.findAllAcc(); 
		return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    
    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<Optional<Customer>> customer(@PathVariable int customer_id) {
    	return new ResponseEntity<>(service.findCust(customer_id), HttpStatus.OK);
    }
    
    @GetMapping("/account/{account_no}")
    public ResponseEntity<Optional<Account>> account(@PathVariable int account_no) {
    	return new ResponseEntity<>(service.findAcc(account_no), HttpStatus.OK);
    }
    
    @PostMapping("/accountToCustomer/{customer_id}")
    public String accountToCustomer(@PathVariable int customer_id, @RequestBody Account account) throws NotFoundException {
    	if(!custRepo.existsById(customer_id)) throw new NotFoundException();
    	 service.accToCust(customer_id, account);
    	 return "Account added successfully";
    }
    
    @DeleteMapping("/deleteCustomer/{customer_id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customer_id)
    {
    	return new ResponseEntity<>(service.deleteCust(customer_id),HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteAccount/{account_no}")
    public ResponseEntity<String> deleteAccount(@PathVariable int account_no) {
    	return new ResponseEntity<>(service.deleteAcc(account_no),HttpStatus.OK);
    }
    
    
    @PostMapping("/sendMoney/{sender_account_no}/{receiver_account_no}/{amount}")
    public String sendMoney(@PathVariable int sender_account_no, @PathVariable int receiver_account_no, @PathVariable int amount) {
    	return service.sendMoney(sender_account_no, receiver_account_no, amount);
    }
    
    @PutMapping("/updateAccount")
    public String updateAccount(@RequestBody Account account) throws NotFoundException {
    	if(!accRepo.existsById(account.getAccountNo())) throw new NotFoundException();
    	service.updateAcc(account);
    	return "Updated successfully";
    }
    
    @PutMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customer cust) throws NotFoundException {
    	if(!custRepo.existsById(cust.getCustomerId())) throw new NotFoundException();
    	service.updateCust(cust);
    	return "Updated Successfully";
    }
 
}
