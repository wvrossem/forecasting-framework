package main.java.forecasting.tools;

/**
 * The Class MathTools.
 * 
 * Provides some commenly used mathematical functions.
 * 
 * @author Wouter Van Rossem
 */
public class MathTools {
	
	/**
	 * Calculate the mean from an array of doubles.
	 *
	 * @param values the values
	 * @return the mean
	 */
	public static double calcMean(double[] values) {
		
		double sum = 0;
		for (double prediction : values) {
			sum += prediction;
		}
		
		return sum / values.length;	
	}
	
	/**
	 * Calculate the standard deviation for an array of doubles.
	 *
	 * @param medians the values
	 * @return the standard deviation
	 */
	public static double calcStandardDeviation(double[] medians) {
		
		double mean = calcMean(medians);
		double sum  = 0;
		
		for (double val : medians) {
			sum += Math.pow(val - mean, 2);
		}
		
		sum /= (medians.length - 1);
		
		return Math.sqrt(sum);
	}

	/**
	 * Find the maximum in an array of doubles.
	 *
	 * @param data the data
	 * @return the double
	 */
	public static double calcMax(Double[] data) {
		
		double max = data[0];
		
		for (double d : data) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}
	
	/**
	 * Normalize data.
	 *
	 * @param data the data
	 * @return the normalized data
	 */
	public static float[] normalizeData(int[][] data) {		
		int t = 0;
		float[] vector = new float[3];
		float[][] xvectors = new float[data.length][3];
		float[][] yvectors = new float[data.length][3];
		float[][] zvectors = new float[data.length][3];
		for (int[] vx : data) {
			int axe = 0;
			for (int el : vx) {
				vector[0] = (float) t;
				vector[1] = (float) el;
				vector[2] = (float) axe++;
				switch (axe) {
					case 0: xvectors[t] = normalizeVector(vector); break;
					case 1: yvectors[t] = normalizeVector(vector); break;
					case 2: zvectors[t] = normalizeVector(vector); break;
				}
			}
			t++;
		}
		float vectors[][] = {
				flattenArray(xvectors),
				flattenArray(yvectors),
				flattenArray(zvectors)
		};
		
		return flattenArray(vectors);
	}
	
	/**
	 * Normalize a vector.
	 *
	 * @param vector the vector
	 * @return the normalized vector
	 */
	public static float[] normalizeVector(float[] vector) {
		
		// Calculate vector length
		float sum = 0.0f;
		for (float  el : vector) {
			sum += Math.pow(el, 2);
		}
		float length = (float) Math.sqrt(sum);
		// Normalize vector
		float[] normalizedVector = vector;
		for (int i = 0; i < vector.length; i++) {
			normalizedVector[i] = vector[i] / length;
		}
		return normalizedVector;
	}
	
	/**
	 * Flatten an array.
	 *
	 * @param multiArray the multi array
	 * @return the a flattened array
	 */
	public static float[] flattenArray(float[][] multiArray) {
		
		float[] singleArray = new float[multiArray.length*3];
		int n = 0;
		for (int i=0; i<multiArray.length; i++) { 
			for (int x=0; x<multiArray[i].length; x++) {
				singleArray[n++] = multiArray[i][x];	
			};
		};
		return singleArray;
	}
}
