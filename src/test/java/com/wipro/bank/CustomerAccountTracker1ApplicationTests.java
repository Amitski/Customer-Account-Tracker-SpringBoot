package com.wipro.bank;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.amit.entities.Account;
import com.amit.entities.Customer;
import com.amit.repositories.AccountRepository;
import com.amit.repositories.CustomerRepository;
import com.amit.service.myService;


@SpringBootTest
class BankApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@InjectMocks
	private myService service;
	
	@Mock
	private CustomerRepository customrepo;
	
	@Mock
	private AccountRepository accountrepo;
	
	@Test
	public void addAccountTest() {
		List<Account> ac= new ArrayList<Account>();
		ac.add(new Account(123456,"savings","icici",5000));
		ac.add(new Account(789456,"current","hdfc",10000));
		Customer c= new Customer(1, "amit","8888888888","vns",ac);
		
		service.addAcc(c);
		verify (customrepo).save(c);
	}
	
	@Test
	public void findCustomerTest() {
		List<Account> ac= new ArrayList<Account>();
		ac.add(new Account(123456,"savings","icici",5000));
		ac.add(new Account(789456,"current","hdfc",10000));
		Optional<Customer> c= Optional.ofNullable( new Customer(1, "amit","8888888888","vns",ac));
		when(customrepo.findById(101)).thenReturn(c);
		
		Optional<Customer> c1= customrepo.findById(101);
		Customer c2= service.findCust(101).get();
		
		assertEquals(c2.getCustomerId(),c1.get().getCustomerId());
		
	}
	
	@Test
	public void findAllAccountsTest() {
		List<Account> ac1= new ArrayList<Account>();
		ac1.add(new Account(123456,"savings","icici",5000));
		ac1.add(new Account(789456,"current","hdfc",10000));
		
		Customer cus1= new Customer(1, "amit","111111111","vns",ac1);
		
		List<Customer> ls1= new ArrayList<Customer>();
		ls1.add(cus1);
	
		customrepo.save(cus1);
		
		when(customrepo.findAll()).thenReturn(ls1);
		
		List<Customer> ls2= service.findAllAcc();
		
		assertEquals(ls1,ls2);
		
		
		
	}
	
	@Test
	public void deleteCustomerTest() {
		List<Account> ac= new ArrayList<Account>();
		ac.add(new Account(123456,"savings","icici",5000));
		ac.add(new Account(789456,"current","hdfc",10000));
		Optional<Customer> c= Optional.ofNullable( new Customer(1, "amit","8888888888","vns",ac));
		when(customrepo.findById(101)).thenReturn(c);
		
		service.deleteCust(101);
		customrepo.deleteById(101);
	}
	
	@Test
	public void deleteAccountTest() {
		Account a= new Account(123456,"savings","icici",5000);
		Optional<Account> ac= Optional.ofNullable(a);
		when(accountrepo.findById(123456)).thenReturn(ac);
		
		service.deleteAcc(123456);
		accountrepo.deleteById(123456);
	}
	
	@Test
	public void findAccountTest() {
		Account ac= new Account(123456,"savings","icici",10000);
		Optional<Account> a= Optional.ofNullable(ac);
		when(accountrepo.findById(123456)).thenReturn(a);
		
		Optional<Account> a1= accountrepo.findById(123456);
		Optional<Account> a2= service.findAcc(123456);
		
		assertEquals(a1.get().getAccountNo(),a2.get().getAccountNo());
	}
	
	@Test
	public void accounttoCustomer() {
		List<Account> ac= new ArrayList<Account>();
		Account a=new Account(123456,"savings","icici",5000);
		ac.add(a);
		 
		Customer cus= new Customer(1, "amit","999999999","vns",ac);
		
		Account account= service.accToCust(cus.getCustomerId(),a);
		
		assertEquals(a.getAccountNo(),account.getAccountNo());
		
	}
	
	
	@Test void updateCustomerTest() {
		List<Account> ac= new ArrayList<Account>();
		ac.add(new Account(123456,"savings","icici",5000));
		ac.add(new Account(789456,"current","hdfc",10000));
		Customer customer= new Customer(1, "amit","99999999","vns",ac);
		 
		 when(customrepo.save(customer)).thenReturn(customer);
		 
		 Customer cus= customrepo.save(customer);
		
		 Customer m= new Customer(1, "amiii","888888888","vns",ac);
		
		cus.setCity(m.getCity());
		cus.setName(m.getName());
		cus.setPhoneNo(m.getPhoneNo());
		
		Customer custom= service.updateCust(m);
		assertEquals(custom.getName(),customer.getName());
	
	}
	
	@Test void updateAccountTest() {
		Account a= new Account(123456,"savings","icici",5000);
		Optional<Account> ac= Optional.ofNullable(a);
		when(accountrepo.findById(123456)).thenReturn(ac);
		
		Account a2= ac.get();
		a2.setAccountType("current");
		
		Account a3= service.updateAcc(a2);
		assertEquals(a3.getAccountType(),a2.getAccountType());
	}

}
