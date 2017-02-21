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
        int gyrowait; 
        EncoderFollower left;
        EncoderFollower right;
        private static double inchesToMeter = 0.0254 / 2; // 0.5 MG factors 
        private boolean drivef;
        //This has a max size of two
        Waypoint[] waypoints = new Waypoint[2];
        
        public AutonomousTrajectoryFollowerTwoPoint(double x0, double y0, double d0, double x1, double y1, double d1, boolean driveforward) {
            requires(Robot.chassis);
            waypoints[0] = new Waypoint(x0 * inchesToMeter, y0 * inchesToMeter, Math.toRadians(d0)); 
            waypoints[1] = new Waypoint(x1 * inchesToMeter, y1 * inchesToMeter, Math.toRadians(d1));
            drivef = driveforward;
            }  
  

        @Override
        protected void initialize() {
        	  timeout = new edu.wpi.first.wpilibj.Timer();
              timeout.start();
              Robot.chassis.setupDrive();
              RobotConstants.isTrajectory = true;
              Robot.chassis.setRotatePIDZero();
              Robot.chassis.resetEncoders();
              left.reset();
              right.reset();
              //  Example   
              // Waypoint[] points = new Waypoint[] {
              //  	    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
              //  	    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
              //  	    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
              //  	};
              //    
              //       Waypoint[] points = new Waypoint[]{
              //         		new Waypoint(0,0, Pathfinder.d2r(0)),
              //          		new Waypoint(5,0, Pathfinder.d2r(0))
              //  		   		}; 
           
              Waypoint[] points = waypoints;
              
                 //               Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
                 //      		Trajectory.Config.SAMPLES_LOW, 0.05, 8.0, 2.0, 60.0);
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
                     Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, 0.05, 1.5, 1.0, 10.0);
               
                   //Next Line causes crash if the Waypoints are not set correctly!!!!
                   Trajectory trajectory = Pathfinder.generate(points, config);

                   double wheelbase_width = 0.82; // MG updated

                   // Create the Modifier Object
                   TankModifier modifier = new TankModifier(trajectory).modify(wheelbase_width);

                   left = new EncoderFollower(modifier.getLeftTrajectory());
                   right = new EncoderFollower(modifier.getRightTrajectory());

                // Encoder Position is the current, cumulative position of your encoder. 
                //  If you're using an SRX, this will be the
                // 'getEncPosition' function.
                // 100 is the amount of encoder ticks per full revolution
                // 20 ticks per rev * 5:1 gear ratio = 100 
                // Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
                // 4" * .0254 = .1016

                   left.configureEncoder(Robot.chassis.getLeftEncoderPosition(), 400, 0.1016);
                   //Tom 2/18: Changed from Robot.  to -Robot.  (negative)
                   right.configureEncoder(Robot.chassis.getRightEncoderPosition(), 400, 0.1016);
                   
                   
                // The first argument is the proportional gain. Usually this will be quite high
                // The second argument is the integral gain. This is unused for motion profiling
                // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
                // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
                // trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
                // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
                   left.configurePIDVA(0.1, 0.0, 0.0, 1 / 1.5, 0);
                   right.configurePIDVA(0.1, 0.0, 0.0, 1 / 1.5, 0);

             
                   
              
                   t = new Timer();
                   gyrowait = 0; 
                   left.reset();
          	       right.reset();
                   Robot.chassis.brakemode(true);
                   t.schedule(new TimerTask() {
                 // Sample setup  	
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
                    	   SmartDashboard.putDouble("tra gyro 3", Robot.chassis.getGyroAngle());
                    	   if (gyrowait >= 1) {
                  	       double l = left.calculate(Robot.chassis.getLeftEncoderPosition());
                           double r = right.calculate(Robot.chassis.getRightEncoderPosition());
                           double desired_heading = Pathfinder.r2d(left.getHeading());
                           if (!drivef)  {
                        	   l = -l;
                               r = -r;
                               desired_heading = 179; 
                              }
                           Robot.chassis.getPIDController().setSetpoint(desired_heading / 4.0);
                           SmartDashboard.putDouble("tra gyro 2", Robot.chassis.getGyroAngle());
                           Robot.chassis.setDrive(l, r);
                           SmartDashboard.putDouble("tra head", desired_heading);
                           SmartDashboard.putDouble("tra right", r);
                           SmartDashboard.putDouble("tra left", l);
                    	   } else {
                    		gyrowait = gyrowait +1;  
                    	   }	
                    	 }
                   }, 0, 50); // end timed task
                   left.reset();
                   Robot.chassis.brakemode(false);
                   right.reset();
                   Robot.chassis.displayChasisData();
              
        }

        @Override
        protected void execute() {
     


        }
        

        @Override
        protected boolean isFinished() {
            if (left.isFinished() || right.isFinished() || timeout.get() > 10) {
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
