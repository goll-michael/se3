package main;

public class DemoSparkasse {
	public static void main(String[] args) {
		Sparkasse dortmund = new SparkasseDortmund();
		dortmund.getPortfolio();

		Sparkasse muenchen = new SparkasseMuenchen();
		muenchen.getPortfolio();
	}
}