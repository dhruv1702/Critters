package assignment4;

public class Critter1 extends Critter {
	
	/*Critter1 only runs in a random direction each time-step.
	 * If it has not moved, it will run from all critters except algae which it will fight. If not, it will fight.
	 * If Critter1 has more than 50 energy it will reproduce. 
	 * If Critter1 does not have enough energy to run, it will stay in its position. */
	
	
	private int direction;
	private int age;
	
	@Override 
	public String toString() {
		return "Critter1"; 
	}
	
	public Critter1() {
		direction = Critter.getRandomInt(8);
		age = 0;
	}
	
	
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		age++;
		if (getEnergy() > 50){
			Critter1 babyCritter1 = new Critter1();
			reproduce(babyCritter1, Critter.getRandomInt(8));
		}
		if (getEnergy() > Params.run_energy_cost){
			run(Critter.getRandomInt(8));
		}

	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
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

}
