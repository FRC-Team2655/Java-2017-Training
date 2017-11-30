package org.usfirst.frc.team2655.robot.subsystems;

import org.usfirst.frc.team2655.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveBaseSubsystem extends Subsystem {


    public void initDefaultCommand() {}
    
    /**
     * Drive the robot
     * @param power Speed to drive
     * @param rotation Power to rotate with
     */
    public void drive(double power, double rotation) {    	
    	Robot.robotDrive.arcadeDrive(power, rotation, false);
    }
    
    /**
     * Drive until a distance at a certain speed in a straight line
     * @param speed The speed to drive at
     * @param distance The distance to drive until
     */
    public void driveDistance(double speed, double distance) {
    	double target = -1; // Distance in ticks
    	double ticks = getAvgTicks();
    	while(ticks < target) {
    		drive(speed, 0);
    		ticks = getAvgTicks();
    	}
    	drive(0, 0);
    }
    
    /**
     * Average the values of all four encoders
     * @return The average number of ticks
     */
    private double getAvgTicks() {
    	// Get avg ticks and return ticks
    	return 0; // This will need to change
    }
}

