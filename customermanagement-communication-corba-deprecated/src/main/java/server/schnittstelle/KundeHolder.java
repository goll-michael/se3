package server.schnittstelle;

/**
 * Generated from IDL struct "Kunde".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public final class KundeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public server.schnittstelle.Kunde value;

	public KundeHolder ()
	{
	}
	public KundeHolder(final server.schnittstelle.Kunde initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return server.schnittstelle.KundeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = server.schnittstelle.KundeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		server.schnittstelle.KundeHelper.write(_out, value);
	}
}
