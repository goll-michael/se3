package server.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import common.CustomerContainerI;

public class CustomerContainer implements CustomerContainerI
{
	private static CustomerContainer customerContainer = null;

	private List<Customer> customerList = new ArrayList<>();
  
	public static CustomerContainer getReference()
	{
		if (customerContainer == null) {
			customerContainer = new CustomerContainer();
		}
		return customerContainer;
	}
   
	public void create(String name, int number)
	{
		System.out.println(String.format("CustomerContainer: create customer %s with number %s", name, number));
		Customer customer = getCustomer(number);
		if(customer == null) {
			customer = new Customer();
			customer.setName(name);
			customer.setNumber(number);
			customerList.add(customer);
		} else {
			customer.setName(name);
		}
	}
	
	public int getNextNumber()
	{
		System.out.println("CustomerContainer: get next number");
		OptionalInt optional = customerList.stream().mapToInt(x -> x.getNumber()).max();
		if(optional.isPresent()) {
			return optional.getAsInt();
		}
		return 1;
	}

	public String getCustomerName(int number)
	{
		System.out.println(String.format("CustomerContainer: get customer for number %s", number));
		Customer customer = getCustomer(number);
		return customer != null ? customer.getName() : ""; 
	}
  
	public void close()
	{
		System.out.println("CustomerContainer: close server");
	}
	
	private Customer getCustomer(int number) {
		Optional<Customer> customer = customerList.stream().filter(x -> x.getNumber() == number).findFirst();
		if(customer.isPresent()) {
			return customer.get();
		}
		return null;
	}
}