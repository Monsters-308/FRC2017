package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{
	
	public AutonomousDeliverGearShootBall(){
		addSequential(new AutonomousCommandClaw(true)); // close claw
		addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 84.7, 0, 0)); 
		addSequential(new AutonomousCommandClawDoor(false)); // open claw door
    	addSequential(new AutonomousCommandLights(true)); // turn on vision lights
  //  	addSequential(new AutonomousRotateToTarget ()); // Need to finish
  //  	addSequential(new VisionDriveStraightToGoal (.2)); // Need to finish
  //  	addSequential(new AutonomousRotateToTarget ()); // Need to finish
    	addSequential(new AutonomousCommandLights(false)); // turn on vision lights
    	addSequential(new AutonomousCommandClaw(false)); // open claw
    	addSequential(new AutonomousTrajectoryFollowerTwoPoint(84.7, 0, 0, 16.94, 135.5, 0)); 
    	addSequential(new AutonomousCommandIntake(RobotConstants.ballintakespeed)); // turn on intake
  		};
}


