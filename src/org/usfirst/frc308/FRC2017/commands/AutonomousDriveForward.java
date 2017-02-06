package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousDriveForward extends CommandGroup {

	public AutonomousDriveForward() {
		Robot.chassis.setRotatePID(0);
		addSequential(new AutonomousDistanceDrive(0.1, 2.0));
	}

}
