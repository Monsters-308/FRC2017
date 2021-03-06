package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnlyCenter extends CommandGroup{
	
	public AutonomousDeliverGearOnlyCenter(){
		   /// Drive to Gear
//	    addSequential(new AutonomousCommandClaw(false)); // close claw door
		addSequential(new AutonomousCommandLights(true)); // turn on vision lights
//   	addSequential(new AutonomousTrajectoryFollowerTwoPointGear(0, 0, 0, 53, 0, 0, true));  
		addSequential(new AutonomousTrajectoryFollowerTwoPointFixMoveFile(1, true, 5));
       addSequential(new AutonomousRotateToCenter(1)); // Vision 
//      addSequential(new AutonomousTrajectoryFollowerTwoPointFixMove(0, 0, 0, 15, 0, 0, true));
		addSequential(new AutonomousTrajectoryFollowerTwoPointFixMoveFile(4, true, 3.5));
//	    addSequential(new AutonomousCommandClawDoor(true)); // close door 
	    addSequential(new AutonomousCommandClaw(true)); // open claw 
//	    addSequential(new AutonomousCommandLights(false)); // turn off vision lights
		}

}