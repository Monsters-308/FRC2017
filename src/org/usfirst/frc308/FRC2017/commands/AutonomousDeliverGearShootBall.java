package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{
	
	public AutonomousDeliverGearShootBall(){
	//	addSequential(new AutonomousCommandClaw(true)); // close claw
 //	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 30, 0, 0, true)); 
 //    	addSequential(new AutonomousCommandClawDoor(false)); // open claw door
  //  	addSequential(new AutonomousCommandLights(true)); // turn on vision lights
  //  	addSequential(new AutonomousWait(3)); // wait 
  //  	addSequential(new AutonomousRotateToTarget ()); // Need to finish
  //  	addSequential(new VisionDriveStraightToGoal (.2)); // Need to finish
  //  	addSequential(new AutonomousRotateToTarget ()); // Need to finish
  //  	addSequential(new AutonomousCommandLights(false)); // turn on vision lights
 //   	addSequential(new AutonomousCommandClaw(false)); // open claw
   // 	addSequential(new AutonomousWait(1)); // wait 
  //	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 30, 0, 0, false)); 
  	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, .5,30, 0, true)); 
  	 	addSequential(new AutonomousWait(3)); // wait 
    	addSequential(new AutonomousCommandShooter()); // turn on shooter
    	addSequential(new AutonomousWait(1)); // wait 
    	addSequential(new AutonomousCommandStartProcess()); 
    	addSequential(new AutonomousWait(2)); // wait 
    	addSequential(new AutonomousCommandClimbShake(true)); 
    	addSequential(new AutonomousWait(.5)); // wait 
    	addSequential(new AutonomousCommandClimbShake(false));
    	addSequential(new AutonomousWait(2)); // wait 
    	addSequential(new AutonomousCommandStopProcess()); 
	};
}


