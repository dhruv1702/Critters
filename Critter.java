/* CRITTERS Critter.java
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

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	
	protected boolean hasMoved;
	private boolean alive;
	private boolean isAlive() {
		return alive;
	}
	
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	private final int moveX(int steps) {
		if ((steps + x_coord) < 0){
			return (Params.world_width - steps);
		} 
		else if ((steps + x_coord) > (Params.world_width - 1)) {
			return (steps - 1);
		}
		else {
			return x_coord += steps;
		}
	}

	private final int moveY(int steps) {
		if ((steps + y_coord) < 0){
			return (Params.world_height - steps);
		} 
		else if ((steps + y_coord) > (Params.world_height - 1)) {
			return (steps - 1);
		}
		else {
			return y_coord += steps;
		}
	}

	protected final void walk(int direction) {
		if (hasMoved = true){
			energy -= Params.walk_energy_cost;
			return;
		}
		if (direction == 0){
			x_coord = moveX(1);
		}
		else if (direction == 1) {
			x_coord = moveX(1);
			y_coord = moveY(-1);
		}
		else if (direction == 2) {
			y_coord = moveY(-1);
		}
		else if (direction == 3) {
			x_coord = moveX(-1);
			y_coord = moveY(-1);
		}
		else if (direction == 4) {
			x_coord = moveX(-1);
		}
		else if (direction == 5) {
			x_coord = moveX(-1);
			y_coord = moveY(1);
		}
		else if (direction == 6) {
			y_coord = moveY(1);
		}
		else if (direction == 7) {
			x_coord = moveX(1);
			y_coord = moveY(1);
		}
		energy -= Params.walk_energy_cost;
		hasMoved = true;
	}
	
	
	protected final void run(int direction) {
		if (hasMoved = true){
			energy -= Params.run_energy_cost;
			return;
		}
		if (direction == 0){
			x_coord = moveX(1);
		}
		else if (direction == 1) {
			x_coord = moveX(1);
			y_coord = moveX(-1);
		}
		else if (direction == 2) {
			y_coord = moveX(-1);
		}
		else if (direction == 3) {
			x_coord = moveX(-1);
			y_coord = moveX(-1);
		}
		else if (direction == 4) {
			x_coord = moveX(-1);
		}
		else if (direction == 5) {
			x_coord = moveX(-1);
			y_coord = moveX(1);
		}
		else if (direction == 6) {
			y_coord = moveX(1);
		}
		else if (direction == 7) {
			x_coord = moveX(1);
			y_coord = moveX(1);
		}
		energy -= Params.run_energy_cost;
		hasMoved = true;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy < Params.min_reproduce_energy) {
			return;
		}
		offspring.energy = (this.energy /2);
		energy = (int) Math.ceil(this.energy / 2);
		
		if (direction == 0){
			offspring.x_coord = moveX(1);
			offspring.y_coord = this.y_coord;
		}
		else if (direction == 1) {
			offspring.x_coord = moveX(1);
			offspring.y_coord = moveY(-1);
		}
		else if (direction == 2) {
			offspring.x_coord = this.x_coord;
			offspring.y_coord = moveY(-1);
		}
		else if (direction == 3) {
			offspring.x_coord = moveX(-1);
			offspring.y_coord = moveY(-1);
		}
		else if (direction == 4) {
			offspring.x_coord = moveX(-1);
			offspring.y_coord = this.y_coord;
		}
		else if (direction == 5) {
			offspring.x_coord = moveX(-1);
			offspring.y_coord = moveY(1);
		}
		else if (direction == 6) {
			offspring.x_coord = this.x_coord;
			offspring.y_coord = moveY(1);
		}
		else if (direction == 7) {
			offspring.x_coord = moveX(1);
			offspring.y_coord = moveY(1);
		}
		babies.add(offspring);
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			myCritter = Class.forName(critter_class_name); 	// Class object of specified name
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} catch (InstantiationException e1) {											// various exceptions 
			// Do whatever is needed to handle the various exceptions here -- e.g. rethrow Exception
			throw new InvalidCritterException(critter_class_name);
		} 
		catch (NoSuchMethodException e) {
			throw new InvalidCritterException(critter_class_name);
		} 
		catch (SecurityException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		catch(IllegalAccessException e2){
			throw new InvalidCritterException(critter_class_name);
		}
		catch(IllegalArgumentException e3){
			throw new InvalidCritterException(critter_class_name);
		} 
		catch (InvocationTargetException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		Critter me = (Critter)instanceOfMyCritter;		// Cast to Critter
		me.energy=Params.start_energy;
		me.x_coord=getRandomInt(Params.world_width);
		me.y_coord=getRandomInt(Params.world_height);
		population.add(me);
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
	
		String className = myPackage + "." + critter_class_name;
		for (Critter crit : population) {
			Class<?> crit_class = null;
			try {
				crit_class = Class.forName(className);
			} catch (Exception e) {
				throw new InvalidCritterException(critter_class_name);
			}
			if (crit_class.isInstance(crit)) {
				result.add(crit);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	private static void individualTimeSteps(){
		for (Critter critter : population){
			critter.hasMoved = false;
			critter.doTimeStep();
			critter.energy -= Params.rest_energy_cost;
			if (critter.energy <= 0){
				critter.alive = false;
			}
			else{
				critter.alive = true;
			}
		}
	}
	private static void removeDead(){
		ArrayList<Critter> deadCritters = new ArrayList<>();
		for (Critter critter : population){
			if (critter.alive == false || critter.energy <= 0){
				deadCritters.add(critter);
			}
		}
		population.removeAll(deadCritters);
	}
	
	private static void refreshAlgae() {
		for (int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				Critter.makeCritter(myPackage+"."+"Algae");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static ArrayList<Critter> checkEncounters(Critter c1){
		// add all critters that are in the same location as c1 to fighters ArrayList
		ArrayList<Critter> fighters = new ArrayList<>();
		for (Critter c2 : population){
			if (!c2.equals(c1)){
				if (c1.x_coord == c2.x_coord && c1.y_coord == c2.y_coord){
					fighters.add(c2);
				}
			}
		}
		return fighters;
	}
	
	private boolean sameLocation(Critter c1){
		if (this.x_coord == c1.x_coord && this.y_coord == c1.y_coord){
			return true;
		}
		else{
			return false;
		}
	}
	
	private static void resolveEncounters(){
		ArrayList<Critter> fighters = new ArrayList<>();
		for (Critter c1 : population){
			fighters = checkEncounters(c1);
			if (!fighters.isEmpty()){
				for (Critter c2 : fighters){
					int c1roll, c2roll;
					boolean c1Fight = c1.fight(c2.toString());
					boolean c2Fight = c2.fight(c1.toString());
					while (c1.alive && c2.alive && c1.sameLocation(c2)){
						if (c1Fight == true){
							c1roll = Critter.getRandomInt(c1.getEnergy());
						}
						else {
							c1roll = 0;
						}
						if (c2Fight == true){
							c2roll = Critter.getRandomInt(c2.getEnergy());
						}
						else {
							c2roll = 0;
						}
						
						if (c1roll > c2roll){
							c1.energy += c2.energy / 2;
							c2.energy = 0;
							c2.alive = false;
						}
						else {
							c2.energy += c1.energy / 2;
							c1.energy = 0;
							c1.alive = false;
						}
					}
				}
			}
		}
	}
	
	public static void worldTimeStep() {
		individualTimeSteps();
		population.addAll(babies);
		babies.clear();
		resolveEncounters();
		removeDead();
		refreshAlgae();
	}
	
	public static void displayWorld() {
		System.out.print("+");
		for(int l=0;l<Params.world_width;l++){
			System.out.print("-");
		}
		System.out.println("+");
		for(int y=0;y<Params.world_height;y++){
			for(int x=0;x<Params.world_width;x++){
				String s=" ";
				if(x==0)
					System.out.print("|");
				for(Critter c:population){
					if((c.x_coord==x)&&(c.y_coord==y)){
						s=c.toString();
					}
				}
				System.out.print(s);
				if(x==Params.world_width-1)
					System.out.println("|");
			}
		}
		System.out.print("+");
		for(int l=0;l<Params.world_width;l++){
			System.out.print("-");
		}
		System.out.println("+");
	}
}
