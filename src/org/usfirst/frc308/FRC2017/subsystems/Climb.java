package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
// import edu.wpi.first.wpilibj.CANTalon;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class Climb extends Subsystem {


    private final CANTalon climb = RobotMap.climbCANTalon;




    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
 
        setDefaultCommand(new TeleopClimb());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

