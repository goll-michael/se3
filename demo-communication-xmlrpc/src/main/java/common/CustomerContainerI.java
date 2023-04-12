package common;

public interface CustomerContainerI
{
	public void create(String name, int nummer);
	
	public int getNextNumber();
	
	public String getCustomerName(int nummer);

	public void close();
}