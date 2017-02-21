package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousRotateToTarget extends Command {

	private double rot;
	
	public AutonomousRotateToTarget(){
		rot = 0.35;
		requires(Robot.chassis);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		Robot.chassis.setupDrive();
	}
	
	@Override
	protected void execute() {
		super.execute();
		Robot.chassis.arcadeDrive(0, rot);
	}

	@Override
	protected void end() {
		super.end();
		Robot.chassis.arcadeDrive(0, rot);
	}
	
	
	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

	@Override
	protected boolean isFinished() {
		//Enhance this method
		if (NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0) {
			double[] array = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
			int index = MathUtils.getLargestIndex(array);
			double centerX = RobotConstants.x / 2;
			if(MathUtils.getDiffrence(centerX, array[index]) < RobotConstants.visionTolerance){
				return true;
			}
			else{
				double diffrence = MathUtils.getDiffrence(centerX, array[index]);
				double correction = diffrence - (RobotConstants.x / 2); 
				rot = correction / 200;
				
			}
			
		}
		return false;
	}
}
