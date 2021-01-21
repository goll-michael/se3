package server.schnittstelle;

import java.io.Serializable;

public class Kunde implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int nummer;

	public Kunde() {
	}
	
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
