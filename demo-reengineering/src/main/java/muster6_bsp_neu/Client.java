package muster6_bsp_neu;

public class Client {
	public static void main(String[] args) {
		// Die einzulesenden Dateien
		String dateiEins = "dateiEins.txt";
		String dateiZwei = "dateiZwei.xml";

		// Beim Manager sollen nun die passenden Plugins fuer die Dateien gesucht werden
		Manager.getObjektreferenz().findePlugin(dateiEins);
		Manager.getObjektreferenz().findePlugin(dateiZwei);
	}
}
