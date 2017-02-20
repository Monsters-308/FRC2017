package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandLights extends Command {

	boolean State;

	public AutonomousCommandLights(boolean state) {
		requires(Robot.lights);
		State = state;
	}

	@Override
	protected void initialize() {
		if (State) {
			Robot.lights.setcameraLights();
			RobotConstants.cameralightState = true;
		} else {
			Robot.lights.disablecameraLights();
			RobotConstants.cameralightState = false;
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