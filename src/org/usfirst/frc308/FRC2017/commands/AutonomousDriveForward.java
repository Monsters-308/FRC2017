package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousDriveForward extends CommandGroup {

    public AutonomousDriveForward() {
		System.out.println("Drive forward");
		addSequential(new AutonomousDistanceDrive(0.3, 20)); // move 20" and 30% power
		System.out.println("Turn");
		addSequential(new AutonomousRotate(90, 3)); //rotate 90 degrees 3 sec timeout
		System.out.println("Drive forward 2");
		addSequential(new AutonomousDistanceDrive(0.3, 20)); // move 20" and 30% power
    }

}
