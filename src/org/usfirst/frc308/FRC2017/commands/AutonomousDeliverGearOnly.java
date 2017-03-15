package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnly extends CommandGroup{
	
	public AutonomousDeliverGearOnly(){
	      addSequential(new AutonomousCommandClaw(false)); // close claw
		  addSequential(new AutonomousCommandExtendClaw(false)); // extent   claw
	     /// Drive to Gear
	//  addSequential(new AutonomousTrajectoryFollowerTwoPointGear(true));  
	  	addSequential(new VisionTurnToTarget(false)); // Vision 
	//	 addSequential(new AutonomousRotateToCenter(1)); // Vision 
   //   addSequential(new AutonomousTrajectoryFollowerTwoPointFixMove(0, 0, 0, 14, 0, 0, true));
	    addSequential(new AutonomousCommandClawDoor(true)); // Open door 
	 	addSequential(new AutonomousCommandClaw(true)); // open claw door
	 	addSequential(new AutonomousWait(1)); // wait
	    addSequential(new AutonomousCommandLights(false)); // turn off vision lights
		}

}