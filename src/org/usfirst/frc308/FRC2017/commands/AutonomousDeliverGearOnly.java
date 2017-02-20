package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearOnly extends CommandGroup{
	
	public AutonomousDeliverGearOnly(){
		SmartDashboard.putNumber("Trajectory", 0);
		System.out.println("Drive trajectory");
		//addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 40, 40, 90));
		addSequential(new AutonomousTrajectoryFollower());
		System.out.println("commadGroup");
	}

}