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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void setIntakeLights(){
    	intakeLights.set(true);
    }
    
    public void disableIntakeLights(){
    	intakeLights.set(false);
    }

    public void initDefaultCommand() {

        setDefaultCommand(new TeleopLights());
    }

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

}
