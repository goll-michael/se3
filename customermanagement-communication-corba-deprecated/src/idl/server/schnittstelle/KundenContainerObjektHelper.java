package server.schnittstelle;


/**
 * Generated from IDL interface "KundenContainerObjekt".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public abstract class KundenContainerObjektHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(KundenContainerObjektHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:server/schnittstelle/KundenContainerObjekt:1.0", "KundenContainerObjekt");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final server.schnittstelle.KundenContainerObjekt s)
	{
			any.insert_Object(s);
	}
	public static server.schnittstelle.KundenContainerObjekt extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:server/schnittstelle/KundenContainerObjekt:1.0";
	}
	public static KundenContainerObjekt read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(server.schnittstelle._KundenContainerObjektStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final server.schnittstelle.KundenContainerObjekt s)
	{
		_out.write_Object(s);
	}
	public static server.schnittstelle.KundenContainerObjekt narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof server.schnittstelle.KundenContainerObjekt)
		{
			return (server.schnittstelle.KundenContainerObjekt)obj;
		}
		else if (obj._is_a("IDL:server/schnittstelle/KundenContainerObjekt:1.0"))
		{
			server.schnittstelle._KundenContainerObjektStub stub;
			stub = new server.schnittstelle._KundenContainerObjektStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static server.schnittstelle.KundenContainerObjekt unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof server.schnittstelle.KundenContainerObjekt)
		{
			return (server.schnittstelle.KundenContainerObjekt)obj;
		}
		else
		{
			server.schnittstelle._KundenContainerObjektStub stub;
			stub = new server.schnittstelle._KundenContainerObjektStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
