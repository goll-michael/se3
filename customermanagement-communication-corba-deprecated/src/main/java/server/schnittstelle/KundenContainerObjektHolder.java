package server.schnittstelle;

/**
 * Generated from IDL interface "KundenContainerObjekt".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public final class KundenContainerObjektHolder	implements org.omg.CORBA.portable.Streamable{
	 public KundenContainerObjekt value;
	public KundenContainerObjektHolder()
	{
	}
	public KundenContainerObjektHolder (final KundenContainerObjekt initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KundenContainerObjektHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KundenContainerObjektHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KundenContainerObjektHelper.write (_out,value);
	}
}
