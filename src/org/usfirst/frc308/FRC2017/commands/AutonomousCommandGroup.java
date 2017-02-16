package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup{
	
	public AutonomousCommandGroup(double x0, double y0, double d0, double x1, double y1, double d1){
		
		addSequential(new AutonomousTrajectoryFollowerTwoPoint(x0, y0, d0, x1, y1, d1));
	}

}
