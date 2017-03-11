package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousVisionDrive extends Command{
	
	private double timeout;
	private Timer timer;
	private double drive;
	
	public AutonomousVisionDrive(double distance){
		this.timeout = timeout;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		timer = new Timer();
		timer.start();
		Robot.chassis.setupDrive();
	}
	
	@Override
	protected void execute() {
		super.execute();
		Robot.chassis.arcade(drive, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.chassis.setRotatePIDZero();
	}
	
	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

}
