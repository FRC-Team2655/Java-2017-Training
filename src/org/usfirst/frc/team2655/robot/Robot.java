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
	
	public double getJoystickValue(Joystick joystick) {
	    if(Math.abs(joystick.getRawAxis(0)) < 0.1) return 0;
	    else return joystick.getRawAxis(0);
	}
	
	
	
	@Override
	public void teleopPeriodic() {
		double power = OI.js0.getRawAxis(1) * -1;
		double rotation = OI.js0.getRawAxis(2);
		driveBase.drive(power, rotation);
	
}
