package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;

/**
 *
 */
public class TeleopPneumatics extends Command {


    public TeleopPneumatics() {


        requires(Robot.pneumatics);


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.startCompressor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pneumatics.startCompressor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
 //  	Robot.pneumatics.stopCompressor();
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
