package server.application;

public class Kunde
{
	private String name;
	private int nummer;

	public Kunde()
	{
	}

	// getter und setter
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setNummer(int nummer)
	{
		this.nummer = nummer;
	}

	public int getNummer()
	{
		return nummer;
	}
}