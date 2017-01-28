package org.usfirst.frc308.FRC2017.subsystems;

import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

// import com.ctre.CANTalon.TalonControlMode;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {

    
    //private final CANTalon shootMotor1 = RobotMap.shooterCANTalon_1;
	private CANTalon shooterMotor1 = RobotMap.shooterCANTalon_1;



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new TeleopShooter());
      }
	/**
	 * sets up shooter with PID
	 */
	public void setupShooter() {
	shooterMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	shooterMotor1.changeControlMode(TalonControlMode.Speed);
	shooterMotor1.setProfile(0);
		shooterMotor1.setPID(
				RobotConstants.shooterPIDKp,
				RobotConstants.shooterPIDKi,
				RobotConstants.shooterPIDKd,
				RobotConstants.shooterPIDKf,
				RobotConstants.shooterPIDIZone, 
				RobotConstants.shooterPIDRampRate,
				0);  
	}
   	/**
	 * sets the shooter speed
	 * 
	 * 
	 */
	public void setShootSpeed(double speed) {
		//shootMotor1.set(speed);
		shooterMotor1.set(speed);
	}

}

