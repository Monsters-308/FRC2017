package org.usfirst.frc308.FRC2017;

import org.usfirst.frc308.FRC2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc308.FRC2017.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    public Joystick joystick1;
    public Joystick joystick2;


    public OI() {


        joystick2 = new Joystick(1);
        
        joystick1 = new Joystick(0);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("TeleopDrive", new TeleopDrive());
        SmartDashboard.putData("TeleopIntake", new TeleopIntake());
        SmartDashboard.putData("TeleopGear", new TeleopGear());
        SmartDashboard.putData("TeleopClimb", new TeleopClimb());
        SmartDashboard.putData("TeleopProcess", new TeleopProcess());
        SmartDashboard.putData("TeleopPneumatics", new TeleopPneumatics());
        SmartDashboard.putData("TeleopShooter", new TeleopShooter());
        SmartDashboard.putData("TeleopLights", new TeleopLights());
        SmartDashboard.putData("TeleopVision", new TeleopVision());


    }


    public Joystick getJoystick1() {
        return joystick1;
    }

    public Joystick getJoystick2() {
        return joystick2;
    }


}

