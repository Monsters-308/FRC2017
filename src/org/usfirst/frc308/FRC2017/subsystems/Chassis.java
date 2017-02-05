package org.usfirst.frc308.FRC2017.subsystems;


import org.usfirst.frc308.FRC2017.RobotMap;
import org.usfirst.frc308.FRC2017.commands.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import de.codeteddy.robotics.first.*;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private final AdvancedRobotDrive robotDrive6 = RobotMap.chassisRobotDrive6;
    private final ADXRS450_Gyro gyro = RobotMap.spiGyro_1;
    Timer setPointTimer =  new Timer();
	
    private boolean turning = true;
	    
	// PID Stuff
	double IAccumulator = 0.0; // the sum of error over time
	double lastError = 0.0;
	double error = 0;
	private final Timer settledTimer = new Timer();
    private double PIDOutput = 0.0;
    private double settledPos = 0;
	BuiltInAccelerometer accel;

  
 
 	public Chassis(){
		super("Drivetrain", RobotConstants.Kp, 0, RobotConstants.Kd);
		setAbsoluteTolerance(RobotConstants.gyroPIDErrorTolerance);
		getPIDController().setContinuous(true);
		getPIDController().setInputRange(-180, 180);
		getPIDController().setOutputRange(-1.0, 1.0);	
		accel = new BuiltInAccelerometer();
  		gyro.calibrate();
   	}
 
 	public void initDefaultCommand() {
 		// Set the default command for a subsystem here.
 		setDefaultCommand(new TeleopDrive());
 	}
 
 
 	//Chassis setup
 	public void setupDrive() {
 		left1.changeControlMode(TalonControlMode.PercentVbus);
 		left1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
 		left2.changeControlMode(TalonControlMode.PercentVbus);
 		left3.changeControlMode(TalonControlMode.PercentVbus);
 		right1.changeControlMode(TalonControlMode.PercentVbus);
		right2.changeControlMode(TalonControlMode.PercentVbus);
		right3.changeControlMode(TalonControlMode.PercentVbus);	
		gyro.reset();
		getPIDController().setSetpoint(0); // make setpoint current angle
		getPIDController().enable();
		IAccumulator = 0; // reset accumulator
		settledTimer.start();
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
 				setPointTimer.start();
 				RobotConstants.gyroPIDOutput = 0.0; //Reset PIDOutput to zero
 				turning = false; 	//Set turning to false, because we are not turning any more
 				System.out.println("first tie stop");	
 			} else if (setPointTimer.get() != 0) {//If this isn't the first time
 				if (setPointTimer.get() >= 3.0){  // Robot is moving straight
  				   enablePID();
				   gyro.reset();
				   getPIDController().setSetpoint(gyro.getAngle());
				   setPointTimer.stop();
				   setPointTimer.reset();
				   double gyrotemp = gyro.getAngle();
 				   System.out.println("drive straight");
 				  System.out.println(setPointTimer.get());
 			       System.out.println(gyrotemp);	
 				   System.out.println(turn);	
 			    }
 			} else { // after initializing
				turn = RobotConstants.gyroPIDOutput;
 		    }
 		    //ELSE the user is still commanding
 		    // User is commanding a turn
 		}	else if(turn != 0.0){
 			disablePID();
			setPointTimer.stop();
			setPointTimer.reset();
			turning = true; 		    
 			//Reset angle
 			System.out.println("in turn");
 		}	
 		robotDrive6.arcadeDrive(forward, turn); // PID controlled Drive
 	} // End of BasicDrive PID Control
 	// ELSE PID is Off 
 	else { // use standard arcadeDrive
   System.out.println("standard drive");		
   robotDrive6.arcadeDrive(forward, turn);
 	     }
 	} // End of PID enable loop 
 	
	public void disablePID() {
		if (getPIDController().isEnabled()) {
			IAccumulator = 0;
			getPIDController().reset(); // disables and resets integral
		}
	}

	public void enablePID() {
		if (!getPIDController().isEnabled()) {
			gyro.reset();
			IAccumulator = 0;
			getPIDController().enable();
			setSetpoint(0.0); }
		}
 	
		/**
		 * sets up the PID for rotate command
		 */
		public void setRotatePID(double angleSetPoint) {
			gyro.reset(); // reset gyro so our angle is 0
			getPIDController().setSetpoint(angleSetPoint);
			IAccumulator = 0; // reset accumulator
			settledTimer.reset();
			settledPos = 0;
		}
/** 	
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
*/ 	
 	
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
 
 	public void displayChasisData() {
		SmartDashboard.putNumber("left enc", left1.getEncPosition());
		SmartDashboard.putNumber("right enc", right1.getEncPosition());
		SmartDashboard.putNumber("left enc speed", left1.getEncVelocity());
		SmartDashboard.putNumber("right enc speed", right1.getEncVelocity());
		SmartDashboard.putNumber("angle", gyro.getAngle());
		SmartDashboard.putNumber("gyro setpoint", getSetpoint());
		SmartDashboard.putNumber("gyro error", getPIDController().getError());
		SmartDashboard.putNumber("IAcc", IAccumulator);
	}
 
	
/**
	//************* ALEX test code

 private boolean ready = true;
	public void simpleDrive(double forward, double turn, boolean isAutonumous){
		if(isAutonumous){
			//Do that kind of code
			if(ready == true){
				//First run
				gyro.reset();
				
				
				ready = false;
			}
			else{
				//After first run, always execute this code
				double angle = gyro.getAngle();
				robotDrive6.arcadeDrive(forward, -angle * 0.03);
				Timer.delay(0.004); //Delay of 4 milliseconds
			}
			
		}
		else{
			ready = true;
			robotDrive6.arcadeDrive(forward, turn);
		}
	}

	
//********************** end of test code **************
	
**/
	
 
}
