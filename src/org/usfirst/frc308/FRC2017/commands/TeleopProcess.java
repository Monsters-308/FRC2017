package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;

/**
 *
 */
public class TeleopProcess extends Command {

    Timer FeedBall = new Timer();

    public TeleopProcess() {

        requires(Robot.processBalls);

    }

    // Called just before this Command runs the first time
    // Sets processor talon to voltage mode
    protected void initialize() {
        // Robot.processBalls.setupProcess();
    }

    // Called repeatedly when this Command is scheduled to run
    // Activates ball processor while shooting
    protected void execute() {
        if (Robot.oi.joystick1.getRawButton(RobotConstants.initShooter)) {
            if (RobotConstants.processState == false) {
                RobotConstants.processState = true;
                Robot.processBalls.runProcess(RobotConstants.processSpeed);
            } else {
                RobotConstants.processState = false;
                Robot.processBalls.runProcess(0);
            }
        }

        // // MG need to use timer
        // sleep with crash roborio
        // Robot.processBalls.runProcess(RobotConstants.processSpeed);
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
