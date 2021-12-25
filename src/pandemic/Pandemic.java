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
public class Pandemic {
	
	// Matrix of "people" where 0 is no infection and 1 infected
	static int[][] people = {
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			};

	
	/**
	 * @return random double number between [0,1]
	 */
	public static double createRandomInfection() {
		return Math.random();
	}
	
	
	/**
	 * Create infections by spreading the virus among people
	 * @return number of infections happened (NOTE: can be greater than popul. size)
	 */
	public static int spreadVirus() {
		double infection = 0;
		double infectionRate = 0.5; // probability of infection
		int numberInfections = 0; // number of infections
		
		// Create infections
		for (int r = 0; r < people.length; r++) {
			for (int c = 1; c <people[0].length; c++) {
				// Horizontal infection spreading in the matrix with previous person 
				if (people[r][c-1] == 1) {
					infection = createRandomInfection();
					// System.out.println(infection); // print random infection rate
					if ( infection < infectionRate ) {
						people[r][c] = 1;
						numberInfections++;
						}
					}
				// Sideways infection spreading with in the matrix
				if (r != 0 && people[r-1][c-1] == 1) {
					infection = createRandomInfection();
					// System.out.println(infection); // print random infection rate
					if ( infection < infectionRate ) {
						people[r][c] = 1;
						numberInfections++;
					}
				}
			}
		}
		return numberInfections;
	}
	
	
	/**
	 * Prints two-dimensional array's numbers
	 */
	public static void printVirusSpreading() {
		for (int r = 0; r < people.length; r++) {
			if (r > 0) System.out.println();
			for (int c = 0; c < people[0].length; c++) {
				System.out.print(people[r][c] + " ");
			}
		}
		System.out.println("\n");
	}
	
	
	/**
	 * Calculates size of a two-dimensional array
	 * @return number of elements in a two-dimensional array
	 */
	public static int calculatePopulationSize() {
		return people.length * people[0].length;
	}
	
	
	/**
	 * Main program for testing
	 * @param args not in use
	 */
	public static void main(String[] args) {
		int populationSize = calculatePopulationSize();
		int infectionsTotal = 0;
		int populationsInfected = 0;
		
		// setSizeOfPopulation(); // TODO: size of population		
		// createVirus(); // TODO: create Virus-class with infection rate
		// setFirstInfections(); // TODO: decide how many persons get infected in Day 1
		// printVirusSpreading(); // print Day 1 population and infections
		
		// Infect populations until x (infectiosnTotal) number of infected in population
		// infectionsTotal = spreadVirus();
		while ( infectionsTotal < populationSize ) {
			infectionsTotal = spreadVirus();
			populationsInfected++;
		}
		
		System.out.println("Population size: " + populationSize);
		System.out.println("Total number of infections: " + infectionsTotal);
		System.out.println("Number of populations infected: " + populationsInfected);
		printVirusSpreading();
	}

}
