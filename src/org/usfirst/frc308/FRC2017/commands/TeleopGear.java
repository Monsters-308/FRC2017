package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TeleopGear extends Command {
    private Timer extendTimer = new Timer();
    private Timer closeTimer = new Timer();
    private Timer doorTimer = new Timer();
    private static boolean buttonExtendState = false;

    public TeleopGear() {

        requires(Robot.gearDelivery);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        RobotConstants.clawExtendState = false;
        RobotConstants.clawOpenState = false;
        RobotConstants.clawDoorState = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // if button to extend/retract claw is pressed
        if (Robot.oi.joystick1.getRawButton(RobotConstants.extendClawButton))
            if (extendTimer.get() == 0) {
                if (RobotConstants.clawExtendState == false) {
                    RobotConstants.clawExtendState = true;
                    Robot.gearDelivery.extendClaw();

                } else { // If the shooter mode was on then toggle off
                    Robot.gearDelivery.retractClaw();
                    RobotConstants.clawExtendState = false;
                }

                // Start Timer to make sure the toggle happens only once
                extendTimer.start();
            }
        if (extendTimer.get() >= RobotConstants.extendTimer_timer) {
            extendTimer.stop();
            extendTimer.reset();
        }

        // if button to open/close claw is pressed
        if (Robot.oi.joystick1.getRawButton(RobotConstants.closeClawButton))
            if (closeTimer.get() == 0) {
                if (RobotConstants.clawOpenState == false) {
                    RobotConstants.clawOpenState = true;
                    Robot.gearDelivery.openClaw();

                } else { // If the shooter mode was on then toggle off
                    Robot.gearDelivery.closeClaw();
                    RobotConstants.clawOpenState = false;
                }

                // Start Timer to make sure the toggle happens only once
                closeTimer.start();
            }
        if (closeTimer.get() >= RobotConstants.closeTimer_timer) {
            closeTimer.stop();
            closeTimer.reset();
        }

        // if button to open/close passive assist doors is pressed
        if (Robot.oi.joystick1.getRawButton(RobotConstants.clawDoorButton))
            if (doorTimer.get() == 0) {
                if (RobotConstants.clawDoorState == false) {
                    RobotConstants.clawDoorState = true;
                    Robot.gearDelivery.closeClawDoor();

                } else { // If the shooter mode was on then toggle off
                    Robot.gearDelivery.openClawDoor();
                    RobotConstants.clawDoorState = false;
                }

                // Start Timer to make sure the toggle happens only once
                doorTimer.start();
            }
        if (doorTimer.get() >= RobotConstants.doorTimer_timer) {
            doorTimer.stop();
            doorTimer.reset();
        }

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
