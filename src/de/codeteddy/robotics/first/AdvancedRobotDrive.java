//Copyright 2017 Alexander Kaschta
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package de.codeteddy.robotics.first;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.hal.HAL;

import org.usfirst.frc308.FRC2017.RobotConstants;

import com.ctre.CANTalon;


/**
 * This is an advanced version of the RobotDrive of the WPILIB for FIRST, that is able 
 * to control a robot with 6 motors, 3 on each side
 * @author Alexander Kaschta
 * @version 1.0
 * @since 2017-02-05
 *
 */
public class AdvancedRobotDrive implements MotorSafety {

    protected MotorSafetyHelper m_safetyHelper;
    public static final double kDefaultExpirationTime = 0.1D;
    public static final double kDefaultSensitivity = 0.5D;
    public static final double kDefaultMaxOutput = 1.0D;
    protected static final int kMaxNumberOfMotors = 4;
    protected double m_sensitivity;
    protected double m_maxOutput;
    protected SpeedController m_left1;
    protected SpeedController m_left2;
    protected SpeedController m_left3;
    protected SpeedController m_rigth1;
    protected SpeedController m_rigth2;
    protected SpeedController m_rigth3;
    protected boolean m_allocatedSpeedControllers;

    
    /**
     * This interface is used in the code and other methods to identify a single motor
     * @author Alexander Kaschta
     *
     */
    public static enum MotorType {
        kLeft1(0), kLeft2(1), kLeft3(2), kRigth4(3), kRigth5(4), kRigth6(5);

        public final int value;

        private MotorType(int value) {
            this.value = value;
        }
    }

    protected static boolean kArcadeRatioCurve_Reported = false;
    protected static boolean kTank_Reported = false;
    protected static boolean kArcadeStandard_Reported = false;
    protected static boolean kMecanumCartesian_Reported = false;
    protected static boolean kMecanumPolar_Reported = false;
    
    
    /**
     * Constructor of the AdvancedRobotDrive that takes the port numbers of the motors.
     * 
     * @author Alexander Kaschta
     * @param left1 Port number of the first motor on the left side
     * @param left2	Port number of the second motor on the left side
     * @param left3 Port number of the third motor on the left side
     * @param rigth4 Port number of the first motor on the right side
     * @param rigth5 Port number of the second motor on the right side
     * @param rigth6 Port number of the third motor on the right side
     */
    public AdvancedRobotDrive(int left1, int left2, int left3, int rigth4, int rigth5, int rigth6) //AK
    {
        this.m_sensitivity = 0.5D;
        this.m_maxOutput = 1.0D;
        this.m_left1 = new Talon(left1);
        this.m_left2 = new Talon(left2);
        this.m_left3 = new Talon(left3);
        this.m_rigth1 = new Talon(rigth4);
        this.m_rigth2 = new Talon(rigth5);
        this.m_rigth3 = new Talon(rigth6);
        this.m_allocatedSpeedControllers = true;
        setupMotorSafety();
    }
    
    /**
     * Constructor that takes CANTalon objects instead port numbers.
     * This can be used nice, when you use a RobotMap, where you contain all of your robot
     * parts.
     * 
     * @author Alexander Kaschta
     * @param left1 CANTalon, that is the first motor on the left side
     * @param left2	CANTalon, that is the second motor on the left side
     * @param left3 CANTalon, that is the third motor on the left side
     * @param rigth4 CANTalon, that is the first motor on the right side
     * @param rigth5 CANTalon, that is the second motor on the right side
     * @param rigth6 CANTalon, that is the third motor on the right side
     */
    public AdvancedRobotDrive(CANTalon left1, CANTalon left2 ,CANTalon left3, CANTalon rigth4, CANTalon rigth5, CANTalon rigth6){
    	this.m_sensitivity = 0.5D;
        this.m_maxOutput = 1.0D;
        this.m_left1 = left1;
        this.m_left2 = left2;
        this.m_left3 = left3;
        this.m_rigth1 = rigth4;
        this.m_rigth2 = rigth5;
        this.m_rigth3 = rigth6;
        this.m_allocatedSpeedControllers = true;
        setupMotorSafety();
    }
    
