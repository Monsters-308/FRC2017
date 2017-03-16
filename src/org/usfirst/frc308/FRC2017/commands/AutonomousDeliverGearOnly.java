package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnly extends CommandGroup{
	
	public AutonomousDeliverGearOnly(){
		   /// Drive to Gear
	   addSequential(new AutonomousTrajectoryFollowerTwoPointGear(true));  
        addSequential(new AutonomousRotateToCenter(1)); // Vision 
        addSequential(new AutonomousTrajectoryFollowerTwoPointFixMove(0, 0, 0, 17, 0, 0, true));
	    addSequential(new AutonomousCommandClawDoor(true)); // close door 
	    addSequential(new AutonomousCommandClaw(true)); // open claw door
	    addSequential(new AutonomousCommandLights(false)); // turn off vision lights
		}

}