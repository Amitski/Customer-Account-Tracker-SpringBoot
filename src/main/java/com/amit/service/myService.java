package com.amit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.entities.Account;
import com.amit.entities.Customer;
import com.amit.entities.Transaction;
import com.amit.repositories.AccountRepository;
import com.amit.repositories.CustomerRepository;
import com.amit.repositories.TransactionRepository;

@Service
public class myService {
	
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private static CustomerRepository custRepo;
	@Autowired
	private TransactionRepository tranRepo;

	public Customer addAcc(Customer cust) {
		return custRepo.save(cust);
	}
	public Account addAccount(Account acc) {
		return accRepo.save(acc);
	}
	
	public List<Customer> findAllAcc(){
		return custRepo.findAll();
	}
	
	public Optional<Customer> findCust(int customerId){
		return custRepo.findById(customerId);
	}
	
	public Optional<Account> findAcc(int accountNo){
		return accRepo.findById(accountNo);
	}
	
	public Account accToCust(int customerId, Account account) {
		if(custRepo.existsById(customerId)) {
   		 var customer= custRepo.findById(customerId);
   	      Customer cust= customer.get();
   	      List<Account> acc= cust.getAccounts();
   	      acc.add(account);
   	      cust.setAccounts(acc);
   	      custRepo.save(cust);}
   	      return account;
   }
	
	public String deleteCust(int customerId)
    {
    	if(custRepo.existsById(customerId))
    	{
    		custRepo.deleteById(customerId);
    		return "Deleted Successfully";
    	}
    	else return "customerId not found";
    }
	
	public String deleteAcc(int accountNo) {
    	if(accRepo.existsById(accountNo)) {
    	  accRepo.deleteById(accountNo);
    	  return "Deleted Successfully";
    	}
    	else return "AccountId not found";
    }
	
	public String sendMoney(int senderAccountNo, int receiverAccountNo,int amount) {
    	if(!accRepo.existsById(senderAccountNo)) return "Sender's account does not exist";
    	if(!accRepo.existsById(receiverAccountNo)) return "Receiver's account does not exist";
    	
    	Account senAcc= accRepo.getById(senderAccountNo);
    	Account recAcc= accRepo.getById(receiverAccountNo);
    	
    	double a= senAcc.getBalance();
    	double b= recAcc.getBalance();
    	
    	if(a<amount) return "Insufficient Balance";
    	
    	senAcc.setBalance(a-amount);
    	recAcc.setBalance(b+amount);
    	
    	accRepo.save(senAcc);
    	accRepo.save(recAcc);
    	
    	Transaction tran= new Transaction() ;
    	
    	tran.setSenderAccountNo(senderAccountNo);
    	tran.setReceiverAccountNo(receiverAccountNo);
    	tran.setAmount(amount);
    	
    	tranRepo.save(tran);
    	
    	return "Transaction successful. " + (a-amount) + " is the remaining balance in account No." + senderAccountNo;
    }
	
	public Account updateAcc(Account acc) {
    	accRepo.save(acc);
    	return acc;
    }
	
    public static Customer updateCust(Customer cust) {
    	
    	Customer customer= custRepo.getById(cust.getCustomerId());
    	if(customer!=null)
    	{customer.setCity(cust.getCity());
    	customer.setName(cust.getName());
    	customer.setPhoneNo(cust.getPhoneNo());
    	if(cust.getAccounts()!=null&&cust.getAccounts().size()>0) customer.setAccounts(cust.getAccounts());
    	
    	custRepo.save(customer);
    	cust.setAccounts(customer.getAccounts());}
    	return cust;
    	
    }
}
