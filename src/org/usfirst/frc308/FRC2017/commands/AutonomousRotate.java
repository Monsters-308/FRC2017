
package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;

/**
 *
 */
public class AutonomousRotate extends Command {

	double setpointAngle = 0.0; // the angle we will rotate
	double t;
	Timer timer;

	public AutonomousRotate(double angle, double timeout) {
		requires(Robot.chassis);
		setpointAngle = angle;
		t = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.start();
		Robot.chassis.setupDrive();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.chassis.displayChasisData();
		System.out.println("Turn loop ");
		Robot.chassis.setRotatePIDstart(setpointAngle * timer.get() / t);
		Robot.chassis.setRotatePIDstop();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timer.get() > t) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
