package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class GearDelivery extends Subsystem {


    private final Solenoid solenoid1 = RobotMap.gearDeliverySolenoid_1;
    private final Solenoid solenoid2 = RobotMap.gearDeliverySolenoid_2;




    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {


        setDefaultCommand(new TeleopGear());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

