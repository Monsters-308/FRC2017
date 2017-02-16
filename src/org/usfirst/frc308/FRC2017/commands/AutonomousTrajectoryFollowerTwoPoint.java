package org.usfirst.frc308.FRC2017.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc308.FRC2017.Robot;
import org.usfirst.frc308.FRC2017.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class AutonomousTrajectoryFollowerTwoPoint extends Command {

    edu.wpi.first.wpilibj.Timer timeout;
    Timer t;
    EncoderFollower left;
    EncoderFollower right;

    public AutonomousTrajectoryFollowerTwoPoint(double x0, double y0, double d0, double x1, double y1, double d1) {
        requires(Robot.chassis);

    }

    @Override
    protected void initialize() {
        timeout = new edu.wpi.first.wpilibj.Timer();
        timeout.start();
        RobotConstants.isTrajectory = true;
      //  Example   
      // Waypoint[] points = new Waypoint[] {
      //  	    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
      //  	    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
      //  	    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
      //  	};
//
 // 
      Waypoint[] points = new Waypoint[]{new Waypoint(0, 0, 0), new Waypoint(25, 20, 0.9)};

     // Prepare the Trajectory for Generation.
        //
        // Arguments: 
        // Fit Function:        FIT_HERMITE_CUBIC or FIT_HERMITE_QUINTIC
        // Sample Count:        PATHFINDER_SAMPLES_HIGH (100 000)
        //                              PATHFINDER_SAMPLES_LOW  (10 000)
        //                              PATHFINDER_SAMPLES_FAST (1 000)
        // Time Step:           0.001 Seconds
        // Max Velocity:        15 m/s
        // Max Acceleration:    10 m/s/s
        // Max Jerk:            60 m/s/s/s
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
                Trajectory.Config.SAMPLES_LOW, 0.05, 8.0, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);

     // The distance between the left and right sides of the wheelbase is 0.6m
        double wheelbase_width = 0.5; // **** NEED to UPDATE

        // Create the Modifier Object
        TankModifier modifier = new TankModifier(trajectory);

        // Generate the Left and Right trajectories using the original
        // trajectory
        // as the center
        modifier.modify(wheelbase_width);

        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());

     // Encoder Position is the current, cumulative position of your encoder. 
     //  If you're using an SRX, this will be the
     // 'getEncPosition' function.
     // 400 is the amount of encoder ticks per full revolution
     // 20 ticks per rev * 5:1 gear ratio = 100 x 4x sampling
     // Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
     // 4" * .0254 = .1016
      
        left.configureEncoder(Robot.chassis.getLeftEncoderPosition(), 400, 0.1016);
        right.configureEncoder(-Robot.chassis.getRightEncoderPosition(), 400, 0.1016);
        
        
     // The first argument is the proportional gain. Usually this will be quite high
     // The second argument is the integral gain. This is unused for motion profiling
     // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
     // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
     // trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
     // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
        left.configurePIDVA(0.1, 0.0, 0.0, 1 / 8.0, 0);
        right.configurePIDVA(0.1, 0.0, 0.0, 1 / 8.0, 0);
       
        Robot.chassis.setRotatePIDZero();
        t = new Timer();
        t.schedule(new TimerTask() {
      // Sample seup  	
      //  	double l = left.calculate(encoder_position_left);
      //  	double r = right.calculate(encoder_position_right);
      // 
      //  	double gyro_heading = ... your gyro code here ...    // Assuming the gyro is giving a value in degrees
      //  	double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
      //
      //  	double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
      //  	double turn = 0.8 * (-1.0/80.0) * angleDifference;
      //
      //  	setLeftMotors(l + turn);
      //  	setRightMotors(r - turn);
      //////   End sample setup  	
            @Override
            public void run() {
                double l = left.calculate(Robot.chassis.getLeftEncoderPosition());
                double r = right.calculate(-Robot.chassis.getRightEncoderPosition());
                double desired_heading = Pathfinder.r2d(left.getHeading());
                Robot.chassis.setSetpoint(desired_heading / 4.0);
                Robot.chassis.setDrive(l, r);
            }
        }, 0, 50); // end timed task
    }

    @Override
    protected void execute() {
        Robot.chassis.displayChasisData();
    }

    @Override
    protected boolean isFinished() {
        if (left.isFinished() || right.isFinished() || timeout.get() > 5.5) {
            return true;
        }
        return false;
    }

    @Override
    protected void end() {
        timeout.stop();
        timeout.reset();

        t.cancel();
        Robot.chassis.setRotatePIDZero();
        RobotConstants.isTrajectory = false;
    }

    @Override
    protected void interrupted() {
        end();
    }

}
