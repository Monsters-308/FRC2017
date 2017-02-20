package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandClimbShake extends Command {

	boolean State;

	public AutonomousCommandClimbShake(boolean state) {
		requires(Robot.climb);
		State = state;
	}

	@Override
	protected void initialize() {
		if (State) {
			Robot.climb.climbActuatoropen();
		} else {
			Robot.climb.climbActuatoropen();
		}
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