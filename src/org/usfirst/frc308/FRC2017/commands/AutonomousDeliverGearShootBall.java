package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{
	
	public AutonomousDeliverGearShootBall(){
		addSequential(new AutonomousCommandClaw(true)); // close claw
		addSequential(new AutonomousCommandExtendClaw(false)); // Extend  claw
		addSequential(new AutonomousCommandClawDoor(true)); // Open door 
		
//	  addSequential(new AutonomousTrajectoryFollowerThreePoint(0, 0, 0, -36,0 ,0, -72, 0, 0, true)); 
	  addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0, 0,  97, 45, 60, true)); 
//  addSequential(new AutonomousTrajectoryFollowerThreePoint(-100,-46, 1, -17, -17,40, 0, 0, 75, true)); 
 // 	 addSequential(new AutonomousWait(2)); // wait
 //   addSequential(new AutonomousTrajectoryFollowerThreePoint (-4, -2, 0, -2,-2,45, 0, 0, 0, true));
 //
     	addSequential(new AutonomousCommandClaw(false)); // open claw door
     	 addSequential(new AutonomousWait(2)); // wait 
//	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 45, 0, 0, false));
//	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 1, 5, 0, true));
     	addSequential(new AutonomousCommandLights(true)); // turn on vision lights
    	addSequential(new AutonomousWait(2)); // wait 
  //   	addSequential(new AutonomousRotateToTarget ()); // Need to finish
  //  	addSequential(new VisionDriveStraightToGoal (.2)); // Need to finish
  //  	addSequential(new AutonomousRotateToTarget ()); // Need to finish
  	addSequential(new AutonomousCommandLights(false)); // turn on vision lights
 	addSequential(new AutonomousCommandClaw(false)); // open claw
   	addSequential(new AutonomousWait(1)); // wait 
 	//    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 1,20,90, true)); 
  //	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, .5,30, 0, true)); 
  //	 	addSequential(new AutonomousWait(3)); // wait 
   	addSequential(new AutonomousCommandShooter()); // turn on shooter
   	addParallel(new AutonomousWait(1)); // wait 
   	addParallel(new AutonomousWait(2)); // wait 
   	addParallel(new AutonomousCommandClimbShake(true)); 
   	addParallel(new AutonomousWait(.5)); // wait 
   	addParallel(new AutonomousCommandClimbShake(false));
  	addSequential(new AutonomousCommandStopProcess()); 
	};
}


