package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopLights extends Command {
public Timer intakeLightTimer = new Timer();
    public TeleopLights() {

        requires(Robot.lights);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (RobotConstants.intakeMode == true) {
			Robot.lights.setIntakeLights();
			RobotConstants.intakeLightState = true;
		} else {
			if (RobotConstants.shooterMode == true) {
				intakeLightTimer.start();
				RobotConstants.intakeLightState = false;
				Robot.lights.disableIntakeLights();
				if (intakeLightTimer.get() > 500 && RobotConstants.intakeLightState == false) {
					Robot.lights.setIntakeLights();
					RobotConstants.intakeLightState = true;
					intakeLightTimer.reset();
				}else{
					if(intakeLightTimer.get() > 500 && RobotConstants.intakeLightState == true){
						Robot.lights.disableIntakeLights();
						RobotConstants.intakeLightState = false;
						intakeLightTimer.reset();
					}else{
						Robot.lights.disableIntakeLights();
					}
				}
			}
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
