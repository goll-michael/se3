package server.schnittstelle;


/**
 * Generated from IDL struct "Kunde".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public abstract class KundeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(KundeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(server.schnittstelle.KundeHelper.id(),"Kunde",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("nummer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final server.schnittstelle.Kunde s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static server.schnittstelle.Kunde extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:server/schnittstelle/Kunde:1.0";
	}
	public static server.schnittstelle.Kunde read (final org.omg.CORBA.portable.InputStream in)
	{
		server.schnittstelle.Kunde result = new server.schnittstelle.Kunde();
		result.name=in.read_string();
		result.nummer=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final server.schnittstelle.Kunde s)
	{
		java.lang.String tmpResult0 = s.name;
out.write_string( tmpResult0 );
		out.write_long(s.nummer);
	}
}
