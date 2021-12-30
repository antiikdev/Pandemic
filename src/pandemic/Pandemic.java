// TODO: ADD how many persons gets infected in Day1

package pandemic;

import java.util.Scanner;

/**
 * Pandemic - simplified simulation of virus spreading
 * Sources (math) inspired by Shen & Bar-Yam. 2020. Pandemic math:
 * Stopping outbreaks, New England Complex Systems Institute.
 * Available online: https://necsi.edu/pandemic-math
 * @author antiik.dev; https://github.com/antiikdev
 * @version 25 Dec 2021
 *
 */
public class Pandemic {
	
	
	/**
	 * Crates a random number between [0,1]
	 * @return random double number between [0,1]
	 */
	public static double createRandomInfection() {
		return Math.random();
	}
	
	
	/**
	 * Create infections by spreading the virus among people
	 * @return number of infections happened (NOTE: can be greater than popul. size)
	 */
	public static int spreadVirus(Sample s, Virus v) {
		double infection = 0;
		double infectionRate = v.getInfectionRate();
		int numberInfections = 0; // number of infections
		
		int rowL = s.getMembers();
		int columnL = s.getDays();
		
		// Create infections
		for (int r = 0; r < rowL; r++) {
			for (int c = 1; c <columnL; c++) {
				// Horizontal infection spreading in the matrix with previous person 
				
				if (s.tableValue(r, c-1) == 1) {
					// Create a new random prob. infection to compare
					infection = createRandomInfection();
					if ( infection < infectionRate ) {
						s.setTableValue(r, c, 1);
						numberInfections++;
						}
					}
				// Sideways infection spreading with in the matrix
				if (r != 0 && s.tableValue(r-1, c-1) == 1) {
					infection = createRandomInfection();
					if ( infection < infectionRate ) {
						s.setTableValue(r, c, 1);
						numberInfections++;
					}
				}
			}
		}
		return numberInfections;
	}
	
	
	/**
	 * Prints pandemic results
	 * @param s sample
	 * @param infections total occured
	 */
	public static void printResults(Sample s, int infections) {
		// TODO: finish if needed
	}
	
	
	/**
	 * Prints program instructions for the user
	 */
	public static void printInstructions() {
		System.out.println("Pandemic program is a simplified simulation " + 
				"of virus spreading in a sample population.\n" +
				"Play once for single pandemic or until population extinction");
	}
	
	
	/**
	 * Creates a new sample of animals/humans for the pandemic
	 * @return created sample
	 */
	public static Sample createSample() {
		Scanner in = new Scanner(System.in);
		String name, days, members;
		
		System.out.print("Write name of the sample (e.g. Village) >");
		name = in.nextLine().trim();
		System.out.print("Write how many days does the pandemic last (min.3, max.30) >");
		days = in.nextLine().trim();
		int d = Integer.parseInt(days);
		System.out.print("Write how many members in contact daily (min.3, max.30) >");
		members = in.nextLine().trim();
		int m = Integer.parseInt(members);
		
		Sample sample = new Sample(name, d, m);
		return sample;
	}
	
	
	/**
	 * Creates a new virus
	 * @return created virus
	 */
	public static Virus createVirus() {
		Scanner in = new Scanner(System.in);
		String name, rate;
		
		System.out.print("Write a virus name >");
		name = in.nextLine().trim();
		System.out.print("Write infection rate for the virus " +
					"in percentages (%) (e.g. 20) >");
		rate = in.nextLine().trim();
		int r = Integer.parseInt(rate);
		Virus virus = new Virus(name, r);
		return virus;
	}
	
	
	/**
	 * Spreads the virus until extinction, i.e.
	 * Infect populations until infection is total
	 * @param s sample
	 * @param v virus
	 */
	public static void spreadVirusExtinction(Sample s, Virus v) {
		int populationSize = s.getPopulationSize();
		int infectionsTotal = 0;
		int populationsInfected = 0;
		
		while ( infectionsTotal < populationSize ) {
			infectionsTotal = spreadVirus(s, v);
			populationsInfected++;
		}
		
		System.out.println("Population size: " + populationSize);
		System.out.println("Total number of infections: " + infectionsTotal);
		System.out.println("Number of populations infected: " + populationsInfected);
		s.printSample();
		pressKeyToContinue();
	}
	
	
	/**
	 * Press any key to continue
	 */
	 private static void pressKeyToContinue()
	 { 
	        System.out.println("Press Any key to continue...");
	        try {
	            System.in.read();
	        } catch(Exception ex) {
	        	System.out.println("Error: " + ex);
	        }
	 }
	
	
	/**
	 * Menu UI for user selection
	 * @return true if continue to use, false if exit
	 */
	public static boolean menuSelect(Sample sample, Virus virus) {
		// Clear console in Java:
		cls();
		System.out.println(sample.getPopulationSize());
		System.out.println("- Pandemic - simple simulation -");
		System.out.println("1) Instructions");
		System.out.println("2) Create a new sample of animals/humans");
		System.out.println("3) Create a new virus");
		System.out.println("4) Start one pandemic");
		System.out.println("5) Start pandemics until extinction");
		System.out.println("6) Exit");
		System.out.print("Write option number and press Enter-key >");
		
		// Scanner to read user input
		Scanner in = new Scanner(System.in);
		switch (in.nextLine()) {
			case "1":
				printInstructions();
				return true;
			case "2":
				sample = createSample();
				return true;
			case "3":
				virus = createVirus();
				return true;
			case "4":
				// TODO: if sample and virus are not initialized
				spreadVirus(sample, virus);
				return true;
			case "5":
				// TODO: if sample and virus are not initialized
				spreadVirusExtinction(sample, virus);
				return true;
			case "6":
				return false;
			default:
				return true;
		}
	}
	
	
	/**
	 * Clear command line screen (Java)
	 * Method from Youtube channel Ubuntu Tricks
	 */
	public static void cls() {
		try {
			new ProcessBuilder("cmd", "/c","cls").inheritIO().start().waitFor();
		} catch (Exception ex) {
			System.out.println("Error:" + ex);
		}
	}
	
	
	/**
	 * Main program for testing
	 * @param args not in use
	 */
	public static void main(String[] args) {
		Sample sample = new Sample();
		Virus virus = new Virus();
		
		boolean menu = true;
		while (menu) {
			menu = menuSelect(sample, virus);
		}
		
		// setSizeOfPopulation(); // TODO: ask user size of population		
		// createVirus(); // TODO: create Virus-class with infection rate
			// TODO: check concepts and naming, e.g. R-number
			// TODO: add prob. and number of deaths, injuries and intensive care
		// setFirstInfections(); // TODO: ask user how many persons gets infected in Day 1
		// printVirusSpreading(); // print Day 1 population and infections
		
		// TODO: a menu for the user to decide if one or many games are played
		// TODO: randomize a new virus with a random infection rate

		
		// TODO: print picture of number of cases spreding
	}
}
