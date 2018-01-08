package org.usfirst.frc.team2655.robot;

import org.usfirst.frc.team2655.robot.subsystems.DriveBaseSubsystem;
import org.usfirst.frc.team2655.robot.values.Values;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	// Constants used in program
	public static int talonClosedLoop = 0; // Normal closed loop
	public static int talonTimeout = 0; // Do not timeout or wait for error codes
	
	// Our motor controllers. These will be initialized (created) in robotInit
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX rearLeft;
	public static WPI_TalonSRX rearRight;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX[] motors;
	// The RobotDrive class handles all the motors
	public static DifferentialDrive robotDrive;
	
	// Our robot's drive base
	public static DriveBaseSubsystem driveBase;
	/**
	 * Setup the motor controllers and the drive object
	 */
	@Override
	public void robotInit() {
		frontLeft = new WPI_TalonSRX(1);
		rearLeft = new WPI_TalonSRX(2);
		rearRight = new WPI_TalonSRX(3);
		frontRight = new WPI_TalonSRX(4);
		
		// Setup the rear motors to follow (copy) the front motors
		rearLeft.follow(frontLeft);
		rearRight.follow(frontRight);
		
		motors = new WPI_TalonSRX[] {frontLeft, frontRight, rearRight, rearLeft};
		driveBase = new DriveBaseSubsystem();
		robotDrive = new DifferentialDrive(frontLeft, frontRight);
		
		// Setup the motor controllers
		for(WPI_TalonSRX m : motors) {
			m.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, talonClosedLoop, talonTimeout);
			m.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
		}
		
		// Add stuff to the dashboard
		SmartDashboard.putNumber(Values.FRENC_KEY, 0);
		SmartDashboard.putNumber(Values.FLENC_KEY, 0);
		SmartDashboard.putNumber(Values.RLENC_KEY, 0);
		SmartDashboard.putNumber(Values.RRENC_KEY, 0);
		SmartDashboard.putBoolean(Values.RESET_ENC, false);
		SmartDashboard.putString("DEBUG", "");
		SmartDashboard.putNumber(Values.ENC_GRAPH_KEY, 0);
		SmartDashboard.putNumber(Values.ENC_SETPOINT_KEY, 0);
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
		SmartDashboard.putNumber(Values.FLENC_KEY, frontLeft.getSelectedSensorPosition(talonClosedLoop));
		SmartDashboard.putNumber(Values.FRENC_KEY, frontRight.getSelectedSensorPosition(talonClosedLoop));
		SmartDashboard.putNumber(Values.RLENC_KEY, rearLeft.getSelectedSensorPosition(talonClosedLoop));
		SmartDashboard.putNumber(Values.RRENC_KEY, rearRight.getSelectedSensorPosition(talonClosedLoop));
		
		double power = getJoystickValue(1, 0.1) * -1;
		double rotation = getJoystickValue(3, 0.1);
		driveBase.drive(power, rotation);
		
		if (OI.js0.getRawButton(2)) {
			driveBase.driveDistance(.5, 24);
		}
		boolean b1 = OI.js0.getRawButton(1);
		SmartDashboard.putBoolean(Values.RESET_ENC, b1);
		if (b1) {
			frontLeft.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			rearLeft.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			rearRight.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			frontRight.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
		}
	}

	@Override
	public void testPeriodic() {
		boolean b1 = OI.js0.getRawButton(1);
		SmartDashboard.putBoolean(Values.RESET_ENC, b1);
		if (b1) {
			frontLeft.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			rearLeft.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			rearRight.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
			frontRight.setSelectedSensorPosition(0, talonClosedLoop, talonTimeout);
		}
	}
	
	
	
}
