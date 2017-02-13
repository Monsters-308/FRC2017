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
    	aim();
    	
    }
    
    @Override
    protected void initialize() {
    	super.initialize();
    	Robot.chassis.setupDrive();
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
    	//Make sure, that aim() is called
    	System.out.println("Aim() got called");
    	
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
		//timer = new Timer();
		
		//No target can be seen
		if(targets.length == 0){
			//turn until you can see the target
			
			//Suggestion: Turn always 10°, to make sure that we don't miss anything
			double suggestedAngle = 10.0; //Amount in degrees
			
			//Check if this get called
			Robot.chassis.arcadeDrive(0, MathUtils.degToDriveDouble(suggestedAngle));
			
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
					double diffrence = x - 160.0;
					
					double angleToTurn = MathUtils.pxToDeg(diffrence);
					
					Robot.chassis.arcadeDrive(0, MathUtils.degToDriveDouble(angleToTurn));
					
					
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
			//TODO This might create some problems
			aim();
		}
		else{
			//Your are now done with the whole process
			//Enjoy it and do nothing
		}
		
    }
    
    
    /**
     * 
     * @author Alexander Kaschta
     * @param x
     * @param y
     * @return if the coordinates are in the center of the camera
     */
    private boolean isInCenter(double x, double y){
    	
    	//Get the coordinates of the center of the camera
    	int centerX = RobotConstants.x / 2;
    	
    	//If the difference in x is smaller than the allowed tolerance
    	if(MathUtils.getDiffrence(centerX, x) < RobotConstants.visionTolerance){
    		//Don't care about y-axis
    		return true;
    	}
    	
    	return false;
    }

}
