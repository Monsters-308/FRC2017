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
        return false;
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
			if(isInCenter())
			{
				//Done
			}
			else{
				//Calculate off and readjusts
			}
			
		}
		
		
    }
    
    private boolean isInCenter(){
    	return false;
    }

}
