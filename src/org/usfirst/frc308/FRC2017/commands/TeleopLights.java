package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopLights extends Command {
// public Timer intakeLightTimer = new Timer();  /// remove 
public Timer lightTimer = new Timer();
private double lightcycle = 4.0;
    public TeleopLights() {

        requires(Robot.lights);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lightTimer.reset();
    	lightTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
// Set low or high light flash rate    	
    if (RobotConstants.processState == true)
       	lightcycle = RobotConstants.lightflashspeedlow;
    else
        lightcycle = RobotConstants.lightflashspeedfast;
    	   
// Reset Light Cycle when timer expires 
    if (lightTimer.get() >= lightcycle) {
         lightTimer.reset();	
     }	

// Light processing for shooter and intake
    if (RobotConstants.shooterMode == true || RobotConstants.intakeMode == true) {
    	if (RobotConstants.shooterMode == true) {
    		// Flash lights for shoot motor
    		if (lightTimer.get() >= (lightcycle/2)) 
    		    Robot.lights.setIntakeLights();
    	    else 
    	    	Robot.lights.disableIntakeLights();
        }else  //Must be intake mode light solid
    		Robot.lights.setIntakeLights(); 
        }
         // end shoot mode
    	else { // No lights on
 		Robot.lights.disableIntakeLights();
        // end of all intake/shoot/process
         }

// Light processing for clawExtendLights      
    if (RobotConstants.clawOpenState) 
    	    Robot.lights.setgearClawLights();
    	    else 
    	    Robot.lights.disablegearClawLights();
        
 // Light processing for gearExtendLights     
    if (RobotConstants.clawExtendState) 
    	    Robot.lights.setgearExtendLights();
    	    else 
    	    Robot.lights.disablegearExtendLights();
    
 // Light processing for camera lights     
    if (true) //mg
    	    Robot.lights.setcameraLights();
    	    else 
    	    Robot.lights.disablecameraLights();
        
        
    }  	// end execute 
    	
 /**  	if (RobotConstants.intakeMode == true) {
			Robot.lights.setIntakeLights();
			RobotConstants.intakeLightState = true;
		} else {
			if (RobotConstants.shooterMode == true) {
				intakeLightTimer.start();
				RobotConstants.intakeLightState = false;
				Robot.lights.disableIntakeLights();
				if (intakeLightTimer.get() > 500 && RobotConstants.intakeLightState == false) {
					Robot.lights.setIntakeLights();
					RobotConstants.intakeLightState = true;
					intakeLightTimer.reset();
				}else{
					if(intakeLightTimer.get() > 500 && RobotConstants.intakeLightState == true){
						Robot.lights.disableIntakeLights();
						RobotConstants.intakeLightState = false;
						intakeLightTimer.reset();
					}else{
						Robot.lights.disableIntakeLights();
					} 
				}
			} 
		} */

  


    

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
