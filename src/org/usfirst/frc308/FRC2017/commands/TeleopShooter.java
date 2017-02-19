package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

//Needed for Button Toggle Code
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TeleopShooter extends Command {

    // Used for Button Toggle Code
    private Timer shooterTimer = new Timer();

    public TeleopShooter() {

        requires(Robot.shooter);

        // RobotConstants.shootertargetspeed
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shooter.setupShooter();
        RobotConstants.shooterMode = false;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // if button A is pressed
        SmartDashboard.putBoolean("shoot mode ", RobotConstants.shooterMode);
        SmartDashboard.putBoolean("shoot start ", Robot.oi.joystick1.getRawButton(RobotConstants.initShooter));

        if (Robot.oi.joystick1.getRawButton(RobotConstants.initShooter)) {

            if (shooterTimer.get() == 0) {
                if (RobotConstants.shooterMode == false) {
                    RobotConstants.shooterMode = true;
                    Robot.shooter.setShootSpeed(RobotConstants.shootertargetspeed);

                    // Turn off intake
                    Robot.intake.setballmotor(0);
                } else { // If the shooter mode was on then toggle off
                    Robot.shooter.setShootSpeed(0);
                    RobotConstants.shooterMode = false;
                }

                // Start Timer to make sure the toggle happens only once
                shooterTimer.start();
            }
        }

        // If the shooterTimer is greater than value then reset it
        // Note: Tune the value to better timing of when the button is pressed
        // and the next pressed
        if (shooterTimer.get() >= .4) {
            shooterTimer.stop();
            shooterTimer.reset();
        }

        // Opens shooter door to allow bal1 into shooter wheels

   //     if (Robot.oi.joystick1.getRawButton(RobotConstants.shootBall)) {
   //         Robot.shooter.Trigger(true);
    //    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
