package application;

public class MainUI {

	public static void main(String[] args) throws Exception {
		execute(100, 200, 10);
		execute(200, 100, 13);
		execute(10, 100, 20); // Exception erwartet
	}

	private static void execute(double balance1, double balance2, double value) {
		
		TransactionRessourceAccount account1 = new TransactionRessourceAccount(balance1);
		TransactionRessourceAccount account2 = new TransactionRessourceAccount(balance2);
		
		jakarta.transaction.TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

		try {
			tm.begin();

			tm.getTransaction().enlistResource(account1);
			tm.getTransaction().enlistResource(account2);

			account1.debit(value);
			account2.credit(value);

			tm.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(account1.print());
		System.out.println(account2.print());
		System.out.println("###");
	}
}