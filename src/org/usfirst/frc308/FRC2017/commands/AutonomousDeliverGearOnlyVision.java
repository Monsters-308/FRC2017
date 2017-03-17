package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnlyVision extends CommandGroup{
	
	public AutonomousDeliverGearOnlyVision(){
		   /// Drive to Gear
	     addSequential(new AutonomousRotateToCenter(1)); // Vision 
      	}

}