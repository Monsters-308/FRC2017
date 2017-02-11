package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousRotateToTarget extends Command {

    boolean shouldRetry = false;

    public AutonomousRotateToTarget(boolean shouldRetry) {
        this.shouldRetry = shouldRetry;
        
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
    	//Make clear, that the robot is trying to aim the target
    	RobotConstants.isAutonomousAiming = true;
    	
    	//Created empty values for default --> optimization possible?
    	double[] defaultValue = new double[0];
		double[] defaultValue2 = new double[0];
		double[] defaultValue3 = new double[0];
		
		//Getting GRIP data from NetworkTable
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", defaultValue);
		double[] targets2 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", defaultValue2);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", defaultValue3);
    }

}
