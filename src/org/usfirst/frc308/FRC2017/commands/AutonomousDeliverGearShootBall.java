package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{

	public AutonomousDeliverGearShootBall(){
        addSequential(new AutonomousCommandClaw(false)); // close claw
	   addSequential(new AutonomousCommandExtendClaw(false)); // extent   claw
       addSequential(new AutonomousCommandClawDoor(true)); // Open door 
	   /// Drive to Gear
       addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
   	   RobotConstants.autostartposition[0][0], // left
       RobotConstants.autostartposition[0][1], // center
 	   RobotConstants.autostartposition[0][2], // right
        true));  
       
  //    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
	//   RobotConstants.autostartposition[RobotConstants.startPositionChooser][0], // left
  //     RobotConstants.autostartposition[RobotConstants.startPositionChooser][1], // center
//	   RobotConstants.autostartposition[RobotConstants.startPositionChooser][2], // right
  //     true));  
  	   addSequential(new AutonomousCommandLights(true)); // turn on vision lights
  	   addSequential(new AutonomousWait(.1)); // wait
 	   addSequential(new AutonomousRotateToCenter(2)); // Vision 
 	   addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 18, 0, 0, true));
 // 	   addSequential(new AutonomousVisionControlledDriving()); // Vision
  	   addSequential(new AutonomousCommandClaw(true)); // open claw door
  	   addSequential(new AutonomousWait(1)); // wait
  	   addSequential(new AutonomousCommandLights(false)); // turn off vision lights
	   // Back up
 //      addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 24, 0, 0, false));
       // Drive to Boiler
   //    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
   //    RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][0],
   //    RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][1],
   //    RobotConstants.autoairshipposition[RobotConstants.boilPositionChooser][RobotConstants.startPositionChooser][2],
  //     true)); 
       // Shoot balls 
       addSequential(new AutonomousCommandShooter()); // turn on shooter
       addParallel(new AutonomousWait(1)); // wait 
       addParallel(new AutonomousCommandClimbShake(true));
       addParallel(new AutonomousWait(.5)); // wait 
   	   addParallel(new AutonomousCommandClimbShake(true)); 
       addParallel(new AutonomousWait(.5)); // wait 
   	   addParallel(new AutonomousCommandClimbShake(false));
       addParallel(new AutonomousWait(.5)); // wait      
  	   addSequential(new AutonomousCommandStopProcess()); 
	};
}


