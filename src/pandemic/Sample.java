package pandemic;


/**
 * Sample class represents a population where the virus spreads
 * in days and groups and therefore causes pandemic.
 * Two-dimensional array where x = days, y = animals/humans per day,
 * 0 is healthy and 1 is infected, e.g. where one infected member in Day 1:
 * <EXAMPLE>
 * int[][] sampleTableExample = { 
 * {0, 0, 0, 0},
 * {1, 0, 0, 0},
 * {0, 0, 0, 0} };
 * </EXAMPLE>
 * @author www.antiik.dev; https://github.com/antiikdev
 * @version 29 Dec 2021
 */
public class Sample {
	
	private String name = "Unknown sample";
	private final static int minSize = 3;
	private final static int maxSize = 20;
	// Matrix of "people" where 0 is healthy and 1 infected by virus
	private int[][] sampleTable;

	
	/**
	 * Sample initialization
	 */
	public Sample() {
		sampleTable = new int[minSize][minSize];
	}
	
	
	/**
	 * Sample initialization
	 * @param name of the sample
	 * @param days of the sample
	 * @param groupMembers of the sample
	 */
	public Sample(String name, int days, int groupMembers, int infected) {
		this.name = name;
		if ( days < minSize || groupMembers < minSize ) {
			days = minSize;
			groupMembers = minSize;
		}
		if ( days > maxSize || groupMembers > maxSize ) {
			days = maxSize;
			groupMembers = maxSize;
		}
		sampleTable = new int[groupMembers][days];
		initializeSample(infected);
	}
	
	
	/**
	 * Initializes two-dimensional array's values with number 0
	 * @param infected number of members infected in Day 1
	 */
	public void initializeSample(int infected) {
		day1Infections(infected);
		for (int r = 0; r < sampleTable.length; r++) {
			for (int c = 1; c < sampleTable[0].length; c++) {
				sampleTable[r][c] = 0;
				}
			}
	}
	
	
	/**
	 * Sets tabbles infections in day 1
	 * @param infected in day 1
	 */
	public void day1Infections(int infected) {
		int numberOfInfected = 0;
		for (int r = 0; r < sampleTable.length; r++) {
			for (int c = 0; c < 1; c++) {
				if ( numberOfInfected < infected ) {
					sampleTable[r][0] = 1;
					numberOfInfected++;
					if ( r < sampleTable.length ) r++;
				}
			}
		}
	}
	
	
	/**
	 * Minimum size of the table
	 * @return min size of the sample
	 */
	public int getMinSize() {
		return Sample.minSize;
	}
	
	
	/**
	 * Maximum size of the table
	 * @return max size of the sample
	 */
	public int getMaxSize() {
		return Sample.maxSize;
	}
	
	
	/**
	 * Calculates size of a two-dimensional array
	 * @return number of elements in a two-dimensional array
	 */
	public int getPopulationSize() {
		if (sampleTable.length <= 0 || sampleTable == null) return 0;
		return sampleTable.length * sampleTable[0].length;
	}
	
	
	/**
	 * Days of the pandemic spread in population
	 * @return number of days
	 */
	public int getDays() {
		return this.sampleTable[0].length;
	}
	
	
	/**
	 * Members interacting per day in population
	 * @return number of members per day
	 */
	public int getMembers() {
		return sampleTable.length;
	}
	
	
	/**
	 * Sample table's value in i
	 * @param r row
	 * @param c column
	 * @return value in table i
	 */
	public int tableValue(int r, int c) {
		return this.sampleTable[r][c];
	}
	
	
	/**
	 * Sets table value
	 * @param r row
	 * @param c column
	 * @param n number to set (0 healthy, 1 infected)
	 */
	public void setTableValue(int r, int c, int n) {
		this.sampleTable[r][c] = n;
	}
	
	
	/**
	 * Sets sample name
	 * @param name of the sample
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Sets table
	 * @param d days of the sample
	 * @param m members of animals/humans per day in the sample
	 * @param infected how many people infected in Day1
	 */
	public void setTable(int d, int m, int infected) {
		sampleTable = new int[m][d];
		initializeSample(infected);
	}
	
	
	/**
	 * Prints two-dimensional array's numbers
	 */
	public void printSample() {
		for (int r = 0; r < sampleTable.length; r++) {
			if (r > 0) System.out.println();
			for (int c = 0; c < sampleTable[0].length; c++) {
				System.out.print(sampleTable[r][c] + " ");
			}
		}
		System.out.println("\n");
	}
	
	
	/**
	 * Population printed to String
	 * @return population attributes in String format
	 */
	public String toString() {
		return this.name + " with population of " + getPopulationSize() +
				" and " + getDays() + " days.";
	}
	

	/**
	 * @param args not in use
	 */
	public static void main(String[] args) {
		Sample monkeys = new Sample();
								// name, days, group size, infected day1
		Sample population = new Sample("Village", 7, 7, 3);
		
		System.out.println(monkeys.toString());
		monkeys.printSample();
		System.out.println(population.toString());
		population.printSample();
	}

}