/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Dhruv Verma
 * dv7229
 * 16230
 * Daniel Laveman
 * del824
 * 16230
 * Slip days used: <0>
 * Spring 2017
 */
package assignment4;

public class Critter2 extends Critter {
	
	/* Dhruv's Critter
	 * Critter2 will reproduce every 2 time steps if it has more than 80 energy.
	 * . 
	 * If Critter2 does not have enough energy to run, it will stay in its position. */
	
	
	
	private int age;
	
	@Override 
	public String toString() {
		return "2"; 
	}
	
	
	public Critter2() {
		age = 0;
	}
	
	@Override
	public void doTimeStep() {
		age++;
		if (age % 2 == 0 && getEnergy() > 80){
			Critter2 babyCritter2 = new Critter2();
			reproduce(babyCritter2, Critter.getRandomInt(8));
		}
		if (getEnergy() > 80){
			run(Critter.getRandomInt(8));
		}
		else{
			walk(Critter.getRandomInt(8));
		}

	}

	@Override
	public boolean fight(String oponent) {
		if (getEnergy() >= 50){
			return true;
		}
		else if (this.hasMoved == false){
			walk(getRandomInt(8));
			return false;
		}
		return false;
	}

	public static void runStats(java.util.List<Critter> critter2) {
		int avgAge = 0;
		int numCrit2 = 0;
		int avgEnergy = 0;
		for (Critter crit : critter2) {
			numCrit2 += 1;
			Critter2 c = (Critter2) crit;
			avgAge += c.age;
			avgEnergy += c.getEnergy();
		}
		avgAge /= numCrit2;
		avgEnergy /= numCrit2;
		System.out.print("" + numCrit2 + " total Critter1s");
		System.out.print("       Average age: " + avgAge + "       ");
		System.out.print("Average energy: " + avgEnergy + "\n");
	}
}
