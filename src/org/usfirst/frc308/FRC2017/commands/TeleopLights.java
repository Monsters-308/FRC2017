package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopLights extends Command {

	public TeleopLights() {

		requires(Robot.lights);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(RobotConstants.intakeMode == true){
			Robot.lights.testIntakeLights();
		}else{
			Robot.lights.disableIntakeLights();
		}
			
		
		/**
		// Extending lights are solid if claw is up
		if (RobotConstants.clawExtendState == true) {
			Robot.lights.gearExtendLights(true, true);
			RobotConstants.extendLightState = true;
		} else {

			// Extend lights are flashing if claw is down
			Robot.lights.gearClawLights(true, false);
			RobotConstants.extendLightState = false;
		}

		// Claw lights are flashing if claw open
		if (RobotConstants.clawOpenState == true) {
			Robot.lights.gearExtendLights(true, true);
			RobotConstants.clawLightState = true;
		} else {

			// Claw lights solid if claw closed
			Robot.lights.gearClawLights(true, false);
			RobotConstants.clawLightState = false;
		}

		// Mode-switch lights flashing if intake is active
		if (RobotConstants.intakeMode == true) {
			Robot.lights.modeSwitchLights(true, true);
		} else {

			// Mode-switch lights solid if shooter is active
			if (RobotConstants.shooterMode == true) {
				Robot.lights.modeSwitchLights(true, false);
			} else {
				Robot.lights.modeSwitchLights(false, false);
			}
		}
		
	*/
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
