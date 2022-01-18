package pandemic;

import java.util.Scanner;

/**
 * Pandemic - simplified simulation of virus spreading in a small sample
 * Source (math inspired by): Shen & Bar-Yam. 2020. "Pandemic math:
 * Stopping outbreaks", New England Complex Systems Institute.
 * Available online: https://necsi.edu/pandemic-math
 * @author www.antiik.dev; https://github.com/antiikdev
 * @version 8 Jan 2022
 * Copyright (c) 2022, Antiikdev
 * All rights reserved.
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree. 
 */
/** 
 * TODO: Ideas to continue the development:
 * - ONGOING DEV: Menu selection to run "quick pandemic leading to extinction"
 * - printStatistics() method distribution to print non-linear (now normal dist.)
 * - remove vertical infections if unnecessary
 * - check concepts, naming, relevant math, e.g. math of R-number
 * - add prob. and number of deaths, injuries and intensive care
 * - if possible to develop spreadVirus-method to not non-linear O(n^2)
 * - add try-catches to new class creation methods
 * - Day 1 cases also to spread?
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
			for (int c = 1; c < columnL; c++) {
				
				// Horizontal (x) infection spreading in the matrix with previous person 
				if (s.tableValue(r, c-1) == 1) {
					// Create a new random prob. infection to compare if infection happen
					infection = createRandomInfection();
					if ( infection < infectionRate ) {
						s.setTableValue(r, c, 1);
						numberInfections++;
						}
					}
				// Sideways infection spreading within the matrix
				if (r != 0 && s.tableValue(r-1, c-1) == 1) {
					infection = createRandomInfection();
					if ( infection < infectionRate ) {
						s.setTableValue(r, c, 1);
						numberInfections++;
					}
				}
				// vertical (y) infection spreading within the matrix
				if (r != 0 && s.tableValue(r-1, c) == 1) {
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
	 * Prints program instructions for the user
	 */
	public static void printInstructions() {
		System.out.println("Pandemic program is a simplified simulation " + 
				"of virus spreading in a limited sample population.\n" +
				"Play once for single pandemic or until population extinction");
	}
	
	
	/**
	 * Creates a new sample of animals/humans for the pandemic
	 * @param sample of the population created
	 */
	public static void createSample(Sample sample) {
		Scanner in = new Scanner(System.in);
		String name, days, members, infected;
		
		cls();
		System.out.print("Write name of the sample (e.g. Village) >");
		name = in.nextLine().trim();
		sample.setName(name);
		
		System.out.print("Write how many days does the pandemic last " +
			"(min." + sample.getMinSize() + ", max."+ sample.getMaxSize() + ") >");
		days = in.nextLine().trim();
		int d = Integer.parseInt(days);
		
		System.out.print("Write how many members in contact daily " +
				"(min." + sample.getMinSize() + ", max."+ sample.getMaxSize() + ") >");
		members = in.nextLine().trim();
		int m = Integer.parseInt(members);
		
		System.out.print("Write how many members get infected in Day 1? (max. " +
							m/2 + ") >"); // m/2 as only every second infected
		infected = in.nextLine().trim();
		int i = Integer.parseInt(infected);
		
		sample.setTable(d, m, i);
	}
	
	
	/**
	 * Creates a new virus
	 * @param virus that is created
	 */
	public static void createVirus(Virus virus) {
		Scanner in = new Scanner(System.in);
		String name, rate;
		
		cls();
		System.out.print("Write a virus name >");
		name = in.nextLine().trim();
		virus.setName(name);
		
		System.out.print("Write infection rate for the virus " +
					"in percentages (%) (e.g. 50) >");
		rate = in.nextLine().trim();
		int r = Integer.parseInt(rate);
		virus.initializeVirus(r);
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
		int[] infectionArray = new int[s.getPopulationSize() * 2]; // bigger array x 2
		
		// While: how many infections required before stopping pandemics
		while ( infectionsTotal < populationSize ) {
			infectionsTotal = spreadVirus(s, v);
			infectionArray[infectionsTotal] += 1; // how many infections per event saved
			populationsInfected++;
		}
		
		cls();
		System.out.println("Population size: " + populationSize);
		System.out.println("Total number of infections: " + infectionsTotal);
		System.out.println("Number of populations infected: " + populationsInfected);
		System.out.println(v.getName() + " virus's infection rate: " +
				v.getInfectionRate());
		s.printSample();
		printStatistics(infectionArray);
		pressEnterToContinue();
	}
	
	
	/**
	 * Asks user to press Enter key to continue...
	 * Source: Stackoverflow, user "E235"
	 */
	 private static void pressEnterToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try {
	            System.in.read();
	        } catch(Exception ex) {
	        	System.out.println("Error: " + ex);
	        }
	 }
	 
	 
	/**
	 * Clear command line screen (Java)
	 * Method from Youtube channel "Ubuntu Tricks", Stackoverflow "J. Bosboom"
	 */
	public static void cls() {
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		} catch (Exception ex) {
			System.out.println("Error:" + ex);
		}
	}
	
	
	/**
	 * Prints one pandemic matrix results
	 * @param s sample
	 * @param v virus
	 * @param infections total occured
	 */
	public static void printResults(Sample s, Virus v, int infections) {
		int populationSize = s.getPopulationSize();
		
		cls();
		System.out.println("Population size: " + populationSize);
		System.out.println("Total number of infections: " + infections);
		System.out.println(v.getName() + " virus's infection rate: " +
					v.getInfectionRate());
		s.printSample();
		pressEnterToContinue();
	}
	
	
	/**
	 * Prints all statistics from certain range of cases
	 * @param array of infections per event
	 */
	public static void printStatistics(int[] array) {
		String star = "*";
		String repeat;
		int maxValue = findMaxValue(array);
		int ratio = 20; // how many stars max
		double numberOfStars = 0.0;
		// NOTE: if not sorted: normally distributed (false); with sorted: non-linear
		// Arrays.sort(array); // Array sorted
		
		System.out.println("*statistics*");
		System.out.println("Warning: non-linear dist. presenter her as normally distributed");
		System.out.println("Infections / times: distribution");
		for (int i = 0; i < array.length; i++) {
			if ( array[i] > 0 ) {
				double rate = (double)array[i] / (double)maxValue;
				// System.out.print(rate + " ");
				numberOfStars = ratio * rate;
				if ( rate == 1 ) numberOfStars = ratio;
				// System.out.print(number + " ");
				
				repeat = new String(new char[(int)numberOfStars]).replace("\0", star);
				// if ( i < 10 ) System.out.print("0" + i + ": ");
				System.out.print(i + " / " + array[i] + ": ");
				System.out.println(repeat);
			}
		}
	}
	
	
	/**
	 * Finds maximum value from an integer array
	 * @param array of infections
	 * @return maximum value int
	 */
	public static int findMaxValue(int[] array) {
		int maxValue = -Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if ( array[i] > maxValue ) maxValue = array[i];
		}
		return maxValue;
	}
	
	
	/**
	 * Menu UI for user selection
	 * @return true if continue to use, false if exit
	 */
	public static boolean menuSelect() {
		// Note: Clear console works in command line, not in Eclipse
		cls();
		System.out.println(" Pandemic - simple simulation -");
		System.out.println("1) Instructions");
		System.out.println("2) Start one pandemic");
		System.out.println("3) Start pandemics until extinction");
		System.out.println("4) Start -quick- pandemics until extinction (10,10,3,30%)");
		System.out.println("5) Exit");
		System.out.print("Write option number and press Enter-key >");
		
		// Scanner to read user input
		Scanner in = new Scanner(System.in);
		switch (in.nextLine()) {
			case "1": // instructions
				printInstructions();
				return true;
			case "2": // start one pandemic
				Sample sample = new Sample();
				Virus virus = new Virus();
				createSample(sample);
				createVirus(virus);
				int infections = spreadVirus(sample, virus);
				printResults(sample, virus, infections);
				return true;
			case "3": // Start pandemics until extinction
				Sample sample2 = new Sample();
				Virus virus2 = new Virus();
				createSample(sample2);
				createVirus(virus2);
				System.out.println("Calculating and spreading the virus...");
				spreadVirusExtinction(sample2, virus2);
				return true;
			case "4": // Start -quick- pandemics until extinction
				Sample sample3 = new Sample("Village", 10, 10, 3);
				Virus virus3 = new Virus("The Evil", 30);
				System.out.println("Calculating and spreading the virus...");
				spreadVirusExtinction(sample3, virus3);
				return true;
			case "5": // exit
				return false;
			default:
				return true;
		}
	}
	
	
	/**
	 * Main to start the program menu
	 * @param args not in use
	 */
	public static void main(String[] args) {
		boolean menu = true;
		while (menu) {
			menu = menuSelect();
		}
	}
	
}
