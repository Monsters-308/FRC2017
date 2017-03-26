package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopClimb extends Command {
	public Timer shakeTimer = new Timer();

	public TeleopClimb() {

		requires(Robot.climb);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.climb.setupClimb();
		shakeTimer.reset();
		shakeTimer.start();
	}
	



	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		// Reset Light Cycle when timer expires 
	    if (shakeTimer.get() >= RobotConstants.shakecycle) {
	    	shakeTimer.reset();	
	     }
		
		
		/// use throttle control
		Robot.climb.climbRope(Robot.oi.joystick1.getZ());
		
		// Shake the root
	
	       if (Robot.oi.joystick1.getRawButton(RobotConstants.climbActuator) || RobotConstants.processState) {
	         	// Flash lights for door closed
	       		if (shakeTimer.get() >= (RobotConstants.shakecycle/2))  {
	       			Robot.climb.climbActuatorclose();
	       		    Robot.lights.setIntakeLights();
	       		}else {
	       			Robot.climb.climbActuatoropen();
	       			Robot.lights.disableIntakeLights();
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
