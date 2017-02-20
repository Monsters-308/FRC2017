package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandStartProcess extends Command {

	

	public AutonomousCommandStartProcess() {
		requires(Robot.intake);
		requires(Robot.processBalls);
	}

	@Override
	protected void initialize() {
		    Robot.intake.setupIntake();
			Robot.intake.setballmotor(RobotConstants.ballintakespeed);
			RobotConstants.intakeMode = true;
			Robot.processBalls.setupProcess();
			Robot.processBalls.runProcess(RobotConstants.processSpeed);
			RobotConstants.processState = true;
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
