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
	SmartDashboard.putNumber("Drive forward 134", 0);
//	addSequential(new AutonomousTrajectoryFollower());
//	addSequential(new AutonomousTrajectoryFollowerTwoPoint( 0,0,0,196,0,0));
		//addSequential(new AutonomousTrajectoryFollower());
		//addSequential(new AutonomousRotateToTarget());
//		addSequential(new RotateUntilGoalIsDetected(0.25));
//		addSequential(new VisionDriveStraightToGoal(.5));
//   	addSequential(new AutonomousDistanceDrive(0.5, 100)); // move 20" and 30% power
//		addSequential (new AutonomousWait(2));
//		SmartDashboard.putNumber("Turn", 0);
//		System.out.println("Turn");
//		addSequential (new AutonomousWait(2));
//		addSequential(new AutonomousTimedRotate(.6,2)); //rotate 90 degrees 3 sec timeout
//		addSequential(new AutonomousRotate(90, 3)); //rotate 90 degrees 3 sec timeout
//		System.out.println("Drive forward 2");
//		addSequential (new AutonomousWait(2));
//		SmartDashboard.putNumber("Drive forward 2", 0);
//		addSequential(new AutonomousDistanceDrive(0.6, 200)); // move 20" and 30% power
   }

}
