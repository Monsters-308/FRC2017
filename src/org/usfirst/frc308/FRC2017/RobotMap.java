package org.usfirst.frc308.FRC2017;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.RobotDrive;
import de.codeteddy.robotics.first.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @version 1.1
 * @author Alex
 * @author Autogenerated
 */
public class RobotMap {
    
    public static CANTalon chassisCANTalon_1 = new CANTalon(1);
    public static CANTalon chassisCANTalon_2= new CANTalon(2);
    public static CANTalon chassisCANTalon_3= new CANTalon(3);
    public static CANTalon chassisCANTalon_4= new CANTalon(4);
    public static CANTalon chassisCANTalon_5= new CANTalon(5);
    public static CANTalon chassisCANTalon_6= new CANTalon(6);
    // public static RobotDrive chassisRobotDrive6= new RobotDrive(chassisCANTalon_1, chassisCANTalon_2, chassisCANTalon_4, chassisCANTalon_5 );
    public static RobotDrive chassisRobotDrive6 = new RobotDrive(chassisCANTalon_1, chassisCANTalon_2, chassisCANTalon_3, chassisCANTalon_4, chassisCANTalon_5, chassisCANTalon_6);
    public static AnalogGyro chassisAnalogGyro1;
    public static CANTalon intakeCANTalon_Ball_1 = new CANTalon(15);
    public static CANTalon intakeCANTalon_Ball_2 = new CANTalon(16);
    public static CANTalon intakeCANTalon_Gear_1 = new CANTalon(17);
    public static Solenoid gearDeliverySolenoid_1 = new Solenoid(0, 0);
    public static Solenoid gearDeliverySolenoid_2 = new Solenoid(0, 1);
    public static Solenoid gearDeliverySolenoid_3 = new Solenoid(0, 2);
    public static CANTalon climbCANTalon = new CANTalon(20);
    public static CANTalon processBallsCANTalon = new CANTalon(19);
    public static CANTalon shooterCANTalon_1 = new CANTalon(10);
    public static Compressor pneumaticsCompressor = new Compressor(0);
    public static ADXRS450_Gyro spiGyro_1 = new ADXRS450_Gyro();

}
