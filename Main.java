package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
//ONLY FOR STEPS 1&2!!!! TAKE OUT LATER!!!
        for(int i=0;i<100;i++){
        	try {
				Critter.makeCritter(myPackage+"."+"Algae");
			} 
			catch (InvalidCritterException e) {
				System.out.println("error processing: " + "Algae");
			}
        }
        for(int j=0;j<25;j++){
        	try {
				Critter.makeCritter(myPackage+"."+"Craig");
			} 
			catch (InvalidCritterException e) {
				System.out.println("error processing: " + "Craig");
			}
        }
//ONLY FOR STEPS 1&2!!!! TAKE OUT LATER!!!
        
        // System.out.println("GLHF");
        boolean end=false;
        while(!end){
	        String command = kb.nextLine();
	        String commandArray[] = command.split(" ");
	        if (commandArray[0].equals("quit")){
	        	if (commandArray.length > 1) {
					System.out.println("error processing: " + command);
				}
	        	end=true;
	        }
	        
	        else if (commandArray[0].equals("show")){
	        	if (commandArray.length > 1) {
					System.out.println("error processing: " + command);
				}
	        	Critter.displayWorld();
	        }
	        
	        else if (commandArray[0].equals("step")){
	        	if (commandArray.length > 2) {
					System.out.println("error processing: " + command);
				}
	        	try{
	        		int steps = Integer.parseInt(commandArray[1]);
	        		for (int i = 0; i < steps; i++){
	        			Critter.worldTimeStep();
	        		}
	        	}
	        	catch (IndexOutOfBoundsException e) {
					Critter.worldTimeStep();
				} 
	        	catch (NumberFormatException e ) {
					System.out.println("error processing: " + command);
				}
	        }
	        
	        else if (commandArray[0].equals("seed")){
	        	if (commandArray.length > 2) {
					System.out.println("error processing: " + command);
				}
	        	
	        	try{
	        		int seedNumber = Integer.parseInt(commandArray[1]);
	        		Critter.setSeed(seedNumber);
	        	}
	        	catch (IndexOutOfBoundsException e) {
					Critter.worldTimeStep();
				} 
	        	catch (NumberFormatException e ) {
					System.out.println("error processing: " + command);
				}
	        	
	        }
	        
	        else if (commandArray[0].equals("make")){
	        	if (commandArray.length == 2) {
					try {
						String s=myPackage+"."+commandArray[1];
						Critter.makeCritter(s);
					} 
					catch (InvalidCritterException e) {
						System.out.println("error processing: " + command);
					}
	        	}
	        	else if (commandArray.length == 3){
	        		int count = Integer.parseInt(commandArray[2]);
	        		for (int i = 0; i < count; i++) {
						try {
							Critter.makeCritter(commandArray[1]);
						} 
						catch (InvalidCritterException e) {
							System.out.println("error processing: " + command);
							break;
						}
	        		}
	        	}
	        	else {
	        		System.out.println("error processing: " + command);
	        	}
	        }
	        
	        else if (commandArray[0].equals("stats")){
	        	if (commandArray.length != 2){
	        		System.out.println("error processing: " + command);
	        	}
	        	else{
	        		String critterName = commandArray[1];
	        		String className = myPackage + "." + critterName;
	        		//TODO
	        		//Critter.getInstances(commandArray[1]);
	        	}
	        	
	        }
	        /* Write your code above */
	        System.out.flush();
        }
    }
}
