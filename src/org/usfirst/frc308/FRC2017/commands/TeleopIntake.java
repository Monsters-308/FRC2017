package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setballmotor(RobotConstants.ballintakespeed);
    	Robot.intake.setgearmotor(RobotConstants.gearintakespeed);
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
