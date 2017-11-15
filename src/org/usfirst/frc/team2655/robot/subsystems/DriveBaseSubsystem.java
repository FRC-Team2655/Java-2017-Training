package org.usfirst.frc.team2655.robot.subsystems;

import org.usfirst.frc.team2655.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveBaseSubsystem extends Subsystem {


    public void initDefaultCommand() {}
    
    public void drive(double power, double rotation) {    	
    	Robot.robotDrive.arcadeDrive(power, rotation, false);
    }
}

