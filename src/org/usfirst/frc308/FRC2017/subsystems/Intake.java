package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
// import edu.wpi.first.wpilibj.CANTalon;
import com.ctre.CANTalon;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Intake extends Subsystem {


    private final CANTalon ballmotor1 = RobotMap.intakeCANTalon_Ball_1;
    private final CANTalon ballmotor2 = RobotMap.intakeCANTalon_Ball_2;
    private final CANTalon gearmotor1 = RobotMap.intakeCANTalon_Gear_1;




    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        

        setDefaultCommand(new TeleopIntake());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

