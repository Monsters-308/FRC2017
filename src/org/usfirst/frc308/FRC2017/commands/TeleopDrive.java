package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class TeleopDrive extends Command {


    public TeleopDrive() {

        requires(Robot.chassis);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.setupDrive();
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double forward = Robot.chassis.deadZone(Robot.oi.joystick1.getY()); //  forward
    	double turn = -(Robot.chassis.deadZone(Robot.oi.joystick1.getX())); // turn 
    	Robot.chassis.arcadeDrive(forward, turn);
    
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.spiGyro_1.getAngle());
    	SmartDashboard.putDouble("Controller X", Robot.oi.joystick1.getX());
    	SmartDashboard.putDouble("Controller Y", Robot.oi.joystick1.getY());
    	SmartDashboard.putDouble("Controller forward", forward);
    	SmartDashboard.putDouble("Controller turn", turn);
    	SmartDashboard.putNumber("gearintake ", RobotConstants.gearintakespeed);
 //   	Robot.chassis.displayChasisData();
 	
	

    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}