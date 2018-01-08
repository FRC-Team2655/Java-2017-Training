package org.usfirst.frc.team2655.robot.subsystems;

import org.usfirst.frc.team2655.robot.Robot;
import org.usfirst.frc.team2655.robot.values.Values;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveBaseSubsystem extends PIDSubsystem {
	
	/**
	 * Initialize LiveWindow PIDs
	 */
	public DriveBaseSubsystem() {
		super("drive", 1, 0, 0, 0);
		this.setAbsoluteTolerance(140);
		getPIDController().setContinuous(false);
		LiveWindow.add(this.getPIDController());
	}
	
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
     * @param speed The speed to drive at (-1 to 1)
     * @param distance The distance to drive until (inches)
     */
    public void driveDistance(double speed, double distance) {
    	if (distance > 0) {
    		speed = Math.abs(speed);
    	} else {
    		speed = -1 * Math.abs(speed);
    	}
    	double target = getAvgTicks() + (distance / 18.7 * 1440); // Distance in ticks
    	SmartDashboard.putString("DEBUG", speed + "," + target);
    	double ticks = getAvgTicks();
    	    	
    	while(Math.abs(ticks) < Math.abs(target)) {
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
    	int fl = Robot.frontLeft.getSelectedSensorPosition(Robot.talonClosedLoop) * -1; 
    	int fr = Robot.frontRight.getSelectedSensorPosition(Robot.talonClosedLoop); 
    	//int rl = Robot.rearLeft.getSelectedSensorPosition(Robot.talonClosedLoop) * -1;
    	int rr = Robot.rearRight.getSelectedSensorPosition(Robot.talonClosedLoop); 
    	int avg = (fl + fr + rr) / 3;
    	return avg;
    	
    }
    
    protected double returnPIDInput() {
    	double val = getAvgTicks();
		SmartDashboard.putNumber(Values.ENC_GRAPH_KEY, val);
    	return val;
    }

    protected void usePIDOutput(double output) {
    	drive(output, 0);
    }
    
}


