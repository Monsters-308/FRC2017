package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousVisionControlledDriving extends CommandGroup {
	
	public AutonomousVisionControlledDriving(){
		SmartDashboard.putDouble("results2",calc());
		addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, calc(), 0, 0, true));
	}

	
	public double calc(){
		if(NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0){
		double angle = RobotConstants.cameraFieldOfView / 2;
		double[] array = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("width", new double[0]);
		int index = MathUtils.getLargestIndex(array);
		double x = array[index] / 2;
		
		double angleInRadians = Math.toRadians(angle);
		double distance = x / Math.tan(angleInRadians);
		System.out.println(distance);
		
		double pxToInch = 1;
		double result = (distance / pxToInch); //10.5 is the diffrence between the sticker and the stick
		System.out.println(result);
		SmartDashboard.putDouble("results",result);
		return result;
		}
		else{
			return 0;
		}
	}
}
