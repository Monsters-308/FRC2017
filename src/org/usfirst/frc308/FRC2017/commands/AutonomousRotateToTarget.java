package org.usfirst.frc308.FRC2017.commands;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousRotateToTarget extends Command {

    boolean shouldRetry = false;

    public AutonomousRotateToTarget(boolean shouldRetry) {
        this.shouldRetry = shouldRetry;
        requires(Robot.chassis);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        super.end();
        RobotConstants.isAutonomousAiming = false;
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }

}
