package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.RobotMap;
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
    	double forward = Robot.oi.joystick1.getY(); //  forward
    	double turn = -(Robot.oi.joystick1.getX()); // turn 
    	//Robot.chassis._drive.arcadeDrive(forward, turn);
    	//Robot.chassis._drive2.arcadeDrive(forward, turn);
    	//RobotMap.chassisRobotDrive6.arcadeDrive(forward, turn);
    	Robot.chassis.arcadeDrive(forward, turn);
    	
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.spiGyro_1.getAngle());
    	SmartDashboard.putDouble("Controller X", Robot.oi.joystick1.getX());
    	SmartDashboard.putDouble("Controller Y", Robot.oi.joystick1.getY());
    	
     //  if button Claw 1 is pressed
    		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton1)) 
    		Robot.chassis.claw1_Open();
    	    else  // close claw 1
    		 Robot.chassis.claw1_Close();
        
    //	if button Claw 2 is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton2)) 
		Robot.chassis.claw2_Open();
	    else  // close claw 2
		 Robot.chassis.claw2_Close();
    	
//		if button Claw 3 is pressed
			if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton3)) 
			Robot.chassis.claw3_Open();
		    else  // close claw 3
			 Robot.chassis.claw3_Close(); 	

    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}