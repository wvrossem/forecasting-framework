package main.java.forecasting.api;

/**
 * The Class PredictionValues.
 * 
 * Holds the actual values that are returned by a 
 * prediction technique.
 * This class can be subclassed to a add additional
 * values/information.
 * 
 * @author Wouter Van Rossem
 */
public class PredictionValues {
	
	/** The values. */
	private double[] values;
	
	/**
	 * Instantiates a new prediction values.
	 *
	 * @param values the values
	 */
	public PredictionValues(double[] values) {
		
		this.values = values;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public double[] getValues() {
		
		return values;
	}
}
