package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnlyRight extends CommandGroup{
	
	public AutonomousDeliverGearOnlyRight(){
		   /// Drive to Gear
//	    addSequential(new AutonomousCommandClaw(false)); // open claw door
//		addSequential(new AutonomousCommandLights(true)); // turn on vision lights
//	    addSequential(new AutonomousTrajectoryFollowerTwoPointGear(0, 0, 0, 86, 54, 72.07, true)); 
		addSequential(new AutonomousTrajectoryFollowerTwoPointFixMoveFile(2, true, 8));
       addSequential(new AutonomousRotateToCenter(1)); // Vision 
//       addSequential(new AutonomousTrajectoryFollowerTwoPointFixMove(0, 0, 0, 17, 0, 0, true));
		addSequential(new AutonomousTrajectoryFollowerTwoPointFixMoveFile(4, true, 3.5));
//	    addSequential(new AutonomousCommandClawDoor(true)); // close door 
	    addSequential(new AutonomousCommandClaw(true)); // open claw 
//	    addSequential(new AutonomousCommandLights(false)); // turn off vision lights
		}

}