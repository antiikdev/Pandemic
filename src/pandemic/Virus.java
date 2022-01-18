package pandemic;

/**
 * Virus class represents a virus that causes the pandemic
 * @author www.antiik.dev; https://github.com/antiikdev
 * @version 29 Dec 2021
 * Copyright (c) 2022, Antiikdev
 * All rights reserved.
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree. 
 */
public class Virus {
	
	private String name;; // virus name
	private double infectionRate; // probability of an infection [0,1]

	
	/**
	 * Virus empty initialization
	 */
	public Virus() {
		this.name = "unknown virus";
		this.infectionRate = createRandomInfectionRate();
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
	 * Crates a random number between [0,1]
	 * @return random double number between [0,1]
	 */
	public static double createRandomInfectionRate() {
		return Math.random();
	}
	
	
	/**
	 * Gets infection rate of the virus
	 * @return virus infection rate
	 */
	public double getInfectionRate() {
		return this.infectionRate;
	}
	
	
	/**
	 * Gets virus name
	 * @return virus name
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Sets virus name
	 * @param name of the virus
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Initializes virus's infection rate
	 * @param infectionRate of the virus
	 */
	public void initializeVirus(int infectionRate) {
		if (infectionRate < 0 || infectionRate > 100) {
			this.infectionRate = createRandomInfectionRate();
		}
		this.infectionRate = infectionRate / 100.0;
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
		Virus corona = new Virus("Covid", 30);
		
		System.out.println(ebola.toString());
		System.out.println(corona.toString());
	}

}
