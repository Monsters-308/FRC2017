package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class TeleopProcess extends Command {

	public Timer FeedBall = new Timer();

	public TeleopProcess() {

		requires(Robot.processBalls);

	}

	// Called just before this Command runs the first time
	// Sets processor talon to voltage mode
	protected void initialize() {
		Robot.processBalls.setupProcess();
	}

	// Called repeatedly when this Command is scheduled to run
	// Activates ball processor while shooting
	protected void execute() {
		if (Robot.oi.joystick1.getRawButton(RobotConstants.shootBall)) {

			if (FeedBall.get() == 0) {
				if (RobotConstants.processState == false) {
					RobotConstants.processState = true;
					Robot.processBalls.runProcess(RobotConstants.feederSpeed);
		//			Robot.Intake.setballmotor(RobotConstants.bspeed);
				} else {
					Robot.processBalls.runProcess(0);
					RobotConstants.processState = false;
				}

				// Start Timer to make sure the toggle happens only once
				FeedBall.start();
			}
		}

		// If the buttonShooterTimer is greater than value then reset it
		// Note: Tune the value to better timing of when the button is pressed
		// and the next pressed
		if (FeedBall.get() >= .4)

		{
			FeedBall.stop();
			FeedBall.reset();
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
