package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class VisionProcessing extends Command {


	public VisionProcessing() {


		requires(Robot.vision);

	}

	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
		double[] targets2 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", new double[0]);
		if(targets.length > 0 && targets2.length > 0){
			SmartDashboard.putNumber("cernterX", targets[0]);
			SmartDashboard.putNumber("cernterY", targets2[0]);
		} else {
			SmartDashboard.putNumber("cernterX", -1);
			SmartDashboard.putNumber("cernterY", -1);
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
