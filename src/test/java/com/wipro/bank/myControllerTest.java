package com.wipro.bank;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.mock.web.MockHttpServletResponse;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import com.amit.controller.myController;
import com.amit.entities.Account;
import com.amit.entities.Customer;
import com.amit.repositories.AccountRepository;
import com.amit.repositories.CustomerRepository;
import com.amit.service.myService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(myController.class)
class myControllerTest {
	

	@Autowired
    private MockMvc mockmvc;
	
	@MockBean
	private myService service;
	
	@MockBean
	private CustomerRepository custRepo;
	
	@MockBean
	private AccountRepository accRepo;
	
	
	@Test
	void contextLoads() {
			
	}
	@Test
	public void AllAccountsTest() throws Exception {
	    
	    Customer cust = new Customer(1, "Amits", "1234", "Varanasi", new ArrayList<Account>());
	    List<Customer> list = new ArrayList<Customer>();
	    list.add(cust);
	    
	    when(service.findAllAcc()).thenReturn(list);
	    
	    MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
	    MockHttpServletResponse response = result.getResponse();
	    
	    assertEquals(200, response.getStatus());
	    
	    }
	
	
	    
	 @Test   
	 public void FindCustomerIdTest() throws Exception{
		 Optional<Customer> cust = Optional.of(new Customer(1, "Amits", "1234", "Varanasi", new ArrayList<Account>()));
		 when(service.findCust(1)).thenReturn(cust);
		 
		 RequestBuilder request= MockMvcRequestBuilders.get("/customer/1");
		 MvcResult result = mockmvc.perform(request).andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(200, response.getStatus());
		 
		 
	 }
	 
	 
	 
	 @Test   
	 public void FindAccountByIdTest() throws Exception{
		 Optional<Account> acc = Optional.of(new Account(1, "hdfc", "Savings", 5000));
		 when(service.findAcc(1)).thenReturn(acc);
		 
		 RequestBuilder request= MockMvcRequestBuilders.get("/account/1");
		 MvcResult result = mockmvc.perform(request).andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(200, response.getStatus());
		 
		 
	 }
	 
	 
	 
	 @Test
	 public void AddAccountTest1() throws Exception 
	 {
		 Customer cust = new Customer(1,"Amits", "1234", "Varanasi", new ArrayList<Account>());
		 //when(service.addAcc(cust).thenReturn(cust));
		 
	   this.mockmvc.perform( MockMvcRequestBuilders
	       .post("/addAccount")
	       .contentType(MediaType.APPLICATION_JSON)
           .content(asJsonString(cust)))
           .andExpect(status().isCreated());
	   
	   
	 }
	  
	 
	 
	 @Test
	 public void deleteCustomer() throws Exception 
	 {
	   mockmvc.perform( MockMvcRequestBuilders.delete("/deleteCustomer/{customer_id}", 1) )
	         .andExpect(status().isOk());
	   
	   
	 }
	 
	 
	 
	 
	 @Test
	 public void deleteAccount() throws Exception 
	 {
	   mockmvc.perform( MockMvcRequestBuilders.delete("/deleteAccount/{account_no}", 1) )
	         .andExpect(status().isOk());
	   
	   
	 }
	 
	 
	 
	 
	 public static String asJsonString(final Object obj) {
	     try {
	         return new ObjectMapper().writeValueAsString(obj);
	     } catch (Exception e) {
	         throw new RuntimeException(e);
	         
	         
	     }
	     
	     
	 }
	 @Test
	 public void testPutExample() throws Exception {
		 Customer cust = new Customer(1,"Amits", "1234", "Varanasi", new ArrayList<Account>());
		 custRepo.save(cust);
		 cust.setCustomerId(1);
		 cust.setName ("John" );
		 cust.setPhoneNo("12345");
		 Mockito.when(myService.updateCust(ArgumentMatchers.any())).thenReturn(cust);
		 String json = asJsonString(cust) ;
		 mockmvc.perform(put ("/updateCustomer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		 .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		 .andExpect(jsonPath("$.customerId", Matchers.equalTo(1)))
		.andExpect(jsonPath("$.name", Matchers.equalTo("John")))
		.andExpect(jsonPath("$.phoneNo", Matchers.equalTo("12345")));}

}
