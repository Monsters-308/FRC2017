package org.usfirst.frc308.FRC2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc308.FRC2017.commands.*;
import org.usfirst.frc308.FRC2017.subsystems.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
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

    Command autonomousCommand;
	SendableChooser<Command> autoChooser;
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

        autoChooser = new SendableChooser();
		autoChooser.addObject("Do Nothing", new AutonomousDoNothing());
		autoChooser.addObject("Drive Forward", new AutonomousDriveForward());
		autoChooser.addObject("Deliver Gear Only", new AutonomousDeliverGearOnly());
		autoChooser.addDefault("Deliver Gear and shoot balls", new AutonomousDeliverGearShootBall());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
     
        SmartDashboard.putString("Start Position","Start Position 0 = left, 1 = Center, 2 = right) ");
        SmartDashboard.putNumber("Start position set" , RobotConstants.startPositionChooser);
        SmartDashboard.putString("Boiler Position","Boiler Position 0 = left, 1 = Right) ");
        SmartDashboard.putNumber("Boiler position set" , RobotConstants.boilPositionChooser);
		
                
        // instantiate the command used for the autonomous period
		server = CameraServer.getInstance();
		server.addAxisCamera("Rear", "10.3.8.3");
		//server.startAutomaticCapture();
		
		//autonomousCommand = new AutonomousCommand(); MG remove after autoChooser works


        // sets of Preferences on smart dash board
        //
        //  The Robot Preference function allows for temporary adjustment of variables
        //  The code below loads reset the temp smartdash board variables with the values hard coded
        //  THis code is run only when the Robrio is restarted enabling will not reset the values

        Preferences.getInstance().putInt("Autonomous Start Position ",  RobotConstants.startPositionChooser);
        Preferences.getInstance().putInt("Autonomous Boiler Position ", RobotConstants.boilPositionChooser);
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
        SmartDashboard.putString("Start Position","Start Position 0 = left, 1 = Center, 2 = right) ");
        SmartDashboard.putNumber("Start position set" , RobotConstants.startPositionChooser);
        SmartDashboard.putString("Boiler Position","Boiler Position 0 = left, 1 = Right) ");
        SmartDashboard.putNumber("Boiler position set" , RobotConstants.startPositionChooser);
    	// schedule the autonomous command 
    	autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null) {
			autonomousCommand.start();
			 System.out.println("robot initialize");
		}
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
        
        
// Reset to initial states   
//  
        Robot.chassis.brakemode(false);
//         
	    Robot.intake.setballmotor(0);
		RobotConstants.intakeMode = false;
		Robot.lights.disableIntakeLights();
//		
		Robot.processBalls.runProcess(0);
		RobotConstants.processState = false;
//	
		RobotConstants.shooterMode = false;
		Robot.shooter.setShootSpeed(0);
//		
		Robot.lights.setcameraLights();
		RobotConstants.cameralightState = true;
//		
		Robot.gearDelivery.closeClaw();
		RobotConstants.clawOpenState = true;
		RobotConstants.clawLightState = true;
//
		Robot.gearDelivery.retractClaw();
		RobotConstants.clawExtendState = false;
		RobotConstants.extendLightState = false;
//		
		Robot.gearDelivery.closeClawDoor();
		RobotConstants.batDoorState = false;
		
		
        /// The Robot Preference function allows for temporary adjustment of variables
        //  When teleop is started the temp variables from the roborio memory are used instead of the had code values

		RobotConstants.startPositionChooser = Preferences.getInstance().getInt("Autonomous Start Position ", RobotConstants.startPositionChooser);
        RobotConstants.boilPositionChooser = Preferences.getInstance().getInt("Autonomous Boiler Position ", RobotConstants.boilPositionChooser);
        SmartDashboard.putString("Start Position","Start Position 0 = left, 1 = Center, 2 = right) ");
        SmartDashboard.putNumber("Start position set" , RobotConstants.startPositionChooser);
        SmartDashboard.putString("Boiler Position","Boiler Position 0 = left, 1 = Right) ");
        SmartDashboard.putNumber("Boiler position set" , RobotConstants.boilPositionChooser);
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
        LiveWindow.addActuator("GearDelivery", " Gear Solenoid 1", RobotMap.gearDeliverySolenoid_1);
        LiveWindow.addActuator("GearDelivery", "Gear Solenoid 2", RobotMap.gearDeliverySolenoid_2);
    }
}
