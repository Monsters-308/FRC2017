package org.usfirst.frc308.FRC2017.utils;

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

}
