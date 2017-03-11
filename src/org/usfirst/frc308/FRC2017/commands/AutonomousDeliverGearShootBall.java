package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{

	public AutonomousDeliverGearShootBall(){
	   addSequential(new AutonomousCommandClaw(true)); // close claw
	   addSequential(new AutonomousCommandExtendClaw(false)); // Extend  claw
	   addSequential(new AutonomousCommandClawDoor(true)); // Open door 
	   /// Drive to Gear
      addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
	   RobotConstants.autostartposition[RobotConstants.startPositionChooser][0], // left
       RobotConstants.autostartposition[RobotConstants.startPositionChooser][1], // center
	   RobotConstants.autostartposition[RobotConstants.startPositionChooser][2], // right
       true));  
  	   addSequential(new AutonomousCommandLights(true)); // turn on vision lights
       addSequential(new AutonomousWait(.5)); // wait
  	   addSequential(new AutonomousCommandClaw(false)); // open claw door
  	  	addSequential(new AutonomousCommandLights(false)); // turn off vision lights
	   addSequential(new AutonomousWait(1.0)); // wait 
	   // Back up
       addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 24, 0, 0, false));
       addSequential(new AutonomousWait(2)); // wait
       // Drive to Boiler
       addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
       RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][0],
       RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][1],
       RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][2],
       true)); 
       	addSequential(new AutonomousWait(2)); // wait 
  	addSequential(new AutonomousCommandLights(false)); // turn off vision lights
 	addSequential(new AutonomousCommandClaw(false)); // open claw
   	addSequential(new AutonomousWait(1)); // wait 
 	//    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 1,20,90, true)); 
  //	    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, .5,30, 0, true)); 
  //	 	addSequential(new AutonomousWait(3)); // wait 
   	addSequential(new AutonomousCommandShooter()); // turn on shooter
   	addParallel(new AutonomousWait(1)); // wait 
   	addParallel(new AutonomousWait(2)); // wait 
   	addParallel(new AutonomousCommandClimbShake(true)); 
   	addParallel(new AutonomousWait(.5)); // wait 
   	addParallel(new AutonomousCommandClimbShake(false));
  	addSequential(new AutonomousCommandStopProcess()); 
	};
}


