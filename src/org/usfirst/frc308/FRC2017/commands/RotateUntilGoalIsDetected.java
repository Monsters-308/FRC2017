package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RotateUntilGoalIsDetected extends Command{

	double rot;

	public RotateUntilGoalIsDetected(double rotation) {
		requires(Robot.chassis);
		rot = rotation;
	}

	@Override
	protected void initialize() {
		Robot.chassis.setupDrive();
	}

	@Override
	protected void execute() {
		Robot.chassis.arcadeDrive(0, rot);
	}

	@Override
	protected boolean isFinished() {
		if (NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		Robot.chassis.arcadeDrive(0, rot);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
