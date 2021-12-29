/**
 * 
 */
package pandemic;

/**
 * Virus class represents a virus that causes the pandemic
 * @author antiik.dev
 * @version 29 Dec 2021
 */
public class Virus {
	
	private String name;; // virus name
	private double infectionRate; // probability of an infection [0,1]

	
	/**
	 * Virus empty initialization
	 */
	public Virus() {
		this.name = "unknown virus";
		this.infectionRate = 0.2;
	}
	
	
	/**
	 * Virus initialization
	 * @param name of the virus
	 * @param rate infection rate in percentages (%)
	 */
	public Virus(String name, int rate) {
		this.name = name;
		if (rate < 0 || rate > 100) return; // use default initialization
		this.infectionRate = rate / 100.0; // percentages
	}
	
	
	/**
	 * Attributes to String format
	 */
	public String toString() {
		return this.name + " infection rate " + this.infectionRate;
	}
	
	
	/**
	 * Main for testing
	 * @param args not in use
	 */
	public static void main(String[] args) {
		Virus ebola = new Virus();
		Virus corona = new Virus("covid", 30);
		
		System.out.println(ebola.toString());
		System.out.println(corona.toString());
	}

}
