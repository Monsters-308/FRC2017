package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TeleopGear extends Command {
	private Timer extendTimer = new Timer();
	private Timer closeTimer = new Timer();
	private Timer doorTimer = new Timer();
	private static boolean buttonExtendState = false;

	public TeleopGear() {

		requires(Robot.gearDelivery);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotConstants.clawExtendState = false;
		RobotConstants.clawOpenState = false;
		RobotConstants.clawDoorState = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if button to extend/retract claw is pressed
		if (extendTimer.get() == 0) {
			if (RobotConstants.clawExtendState == false) {
				RobotConstants.clawExtendState = true;
				Robot.gearDelivery.extendClaw();

			} else { // If the shooter mode was on then toggle off
				Robot.gearDelivery.retractClaw();
				RobotConstants.clawExtendState = false;
			}

			// Start Timer to make sure the toggle happens only once
			extendTimer.start();
		}
		if (extendTimer.get() >= .4) {
			System.out.println("In Reset Timer Code");
			extendTimer.stop();
			extendTimer.reset();
		}

		// if button to open/close claw is pressed
		if (closeTimer.get() == 0) {
			if (RobotConstants.clawOpenState == false) {
				RobotConstants.clawOpenState = true;
				Robot.gearDelivery.closeClaw();

			} else { // If the shooter mode was on then toggle off
				Robot.gearDelivery.openClaw();
				RobotConstants.clawOpenState = false;
			}

			// Start Timer to make sure the toggle happens only once
			closeTimer.start();
		}
		if (closeTimer.get() >= .4) {
			System.out.println("In Reset Timer Code");
			closeTimer.stop();
			closeTimer.reset();
		}

		// if button to open/close passive assist doors is pressed
		if (doorTimer.get() == 0) {
			if (RobotConstants.clawDoorState == false) {
				RobotConstants.clawDoorState = true;
				Robot.gearDelivery.closeClawDoor();

			} else { // If the shooter mode was on then toggle off
				Robot.gearDelivery.openClawDoor();
				RobotConstants.clawDoorState = false;
			}

			// Start Timer to make sure the toggle happens only once
			doorTimer.start();
		}
		if (doorTimer.get() >= .4) {
			System.out.println("In Reset Timer Code");
			doorTimer.stop();
			doorTimer.reset();
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
