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
		
	if (NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0) {
			//Check more
			int indexBiggest = MathUtils.getLargestIndex(NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]));
			double[] array = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
			double centerX = RobotConstants.x / 2;
			if(MathUtils.getDiffrence(centerX, array[indexBiggest]) < RobotConstants.visionTolerance){
				
				//Done;
				return true;
			}else{
				//Do adjustments later
				double diffrence = array[indexBiggest] - centerX;
				if(diffrence > 0){
					rot = 0.20;
				}else{
					rot = -0.20;
				}
				return false;
			}
		}
		else{
			//Done if there is no target
			return true;
		}
		
	} 
}
