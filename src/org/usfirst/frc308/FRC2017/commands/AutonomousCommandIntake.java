package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandIntake extends Command {

	double bspeed;

	public AutonomousCommandIntake(double bspeed) {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
			Robot.intake.setballmotor(bspeed);
			}
	

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
