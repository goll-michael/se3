package muster6_bsp_neu;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager {
	// Singleton
	public static Manager manager = null;

	private ArrayList<Plugin> meinePlugins;

	public Manager() {
		meinePlugins = new ArrayList<Plugin>();
	}

	@SuppressWarnings("unused")
	public static Manager getObjektreferenz() {
		if (manager == null) {
			manager = new Manager();

			// Die vorhandenen Plugins hinzufuegen
			PluginTXT pluginTXT = new PluginTXT();
			PluginXML pluginXML = new PluginXML();
		}
		return manager;
	}

	public void einfuegePlugin(Plugin einPlugin) {
		meinePlugins.add(einPlugin);
	}

	public void entfernePlugin(Plugin einPlugin) {
		meinePlugins.remove(einPlugin);
	}

	public void findePlugin(String dateiname) {
		Iterator<Plugin> iter = meinePlugins.iterator();
		while (iter.hasNext()) {
			Plugin plugin = iter.next();
			if (plugin.getSuffix().endsWith(dateiname.substring(dateiname.length() - 3, dateiname.length())))
				plugin.action(dateiname);
		}
	}
}
