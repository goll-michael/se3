package server.schnittstelle;


/**
 * Generated from IDL interface "KundenContainerObjekt".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public abstract class KundenContainerObjektPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, server.schnittstelle.KundenContainerObjektOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "einfuegeKunde", Integer.valueOf(0));
		m_opsHash.put ( "getKundeZuNr", Integer.valueOf(1));
		m_opsHash.put ( "endeAnwendung", Integer.valueOf(2));
		m_opsHash.put ( "getNaechsteKundenNr", Integer.valueOf(3));
		m_opsHash.put ( "erstelleKunde", Integer.valueOf(4));
	}
	private String[] ids = {"IDL:server/schnittstelle/KundenContainerObjekt:1.0"};
	public server.schnittstelle.KundenContainerObjekt _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		server.schnittstelle.KundenContainerObjekt __r = server.schnittstelle.KundenContainerObjektHelper.narrow(__o);
		return __r;
	}
	public server.schnittstelle.KundenContainerObjekt _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		server.schnittstelle.KundenContainerObjekt __r = server.schnittstelle.KundenContainerObjektHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // einfuegeKunde
			{
				server.schnittstelle.Kunde _arg0=server.schnittstelle.KundeHelper.read(_input);
				_out = handler.createReply();
				einfuegeKunde(_arg0);
				break;
			}
			case 1: // getKundeZuNr
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				server.schnittstelle.KundeHelper.write(_out,getKundeZuNr(_arg0));
				break;
			}
			case 2: // endeAnwendung
			{
				_out = handler.createReply();
				endeAnwendung();
				break;
			}
			case 3: // getNaechsteKundenNr
			{
				_out = handler.createReply();
				_out.write_long(getNaechsteKundenNr());
				break;
			}
			case 4: // erstelleKunde
			{
				_out = handler.createReply();
				server.schnittstelle.KundeHelper.write(_out,erstelleKunde());
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
