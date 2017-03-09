/* CRITTERS Critter1.java
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

public class Critter1 extends Critter {
	
	/* Dhruv's Critter
	 * Critter1 is a marathoner who only runs in a random direction each time-step.
	 * If it has not moved, it will run from all critters except algae which it will fight. If not, it will fight.
	 * If Critter1 has more than 100 energy it will reproduce. 
	 * If Critter1 does not have enough energy to run, it will stay in its position. */
	
	
	private int age;
	
	@Override 
	public String toString() {
		return "1"; 
	}
	
	public Critter1() {
		age = 0;
	}
	
	
	@Override
	public void doTimeStep() {
		age++;
		if (getEnergy() > 100){
			Critter1 babyCritter1 = new Critter1();
			reproduce(babyCritter1, Critter.getRandomInt(8));
		}
		if (getEnergy() > Params.run_energy_cost){
			run(Critter.getRandomInt(8));
		}

	}

	@Override
	public boolean fight(String oponent) {
		if(this.hasMoved == false){	
			if (oponent != "Algae"){
				run(Critter.getRandomInt(8));
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return true;
		}
	}
	
	
	public static void runStats(java.util.List<Critter> critter1) {
		int avgAge = 0;
		int numCrit1 = 0;
		int avgEnergy = 0;
		for (Critter crit : critter1) {
			numCrit1 += 1;
			Critter1 c = (Critter1) crit;
			avgAge += c.age;
			avgEnergy += c.getEnergy();
		}
		avgAge /= numCrit1;
		avgEnergy /= numCrit1;
		System.out.print("" + numCrit1 + " total Critter1s");
		System.out.print("       Average age: " + avgAge + "       ");
		System.out.print("Average energy: " + avgEnergy + "\n");
	}


}
