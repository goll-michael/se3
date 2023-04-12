package client;

public class StartClientUI {

	public static void main(String args[]) {
		int nextNumber = CommunicationProxy.getReference().getNextNumber();
		System.out.println(String.format("next number is %s", nextNumber));
		
		CommunicationProxy.getReference().create("John Doe", nextNumber);
		System.out.println("customer created");
		
		String name = CommunicationProxy.getReference().getCustomerName(nextNumber);
		System.out.println(String.format("name for number %s is %s", nextNumber, name));
		
		CommunicationProxy.getReference().close();
		System.out.println(String.format("connection closed", nextNumber));
	}
}