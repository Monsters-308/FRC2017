package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class GearDelivery extends Subsystem {


    private final Solenoid extendClaw = RobotMap.gearDeliverySolenoid_1;
    private final Solenoid closeClaw = RobotMap.gearDeliverySolenoid_2;
    private final Solenoid clawDoor = RobotMap.gearDeliverySolenoid_3;
    DigitalInput inboardswitch = RobotMap.inboardswitch;
    boolean bool;


    // here. Call these from Commands.

    public void initDefaultCommand() {


        setDefaultCommand(new TeleopGear());


    }

    public void extendClaw() {
        //
        // extends extend
        Robot.gearDelivery.extendClaw.set(true);
    }

    public void retractClaw() {
        //
        // retracts  extend
        Robot.gearDelivery.extendClaw.set(false);
    }

    public void openClaw() {
        //
        // opens claw
        Robot.gearDelivery.closeClaw.set(true);
    }

    public void closeClaw() {
        //
        // closes claw
        Robot.gearDelivery.closeClaw.set(false);
    }

    public void openClawDoor() {
        //
        // opens passive assist doors
        Robot.gearDelivery.clawDoor.set(false);
    }

    public void closeClawDoor() {
        //
        // closes passive assist doors
        Robot.gearDelivery.clawDoor.set(true);
    }

    public boolean readinboardswitch() {
        //
        // gets value of inboard switch
    	bool = inboardswitch.get();
    	return bool;
    }
    
    
}

