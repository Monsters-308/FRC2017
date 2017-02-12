package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousDriveForward extends CommandGroup {

    public AutonomousDriveForward() {
   	 SmartDashboard.putNumber("Drive forward", 0);
		System.out.println("Drive forward");
		addSequential(new AutonomousDistanceDrive(-0.6, 300)); // move 20" and 30% power
		SmartDashboard.putNumber("Turn", 0);
		System.out.println("Turn");
		addSequential(new AutonomousTimedRotate(.6,2)); //rotate 90 degrees 3 sec timeout
//		addSequential(new AutonomousRotate(90, 3)); //rotate 90 degrees 3 sec timeout
//		System.out.println("Drive forward 2");
		SmartDashboard.putNumber("Drive forward 2", 0);
		addSequential(new AutonomousDistanceDrive(0.6, 200)); // move 20" and 30% power
   }

}
