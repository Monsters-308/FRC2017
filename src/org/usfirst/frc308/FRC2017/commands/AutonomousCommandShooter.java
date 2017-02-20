package org.usfirst.frc308.FRC2017.commands;
import org.usfirst.frc308.FRC2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.RobotConstants;

public class AutonomousCommandShooter extends Command {

	

	public AutonomousCommandShooter() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
			Robot.shooter.setupShooter();
			Robot.shooter.setShootSpeed(RobotConstants.shootertargetspeed);
			RobotConstants.shooterMode = true;
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
