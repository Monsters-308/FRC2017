package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandStopProcess extends Command {

	

	public AutonomousCommandStopProcess() {
		requires(Robot.intake);
		requires(Robot.processBalls);
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		    Robot.intake.setballmotor(0);
			RobotConstants.intakeMode = false;
			Robot.processBalls.runProcess(0);
			RobotConstants.processState = false;
			Robot.shooter.setShootSpeed(0);
			RobotConstants.shooterMode = false;
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
