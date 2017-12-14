package org.usfirst.frc.team2655.robot;

import org.usfirst.frc.team2655.robot.subsystems.DriveBaseSubsystem;
import org.usfirst.frc.team2655.robot.values.Values;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

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
		
		frontLeft.setEncPosition(0);
		rearLeft.setEncPosition(0);
		rearRight.setEncPosition(0);
		frontRight.setEncPosition(0);
		
		SmartDashboard.putNumber(Values.FRENC_KEY, 0);
		SmartDashboard.putNumber(Values.FLENC_KEY, 0);
		SmartDashboard.putNumber(Values.RLENC_KEY, 0);
		SmartDashboard.putNumber(Values.RRENC_KEY, 0);
		SmartDashboard.putBoolean(Values.RESET_ENC, false);
		SmartDashboard.putString("DEBUG", "");
		
		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		frontLeft.configEncoderCodesPerRev(360);
		rearLeft.configEncoderCodesPerRev(360);
		rearRight.configEncoderCodesPerRev(360);
		frontRight.configEncoderCodesPerRev(360);
		
		rearLeft.reverseSensor(true);
		frontLeft.reverseSensor(true);
		
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
		SmartDashboard.putNumber(Values.FLENC_KEY, frontLeft.getEncPosition());
		SmartDashboard.putNumber(Values.FRENC_KEY, frontRight.getEncPosition());
		SmartDashboard.putNumber(Values.RLENC_KEY, rearLeft.getEncPosition());
		SmartDashboard.putNumber(Values.RRENC_KEY, rearRight.getEncPosition());
		
		double power = getJoystickValue(1, 0.1) * -1;
		double rotation = getJoystickValue(3, 0.1);
		driveBase.drive(power, rotation);
		
		if (OI.js0.getRawButton(2)) {
			driveBase.driveDistance(.5, 24);
		}
		boolean b1 = OI.js0.getRawButton(1);
		SmartDashboard.putBoolean(Values.RESET_ENC, b1);
		if (b1) {
			frontLeft.setEncPosition(0);
			rearLeft.setEncPosition(0);
			rearRight.setEncPosition(0);
			frontRight.setEncPosition(0);
		}
	}
	
}
