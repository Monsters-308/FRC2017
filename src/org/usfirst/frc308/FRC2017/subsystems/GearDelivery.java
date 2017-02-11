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


    private final Solenoid extendClaw = RobotMap.gearDeliverySolenoid_1;
    private final Solenoid closeClaw = RobotMap.gearDeliverySolenoid_2;
    private final Solenoid clawDoor = RobotMap.gearDeliverySolenoid_3;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {


        setDefaultCommand(new TeleopGear());


    }

    public void extendClaw() {
        //
        // extends claw
        Robot.gearDelivery.extendClaw.set(true);
    }

    public void retractClaw() {
        //
        // retracts claw
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
        Robot.gearDelivery.clawDoor.set(true);
    }

    public void closeClawDoor() {
        //
        // closes passive assist doors
        Robot.gearDelivery.clawDoor.set(false);
    }

}

