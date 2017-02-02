package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class GearDelivery extends Subsystem {


    private final Solenoid claw1 = RobotMap.gearDeliverySolenoid_1;
    private final Solenoid claw2 = RobotMap.gearDeliverySolenoid_2;
    private final Solenoid claw3 = RobotMap.gearDeliverySolenoid_3; 


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {


     setDefaultCommand(new TeleopGear());


    }
    
    public void claw1_Open() {
		// 
		// open claw 1
		Robot.gearDelivery.claw1.set(true);
	}
 public void claw1_Close() {
		// 
		// close claw 1
		Robot.gearDelivery.claw1.set(false);
	}
 public void claw2_Open() {
		// 
		// open claw 2
		Robot.gearDelivery.claw2.set(true);
	}
 public void claw2_Close() {
		// 
		// close claw 2
		Robot.gearDelivery.claw2.set(false);
	}
 public void claw3_Open() {
		// 
		// open claw 3
		Robot.gearDelivery.claw3.set(true);
	}
public void claw3_Close() {
		// 
		// close claw 3
		Robot.gearDelivery.claw3.set(false);
	}
 
}

