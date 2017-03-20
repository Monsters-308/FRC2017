
package org.usfirst.frc308.FRC2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

/**
 *
 */
public class VisionTurnToTarget extends Command {



	double setpointAngle = 0.0; // the angle we will rotate
	double t;
	boolean pass = true;
	boolean retry = false;
	Timer timer;


	public VisionTurnToTarget(boolean Retry) {
		retry = Retry;
		requires(Robot.chassis);
	}
	public void initialize() {
		RobotConstants.isAiming = true;
		double[] defaultValue = new double[0];
		double[] defaultValue2 = new double[0];
		double[] defaultValue3 = new double[0];
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", defaultValue);
		double[] targets2 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", defaultValue2);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", defaultValue3);
		int biggestTarget = Robot.vision.getLargestIndex(targets3);
		Robot.chassis.setupDrive();
		timer = new Timer();
		if (targets.length > 0) {
			SmartDashboard.putNumber("target l",targets.length );
			SmartDashboard.putNumber("centerX", targets[biggestTarget]);
			SmartDashboard.putNumber("centerY", targets2[biggestTarget]);
			setpointAngle = (targets[biggestTarget] - 150.0) / 120.0 * (0.5 * RobotConstants.cameraFieldOfView);
			t = 3;
			timer.start();
	//		Robot.chassis.setRotatePID(setpointAngle);
	//		Robot.chassis.arcade(0, RobotConstants.gyroPIDOutput);
			SmartDashboard.putNumber("setpointAngle", setpointAngle);
			SmartDashboard.putNumber("vision pid", RobotConstants.gyroPIDOutput);
	//	if (setpointAngle > 0) {
	//		Robot.chassis.setIAccumulator((0.08 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
	//	} else if (setpointAngle < 0) {
	//		Robot.chassis.setIAccumulator((-0.08 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
	//	}
		} else {
			pass = false;
			SmartDashboard.putNumber("centerX", -1);
			SmartDashboard.putNumber("centerY", -1);
		}
	}

	public void reInit() {
		pass = true;
		double[] defaultValue = new double[0];
		double[] defaultValue2 = new double[0];
		double[] defaultValue3 = new double[0];
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", defaultValue);
		double[] targets2 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerY", defaultValue2);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", defaultValue3);
		int biggestTarget = Robot.vision.getLargestIndex(targets3);
		Robot.chassis.setupDrive();
		timer = new Timer();
		if (targets.length > 0) {
			SmartDashboard.putNumber("centerX", targets[biggestTarget]);
			SmartDashboard.putNumber("centerY", targets2[biggestTarget]);
			setpointAngle = (targets[biggestTarget] - 200.0) / 120.0 * (0.5 * RobotConstants.cameraFieldOfView);
			t = 3;
			timer.start();
	//		Robot.chassis.setRotatePID(setpointAngle);
	//		Robot.chassis.arcade(.0, RobotConstants.gyroPIDOutput);
			SmartDashboard.putNumber("setpointAngle", setpointAngle);
			System.out.println("setpointAngle "  + setpointAngle);
		//	if (setpointAngle > 0) {
		//		Robot.chassis.setIAccumulator((0.05 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
		//	} else if (setpointAngle < 0) {
		//		Robot.chassis.setIAccumulator((-0.05 - RobotConstants.Kp * setpointAngle) / RobotConstants.Ki);
		//	}
		} else {
			pass = false;
			SmartDashboard.putNumber("centerX", -1);
			SmartDashboard.putNumber("centerY", -1);
//			Robot.chassis.arcade(0, RobotConstants.gyroPIDOutput);
		}
	}

	public boolean isFinished() {
		double[] targets = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("centerX", new double[0]);
		double[] targets3 = NetworkTable.getTable("GRIP/myContoursReport").getNumberArray("area", new double[0]);
		int biggestTarget = Robot.vision.getLargestIndex(targets3);
		if (targets.length == 0) {
			pass = false;
		}
		if (!pass) {
			if (retry || Robot.oi.joystick1.getRawButton(3)) {
				reInit();
			} else {
				return true;
			}
		} else if (timer.get() > t ) {
			if (!retry && !Robot.oi.joystick1.getRawButton(3)) {
				return true;
			} else if (targets.length > 0 && Math.abs(targets[biggestTarget] - 150) <= 10) {
				return true;
			} else {
				reInit();
			}
		}
		return false;

	}

	@Override
	protected void execute() {
		Robot.chassis.displayChasisData();
		}

	@Override
	protected void end() {
		RobotConstants.isAiming = false;
	}

	@Override
	protected void interrupted() {
		end();
	}
}
