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
 
public Timer lightTimer = new Timer();
public Timer cameralightTimer = new Timer();
private double lightcycle;
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

// Light processing for claw Lights       
    if (RobotConstants.clawOpenState == true ) {
    	if (RobotConstants.batDoorState == true) {
    		// Flash lights for door closed
    		if (lightTimer.get() >= (lightcycle/2)) { 
    			Robot.lights.setgearClawLights();;
    		}else 
    	    	Robot.lights.disablegearClawLights();
        } else  // lights solid
        	Robot.lights.setgearClawLights(); 
        // end shoot mode
     } else { // No lights on
    		Robot.lights.disablegearClawLights();;
        // end of all claw extend process
     } 
     
        
 // Light processing for gearExtendLights     
       if (RobotConstants.clawExtendState == true ) {
    	if (RobotConstants.batDoorState == true) {
    		// Flash lights for door closed
    		if (lightTimer.get() >= (lightcycle/2)) { 
    			Robot.lights.setgearExtendLights();;
    		}else 
    			Robot.lights.disablegearExtendLights();
        } else  // lights solid
        	Robot.lights.setgearExtendLights(); 
        // end shoot mode
     } else { // No lights on
    	 Robot.lights.disablegearExtendLights();
        // end of all gear extend process  
 
    	 
 // Light processing for camera lights  
    SmartDashboard.putBoolean("Camera Lights ", RobotConstants.cameralightState);
    if (Robot.oi.joystick1.getRawButton(RobotConstants.cameralight)) {
        if (cameralightTimer.get() == 0) {
            if (RobotConstants.cameralightState == false) {
                RobotConstants.cameralightState = true;
                Robot.lights.setcameraLights();
             } else { // If the camera light was on then toggle off
            	Robot.lights.disablecameraLights();
                RobotConstants.cameralightState = false;
             }
         } // end timer check
           // Start Timer to make sure the toggle happens only once
        cameralightTimer.start();
        }
    } //end camera light

    // If the cameralightTimer is greater than value then reset it
    // Note: Tune the value to better timing of when the button is pressed
    // and the next pressed
    if (cameralightTimer.get() >= RobotConstants.cameralightTimer_time )
    {
    	cameralightTimer.stop();
    	cameralightTimer.reset();
    }
        
    }  	// end execute 
    	
    

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
