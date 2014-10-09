package main.java.forecasting.impl.confidenceIntervals;

import main.java.forecasting.api.PredictionValues;

/**
 * The Class PredictionValuesWithConfidence.
 * 
 * @author Wouter Van Rossem
 */
public class PredictionValuesWithConfidence extends PredictionValues {

	/** The left side of the confidence interval. */
	double mLeft;
	
	/** The right side of the confidence interval. */
	double mRight;
	
	/**
	 * Instantiates a new prediction values with confidence interval.
	 *
	 * @param values the values of the predictions
	 * @param left the left side of the confidence interval
	 * @param right the right side of the confidence interval
	 */
	public PredictionValuesWithConfidence(double[] values, double left, double right) {
		
		super(values);
	
		mLeft  = left;
		mRight = right;
	}
	
	/**
	 * Gets the left side of the confidence interval.
	 *
	 * @return the left
	 */
	public double getLeft() {
		
		return mLeft;
	}
	
	/**
	 * Gets the right side of the confidence interval.
	 *
	 * @return the right
	 */
	public double getRight() {
		
		return mRight;
	}
}
