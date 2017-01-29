package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

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
        
     // Set the default command for a subsystem here.
     setDefaultCommand(new TeleopProcess());

    }
    
  //Sets processor talon to voltage mode
    public void setupProcess(){
    	processballmotor.changeControlMode(TalonControlMode.PercentVbus); // MG changed from Voltage to PercentVbus
    	processballmotor.setProfile(0);
    }
 // Activates ball processor while shooting  
    public void runProcess(double processSpeed){
    	processballmotor.set(processSpeed);
    }
	public void sleep(int i) {
		// TODO Auto-generated method stub
		
	}
}

