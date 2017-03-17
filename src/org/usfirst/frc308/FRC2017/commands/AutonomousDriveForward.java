package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousDriveForward extends CommandGroup {

    public AutonomousDriveForward() {
 	   addSequential(new AutonomousTrajectoryFollowerTwoPointGear(0,0,0, 100, 0, 0, true));
 	  }

}
