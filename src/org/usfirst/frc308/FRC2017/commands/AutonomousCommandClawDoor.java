package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandClawDoor extends Command {

	boolean State;

	public AutonomousCommandClawDoor(boolean state) {
		requires(Robot.gearDelivery);
		State = state;
	}

	@Override
	protected void initialize() {
		if (State) {
			Robot.gearDelivery.openClawDoor();
			RobotConstants.batDoorState = true;
		} else {
			Robot.gearDelivery.closeClawDoor();
			RobotConstants.batDoorState = false;
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
