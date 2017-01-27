package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopShooter extends Command {


    public TeleopShooter() {


        requires(Robot.shooter);

        //RobotConstants.shootertargetspeed
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   Robot.shooter.setupShooter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	//	if button A is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.shootBallHigh)) 
		Robot.shooter.setShootSpeed(RobotConstants.shootertargetspeed);
	 else  // else setShootPower 0
			Robot.shooter.setShootSpeed(0);
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
