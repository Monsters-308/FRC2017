package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDeliverGearShootBall extends CommandGroup{

	public AutonomousDeliverGearShootBall(){
		int startindex = 1;
	//	SmartDashboard.getNumber("Start position set", startindex);
        addSequential(new AutonomousCommandClaw(false)); // close claw
	   addSequential(new AutonomousCommandExtendClaw(false)); // extent   claw
     /// Drive to Gear
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser); 
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);
	      System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);
     addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
	   RobotConstants.autostartposition[1][0], // left
     RobotConstants.autostartposition[1][1], // center
       RobotConstants.autostartposition[1][2], // right
       true));  
       
 //      addSequential(new AutonomousTrajectoryFollowerTwoPoint(0,0,0,
//	   RobotConstants.autostartposition[startindex][0], // left
 //      RobotConstants.autostartposition[startindex][1], // center
//	   RobotConstants.autostartposition[startindex][2], // right
 //      true));  
       System.out.println("*************auto mode************ "  + RobotConstants.startPositionChooser);

  	   addSequential(new AutonomousCommandLights(true)); // turn on vision lights
  	   addSequential(new AutonomousWait(.1)); // wait
   	  addSequential(new VisionTurnToTarget(false)); // Vision 
 	   addSequential(new AutonomousRotateToCenter(1)); // Vision 
    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 13, 0, 0, true));
 // 	   addSequential(new AutonomousVisionControlledDriving()); // Vision
    addSequential(new AutonomousCommandClawDoor(true)); // Open door 
  	   addSequential(new AutonomousCommandClaw(true)); // open claw door
  	   addSequential(new AutonomousWait(1)); // wait
  	   addSequential(new AutonomousCommandLights(false)); // turn off vision lights
	   // Back up
  //    addSequential(new AutonomousTrajectoryFollowerTwoPoint(0, 0, 0, 24, 0, 0, false));
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


