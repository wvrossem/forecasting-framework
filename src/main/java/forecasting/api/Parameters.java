package main.java.forecasting.api;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Parameters.
 * 
 * This class represents the parameters than can be given
 * to a prediction technique. It contains a map with the 
 * names of the parameters and their values.
 * 
 * @author Wouter Van Rossem
 */
public class Parameters {

	/** A map containing the parameters and their values. */
	protected Map<String, Double> parameters;

	/**
	 * Instantiates a new parameters object with a 
	 * given Map of parameters.
	 *
	 * @param parameters the parameters
	 */
	public Parameters(Map<String, Double> parameters) {
		
		this.parameters = parameters;
	}
	
	/**
	 * Instantiates a new parameters object with an
	 * empty Map.
	 */
	public Parameters() {
		
		this.parameters = new HashMap<String, Double>();
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public Map<String, Double> getParameters() {
		
		return parameters;
	}
	
	/**
	 * Sets the value of a certain parameter.
	 *
	 * @param parameter the name of the parameter
	 * @param value the value for the parameter
	 * @return true, if successful
	 */
	public boolean setParameter(String parameter, double value) {

		parameters.put(parameter, value);
		return true;
	}
	
	/**
	 * Gets the value of a parameter.
	 *
	 * @param parameter the name of the parameter
	 * @return the value of the parameter
	 */
	public double getParameter(String parameter) {
		
		return parameters.get(parameter);
	}
}
