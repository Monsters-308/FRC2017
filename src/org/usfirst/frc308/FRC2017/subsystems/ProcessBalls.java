package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import com.ctre.CANTalon;
import org.usfirst.frc308.FRC2017.commands.*;
// import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ProcessBalls extends Subsystem {

    
    private final CANTalon processballmotor = RobotMap.processBallsCANTalon;

 


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        

        setDefaultCommand(new TeleopProcess());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

