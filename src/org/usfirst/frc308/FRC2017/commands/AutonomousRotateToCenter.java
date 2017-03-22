package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousRotateToCenter extends Command{
	
	private double timeout;
	private Timer timer;
	private double turn = 0.0;
	
	public AutonomousRotateToCenter(double timeout){
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
			turn = 0.0;
			Robot.chassis.resetEncoders();
			Robot.chassis.resetGyro();
			Robot.chassis.setRotatePIDZero();
			Robot.chassis.brakemode(true);
		    Robot.chassis.arcade(0,0);
			return true;
		}
		else{
			//If vision is able to see a target
			if (NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]).length > 0) {
				
				double[] array = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
				int index = MathUtils.getLargestIndex(array);
				double centerX = (RobotConstants.x / 2) - RobotConstants.visionoffsetcal;
				double diffrence = array[index] - (centerX );  
				SmartDashboard.putNumber(" Vision diffrence",diffrence);
				SmartDashboard.putNumber(" Vision centerX",centerX); 
				turn = 0.0;
				if(Math.abs(diffrence) < (2.5)){
					//I'm really close to the target
					Robot.chassis.arcade(0,0);
					Robot.chassis.resetEncoders();
					return true;
				}
				
				if(diffrence < 0){
					//Turn
					//It's negative
					System.out.println("+++++++++++++++++++++ diffrence " + diffrence);
					turn =  RobotConstants.Vision_neg_rotate;
					System.out.println("+++++++++++++++++++++ turn " + turn);
					Robot.chassis.arcade(0, turn);
					Robot.chassis.resetEncoders();
					return false;
				}
				else {
					//It's positive
					//Turn into other direction
					turn = RobotConstants.Vision_pos_rotate;
					Robot.chassis.arcade(0, turn);
					Robot.chassis.resetEncoders();
					return false;
				}  //  diff loop
				
			}else{
				//I'm not able to see a goal
				SmartDashboard.putNumber("centerX", -1);
				Robot.chassis.brakemode(true);
				Robot.chassis.arcade(0,0);
				Robot.chassis.resetEncoders();
				Robot.chassis.arcade(0,0);
				timer.stop();
				return true;
			} // end length
		}  // end timer
	} // is finish 
	
	@Override
	protected void end() {
		Robot.chassis.arcade(0,0);
		Robot.chassis.resetEncoders();
	//	super.end();
	//	Robot.chassis.setRotatePIDZero();
	}
	
	@Override
	protected void interrupted() {
	//	super.interrupted();
		Robot.chassis.arcade(0,0);
		Robot.chassis.resetEncoders();
		end();
	}
	
	

}
