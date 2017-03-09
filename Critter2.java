package assignment4;

public class Critter2 extends Critter {
	
	private int direction;
	private int age;
	
	@Override 
	public String toString() {
		return "Critter2"; 
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

}
