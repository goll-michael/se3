package application;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kunde
{
	private String name;
	
	@Id
	private int nummer;
	
	public String getName()
	{
		return name;
	}
	
	public int getNummer()
	{
		return nummer;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setNummer(int nummer)
	{
		this.nummer = nummer;
	}
}
