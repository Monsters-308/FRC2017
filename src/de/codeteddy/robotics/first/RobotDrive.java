package de.codeteddy.robotics.first;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.hal.HAL;

import com.ctre.CANTalon;

public class RobotDrive implements MotorSafety {

    protected MotorSafetyHelper m_safetyHelper;
    public static final double kDefaultExpirationTime = 0.1D;
    public static final double kDefaultSensitivity = 0.5D;
    public static final double kDefaultMaxOutput = 1.0D;
    protected static final int kMaxNumberOfMotors = 4;
    protected double m_sensitivity;
    protected double m_maxOutput;
    //Left side
    protected SpeedController m_left1;//AK
    protected SpeedController m_left2;//AK
    protected SpeedController m_left3;//AK
    //Rigth side
    protected SpeedController m_rigth1;//AK
    protected SpeedController m_rigth2;//AK
    protected SpeedController m_rigth3;//AK
    protected boolean m_allocatedSpeedControllers;

    public static enum MotorType {
        kLeft1(0), kLeft2(1), kLeft3(2), kRigth4(3), kRigth5(4), kRigth6(5);//AK

        public final int value;

        private MotorType(int value) {
            this.value = value;
        }
    }

    protected static boolean kArcadeRatioCurve_Reported = false;
    protected static boolean kTank_Reported = false;
    protected static boolean kArcadeStandard_Reported = false;
    protected static boolean kMecanumCartesian_Reported = false;
    protected static boolean kMecanumPolar_Reported = false;

    /** We dont use that
     *
     * @param leftMotorChannel
     * @param rightMotorChannel
     * @deprecated
     */

    /**
     * public RobotDrive(int leftMotorChannel, int rightMotorChannel)
     * {
     * this.m_sensitivity = 0.5D;
     * this.m_maxOutput = 1.0D;
     * this.m_frontLeftMotor = null;
     * this.m_rearLeftMotor = new Talon(leftMotorChannel);
     * this.m_frontRightMotor = null;
     * this.m_rearRightMotor = new Talon(rightMotorChannel);
     * this.m_allocatedSpeedControllers = true;
     * setupMotorSafety();
     * drive(0.0D, 0.0D);
     * }
     **/

    public RobotDrive(int left1, int left2, int left3, int rigth4, int rigth5, int rigth6) //AK
    {
        this.m_sensitivity = 0.5D;
        this.m_maxOutput = 1.0D;
        this.m_left1 = new Talon(left1);    //AK
        this.m_left2 = new Talon(left2);    //AK
        this.m_left3 = new Talon(left3);    //AK
        this.m_rigth1 = new Talon(rigth4);    //AK
        this.m_rigth2 = new Talon(rigth5);    //AK
        this.m_rigth3 = new Talon(rigth6);    //AK
        this.m_allocatedSpeedControllers = true;
        setupMotorSafety();
        //drive(0.0D, 0.0D);
    }
    
    public RobotDrive(CANTalon left1, CANTalon left2 ,CANTalon left3, CANTalon rigth4, CANTalon rigth5, CANTalon rigth6){
    	this.m_sensitivity = 0.5D;
        this.m_maxOutput = 1.0D;
        this.m_left1 = left1;    //AK
        this.m_left2 = left2;   //AK
        this.m_left3 = left3;    //AK
        this.m_rigth1 = rigth4;    //AK
        this.m_rigth2 = rigth5;    //AK
        this.m_rigth3 = rigth6;    //AK
        this.m_allocatedSpeedControllers = true;
    }

    /**

     public RobotDrive(SpeedController leftMotor, SpeedController rightMotor)
     {
     if ((leftMotor == null) || (rightMotor == null))
     {
     this.m_rearLeftMotor = (this.m_rearRightMotor = null);
     throw new NullPointerException("Null motor provided");
     }
     this.m_frontLeftMotor = null;
     this.m_rearLeftMotor = leftMotor;
     this.m_frontRightMotor = null;
     this.m_rearRightMotor = rightMotor;
     this.m_sensitivity = 0.5D;
     this.m_maxOutput = 1.0D;
     this.m_allocatedSpeedControllers = false;
     setupMotorSafety();
     drive(0.0D, 0.0D);
     }
     **/

    /**
     public RobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor)
     {
     this.m_frontLeftMotor = ((SpeedController)Objects.requireNonNull(frontLeftMotor, "frontLeftMotor cannot be null"));
     this.m_rearLeftMotor = ((SpeedController)Objects.requireNonNull(rearLeftMotor, "rearLeftMotor cannot be null"));
     this.m_frontRightMotor = ((SpeedController)Objects.requireNonNull(frontRightMotor, "frontRightMotor cannot be null"));
     this.m_rearRightMotor = ((SpeedController)Objects.requireNonNull(rearRightMotor, "rearRightMotor cannot be null"));
     this.m_sensitivity = 0.5D;
     this.m_maxOutput = 1.0D;
     this.m_allocatedSpeedControllers = false;
     setupMotorSafety();
     drive(0.0D, 0.0D);
     }
     **/

