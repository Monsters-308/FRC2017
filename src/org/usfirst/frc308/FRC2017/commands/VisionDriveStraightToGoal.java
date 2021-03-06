package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionDriveStraightToGoal extends Command {

	double power;
	Timer timeout;

	/**
	 * starts a command that drives robot for a specific amount of time
	 * 
	 * @param motorpower
	 *            the motor power to drive from -1.0 to 1.0
	 * @param time
	 *            how long to drive in seconds
	 */
	public VisionDriveStraightToGoal (double motorpower) {
		power = motorpower;
		requires(Robot.chassis);
	}

	@Override
	protected void initialize() {
		timeout = new Timer();
		Robot.chassis.setupDrive();
	}

	@Override
	protected void execute() {
		Robot.chassis.displayChasisData();
		Robot.chassis.arcadeDrive(power, 0);
	}

	@Override
	protected boolean isFinished() {
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", new double[0]);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", new double[0]);
		int biggestTarget = MathUtils.getLargestIndex(targets3);
		if (targets.length == 0 && timeout.get() == 0) {
			timeout.start();
		} else if (targets.length > 0 && timeout.get() != 0) {
			timeout.stop();
			timeout.reset();
		}
		if (timeout.get() > 2.0) {
			return true;
		}
		if (targets.length > 0 && targets[biggestTarget] <= 95) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		Robot.chassis.arcadeDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
