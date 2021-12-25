/**
 * 
 */
package pandemic;

/**
 * Simplified simulation of virus spreading
 * @author AntiikDev
 * @version 25 Dec 2021
 *
 */
// TODO: create Virus-class with infection rate
public class Pandemic {
	
	// Matrix of "people" where 0 is no infection and 1 infected
	static int[][] people = {
			{ 1, 0, 0 },
			{ 1, 0, 0 },
			{ 1, 0, 0 } 
			};

	
	/**
	 * @return random double number between [0,1]
	 */
	public static double createRandomInfection() {
		return Math.random();
	}
	
	
	/**
	 * Spreads the virus among people
	 * @return 
	 */
	public static void spreadVirus() {
		double infection = 0;
		double infectionRate = 0.5; // probability of infection
		
		// TODO: infection possible sideways
		for (int r = 0; r < people.length; r++) {
			for (int c = 1; c <people[0].length; c++) {
				if (people[r][c-1] == 1) {
					infection = createRandomInfection();
					System.out.println(infection);
					if ( infection < infectionRate ) people[r][c] = 1;
					}
			}
		}
	}
	
	
	/**
	 * Prints a two-dimensional array numbers
	 */
	public static void printVirusSpreading() {
		for (int r = 0; r < people.length; r++) {
			if (r > 0) System.out.println();
			for (int c = 0; c < people[0].length; c++) {
				System.out.print(people[r][c]);
			}
		}
	}
	
	
	/**
	 * Main program for testing
	 * @param args not in use
	 */
	public static void main(String[] args) {
		// firstInfections();
		spreadVirus();
		printVirusSpreading();
	}

}
