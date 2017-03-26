package org.usfirst.frc308.FRC2017;


/**
 * This class contains all the variables set by the developers for the robot.
 *
 * @author Alex
 * @version 1.0.0
 */

public class RobotConstants {

	// IMPORTANT NOTICE
	//
	// Please make sure, that all the constants you insert here, are in the
	// right area. If no matches, you create your own one.

	public static boolean last = true;
	public static boolean isAutonomousAiming = false;

	// Joystick 1 constants
	public static boolean singleJoystick = true;
	public static double deadzone = 0.1; // updated by MG
	public static int shootBall = 4;
	public static int extendClawButton = 5;
	public static int closeClawButton = 6;
	public static int clawDoorButton = 7;
	public static int initShooter = 10;
	public static int climbButton = 3;
	public static int initIntake = 8;
	public static int climbActuator = 9;
	public static int cameralight = 2;
	
	// Autonomous Drive profiles
	// units in inch and degree with scaling to meter and radians in command
	
	public static int startPositionChooser = 1;;
	public static int boilPositionChooser = 1;
	
	public static double [][] autostartposition = { // ** NOT USED
  			{87, -56, 108.07}, // Left 
            {53, 0, 0}, // Center
            {86, 54, 72.07}  // Right
            };
    public static double [][][] autoairshipposition = {  // ** NOT USED
    		{
            {10, 0,0}, // Left + Left boiler
            {10, 0,0},  // Center + Left boiler
            {10, 0,0}},  // Right + Left boiler
            {
            {10, 0,0}, // Left + right boiler
            {10, 0,0},  // Center + right boiler
            {10, 0,0}   // Right  + right boiler
             }};
    
    public static double [][] autoextrawaypointposition = { 
            {0, 0, 0}, // Left
            {0, 0, 0}, // Center
            {0, 0, 0}  // Right
             };
	
	
	//Vision --> Axis-Camera //TODO Needs to be changed when switching cameras
    public static boolean isAiming = true;
	public static int visionTolerance = 5; //Count in pixels
	public static int x = 320; //Count in pixels
	public static int y = 240; //Count in pixels
	public static int visionoffsetcal = -20;
	public static double Vision_neg_rotate= -.25;
	public static double Vision_pos_rotate= .25;
	public static double Vision_setpointAngle= 0;
	public static double cameraFieldOfView = 49.0; // in degrees mg changed from 67
	// Notice that the datasheets give approximate view angle information. 
	// When testing, it was found that the calculated distance to the target tended to be a bit short.
	// Using a tape measure to measure the distance and treating the angle as the unknown it was found
	// that view angles of 41.7 for the 206, 37.4 for the M1011, and 49 for the M1013 gave better result
	
	
	// Driving
	public static boolean safetyEnabled = false;
	public static double arcadeMaxOutput = 1.0;
	public static double arcadeSensitivity = 0.5;
	public static double arcadeExpiration = 0.1;
//	public static boolean exponentialDrive = false;

	// Shooter Constants

	/**
	 * Velocity is measured in change in native units per TvelMeas= 100ms.
	 * Example: (1366 Rotations / min) X (1 min / 60 sec) X (1 sec / 10
	 * TvelMeas) X (4096 native units / rotation) = 9326 native units per 100ms
	 * Now lets calculate a Feed-forward gain so that 100% motor output is
	 * calculated when the requested speed is 9328 native units per 100ms.
	 * F-gain = (100% X 1023) / 9326 F-gain = 0.1097 Lets check our math, if the
	 * target speed is 9326 native units per 100ms, Closed-loop output will be
	 * (0.1097 X 9326) => 1023 (full forward).
	 */
	public static double shootertargetRPM = 675; // Desired RPM
	// public static double shootertargetspeed = (shootertargetRPM * 1/60 *1/10
	// * 4096); // Desired RPM
	public static double shootertargetspeed = 4200;
	public static double shooterTolerance = 300.0;
	public static double shooterPIDKp = 0.4;
	public static double shooterPIDKi = 0.00004;
	public static double shooterPIDKd = 0.0;
	public static double shooterPIDKf = 1023.0 / 13000.0; // 1023/maximumspeed
	public static int shooterPIDIZone = 2000;
	public static double shooterPIDRampRate = 0.0;
	public static double shooterTimer_timer = 0.7;
	public static boolean shooterMode = false;

	// Light Constants
	public static boolean intakeLightState = false;
	public static boolean extendLightState = false;
	public static boolean clawLightState = false;
	public static boolean cameralightState = false;
	public static double cameralightTimer_time = .7;
	public static double lightflashspeedlow = 2.0;
	public static double lightflashspeedfast = 1.0;
	
	// Climbing constants
	public static double climbSpeed = 1.0;
	public static double shakecycle = 2.0;

	// Intake constants
	public static double ballintakespeed = -0.8;
	public static double intakeTimer_time =.7;
	public static boolean intakeMode = false;

	// Gear constants
	public static boolean clawExtendState = false;
	public static boolean clawOpenState = false;
	public static boolean batDoorState = false;
	public static double extendTimer_timer = .7;
	public static double doorTimer_timer = .7;
	public static double closeTimer_timer = .3;
	public static double autogearTimer_timer = .5;
	public static double gearTimer_timer = 4.0;
	public static double latchgearTimer_timer = 3.0;
	public static boolean autogear = true;

	// Process-Hopper constants
	public static double feedBalltimer_time = .7;
	public static double processSpeed = -.3;  // not used
	public static boolean processState = false;

	// Chassis Constants
	public static boolean enablePID = false;
	public static double Kp = 0.005; // 0.012
	public static double Ki = 0.001; // 0.0025
	public static double Kd = 0.0;
	public static double iZone = 15.0;
	public static double maximumIZoneSpeed = 1.0;
	public static double rotateInertiaBias = 0.15;
	public static double gyroPIDOutput = 0.0;
	public static double gyroPIDErrorTolerance = 1.0; // in degrees
	public static boolean isTrajectory = false;

	// Autonomous Constants
	public static int TrajectorySegments;
	
	
	
}
	