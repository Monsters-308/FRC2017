package org.usfirst.frc308.FRC2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc308.FRC2017.commands.*;
import org.usfirst.frc308.FRC2017.subsystems.*;
import edu.wpi.first.wpilibj.Preferences;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Alex
 * @author Autogenerated
 */
public class Robot extends IterativeRobot {


    Preferences prefs;

    public static OI oi;

    public static Chassis chassis = new Chassis() ;
    public static Intake intake = new Intake();
    public static GearDelivery gearDelivery = new GearDelivery();
    public static  Climb climb = new Climb();
    public static  ProcessBalls processBalls = new ProcessBalls();
    public static Shooter shooter = new Shooter();
    public static  Lights lights = new Lights();
    public static Pneumatics pneumatics = new Pneumatics();
   
    Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

    public static final Vision vision = new Vision();



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	initLiveWindow();
    	

    	/** chassis = new Chassis();
        intake = new Intake();
        gearDelivery = new GearDelivery();
        climb = new Climb();
        processBalls = new ProcessBalls();
        shooter = new Shooter();
        lights = new Lights();
        pneumatics = new Pneumatics();
        vision = new Vision();
*/

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period


        autonomousCommand = new AutonomousCommand();
        
        prefs = Preferences.getInstance();
    	  
        
        prefs.putDouble("shooter Target RPM", RobotConstants.shootertargetRPM);
        prefs.putDouble("shooter ticks", RobotConstants.shootertargetspeed);
        prefs.putDouble("shooter Tolerance", RobotConstants.shooterTolerance);
		prefs.putDouble("shooter kp", RobotConstants.shooterPIDKp);
		prefs.putDouble("shooter ki", RobotConstants.shooterPIDKi);
		prefs.putDouble("shooter kd", RobotConstants.shooterPIDKd);
		prefs.putDouble("shooter kf", RobotConstants.shooterPIDKf);
		prefs.putDouble("ball intake spd", RobotConstants.gearintakespeed);
		prefs.putDouble("gear intake spd", RobotConstants.gearintakespeed);
		
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();

        RobotConstants.shootertargetRPM = prefs.getDouble("shooter Target RPM", RobotConstants.shootertargetRPM);
        RobotConstants.shootertargetspeed = prefs.getDouble("shooter ticks", RobotConstants.shootertargetspeed);
        RobotConstants.shooterTolerance = prefs.getDouble("shooter Tolerance", RobotConstants.shooterTolerance);
		RobotConstants.shooterPIDKp = prefs.getDouble("shooter kp", RobotConstants.shooterPIDKp);
		RobotConstants.shooterPIDKi = prefs.getDouble("shooter ki", RobotConstants.shooterPIDKi);
		RobotConstants.shooterPIDKd = prefs.getDouble("shooter kd", RobotConstants.shooterPIDKd);
		RobotConstants.shooterPIDKf = prefs.getDouble("shooter kf", RobotConstants.shooterPIDKf);
		RobotConstants.ballintakespeed = prefs.getDouble("ball in spd", RobotConstants.ballintakespeed);
		RobotConstants.gearintakespeed = prefs.getDouble("gear in spd", RobotConstants.gearintakespeed);
   
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * This method replaces the previous RobotMap.init();
     * It add all kind of components to the LiveWindow
     */
    public void initLiveWindow(){
    	//TODO Add other componets to LiveWindow
    	LiveWindow.addActuator("GearDelivery", " Gear Solenoid 1", RobotMap.gearDeliverySolenoid_1);
        LiveWindow.addActuator("GearDelivery", "Gear Solenoid 2", RobotMap.gearDeliverySolenoid_2);
    }
}
