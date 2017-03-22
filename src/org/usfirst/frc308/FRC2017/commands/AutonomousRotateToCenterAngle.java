package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousRotateToCenterAngle extends Command{
	
	private double timeout;
	private Timer timer;
	private double turn = 0.0;
	private double 	setpointAngle = 0.0;
	
	public AutonomousRotateToCenterAngle(double timeout){
		this.timeout = timeout;
	}
	
	@Override
	protected void initialize() {
	     turn = 0.0;
//		super.initialize();
		timer = new Timer();
		timer.start();
		Robot.chassis.setupDrive();
	 }
	
	@Override
	protected void execute() {
//		super.execute();
//		Robot.chassis.arcade(0, turn);
	}

	@Override
	protected boolean isFinished() {
		if(timer.get() > timeout){
			//Done
			return true;
		}
		else{
			//If vision is able to see a target
			if (NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0) {
				
				double[] array = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
				int index = MathUtils.getLargestIndex(array);
				setpointAngle = (array[index] - 150.0) / 120.0 * (0.5 * RobotConstants.cameraFieldOfView);
				SmartDashboard.putNumber("SetpointAngle ",setpointAngle); 
				turn = 0.0;
				if(Math.abs(setpointAngle) < (2.5)){
					//I'm really close to the target
					RobotConstants.Vision_setpointAngle = 0;
					return true;
				} else {	
						RobotConstants.Vision_setpointAngle = setpointAngle;
						return false;
					}			
			}else{
				//I'm not able to see a goal
				SmartDashboard.putNumber("centerX", -1);
				timer.stop();
				return true;
			} // end length
		}  // end timer
	} // is finish 
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	

}
