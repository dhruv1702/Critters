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
	
	/* Dhruv Verma
	 * .
	 * . 
	 * If Critter2 does not have enough energy to run, it will stay in its position. */
	
	
	
	private int direction;
	private int age;
	
	@Override 
	public String toString() {
		return "2"; 
	}
	
	
	public Critter2() {
		direction = Critter.getRandomInt(8);
		age = 0;
	}
	
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void runStats(java.util.List<Critter> critter2) {
		int avgAge = 0;
		int numCrit2 = 0;
		int avgEnergy = 0;
		for (Object obj : critter2) {
			numCrit2 += 1;
			Critter2 c = (Critter2) obj;
			avgAge += c.age;
			avgEnergy += c.getEnergy();
		}
		avgAge /= numCrit2;
		avgEnergy /= numCrit2;
		System.out.print("" + numCrit2 + " total Critter1s");
		System.out.print("Average age: " + avgAge + "       ");
		System.out.print("Average energy: " + avgEnergy + "\n");
	}
}
