package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
import edu.wpi.first.wpilibj.Compressor;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class Pneumatics extends Subsystem {


    private final Compressor compressor = RobotMap.pneumaticsCompressor;




    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
 

        setDefaultCommand(new TeleopPneumatics());



        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void stopCompressor(){
    	compressor.stop();
    }
    
    public void startCompressor(){
    	compressor.start();
    }
}

