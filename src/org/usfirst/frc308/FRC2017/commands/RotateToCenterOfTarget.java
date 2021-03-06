package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RotateToCenterOfTarget extends Command{
	
	private double rot;
	
	public RotateToCenterOfTarget(double rotation){
		//Try it with a really small value
		rot = rotation;
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
				//Maybe we need to adjust the rotation value here ...
			}
			
		}
		return false;
	}
}
