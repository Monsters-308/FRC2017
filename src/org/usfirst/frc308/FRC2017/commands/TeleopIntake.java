package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopIntake extends Command {


    public TeleopIntake() {


        requires(Robot.intake);


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setupIntake();
    	RobotConstants.intakeMode = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Ball Intake ", RobotConstants.intakeMode);
       	SmartDashboard.putBoolean("Ball run ", Robot.oi.joystick1.getRawButton(RobotConstants.initIntake));
    	if(Robot.oi.joystick1.getRawButton(RobotConstants.initIntake)){      		// MG fix missing brackets
    		if(RobotConstants.intakeMode == false){  // MG fix = should be ==
    			RobotConstants.intakeMode = true;
    			Robot.intake.setballmotor(RobotConstants.ballintakespeed);
    			}
    		else{
    			RobotConstants.intakeMode = false;
    			Robot.intake.setballmotor(0);
    		}  // MG fix missing brackets
    		}
  
    	//Robot.intake.setgearmotor(RobotConstants.gearintakespeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
