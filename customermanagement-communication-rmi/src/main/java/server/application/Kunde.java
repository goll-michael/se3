package server.application;

import java.io.Serializable;

import server.schnittstelle.KundeI;

public class Kunde implements KundeI, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1489790394897742241L;
	
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