    /**
     * Constructor that takes Talon objects instead port numbers.
     * This can be used nice, when you use a RobotMap, where you contain all of your robot
     * parts.
     * 
     * @author Alexander Kaschta
     * @param left1 Talon, that is the first motor on the left side
     * @param left2	Talon, that is the second motor on the left side
     * @param left3 Talon, that is the third motor on the left side
     * @param rigth4 Talon, that is the first motor on the right side
     * @param rigth5 Talon, that is the second motor on the right side
     * @param rigth6 Talon, that is the third motor on the right side
     */
    public AdvancedRobotDrive(Talon left1, Talon left2 ,Talon left3, Talon rigth4, Talon rigth5, Talon rigth6){
    	this.m_sensitivity = 0.5D;
        this.m_maxOutput = 1.0D;
        this.m_left1 = left1;
        this.m_left2 = left2;
        this.m_left3 = left3;
        this.m_rigth1 = rigth4;
        this.m_rigth2 = rigth5;
        this.m_rigth3 = rigth6;
        this.m_allocatedSpeedControllers = true;
        setupMotorSafety();
    }
    
    /**
     * This uses two joysticks to control the robot like a tank
     * @author Alexander Kaschta
     * @exception NullPointerException Null HID provided
     * @param leftStick stick for the left motors
     * @param rightStick stick for the right motors
     */
    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getY(), rightStick.getY(), true);
    }

    /**
     * This uses two joysticks to control the robot like a tank
     * @author Alexander Kaschta
     * @param leftValue joystick for the left motors
     * @param rightValue joystick for the right motors
     * @param squaredInputs are the input values already squared?
     * @exception NullPointerException Null HID provided
     */
    public void tankDrive(GenericHID leftStick, GenericHID rightStick, boolean squaredInputs) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getY(), rightStick.getY(), squaredInputs);
    }

    /**
     * This uses two joysticks to control the robot like a tank
     * @author Alexander Kaschta
     * @param leftStick joystick for the left motors
     * @param leftAxis index of the axis of that joystick
     * @param rightStick joystick for the right motors
     * @param rightAxis index of the axis of that joystick
     * @exception NullPointerException Null HID provided
     */
    public void tankDrive(GenericHID leftStick, int leftAxis, GenericHID rightStick, int rightAxis) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getRawAxis(leftAxis), rightStick.getRawAxis(rightAxis), true);
    }

    /**
     * This uses two joysticks to control the robot like a tank
     * @author Alexander Kaschta
     * @param leftStick joystick for the left motors
     * @param leftAxis index of the axis of that joystick
     * @param rightStick joystick for the right motors
     * @param rightAxis index of the axis of that joystick
     * @param squaredInputs are the input values already squared?
     * @exception NullPointerException Null HID provided
     */
    public void tankDrive(GenericHID leftStick, int leftAxis, GenericHID rightStick, int rightAxis, boolean squaredInputs) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getRawAxis(leftAxis), rightStick.getRawAxis(rightAxis), squaredInputs);
    }

    /**
     * Simple method to drive the robot like a tank
     * @author Alexander Kaschta
     * @param leftValue value for the left motors
     * @param rightValue value for the right motors
     * @param squaredInputs are the input values already squared?
     */
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
        if (!kTank_Reported) {
            HAL.report(31, getNumMotors(), 4);

            kTank_Reported = true;
        }
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);
   /**     if (squaredInputs) {
                 leftValue *= leftValue;
            } else {
                leftValue = -(leftValue * leftValue);
            }
            if (rightValue >= 0.0D) {
                rightValue *= rightValue;
            } else {
                rightValue = -(rightValue * rightValue);
            }
       }  */
        setLeftRightMotorOutputs(leftValue, rightValue);
    }
    
    /**
     * Simple method to drive the robot like a tank
     * @author Alexander Kaschta
     * @param leftValue value for the left motors
     * @param rightValue value for the right motors
     */
    public void tankDrive(double leftValue, double rightValue) {
        tankDrive(leftValue, rightValue, false); // MG keep false
    }
    
    /**
     * Very simple arcade drive method that uses only one joystick to control the complete robot
     * @author Alexander Kaschta
     * @param stick joystick to control the robot
     * @param squaredInputs are the input values already squared?
     */
    public void arcadeDrive(GenericHID stick, boolean squaredInputs) {
        arcadeDrive(stick.getY(), stick.getX(), squaredInputs);
    }

    /**
     * Very simple arcade drive method that uses only one joystick to control the complete robot
     * @author Alexander Kaschta
     * @param stick joystick to control the robot
     */
    public void arcadeDrive(GenericHID stick) {
        arcadeDrive(stick, true);
    }
    
    /**
     * More customizable method to control the robot using arcade drive
     * @author Alexander Kaschta
     * @param moveStick joystick from which the value should be used to control the robot
     * @param moveAxis index of axis that should be used from the joystick <b>moveStick</b>
     * @param rotateStick joystick that should be used to control the rotation of the robot
     * @param rotateAxis index of the axis that should be used from the joystick <b>rotateStick</b>
     * @param squaredInputs are the input values already squared?
     */
    public void arcadeDrive(GenericHID moveStick, int moveAxis, GenericHID rotateStick, int rotateAxis, boolean squaredInputs) {
        double moveValue = moveStick.getRawAxis(moveAxis);
        double rotateValue = rotateStick.getRawAxis(rotateAxis);

        arcadeDrive(moveValue, rotateValue, squaredInputs);
    }
    
    /**
     * More customizable method to control the robot using arcade drive
     * @author Alexander Kaschta
     * @param moveStick joystick from which the value should be used to control the robot
     * @param moveAxis index of axis that should be used from the joystick <b>moveStick</b>
     * @param rotateStick joystick that should be used to control the rotation of the robot
     * @param rotateAxis index of the axis that should be used from the joystick <b>rotateStick</b>
     */
    public void arcadeDrive(GenericHID moveStick, int moveAxis, GenericHID rotateStick, int rotateAxis) {
        arcadeDrive(moveStick, moveAxis, rotateStick, rotateAxis, true);
    }

    /**
     * More advanced method to control the robot with just one joystick
     * @author Alexander Kaschta
     * @param moveValue value that show how much the robot should go forward (y-axis)
     * @param rotateValue value how much the robot should rotate (x-axis)
     * @param squaredInputs show's if the input values already had been squared (usually true)
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        if (!kArcadeStandard_Reported) {
            HAL.report(31, getNumMotors(), 1);

            kArcadeStandard_Reported = true;
        }
        
        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
        
        if (squaredInputs) {
            if (moveValue >= 0.0D) {	
                moveValue *= moveValue;
            } else {
                moveValue = -(moveValue * moveValue);
            }
       
            if (rotateValue >= 0.0D) {
                rotateValue *= rotateValue;
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }
        
        
        double rightMotorSpeed;
        double leftMotorSpeed;
        
        if (moveValue > 0.0D) {
            if (rotateValue > 0.0D) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            
            if (rotateValue > 0.0D) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        

        setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }
    
    /**
     * Method that uses values of only one joystick to drive the robot
     * @author Alexander Kaschta
     * @param moveValue value that show how much the robot should go forward (y-axis)
     * @param rotateValue value how much the robot should rotate (x-axis)
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
        arcadeDrive(moveValue, rotateValue, false); // MG keep false
    }

    /**
     * Set's the output of the motors
     * @param leftOutput value for the motors on the left side
     * @param rightOutput value for the motors on the right side
     * @author Alexander Kaschta
     * @exception NullPointerException No motor provided
     */
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        if ((this.m_left1 == null) || (this.m_left2 == null) || (this.m_left3 == null)) {
            throw new NullPointerException("No motor provided");
        }
        if ((this.m_rigth1 == null) || (this.m_rigth2 == null) || (this.m_rigth3 == null)) {
            throw new NullPointerException("No motor is provided");
        }
        if (this.m_left1 != null) {
            this.m_left1.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_left2 != null) {
            this.m_left2.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_left3 != null) {
            this.m_left3.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_rigth1 != null) {
            this.m_rigth1.set(-limit(rightOutput) * this.m_maxOutput);
        }
        if (this.m_rigth2 != null) {
            this.m_rigth2.set(-limit(rightOutput) * this.m_maxOutput);
        }
        if (this.m_rigth3 != null) {
            this.m_rigth3.set(-limit(rightOutput) * this.m_maxOutput);
        }

        if (this.m_safetyHelper != null) {
            this.m_safetyHelper.feed();
        }
    }
    
    /**
     * Method to check if input is in range
     * @param number to check
     * @return checked number
     * @author Alexander Kaschta
     */
    protected static double limit(double number) {
        if (number > 1.0D) {
            return 1.0D;
        }
        if (number < -1.0D) {
            return -1.0D;
        }
        return number;
    }

    /**
     * @deprecated
     * @author Alexander Kaschta
     * @param wheelSpeeds
     */
    protected static void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < 4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0D) {
            for (int i = 0; i < 4; i++) {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

    /**
     * @author Alexander Kaschta
     * @deprecated
     * @param x
     * @param y
     * @param angle
     * @return
     */
    protected static double[] rotateVector(double x, double y, double angle) {
        double cosA = Math.cos(angle * 0.017453277777777776D);
        double sinA = Math.sin(angle * 0.017453277777777776D);
        double[] out = new double[2];
        out[0] = (x * cosA - y * sinA);
        out[1] = (x * sinA + y * cosA);
        return out;
    }

    /**
     * Method to invert a single motor
     * @author Alexander Kaschta
     * @param motor representing the motor you want to change
     * @param isInverted If should be inverted, true, else false
     * @exception IllegalArgumentException Illegal motor type
     */
    public void setInvertedMotor(MotorType motor, boolean isInverted) {
        switch (motor) {
            case kLeft1:
                this.m_left1.setInverted(isInverted);
                break;
            case kLeft2:
                this.m_left2.setInverted(isInverted);
                break;
            case kLeft3:
                this.m_left3.setInverted(isInverted);
                break;
            case kRigth4:
                this.m_rigth1.setInverted(isInverted);
                break;
            case kRigth5:
                this.m_rigth2.setInverted(isInverted);
                break;
            case kRigth6:
                this.m_rigth3.setInverted(isInverted);
                break;
            default:
                throw new IllegalArgumentException("Illegal motor type: " + motor);
        }
    }

    /**
     * This set's the sensitivity of the Motor safety (Standard is 0.5D).
     * @author Alexander Kaschta
     * @param sensitivity representing the sensitivity
     */
    public void setSensitivity(double sensitivity) {
        this.m_sensitivity = sensitivity;
    }

    /**
     * Method to change the maximum output for the Talons controlling the robot.
     * (Standard is 1)
     * @author Alexander Kaschta
     * @param maxOutput representing the maximum output for the motors
     */
    public void setMaxOutput(double maxOutput) {
        this.m_maxOutput = maxOutput;
    }
    
    /**
     * This method free's all PWM channels from their motors
     * @author Alexander Kaschta
     */
    public void free() {
        if (this.m_allocatedSpeedControllers) {
            if (this.m_left1 != null) {
                ((PWM) this.m_left1).free();
            }
            if (this.m_left2 != null) {
                ((PWM) this.m_left2).free();
            }
            if (this.m_left3 != null) {
                ((PWM) this.m_left3).free();
            }
            if (this.m_rigth1 != null) {
                ((PWM) this.m_rigth1).free();
            }
            if (this.m_rigth2 != null) {
                ((PWM) this.m_rigth2).free();
            }
            if (this.m_rigth3 != null) {
                ((PWM) this.m_rigth3).free();
            }
        }
    }

    /**
     * Method to set the timeout of the safety helper
     * @author Alexander Kaschta
     * @param timeout double value in seconds
     */
    public void setExpiration(double timeout) {
        this.m_safetyHelper.setExpiration(timeout);
    }
    
    /**
     * Method to get the timeout to which the safety helper is set
     * @author Alexander Kaschta
     * @return double containing the actual timeout value
     */
    public double getExpiration() {
        return this.m_safetyHelper.getExpiration();
    }

    /**
     * Returns a boolean telling you, if the safety helper is still alive
     * @author Alexander Kaschta
     * @return boolean, which is true, when the safety helper is alive
     * @see SafetyHelper
     */
    public boolean isAlive() {
        return this.m_safetyHelper.isAlive();
    }

    /**
     * Returns if safety is enabled on the robot
     * @author Alexander Kaschta
     * @return boolean containing the status, if the safety helper is enabled
     */
    public boolean isSafetyEnabled() {
        return this.m_safetyHelper.isSafetyEnabled();
    }

    /**
     * This method is used to enable or disable Safety on the Robot Drive. Put true to
     * enable, false to disable.
     * @param enabled Boolean that represents the status to which it should be changed to
     * @author Alexander Kaschta
     */
    public void setSafetyEnabled(boolean enabled) {
        this.m_safetyHelper.setSafetyEnabled(enabled);
    }

    /**
     * @author Alexander Kaschta
     * @return a String with a description of the function of the Advanced Robot Drive
     */
    public String getDescription() {
        return "Robot Drive";
    }
    
    /**
     * This method is used for getting the author of that code
     * @author Alexander Kaschta
     * @return String containing the name of the author 
     */
    public String getAuthor(){
    	return "Alexander Kaschta";
    }

    /**
     * Stops all motors 
     * @author Alexander Kaschta
     */
    public void stopMotor() {
        if (this.m_left1 != null) {
            this.m_left1.stopMotor();
        }
        if (this.m_left2 != null) {
            this.m_left2.stopMotor();
        }
        if (this.m_left3 != null) {
            this.m_left3.stopMotor();
        }
        if (this.m_rigth1 != null) {
            this.m_rigth1.stopMotor();
        }
        if (this.m_rigth2 != null) {
            this.m_rigth2.stopMotor();
        }
        if (this.m_rigth3 != null) {
            this.m_rigth3.stopMotor();
        }
        if (this.m_safetyHelper != null) {
            this.m_safetyHelper.feed();
        }
    }

    /**
     * Enables motor safety for the robot
     * @author Alexander Kaschta
     */
    private void setupMotorSafety() {
        this.m_safetyHelper = new MotorSafetyHelper(this);
        this.m_safetyHelper.setExpiration(0.1D);
        this.m_safetyHelper.setSafetyEnabled(true);
    }

    /**
     * Method to figure out, how much motors does the RobotDrive has
     * @return Number of Motors on the robot
     * @author Alexander Kaschta
     */
    protected int getNumMotors() {
        int motors = 0;
        if (this.m_left1 != null) {
            motors++;
        }
        if (this.m_left2 != null) {
            motors++;
        }
        if (this.m_left3 != null) {
            motors++;
        }
        if (this.m_rigth1 != null) {
            motors++;
        }
        if (this.m_rigth2 != null) {
            motors++;
        }
        if (this.m_rigth3 != null) {
            motors++;
        }
        return motors;
    }
}
