package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
// import edu.wpi.first.wpilibj.CANTalon;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Intake extends Subsystem {


    private final CANTalon ballmotor1 = RobotMap.intakeCANTalon_Ball_1;
   // private final CANTalon ballmotor2 = RobotMap.intakeCANTalon_Ball_2;
    private final CANTalon gearmotor1 = RobotMap.intakeCANTalon_Gear_1;


    public void initDefaultCommand() {
     setDefaultCommand(new TeleopIntake());	
    }
	public void setupIntake() {
    	ballmotor1.changeControlMode(TalonControlMode.PercentVbus); // MG changed from Voltage to PercentVbus
    	ballmotor1.setProfile(0);
    	
    	gearmotor1.changeControlMode(TalonControlMode.PercentVbus); // MG changed from Voltage to PercentVbus
    	gearmotor1.setProfile(0);
	}
   	/**
	 * sets the ball motor speed  -1 to +1
 	 */
	public void setballmotor(double bspeed) {
			ballmotor1.set(bspeed);
		 	SmartDashboard.putDouble("Ball Intake Speed", bspeed);
	}
	/**
	 * sets the ball motor speed  -1 to +1
 	 */
	public void setgearmotor(double gspeed) {
			gearmotor1.set(gspeed);
		   	SmartDashboard.putDouble("Gear Intake Speed", gspeed);
	}	
    		
}

