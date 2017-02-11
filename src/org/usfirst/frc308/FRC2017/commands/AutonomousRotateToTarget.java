package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import org.usfirst.frc308.FRC2017.utils.MathUtils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousRotateToTarget extends Command {

    boolean shouldRetry = false;
    boolean isFinished = false;
    
    private Timer timer;

    public AutonomousRotateToTarget() {
        
        //Is needed to turn the robot to the right direction
        requires(Robot.chassis);
    }
    
    @Override
    protected void execute() {
    	super.execute();
    	
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    @Override
    protected void end() {
        super.end();
        RobotConstants.isAutonomousAiming = false;
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }
    
    private void aim(){
    	isFinished = false;
    	//Make clear, that the robot is trying to aim the target
    	RobotConstants.isAutonomousAiming = true;
		
		//Getting GRIP data from NetworkTable
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
		double[] targets2 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", new double[0]);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", new double[0]);
		
		//Find the biggest target
		int biggestTarget = MathUtils.getLargestIndex(targets3);
		
		//Create a new timer
		timer = new Timer();
		
		//No target can be seen
		if(targets.length == 0){
			//turn until you can see the target
			
			
		}
		else if(targets.length > 0){
			//Target can be seen now
			
			//Check if there is a biggest target
			if(biggestTarget != -1){
				
				//Get X and Y values
				double x = targets[biggestTarget];
				double y = targets[biggestTarget];
				
				if(isInCenter(x, y))
				{
					//Done with rotating to the target
					isFinished = true;
				}
				else{
					//Calculate off and readjusts
					
					
				}
				
			}
			else{
				//Retry it
				biggestTarget = MathUtils.getLargestIndex(targets3);
			}

		}
		
		//If the robot didn't finished
		if(isFinished == false){
			//Restart
			aim();
		}
		else{
			//Your are now done with the whole process
			//Enjoy it and do nothing
		}
		
    }
    
    private boolean isInCenter(double x, double y){
    	
    	//Get the coordinates of the center of the camera
    	int centerX = RobotConstants.x / 2;
    	int centerY = RobotConstants.y / 2;
    	
    	//If the difference in x is smaller than the allowed tolerance
    	if(MathUtils.getDiffrence(centerX, x) < RobotConstants.visionTolerance){
    		//If the difference in y is smaller than the allowed tolerance
    		
    		
    		return true;
    		/**
    		if(MathUtils.getDiffrence(centerY, y) < RobotConstants.visionTolerance){
    			return true;
    		}
    		**/
    	}
    	
    	return false;
    }

}
