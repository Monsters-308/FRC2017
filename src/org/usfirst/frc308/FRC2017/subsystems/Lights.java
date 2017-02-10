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

	//DigitalOutput gearExtendLights = RobotMap.gearExtendLights;
	DigitalOutput intakeLights = RobotMap.intakeLights;
	//DigitalOutput gearClawLights = RobotMap.gearClawLights;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Toggles lights for gear handler
	public void testIntakeLights(){
		intakeLights.set(true);
		System.out.println("Intake mode is on");
	}
	
	public void disableIntakeLights(){
		intakeLights.set(false);
		System.out.println("Intake mode is off");
	}
/**	public void gearExtendLights(boolean green, boolean off) {
		gearExtendLights.set(green);
		gearExtendLights.set(off);
	}

	public void gearClawLights(boolean green, boolean off) {
		gearClawLights.set(green);
		gearClawLights.set(off);
	}

	public void modeSwitchLights(boolean green, boolean off) {
		intakeLights.set(green);
		intakeLights.set(off);
	}
*/
	public void initDefaultCommand() {

		setDefaultCommand(new TeleopLights());
	}

	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());

}
