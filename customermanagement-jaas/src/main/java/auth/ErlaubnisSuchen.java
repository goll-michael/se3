package auth;

import java.security.BasicPermission;

public class ErlaubnisSuchen<T> extends BasicPermission
{

	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehoerigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt spaeter deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * ueberein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = 2998392303198140739L;

	public ErlaubnisSuchen()
	{
		super("SuchenErlaubnis");
	}

}
