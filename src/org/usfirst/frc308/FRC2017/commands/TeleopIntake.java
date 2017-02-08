package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TeleopIntake extends Command {
	private boolean buttonIntakeState = false;
	private Timer intakeTimer = new Timer();

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
		// SmartDashboard.putBoolean("ALEX'S TEST", test);
		// SmartDashboard.putBoolean("ALEX'S TEST2", out);

		SmartDashboard.putBoolean("Ball Intake ", RobotConstants.intakeMode);
		SmartDashboard.putBoolean("Ball run ", Robot.oi.joystick1.getRawButton(RobotConstants.initIntake));
		if (Robot.oi.joystick1.getRawButton(RobotConstants.initIntake)) {

			if (intakeTimer.get() == 0) {
				if (RobotConstants.intakeMode == false) {
					RobotConstants.intakeMode = true;
					Robot.intake.setballmotor(RobotConstants.ballintakespeed);

					// Turn off shooter
					Robot.shooter.setShootSpeed(0);
					RobotConstants.shooterMode = false;
				} else { // If the shooter mode was on then toggle off
					Robot.intake.setballmotor(0);
					RobotConstants.intakeMode = false;
				}

				// Start Timer to make sure the toggle happens only once
				intakeTimer.start();
			}
		}

		// If the buttonShooterTimer is greater than value then reset it
		// Note: Tune the value to better timing of when the button is pressed
		// and the next pressed
		if (intakeTimer.get() >= .4)

		{
			System.out.println("In Reset Timer Code");
			intakeTimer.stop();
			intakeTimer.reset();
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
