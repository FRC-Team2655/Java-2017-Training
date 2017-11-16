package org.usfirst.frc.team2655.robot;

import org.usfirst.frc.team2655.robot.subsystems.DriveBaseSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;


public class Robot extends IterativeRobot {
	
	public static CANTalon frontLeft;
	public static CANTalon rearLeft;
	public static CANTalon rearRight;
	public static CANTalon frontRight;

	public static RobotDrive robotDrive;
	
	public static DriveBaseSubsystem driveBase = new DriveBaseSubsystem();

	@Override
	public void robotInit() {
		frontLeft = new CANTalon(1);
		rearLeft = new CANTalon(2);
		rearRight = new CANTalon(3);
		frontRight = new CANTalon(4);
		
		robotDrive = new RobotDrive(frontLeft, rearLeft, rearRight, frontRight);
	}
	
	public double getJoystickValue(int axis) {
	    if(Math.abs(OI.js0.getRawAxis(axis)) < 0.1) return 0;
	    else return OI.js0.getRawAxis(axis);
	}
	
	
	
	@Override
	public void teleopPeriodic() {
		double power = getJoystickValue(1) * -1;
		double rotation = getJoystickValue(3);
		driveBase.drive(power, rotation);
	}
	
}
