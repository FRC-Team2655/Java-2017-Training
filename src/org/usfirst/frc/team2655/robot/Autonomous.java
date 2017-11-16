package org.usfirst.frc.team2655.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Autonomous {
	
	static ArrayList<String> commands = new ArrayList<>();
	static ArrayList<Double> args = new ArrayList<>();		
	
	//Gets the autonomous scripts for the drive and rotate functions
	public static boolean loadScript(String path) {
		
		try {
			BufferedReader reader = new BufferedReader( new FileReader( new File(path) ) );
			String currentLine = "";
			
			while((currentLine = reader.readLine()) != null) {
				String[] columns = currentLine.split(",");
				String CMD = columns[0];
				double arg = Double.parseDouble(columns[1]);
				commands.add(CMD);
				args.add(arg);
			}
			reader.close();
			return true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void runScript() {
		
		for(int i = 0; i < args.size(); i++) {
			
			switch(commands.get(i)) {
			case "DRIVE":
				drive();
				break;
			
			case "ROTATE":
				rotate();
				break;
			}
			
		}
		
	}
	
	//This will eventually drive the robit
	private static void drive() {
		
	}
	//This will eventually rotate the robit
	private static void rotate() {
		
	}

}
