package org.usfirst.frc.team2655.robot;

import org.usfirst.frc.team2655.robot.subsystems.DriveBaseSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	// Our motor controllers. These will be initialized (created) in robotInit
	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon rearRight;
	public static CANTalon frontRight;

	// The RobotDrive class handles all the motors
	public static RobotDrive robotDrive;
	
	// Our robot's drive base
	public static DriveBaseSubsystem driveBase = new DriveBaseSubsystem();

	/**
	 * Setup the motor controllers and the drive object
	 */
	@Override
	public void robotInit() {
		frontLeft = new CANTalon(1);
		rearLeft = new CANTalon(2);
		rearRight = new CANTalon(3);
		frontRight = new CANTalon(4);
		
		robotDrive = new RobotDrive(frontLeft, rearLeft, rearRight, frontRight);
		
		SmartDashboard.putString("DEBUG", "");
	}
	
	/**
	 * Get a value for an axis with a dead zone (deadband)
	 * @param axis The axis to get the value for
	 * @param deadband The dead zone
	 * @return The value if it is outside the deadband otherwise zero.
	 */
	public double getJoystickValue(int axis, double deadband) {
	    if(Math.abs(OI.js0.getRawAxis(axis)) < deadband) return 0;
	    else return OI.js0.getRawAxis(axis);
	}
	
	/**
	 * Called every 20ms during the driver controlled period
	 */
	@Override
	public void teleopPeriodic() {
		double power = getJoystickValue(1, 0.1) * -1;
		double rotation = getJoystickValue(3, 0.1);
		driveBase.drive(power, rotation);
		
		if (OI.js0.getRawButton(2)) {
			SmartDashboard.putString("DEBUG", "GO");
			driveBase.driveDistance(.5, 24);
			SmartDashboard.putString("DEBUG", "STOP");
		}else {
			SmartDashboard.putString("DEBUG", "STOP");
		}
		
	}
	
}
