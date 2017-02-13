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
    Timer setPointTimer = new Timer();

    private boolean turning = true;

	// PID Stuff
	double IAccumulator = 0.0; // the sum of error over time
	double lastError = 0.0;
	double error = 0;
	Timer settledTimer = new Timer();

//	BuiltInAccelerometer accel;


    public Chassis() {
        super("Drivetrain", RobotConstants.Kp, 0, RobotConstants.Kd);
        setAbsoluteTolerance(RobotConstants.gyroPIDErrorTolerance);
        getPIDController().setContinuous(true);
        getPIDController().setInputRange(-180, 180);
        getPIDController().setOutputRange(-1.0, 1.0);
        gyro.calibrate();
    }

    protected double returnPIDInput() { // Set PID Input value
        // Used - in code to create PID thread
        // Return your input value for the PID loop
        return gyro.getAngle();
    }

    protected void usePIDOutput(double output) {  // Get PID Output value
        // NOT Used - in code to create PID thread
        // Use output to drive your system, like a motor
        RobotConstants.gyroPIDOutput = output + RobotConstants.Ki * IAccumulator;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopDrive());
    }

    public void setIAccumulator(double value) {
        IAccumulator = value;
    }

    //Chassis setup
    public void setupDrive() {
        left1.changeControlMode(TalonControlMode.PercentVbus);
        left1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        left2.changeControlMode(TalonControlMode.PercentVbus);
        left3.changeControlMode(TalonControlMode.PercentVbus);
        right1.changeControlMode(TalonControlMode.PercentVbus);
        right1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        right2.changeControlMode(TalonControlMode.PercentVbus);
        right3.changeControlMode(TalonControlMode.PercentVbus);
        gyro.reset();
        getPIDController().setSetpoint(0); // make setpoint current angle
        getPIDController().enable();
        IAccumulator = 0; // reset accumulator
 //       settledTimer.start();  //not used
    }


    public void arcadeDrive(double forward, double turn) {

		if (RobotConstants.enablePID == true )  { // use PID process
		 	   //IF user stops driving with the joystick 		
		 		if(turn == 0.0){
		 			if(turning == true){ //Code is called first time, when we stopped turning
		 				setPointTimer.start();
		 				RobotConstants.gyroPIDOutput = 0.0; //Reset PIDOutput to zero
		 				turning = false; 	//Set turning to false, because we are not turning any more
		 			} else if (setPointTimer.get() != 0) {//If this isn't the first time
		 				if (setPointTimer.get() >= 1.0){  // Robot is moving straight
		  				   enablePID();
						   gyro.reset();
						   getPIDController().setSetpoint(0);
						   setPointTimer.stop();
						   setPointTimer.reset();
						   }
		 			} else { // after initializing ** Driving straight using PID
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
		 		}	
		 		robotDrive6.arcadeDrive(forward, turn); // PID controlled Drive
		 	} // End of BasicDrive PID Control
		 	// ELSE PID is Off 
		 	else { // use standard arcadeDrive
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
            setSetpoint(0.0);
        }
    }

  

    public void setDrive(double left, double right) {
        if (Math.abs(left) > 1.0) {
            left /= Math.abs(left);
        }
        if (Math.abs(right) > 1.0) {
            right /= Math.abs(right);
        }
        SmartDashboard.putNumber("left power", left);
        SmartDashboard.putNumber("right power", right);
        SmartDashboard.putNumber("pid turn", RobotConstants.gyroPIDOutput);
        robotDrive6.tankDrive((left + RobotConstants.gyroPIDOutput),
                (-(right - RobotConstants.gyroPIDOutput)));
    }

    public void resetEncoders() {
        left1.setEncPosition(0);
        right1.setEncPosition(0);
    }

    public double getEncoderPosition() {
        return left1.getEncPosition();
    }

    public int getLeftEncoderPosition() {
        return left1.getEncPosition();
    }

    public int getRightEncoderPosition() {
        return -right1.getEncPosition();
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }


    public double deadZone(double input) {
        double d = Math.abs(input);
        if (d < RobotConstants.deadzone) {
            return 0.0;
        }
        return input;
    }
/** Not used
	public void setRotatePIDstart(double angleSetPoint) {
		enablePID();
		double out;
	    getPIDController().setSetpoint(angleSetPoint);
	    System.out.println("PID Turn ");
	    System.out.println(angleSetPoint);
	    SmartDashboard.putNumber("turn out", RobotConstants.gyroPIDOutput);
	    robotDrive6.arcadeDrive(0, RobotConstants.gyroPIDOutput);
    }
		
	public void setRotatePIDstop() {
		disablePID(); 
 	    gyro.reset();
		getPIDController().setSetpoint(0);
	    robotDrive6.arcadeDrive(0, 0);
	}
*/
	  /**
     * resets PID for to zero
     */
  public void setRotatePIDZero() {
      gyro.reset(); // reset gyro so our angle is 0
      getPIDController().setSetpoint(0);
      IAccumulator = 0; // reset accumulator
     }
  /**
   * sets up the PID for rotate command
   */
  public void setRotatePID(double setpointAngle) {
      gyro.reset(); // reset gyro so our angle is 0
      getPIDController().setSetpoint(setpointAngle);
      if (setpointAngle >= 0) {
		setIAccumulator((0.05 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
	  } else if (setpointAngle < 0) {
		setIAccumulator((-0.05 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
	  }
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

    
}
