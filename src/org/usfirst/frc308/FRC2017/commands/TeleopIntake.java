package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.debounce.robotics.first.*;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopIntake extends Command {


    public TeleopIntake() {


        requires(Robot.intake);


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setupIntake();
    	RobotConstants.intakeMode = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Debounce debounce = new Debounce(RobotConstants.initIntake);
    	boolean test = debounce.getValue();
    	boolean out = false;
    	/**
    	while(test){
    		if(out){
    			out = false;
    		}
    		else if(!out){
    			out = true;
    		}
    	}
    	while(!test){
    		if(out){
    			out = false;
    		}
    		else if(!out){
    			out = true;
    		}
    	}
    	**/
    	
    	//SmartDashboard.putBoolean("ALEX'S TEST", test);
    	//SmartDashboard.putBoolean("ALEX'S TEST2", out);
    	
    	SmartDashboard.putBoolean("Ball Intake ", RobotConstants.intakeMode);
       	SmartDashboard.putBoolean("Ball run ", Robot.oi.joystick1.getRawButton(RobotConstants.initIntake));
    	if(Robot.oi.joystick1.getRawButton(RobotConstants.initIntake)){   
    		if(RobotConstants.intakeMode == false){  
    			RobotConstants.intakeMode = true;
    			Robot.intake.setballmotor(RobotConstants.ballintakespeed);
    			
//    			turn off shooter mode
    			RobotConstants.processState = false;
    			RobotConstants.shooterMode = false;
    			Robot.shooter.setShootSpeed(0);
    			Robot.processBalls.runProcess(0);
    			}
    		else{
    			RobotConstants.intakeMode = false;
    			Robot.intake.setballmotor(0);
    		}  	
    		
    		
    		
    		
//	    		if(RobotConstants.intakeMode == false){
//	    			toggleIntake(true);
//	    			}
//	    		else{
//	    			toggleIntake(false);
//	    		}  
    		}
    
  
   /** 	if(Debounce.getInstance().Debounce(Robot.oi.joystick1, RobotConstants.initIntake, RobotConstants.last)){      		// MG fix missing brackets
    		if(RobotConstants.intakeMode == false){  // MG fix = should be ==
    			RobotConstants.intakeMode = true;
    			Robot.intake.setballmotor(RobotConstants.ballintakespeed);
    			}
    		else{
    			RobotConstants.intakeMode = false;
    			Robot.intake.setballmotor(0);
    		}  // MG fix missing brackets
    		}
    */	
    	
    	Robot.intake.setgearmotor(RobotConstants.gearintakespeed);
    }
    
    public void toggleIntake(boolean intakeState){
    	if(intakeState == false){  
			RobotConstants.intakeMode = true;
			Robot.intake.setballmotor(RobotConstants.ballintakespeed);
			}
		else{
			RobotConstants.intakeMode = false;
			Robot.intake.setballmotor(0);
		}  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
