package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class Lights extends Subsystem {
	
    DigitalOutput gearExtendLights = RobotMap.gearExtendLights;
    DigitalOutput intakeLights = RobotMap.intakeLights;
    DigitalOutput gearClawLights = RobotMap.gearClawLights;
    
    
    public void initDefaultCommand() {

        setDefaultCommand(new TeleopLights());
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
  ///  Intake Lights   
    public void setIntakeLights(){
    	intakeLights.set(true);
    }
    
    public void disableIntakeLights(){
    	intakeLights.set(false);
    }
///  Gear Claw Lights 
    public void setgearClawLights(){
    	gearClawLights.set(true);
    }
    
    public void disablegearClawLights(){
    	gearClawLights.set(false);
    }
  ///  Gear Claw Lights 
    public void setgearExtendLights(){
    	gearExtendLights.set(true);
    }
    
    public void disablegearExtendLights(){
    	gearExtendLights.set(false);
    }
  ///  Camera Lights 
    public void setcameraLights(){
    	gearExtendLights.set(true);
    }
    
    public void disablecameraLights(){
    	gearExtendLights.set(false);
    }

}
