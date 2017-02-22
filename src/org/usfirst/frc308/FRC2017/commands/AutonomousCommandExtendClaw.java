package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandExtendClaw extends Command {

	boolean State;

	public AutonomousCommandExtendClaw(boolean state) {
		requires(Robot.gearDelivery);
		State = state;
	}

	@Override
	protected void initialize() {
		if (State) {
			Robot.gearDelivery.extendClaw();
			RobotConstants.clawExtendState = true;
		} else {
			Robot.gearDelivery.retractClaw();
			RobotConstants.clawExtendState = false;
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
