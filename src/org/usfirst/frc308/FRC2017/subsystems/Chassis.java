package org.usfirst.frc308.FRC2017.subsystems;


import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import de.codeteddy.robotics.first.*;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 * 
 * @author Matteo
 * @author Autogenerated
 *
 */

public class Chassis extends PIDSubsystem {
	
	

	private final CANTalon left1 = RobotMap.chassisCANTalon_1;
	private final CANTalon left2 = RobotMap.chassisCANTalon_2;
	private final CANTalon left3 = RobotMap.chassisCANTalon_3;
	private final CANTalon right1 = RobotMap.chassisCANTalon_4;
	private final CANTalon right2 = RobotMap.chassisCANTalon_5;
	private final CANTalon right3 = RobotMap.chassisCANTalon_6;
	private final RobotDrive robotDrive6 = RobotMap.chassisRobotDrive6;
    private final ADXRS450_Gyro gyro = RobotMap.spiGyro_1;
    private final Solenoid claw1 = RobotMap.gearDeliverySolenoid_1;
    private final Solenoid claw2 = RobotMap.gearDeliverySolenoid_2;
    private final Solenoid claw3 = RobotMap.gearDeliverySolenoid_3; 
    
    
    private boolean turning = true;
    private double PIDOutput = 0.0;
    double IAccumulator = 0.0; // the sum of error over time
    double lastError = 0.0;
    //private double previousAngle = 0.0;
 	
 	//BuiltInAccelerometer accel;


 	// Initialize your subsystem here
 	public Chassis(){
 	    // NOT used - in code to setup PID thread
 		super("Chassis", 1.0, 0.0, 0.0);
 		setAbsoluteTolerance(0.2);
 		getPIDController().setContinuous(false); // forces a separate thread to control PID
 		gyro.calibrate();
 		// end of Not used section
 	

 		// Use these to get going:
 		// setSetpoint() -  Sets where the PID controller should move the system
 		//                  to
 		// enable() - Enables the PID controller.
 		//super("Drivetrain", RobotConstants.Kp, 0, RobotConstants.Kd);
//		setAbsoluteTolerance(RobotConstants.gyroPIDErrorTolerance);
//		getPIDController().setContinuous(true);
//		getPIDController().setInputRange(-180, 180);
		//LiveWindow.addActuator("Drivetrain", "PIDSubsystem Controller", getPIDController());
//		getPIDController().setOutputRange(-1.0, 1.0);
		//accel = new BuiltInAccelerometer();
		
  	}
 


 	public void initDefaultCommand() {
 		// Set the default command for a subsystem here.
 		setDefaultCommand(new TeleopDrive());
 	}
 
 
 	//Chassis setup
 	public void setupDrive() {
 		left1.changeControlMode(TalonControlMode.PercentVbus);
 		left2.changeControlMode(TalonControlMode.PercentVbus);
 		left3.changeControlMode(TalonControlMode.PercentVbus);
 		right1.changeControlMode(TalonControlMode.PercentVbus);
		right2.changeControlMode(TalonControlMode.PercentVbus);
		right3.changeControlMode(TalonControlMode.PercentVbus);		
 	
		/**
 	 * 	left2.changeControlMode(TalonControlMode.Follower);
 		left2.set(1);
 		left3.changeControlMode(TalonControlMode.Follower);
 		left3.set(1);
 		right1.changeControlMode(TalonControlMode.PercentVbus);
 		right2.changeControlMode(TalonControlMode.Follower);
 		right2.set(4);
 		right3.changeControlMode(TalonControlMode.Follower);
 		right3.set(4);
 		**/
 	}
 	

 	public void arcadeDrive(double forward, double turn) {
 		//Do we use them?
 		//robotDrive6.setSafetyEnabled(RobotConstants.safetyEnabled);
 		//robotDrive6.setExpiration(RobotConstants.arcadeExpiration);
 		//robotDrive6.setSensitivity(RobotConstants.arcadeSensitivity);
 		//robotDrive6.setMaxOutput(RobotConstants.arcadeMaxOutput);
 		if (RobotConstants.enablePID == true )  { // use PID process
 	
 	   //IF user stops driving with the joystick 		
 		if(turn == 0.0){
 			if(turning == true){ //Code is called first time, when we stopped turning
 				
 				//Reset PIDOutput to zero
 				PIDOutput = 0.0;
 				
 				//Reset gyro to 0
 				gyro.reset();
 				
 				//Set turning to false, because we are not turning any more
 				turning = false;
 				
 				//Drive
 				robotDrive6.arcadeDrive(forward, turn);
 			}
 			else{ //If this isn't the first time
 				  // Robot is moving straight
 				
 				//Calculate PID 
 				turn = calcPID();
 				robotDrive6.arcadeDrive(forward, turn);
 			}
 			
 		}
 		//ELSE the user is still commanding
 		// User is commanding a turn
 		else if(turn != 0.0){
 			//Reset angle
 			turning = true;
 			IAccumulator = 0;  
 			//Drive normal driving
 			robotDrive6.arcadeDrive(forward, turn);
 		}
 		}
 		// ELSE PID is Off 
 	    // use standard arcadeDrive
 		else { 
 		robotDrive6.arcadeDrive(forward, turn);
 		}
 	}
 	
 	public double calcPID(){
 		
 		//Do all the calculations
 		double error = 0.0 - gyro.getAngle();
 		
 		if (error < -180.0) {
			while (error < -180.0) {
				error += 360.0;
			}
		} else if (error > 180.0) {
			while (error > 180.0) {
				error -= 360.0;
			}
		}
 		
 		if (Math.abs(error) < RobotConstants.iZone
				&& (RobotConstants.isTrajectory || error * lastError > 0)) {
			IAccumulator += error;
			if (RobotConstants.Kp * error + RobotConstants.Ki * IAccumulator > RobotConstants.maximumIZoneSpeed) {
				IAccumulator = (RobotConstants.maximumIZoneSpeed - RobotConstants.Kp * error) / RobotConstants.Ki;
			} else if (RobotConstants.Kp * error
					+ RobotConstants.Ki * IAccumulator < -RobotConstants.maximumIZoneSpeed) {
				IAccumulator = (-RobotConstants.maximumIZoneSpeed - RobotConstants.Kp * error) / RobotConstants.Ki;
			}
		} else {
			IAccumulator = 0;
		}
 	return error + RobotConstants.Ki * IAccumulator;
 	}
 	
 	public double deadZone(double input){
 		double d = Math.abs(input);
 		if(d < RobotConstants.deadzone){
 			return 0.0;
 		}
 		return input;
 	}
 
 
 protected double returnPIDInput() {
     // NOT Used - in code to create PID thread 
	 // Return your input value for the PID loop
     return gyro.getAngle();
 }

 protected void usePIDOutput(double output) {
     // NOT Used - in code to create PID thread 
     // Use output to drive your system, like a motor
 }
 
 
 public void claw1_Open() {
		// 
		// open claw 1
		Robot.chassis.claw1.set(true);
	}
 public void claw1_Close() {
		// 
		// close claw 1
		Robot.chassis.claw1.set(false);
	}
 public void claw2_Open() {
		// 
		// open claw 2
		Robot.chassis.claw2.set(true);
	}
 public void claw2_Close() {
		// 
		// close claw 2
		Robot.chassis.claw2.set(false);
	}
 public void claw3_Open() {
		// 
		// open claw 3
		Robot.chassis.claw3.set(true);
	}
public void claw3_Close() {
		// 
		// close claw 3
		Robot.chassis.claw3.set(false);
	}
 
}
