package org.usfirst.frc308.FRC2017;


/**
 * This class contains all the variables set my the developers for the robot.
 *
 * @author Alex
 * @version 1.0.0
 */

public class RobotConstants {

    //		IMPORTANT NOTICE
    //
    //		Please make sure, that all the constants you insert here, are in the
    //		right area. If no matches, you create your own one.


    //Generall Controller
    public static double deadzone = 0.0; //TODO Need to be changed
    public static int shootBallHigh = 1;
    public static int clawButton = 2;



    //Vision


    //Driving
    public static boolean safetyEnabled = false;
    public static double arcadeMaxOutput = 1.0;
    public static double arcadeSensitivity = 0.5;
    public static double arcadeExpiration = 0.1;

    // Shooter Constants

    /**
     * Velocity is measured in change in native units per TvelMeas= 100ms.
     * Example:
     * (1366 Rotations / min) X (1 min / 60 sec) X (1 sec / 10 TvelMeas) X (4096 native units / rotation)
     * = 9326 native units per 100ms
     * Now lets calculate a Feed-forward gain so that 100% motor output is calculated when the requested speed is 9328 native units per 100ms.
     * F-gain = (100% X 1023) / 9326
     * F-gain = 0.1097
     * Lets check our math, if the target speed is 9326 native units per 100ms, Closed-loop output will be (0.1097 X 9326) => 1023 (full forward).
     */
    public static double shootertargetRPM = 5000; // Desired RPM
    public static double shootertargetspeed = 4600; // Desired RPM
    public static double shooterTolerance = 300.0;
    public static double shooterPIDKp = 0.4;
    public static double shooterPIDKi = 0.00004;
    public static double shooterPIDKd = 0.0;
    public static double shooterPIDKf = 1023.0 / 13000.0; //1023/maximumspeed
    public static int shooterPIDIZone = 4000;
    public static double shooterPIDRampRate = 0.0;

    // Chassis Constants
    public static double gyroPIDOutput = 0;
    public static boolean direction = true;
    public static final boolean shooterDirection = true;
    public static final boolean intakeDirection = false;
    public static double Kp = 0.015; //0.012
    public static double Ki = 0.0015; //0.0025
    public static double Kd = 0.0;
    public static double iZone = 15.0;
    public static double maximumIZoneSpeed = 1.0;
    public static double rotateInertiaBias = 0.15;
    public static double gyroPIDErrorTolerance = 1.0; //in degrees
    public static double gyroPIDVelocityTolerance = 0.5; //in degrees/second
    public static double Kv = 1.0/90.0; // 1/maximum
    public static double Ka = 0.0;
    public static double Kp2 = 0.0;
    public static double Ki2 = 0.0;
    public static double Kd2 = 0.0;
    public static boolean isAiming = false;
    public static boolean isTrajectory = false;


    /**  FRC2016 Below this line

     // Joystick 1 Buttons
     public static int platformShiftUpButton = 7;
     public static int platformShiftDownButton = 8;
     public static int chasisShiftUpButton = 10;
     public static int chasisShiftDownButton = 9;
     public static int chasisReverseDirectionButton = 3;
     public static int chasisBrakeButton = 1;

     // Joystick 2 Buttons
     public static int shootBallHigh = 1;
     public static int runIntakeMotor = 2;
     public static int ejectBall = 4;
     public static int gateRoll = 9;


     // Chassis Constants
     public static double gyroPIDOutput = 0;
     public static boolean direction = true;
     public static final boolean shooterDirection = true;
     public static final boolean intakeDirection = false;
     public static double Kp = 0.015; //0.012
     public static double Ki = 0.0015; //0.0025
     public static double Kd = 0.0;
     public static double iZone = 15.0;
     public static double maximumIZoneSpeed = 1.0;
     public static double rotateInertiaBias = 0.15;
     public static double gyroPIDErrorTolerance = 1.0; //in degrees
     public static double gyroPIDVelocityTolerance = 0.5; //in degrees/second
     public static double Kv = 1.0/90.0; // 1/maximum
     public static double Ka = 0.0;
     public static double Kp2 = 0.0;
     public static double Ki2 = 0.0;
     public static double Kd2 = 0.0;
     public static boolean isAiming = false;
     public static boolean isTrajectory = false;

     // Shooter Constants
     public static boolean shooting = false;
     public static double intakeGrabSpeed = 6000; // in rpm
     public static double intakeAdjustSpeed = 3000; // in rpm
     public static double intakeShooterSpeed = 8200.0;
     public static double intakePIDKp = 0.12;
     public static double intakePIDKi = 0.0002;
     public static double intakePIDKd = 0.0;
     public static double intakePIDKf = 1023.0/6300.0; //1023/maximumspeed
     public static int intakePIDIZone = 500;
     public static double intakePIDRampRate = 0.0;
     public static double shooterSpeed = 11500.0; //TODO 17000
     public static double shooterTolerance = 300.0; // units?
     public static double shooterPIDKp = 0.4;
     public static double shooterPIDKi = 0.00004;
     public static double shooterPIDKd = 0.0;
     public static double shooterPIDKf = 1023.0/13000.0; //TODO 1023/maximumspeed
     public static int shooterPIDIZone = 4000;
     public static double shooterPIDRampRate = 0.0;
     public static boolean reverseoptical = false;


     //Joysick Constants
     public static double deadZone = 0.1;


     //vision Constants
     public static double cameraFieldOfView = 14.0; //in degrees

     public static boolean introduceBall = false;
     */

}