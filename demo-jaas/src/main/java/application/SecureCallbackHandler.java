package application;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swing.JOptionPane;

public class SecureCallbackHandler implements CallbackHandler {

	private String name;
	private char[] password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof TextOutputCallback) {
				// Zeige dem Typ gemaess die Nachricht an
				TextOutputCallback toc = (TextOutputCallback) callbacks[i];
				switch (toc.getMessageType()) {
				case TextOutputCallback.INFORMATION:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
					break;
				case TextOutputCallback.ERROR:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
					break;
				case TextOutputCallback.WARNING:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Warnung", JOptionPane.WARNING_MESSAGE);
					break;
				default:
					throw new IOException("Nachrichtentyp nicht unterstuetzt: " + toc.getMessageType());
				}
			} else if (callbacks[i] instanceof NameCallback) {
				// Setze den Namen des Benutzers
				NameCallback nc = (NameCallback) callbacks[i];
				nc.setName(name);
			} else if (callbacks[i] instanceof PasswordCallback) {
				// Setze Passwort des Benutzers
				PasswordCallback pc = (PasswordCallback) callbacks[i];
				pc.setPassword(password);
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unidentifizierbares Callback");
			}
		}
	}

}