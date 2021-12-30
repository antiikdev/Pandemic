/**
 * 
 */
package pandemic;

/**
 * Sample class represents a population where the virus spreads
 * in days and groups and therefore causes pandemic.
 * @author antiik.dev; https://github.com/antiikdev
 * @version 29 Dec 2021
 */
public class Sample {
	
	private String name = "Unknown sample";
	private final static int minSize = 3;
	private final static int maxSize = 30;
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
	public Sample(String name, int days, int groupMembers) {
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
		initializeSample();
	}
	
	
	/**
	 * Initializes two-dimensional array's values with number 0
	 */
	public void initializeSample() {
		for (int r = 0; r < sampleTable.length; r++) {
			for (int c = 0; c < sampleTable[0].length; c++) {
				sampleTable[r][c] = 0;
			}
		}
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
										// name, days, group size
		Sample population = new Sample("Village", 5, 6);
		
		System.out.println(monkeys.toString());
		monkeys.printSample();
		System.out.println(population.toString());
		population.printSample();
	}

}