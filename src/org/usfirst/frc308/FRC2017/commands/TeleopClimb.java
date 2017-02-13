package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopClimb extends Command {
	public Timer climbTimer = new Timer();

	public TeleopClimb() {

		requires(Robot.climb);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.climb.setupClimb();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.joystick1.getRawButton(RobotConstants.initIntake)) {

			if (climbTimer.get() == 0) {
				Robot.climb.climbRope(RobotConstants.climbSpeed);

			} else { // If the shooter mode was on then toggle off
				Robot.climb.climbRope(0);
			}

			// Start Timer to make sure the toggle happens only once
			climbTimer.start();
		}

		if (climbTimer.get() >= .4)

		{
			climbTimer.stop();
			climbTimer.reset();
		}

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
