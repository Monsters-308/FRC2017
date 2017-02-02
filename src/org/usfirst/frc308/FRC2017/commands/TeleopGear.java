package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class TeleopGear extends Command {

    
    public TeleopGear() {


        requires(Robot.gearDelivery);


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
      //  if button Claw 1 is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton1)) 
		Robot.gearDelivery.claw1_Open();
	    else  // close claw 1
		 Robot.gearDelivery.claw1_Close();
    
     //	if button Claw 2 is pressed
	    if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton2)) 
	    Robot.gearDelivery.claw2_Open();
        else  // close claw 2
	    Robot.gearDelivery.claw2_Close();
	
    //	if button Claw 3 is pressed
		if (Robot.oi.joystick1.getRawButton(RobotConstants.clawButton3)) 
		Robot.gearDelivery.claw3_Open();
	    else  // close claw 3
		Robot.gearDelivery.claw3_Close(); 
    	
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
