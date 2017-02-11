package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousDistanceDrive extends Command {

    double power;
    double distancetorun;

    /**
     * starts a command that drives robot until a specific distance is reached
     *
     * @param motorpower the motor power to drive from -1.0 to 1.0
     * @param time       how long to drive in seconds
     */
    public AutonomousDistanceDrive(double motorpower, double distance) {
        power = motorpower;
        distancetorun = distance;
        requires(Robot.chassis);
    }

    @Override
    protected void initialize() {
        Robot.chassis.setupDrive();
        Robot.chassis.resetEncoders();
    }

    @Override
    protected void execute() {
        Robot.chassis.arcadeDrive(power, 0);
    }

    @Override
    protected boolean isFinished() {
        if (Robot.chassis.getEncoderPosition() >= distancetorun) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        Robot.chassis.arcadeDrive(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
