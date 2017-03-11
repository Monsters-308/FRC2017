package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousRotateToCenter extends Command{
	
	private double timeout;
	private Timer timer;
	private double turn = 0.0;
	
	public AutonomousRotateToCenter(double timeout){
		this.timeout = timeout;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		timer = new Timer();
		timer.start();
		Robot.chassis.setupDrive();
	}
	
	@Override
	protected void execute() {
		super.execute();
		Robot.chassis.arcade(0, turn);
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
				double centerX = RobotConstants.x / 2;
				double diffrence = array[index] - centerX;
				
				if(Math.abs(diffrence) < 2.5){
					//I'm really close to the target
					return true;
				}
				
				if(diffrence < 0){
					//Turn
					//It's negative
					turn =  -0.25;
					return false;
				}
				else {
					//It's positive
					//Turn into other direction
					turn = 0.25;
					return false;
				}
				
			}else{
				//I'm not able to see a goal
				timer.stop();
				return true;
			}
		
		}
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.chassis.setRotatePIDZero();
	}
	
	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}
	
	

}