    /**
     * public void drive(double outputMagnitude, double curve)
     * {
     * if (!kArcadeRatioCurve_Reported)
     * {
     * HAL.report(31, getNumMotors(), 3);
     * <p>
     * kArcadeRatioCurve_Reported = true;
     * }
     * double rightOutput;
     * double leftOutput;
     * double rightOutput;
     * if (curve < 0.0D)
     * {
     * double value = Math.log(-curve);
     * double ratio = (value - this.m_sensitivity) / (value + this.m_sensitivity);
     * if (ratio == 0.0D) {
     * ratio = 1.0E-10D;
     * }
     * double leftOutput = outputMagnitude / ratio;
     * rightOutput = outputMagnitude;
     * }
     * else
     * {
     * double rightOutput;
     * if (curve > 0.0D)
     * {
     * double value = Math.log(curve);
     * double ratio = (value - this.m_sensitivity) / (value + this.m_sensitivity);
     * if (ratio == 0.0D) {
     * ratio = 1.0E-10D;
     * }
     * double leftOutput = outputMagnitude;
     * rightOutput = outputMagnitude / ratio;
     * }
     * else
     * {
     * leftOutput = outputMagnitude;
     * rightOutput = outputMagnitude;
     * }
     * }
     * setLeftRightMotorOutputs(leftOutput, rightOutput);
     * }
     **/

    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getY(), rightStick.getY(), true);
    }

    public void tankDrive(GenericHID leftStick, GenericHID rightStick, boolean squaredInputs) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getY(), rightStick.getY(), squaredInputs);
    }

    public void tankDrive(GenericHID leftStick, int leftAxis, GenericHID rightStick, int rightAxis) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getRawAxis(leftAxis), rightStick.getRawAxis(rightAxis), true);
    }

    public void tankDrive(GenericHID leftStick, int leftAxis, GenericHID rightStick, int rightAxis, boolean squaredInputs) {
        if ((leftStick == null) || (rightStick == null)) {
            throw new NullPointerException("Null HID provided");
        }
        tankDrive(leftStick.getRawAxis(leftAxis), rightStick.getRawAxis(rightAxis), squaredInputs);
    }

    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
        if (!kTank_Reported) {
            HAL.report(31, getNumMotors(), 4);

            kTank_Reported = true;
        }
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);
        if (squaredInputs) {
            if (leftValue >= 0.0D) {
                leftValue *= leftValue;
            } else {
                leftValue = -(leftValue * leftValue);
            }
            if (rightValue >= 0.0D) {
                rightValue *= rightValue;
            } else {
                rightValue = -(rightValue * rightValue);
            }
        }
        setLeftRightMotorOutputs(leftValue, rightValue);
    }

    public void tankDrive(double leftValue, double rightValue) {
        tankDrive(leftValue, rightValue, true);
    }

    public void arcadeDrive(GenericHID stick, boolean squaredInputs) {
        arcadeDrive(stick.getY(), stick.getX(), squaredInputs);
    }

    public void arcadeDrive(GenericHID stick) {
        arcadeDrive(stick, true);
    }

    public void arcadeDrive(GenericHID moveStick, int moveAxis, GenericHID rotateStick, int rotateAxis, boolean squaredInputs) {
        double moveValue = moveStick.getRawAxis(moveAxis);
        double rotateValue = rotateStick.getRawAxis(rotateAxis);

        arcadeDrive(moveValue, rotateValue, squaredInputs);
    }

    public void arcadeDrive(GenericHID moveStick, int moveAxis, GenericHID rotateStick, int rotateAxis) {
        arcadeDrive(moveStick, moveAxis, rotateStick, rotateAxis, true);
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
    	
    	//Check's if arcade drive is registered
        if (!kArcadeStandard_Reported) {
        	//IF NOT, it registers it
            HAL.report(31, getNumMotors(), 1);

            kArcadeStandard_Reported = true;
        }
        
        //Check's that both values are between -1.0 and 1
        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
        
        //Usually true --> why?
        if (squaredInputs) {
        	
        	//If positive
            if (moveValue >= 0.0D) {
            	
                moveValue *= moveValue;
                //return the double of it
            } else {
            	//If negative , return the  negative square of it
                moveValue = -(moveValue * moveValue);
            }
            
            //The same down here
            if (rotateValue >= 0.0D) {
                rotateValue *= rotateValue;
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }
        
        
        double rightMotorSpeed;
        double leftMotorSpeed;
        
        
        if (moveValue > 0.0D) {
            //double rightMotorSpeed;
            if (rotateValue > 0.0D) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            
            if (rotateValue > 0.0D) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        
        
        //Put the values to the motors
        setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }

    public void arcadeDrive(double moveValue, double rotateValue) {
        arcadeDrive(moveValue, rotateValue, true);
    }


    /**
     public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle)
     {
     if (!kMecanumCartesian_Reported)
     {
     HAL.report(31, getNumMotors(), 6);

     kMecanumCartesian_Reported = true;
     }
     double xIn = x;

     double yIn = y;

     yIn = -yIn;

     double[] rotated = rotateVector(xIn, yIn, gyroAngle);
     xIn = rotated[0];
     yIn = rotated[1];

     double[] wheelSpeeds = new double[4];
     wheelSpeeds[MotorType.kLeft1.value] = (xIn + yIn + rotation);
     wheelSpeeds[MotorType.kFrontRight.value] = (-xIn + yIn - rotation);
     wheelSpeeds[MotorType.kRearLeft.value] = (-xIn + yIn + rotation);
     wheelSpeeds[MotorType.kRearRight.value] = (xIn + yIn - rotation);

     normalize(wheelSpeeds);
     this.m_frontLeftMotor.set(wheelSpeeds[MotorType.kLeft1.value] * this.m_maxOutput);
     this.m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * this.m_maxOutput);
     this.m_left1.set(wheelSpeeds[MotorType.kRearLeft.value] * this.m_maxOutput);
     this.m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * this.m_maxOutput);
     if (this.m_safetyHelper != null) {
     this.m_safetyHelper.feed();
     }
     }
     **/

    /**
     public void mecanumDrive_Polar(double magnitude, double direction, double rotation)
     {
     if (!kMecanumPolar_Reported)
     {
     HAL.report(31, getNumMotors(), 5);

     kMecanumPolar_Reported = true;
     }
     magnitude = limit(magnitude) * Math.sqrt(2.0D);

     double dirInRad = (direction + 45.0D) * 3.14159D / 180.0D;
     double cosD = Math.cos(dirInRad);
     double sinD = Math.sin(dirInRad);

     double[] wheelSpeeds = new double[4];
     wheelSpeeds[MotorType.kLeft1.value] = (sinD * magnitude + rotation);
     wheelSpeeds[MotorType.kFrontRight.value] = (cosD * magnitude - rotation);
     wheelSpeeds[MotorType.kRearLeft.value] = (cosD * magnitude + rotation);
     wheelSpeeds[MotorType.kRearRight.value] = (sinD * magnitude - rotation);

     normalize(wheelSpeeds);

     this.m_frontLeftMotor.set(wheelSpeeds[MotorType.kLeft1.value] * this.m_maxOutput);
     this.m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * this.m_maxOutput);
     this.m_left1.set(wheelSpeeds[MotorType.kRearLeft.value] * this.m_maxOutput);
     this.m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * this.m_maxOutput);
     if (this.m_safetyHelper != null) {
     this.m_safetyHelper.feed();
     }
     }

     **/

    /**
     * void holonomicDrive(double magnitude, double direction, double rotation)
     * {
     * mecanumDrive_Polar(magnitude, direction, rotation);
     * }
     **/

    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        //AK
        if ((this.m_left1 == null) || (this.m_left2 == null) || (this.m_left3 == null)) {
            throw new NullPointerException("Null motor provided");
        }
        if ((this.m_rigth1 == null) || (this.m_rigth2 == null) || (this.m_rigth3 == null)) {
            throw new NullPointerException("No motor is provided");
        }
        if (this.m_left1 != null) {
            this.m_left1.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_left2 != null) {
            this.m_left2.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_left3 != null) {
            this.m_left3.set(limit(leftOutput) * this.m_maxOutput);
        }
        if (this.m_rigth1 != null) {
            this.m_rigth1.set(-limit(rightOutput) * this.m_maxOutput);
        }
        if (this.m_rigth2 != null) {
            this.m_rigth2.set(-limit(rightOutput) * this.m_maxOutput);
        }
        if (this.m_rigth3 != null) {
            this.m_rigth3.set(-limit(rightOutput) * this.m_maxOutput);
        }


        //this.m_left1.set(limit(leftOutput) * this.m_maxOutput);
        //if (this.m_frontRightMotor != null) {
        //  this.m_frontRightMotor.set(-limit(rightOutput) * this.m_maxOutput);
        //}
        //this.m_rearRightMotor.set(-limit(rightOutput) * this.m_maxOutput);

        if (this.m_safetyHelper != null) {
            this.m_safetyHelper.feed();
        }
    }

    protected static double limit(double num) {
        if (num > 1.0D) {
            return 1.0D;
        }
        if (num < -1.0D) {
            return -1.0D;
        }
        return num;
    }

    protected static void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < 4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0D) {
            for (int i = 0; i < 4; i++) {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

    protected static double[] rotateVector(double x, double y, double angle) {
        double cosA = Math.cos(angle * 0.017453277777777776D);
        double sinA = Math.sin(angle * 0.017453277777777776D);
        double[] out = new double[2];
        out[0] = (x * cosA - y * sinA);
        out[1] = (x * sinA + y * cosA);
        return out;
    }


    /**
     * @param motor
     * @param boolean isInverted is the state of inverted (true == inverted, false == normal)
     * @author Alex
     * //AK
     */

    public void setInvertedMotor(MotorType motor, boolean isInverted) {
        switch (motor) {
            case kLeft1:
                this.m_left1.setInverted(isInverted);
                break;
            case kLeft2:
                this.m_left2.setInverted(isInverted);
                break;
            case kLeft3:
                this.m_left3.setInverted(isInverted);
                break;
            case kRigth4:
                this.m_rigth1.setInverted(isInverted);
                break;
            case kRigth5:
                this.m_rigth2.setInverted(isInverted);
                break;
            case kRigth6:
                this.m_rigth3.setInverted(isInverted);
                break;
            default:
                throw new IllegalArgumentException("Illegal motor type: " + motor);
        }
    }

    public void setSensitivity(double sensitivity) {
        this.m_sensitivity = sensitivity;
    }

    public void setMaxOutput(double maxOutput) {
        this.m_maxOutput = maxOutput;
    }

    /**
     * @author Alex
     * //AK
     */

    public void free() {
        if (this.m_allocatedSpeedControllers) {
            if (this.m_left1 != null) {
                ((PWM) this.m_left1).free();
            }
            if (this.m_left2 != null) {
                ((PWM) this.m_left2).free();
            }
            if (this.m_left3 != null) {
                ((PWM) this.m_left3).free();
            }
            if (this.m_rigth1 != null) {
                ((PWM) this.m_rigth1).free();
            }
            if (this.m_rigth2 != null) {
                ((PWM) this.m_rigth2).free();
            }
            if (this.m_rigth3 != null) {
                ((PWM) this.m_rigth3).free();
            }
        }
    }

    public void setExpiration(double timeout) {
        this.m_safetyHelper.setExpiration(timeout);
    }

    public double getExpiration() {
        return this.m_safetyHelper.getExpiration();
    }

    public boolean isAlive() {
        return this.m_safetyHelper.isAlive();
    }

    public boolean isSafetyEnabled() {
        return this.m_safetyHelper.isSafetyEnabled();
    }

    public void setSafetyEnabled(boolean enabled) {
        this.m_safetyHelper.setSafetyEnabled(enabled);
    }

    public String getDescription() {
        return "Robot Drive";
    }
    
    
    /**
     * 
     * @return String containing the name of the author 
     */
    public String getAuthor(){
    	return "Alexander Kaschta";
    }

    /**
     * @author Alex
     */
    public void stopMotor() {
        if (this.m_left1 != null) {
            this.m_left1.stopMotor();
        }
        if (this.m_left2 != null) {
            this.m_left2.stopMotor();
        }
        if (this.m_left3 != null) {
            this.m_left3.stopMotor();
        }
        if (this.m_rigth1 != null) {
            this.m_rigth1.stopMotor();
        }
        if (this.m_rigth2 != null) {
            this.m_rigth2.stopMotor();
        }
        if (this.m_rigth3 != null) {
            this.m_rigth3.stopMotor();
        }

        if (this.m_safetyHelper != null) {
            this.m_safetyHelper.feed();
        }
    }

    private void setupMotorSafety() {
        this.m_safetyHelper = new MotorSafetyHelper(this);
        this.m_safetyHelper.setExpiration(0.1D);
        this.m_safetyHelper.setSafetyEnabled(true);
    }

    /**
     * @return Number of Montors on the robot
     * @author Alex
     */

    protected int getNumMotors() {
        int motors = 0;
        if (this.m_left1 != null) {
            motors++;
        }
        if (this.m_left2 != null) {
            motors++;
        }
        if (this.m_left3 != null) {
            motors++;
        }
        if (this.m_rigth1 != null) {
            motors++;
        }
        if (this.m_rigth2 != null) {
            motors++;
        }
        if (this.m_rigth3 != null) {
            motors++;
        }
        return motors;
    }
}
