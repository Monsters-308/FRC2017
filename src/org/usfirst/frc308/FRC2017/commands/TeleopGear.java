package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		RobotConstants.batDoorState = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	// if button to open/close passive assist doors is pressed

		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawDoorButton)) {
			if (doorTimer.get() == 0) {
				
				if (RobotConstants.batDoorState == false) {
					RobotConstants.batDoorState = true;
					Robot.gearDelivery.closeClawDoor();
				
				} else { // If the shooter mode was on then toggle off
					RobotConstants.batDoorState = false;
					Robot.gearDelivery.openClawDoor();
				} // end state check
			} // end timer check
			// Start Timer to make sure the toggle happens only once
			doorTimer.start();
		} // end joystick button
		if (doorTimer.get() >= RobotConstants.doorTimer_timer) {
			doorTimer.stop();
			doorTimer.reset();
		}

		// if button to extend/retract claw is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.extendClawButton)) {
			if (extendTimer.get() == 0) {
				if (RobotConstants.clawExtendState == false) {
					RobotConstants.clawExtendState = true;
					Robot.gearDelivery.extendClaw();

				} else { // If the shooter mode was on then toggle off
					Robot.gearDelivery.retractClaw();
					RobotConstants.clawExtendState = false;
				} // state check
			} // end timer check

			// Start Timer to make sure the toggle happens only once
			extendTimer.start();
		} // end joystick check

		if (extendTimer.get() >= RobotConstants.extendTimer_timer) {
			extendTimer.stop();
			extendTimer.reset();
		} // end timer loop

		// if button to open/close claw is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.closeClawButton)) {
			if (closeTimer.get() == 0) {
				if (RobotConstants.clawOpenState == false) {
					RobotConstants.clawOpenState = true;
					Robot.gearDelivery.openClaw();

				} else { // If the shooter mode was on then toggle off
					Robot.gearDelivery.closeClaw();
					RobotConstants.clawOpenState = false;
				} // end sate check
			} // end timer
				// Start Timer to make sure the toggle happens only once
			closeTimer.start();
		} // End joystick check

		if (closeTimer.get() >= RobotConstants.closeTimer_timer) {
			closeTimer.stop();
			closeTimer.reset();
		}
		SmartDashboard.putBoolean("claw end", RobotConstants.clawOpenState);
		SmartDashboard.putBoolean("extend end", RobotConstants.clawExtendState);
		SmartDashboard.putBoolean("Geardoor end", RobotConstants.batDoorState);
	} // end exectute

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
