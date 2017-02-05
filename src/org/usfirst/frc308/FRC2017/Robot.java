package org.usfirst.frc308.FRC2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc308.FRC2017.commands.*;
import org.usfirst.frc308.FRC2017.subsystems.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CameraServer;


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


    CameraServer server;

    public static OI oi;

    public static Chassis chassis = new Chassis();
    public static Intake intake = new Intake();
    public static GearDelivery gearDelivery = new GearDelivery();
    public static Climb climb = new Climb();
    public static ProcessBalls processBalls = new ProcessBalls();
    public static Shooter shooter = new Shooter();
    public static Lights lights = new Lights();
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


        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        server = CameraServer.getInstance();


        autonomousCommand = new AutonomousCommand();


        // sets of Preferences on smart dash board
        //
        //  The Robot Preference function allows for temporary adjustment of variables
        //  The code below loads reset the temp smartdash board variables with the values hard coded
        //  THis code is run only when the Robrio is restarted enabling will not reset the values

        Preferences.getInstance().putDouble("shooter ticks target", RobotConstants.shootertargetspeed);
        Preferences.getInstance().putDouble("shooter Tolerance", RobotConstants.shooterTolerance);
        Preferences.getInstance().putDouble("shooter kp", RobotConstants.shooterPIDKp);
        Preferences.getInstance().putDouble("shooter ki", RobotConstants.shooterPIDKi);
        Preferences.getInstance().putDouble("shooter kd", RobotConstants.shooterPIDKd);
        Preferences.getInstance().putDouble("shooter kf", RobotConstants.shooterPIDKf);
        Preferences.getInstance().putDouble("shooter PID Ramprate", RobotConstants.shooterPIDRampRate);
        Preferences.getInstance().putDouble("ball intake spd", RobotConstants.ballintakespeed);
        Preferences.getInstance().putDouble("gear intake spd", RobotConstants.gearintakespeed);
        Preferences.getInstance().putDouble("feed speed", RobotConstants.feederSpeed);
        Preferences.getInstance().putDouble("processSpeed", RobotConstants.processSpeed);
        Preferences.getInstance().putBoolean("enable drive PID", RobotConstants.enablePID);
        Preferences.getInstance().putDouble("Drive Kp", RobotConstants.Kp);
        Preferences.getInstance().putDouble("Drive Ki", RobotConstants.Ki);
        Preferences.getInstance().putDouble("Drive Kd", RobotConstants.Kd);
        Preferences.getInstance().putDouble("iZone", RobotConstants.iZone);
        Preferences.getInstance().putDouble("maximumIZoneSpeed", RobotConstants.maximumIZoneSpeed);
        Preferences.getInstance().putDouble("rotateInertiaBias", RobotConstants.rotateInertiaBias);
        Preferences.getInstance().putDouble("gyroPIDErrorTolerance", RobotConstants.gyroPIDErrorTolerance);

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {

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

        /// The Robot Preference function allows for temporary adjustment of variables
        //  When teleop is started the temp variables from the roborio memory are used instead of the had code values

        RobotConstants.shootertargetspeed = Preferences.getInstance().getDouble("shooter ticks target", RobotConstants.shootertargetspeed);
        RobotConstants.shooterTolerance = Preferences.getInstance().getDouble("shooter Tolerance", RobotConstants.shooterTolerance);
        RobotConstants.shooterPIDKp = Preferences.getInstance().getDouble("shooter kp", RobotConstants.shooterPIDKp);
        RobotConstants.shooterPIDKi = Preferences.getInstance().getDouble("shooter ki", RobotConstants.shooterPIDKi);
        RobotConstants.shooterPIDKd = Preferences.getInstance().getDouble("shooter kd", RobotConstants.shooterPIDKd);
        RobotConstants.shooterPIDKf = Preferences.getInstance().getDouble("shooter kf", RobotConstants.shooterPIDKf);
        RobotConstants.shooterPIDRampRate = Preferences.getInstance().getDouble("shooter PID Ramprate", RobotConstants.shooterPIDRampRate);
        RobotConstants.ballintakespeed = Preferences.getInstance().getDouble("ball intake spd", RobotConstants.ballintakespeed);
        RobotConstants.gearintakespeed = Preferences.getInstance().getDouble("gear intake spd", RobotConstants.gearintakespeed);
        RobotConstants.feederSpeed = Preferences.getInstance().getDouble("feed speed", RobotConstants.feederSpeed);
        RobotConstants.processSpeed = Preferences.getInstance().getDouble("processSpeed", RobotConstants.processSpeed);
        RobotConstants.enablePID = Preferences.getInstance().getBoolean("enable drive PID", RobotConstants.enablePID);
        RobotConstants.Kp = Preferences.getInstance().getDouble("Drive Kp", RobotConstants.Kp);
        RobotConstants.Ki = Preferences.getInstance().getDouble("Drive Ki", RobotConstants.Ki);
        RobotConstants.Kd = Preferences.getInstance().getDouble("Drive Kd", RobotConstants.Kd);
        RobotConstants.iZone = Preferences.getInstance().getDouble("iZone", RobotConstants.iZone);
        RobotConstants.maximumIZoneSpeed = Preferences.getInstance().getDouble("maximumIZoneSpeed", RobotConstants.maximumIZoneSpeed);
        RobotConstants.rotateInertiaBias = Preferences.getInstance().getDouble("rotateInertiaBias", RobotConstants.rotateInertiaBias);
        RobotConstants.gyroPIDErrorTolerance = Preferences.getInstance().getDouble("gyroPIDErrorTolerance", RobotConstants.gyroPIDErrorTolerance);
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
    public void initLiveWindow() {
        //TODO Add other componets to LiveWindow
        LiveWindow.addActuator("GearDelivery", " Gear Solenoid 1", RobotMap.gearDeliverySolenoid_1);
        LiveWindow.addActuator("GearDelivery", "Gear Solenoid 2", RobotMap.gearDeliverySolenoid_2);
    }
}
