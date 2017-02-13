package org.usfirst.frc308.FRC2017.utils;

import org.usfirst.frc308.FRC2017.RobotConstants;

public class MathUtils {
	
	/**
	 * All math-methods are going to live here
	 */
	
	public static int getLargestIndex(double[] input) {
        if (input.length == 0) {
            return -1;
        }
        int biggest = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i] > input[biggest]) {
                biggest = i;
            }
        }
        return biggest;
    }
	
	/**
	 * 
	 * @author Alexander Kaschta
	 * @param number1 A number
	 * @param number2 Another number
	 * @return the absolute diffrence between these two numbers
	 */
	public static double getDiffrence(double number1, double number2){
		return Math.abs(number1 - number2);
	}
	

	/**
	 * Converts any pixel count from vision into a angle (that can be used to turn the robot)
	 * @author Alexander Kaschta
	 * @param pixels Amount of pixel you want to convert into degrees
	 * @return a double that represents an amount of degrees
	 */
	public static double pxToDeg(double pixels){
		
		//Handle any pixel counts that is bigger or equal that is bigger than the half of the display-size in x
		if(Math.abs(pixels) >= (RobotConstants.x / 2)){
			if(pixels > 0){
				//Is positive
				return RobotConstants.cameraFieldOfView / 2;
			}
			else{
				//It's negative
				return -(RobotConstants.cameraFieldOfView / 2);
			}
			
		}else
		{
			//Calculate how much degrees equal one pixel
			double onePixelInDegree = RobotConstants.cameraFieldOfView / RobotConstants.x;
			//Calculate the amount of all pixels
			return onePixelInDegree * pixels;
		}
	}

}
