package application;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Benutzer {
	private Long benutzernr;
	private String pseudonym;
	private String passwort;

	// @GeneratedValue zusätzlich, wenn Wert durch
	// Persistenzschicht generiert werden soll.
	@Id
	public Long getBenutzernr() {
		return benutzernr;
	}

	public void setBenutzernr(Long benutzernr) {
		this.benutzernr = benutzernr;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}
}