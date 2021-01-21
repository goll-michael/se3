package server.schnittstelle;

/**
 * Generated from IDL struct "Kunde".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public final class Kunde
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Kunde(){}
	public java.lang.String name = "";
	public int nummer;
	public Kunde(java.lang.String name, int nummer)
	{
		this.name = name;
		this.nummer = nummer;
	}
}
