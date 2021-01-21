package server.schnittstelle;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "KundenContainerObjekt".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public class KundenContainerObjektPOATie
	extends KundenContainerObjektPOA
{
	private KundenContainerObjektOperations _delegate;

	private POA _poa;
	public KundenContainerObjektPOATie(KundenContainerObjektOperations delegate)
	{
		_delegate = delegate;
	}
	public KundenContainerObjektPOATie(KundenContainerObjektOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public KundenContainerObjektOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(KundenContainerObjektOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void einfuegeKunde(server.schnittstelle.Kunde einKunde)
	{
_delegate.einfuegeKunde(einKunde);
	}

	public server.schnittstelle.Kunde getKundeZuNr(int nummer)
	{
		return _delegate.getKundeZuNr(nummer);
	}

	public void endeAnwendung()
	{
_delegate.endeAnwendung();
	}

	public int getNaechsteKundenNr()
	{
		return _delegate.getNaechsteKundenNr();
	}

	public server.schnittstelle.Kunde erstelleKunde()
	{
		return _delegate.erstelleKunde();
	}

}
