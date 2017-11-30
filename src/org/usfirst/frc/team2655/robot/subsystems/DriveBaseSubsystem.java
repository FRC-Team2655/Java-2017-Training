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
    	if (distance < 0) {
    		speed *= -1;
    	}
    	double target = getAvgTicks() + (distance / 18.7 * 360); // Distance in ticks
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
    private int getAvgTicks() {
    	int fl = Robot.frontLeft.getEncPosition(); 
    	int fr = Robot.frontRight.getEncPosition(); 
    	int rl = Robot.rearLeft.getEncPosition();
    	int rr = Robot.rearRight.getEncPosition(); 
    	int avg = (fl + fr + rl + rr) / 1; // Only 1 functional encoder. HELP US!!!!
    	return avg;
    	
    }
}

