package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopGear extends Command {

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
		if (Robot.oi.joystick1.getRawButton(RobotConstants.extendClawButton)) {
			if (RobotConstants.clawExtendState == false) {
				RobotConstants.clawExtendState = true;
				Robot.gearDelivery.extendClaw();
			} else {
				RobotConstants.clawExtendState = false;
				Robot.gearDelivery.retractClaw();
			}
		}

//		if (Robot.oi.joystick1.getRawButton(RobotConstants.extendClawButton)) {
//			if (RobotConstants.clawExtendState == true) {
//				RobotConstants.clawExtendState = false;
//				Robot.gearDelivery.retractClaw();
//			}
//		}

		// if button to open/close claw is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.closeClawButton)) {
			if (RobotConstants.clawOpenState == false) {
				RobotConstants.clawOpenState = true;
				Robot.gearDelivery.openClaw();
			} else {
				RobotConstants.clawOpenState = false;
				Robot.gearDelivery.closeClaw();
			}		
		}

//
//		if (Robot.oi.joystick1.getRawButton(RobotConstants.closeClawButton)) {
//			if (RobotConstants.clawOpenState == true) {
//				RobotConstants.clawOpenState = false;
//				Robot.gearDelivery.closeClaw();
//			}
//		}

		// if button to open/close passive assist doors is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawDoorButton)) {
			if (RobotConstants.clawDoorState == false) {
				RobotConstants.clawDoorState = true;
				Robot.gearDelivery.openClawDoor();
			}
		}

		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawDoorButton)) {
			if (RobotConstants.clawDoorState == true) {
				RobotConstants.clawDoorState = false;
				Robot.gearDelivery.closeClawDoor();
			}
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
