package main.java.forecasting.api;

import main.java.forecasting.impl.regression.AutoRegression;
import main.java.forecasting.impl.regression.StandardRegression;

/**
 * The Enum PredictionTechniqueType.
 * 
 * Defines the different types of predictions that are available.
 * Each prediction technique type has:
 * 		- A name
 * 		- The actual class implementing the technique
 * 		- The names of the parameters used by the technique
 * 
 * @author Wouter Van Rossem
 */
public enum PredictionTechniqueType {

	/** The Standard regression technique. */
	StandardRegression("Standard Regression", StandardRegression.class), 
	
	/** The Auto regression technique. */
	AutoRegression("Auto Regression", AutoRegression.class, new String [] {"p"});

	/** The different fields of a prediction technique. */
	private String                     name;
	private Class<PredictionTechnique> predictionTechnique;
	private String[]                   parameters;

	/**
	 * Instantiates a new prediction technique type
	 * without parameters.
	 *
	 * @param name the name of the technique
	 * @param predictionTechnique the class implementing the technique
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private PredictionTechniqueType(String name, Class predictionTechnique) {
		
		this.name = name;
		this.parameters = null;
		this.predictionTechnique = predictionTechnique;
	}

	/**
	 * Instantiates a new prediction technique type
	 * with parameters.
	 *
	 * @param name the name of the technique
	 * @param predictionTechnique the class implementing the technique
	 * @param parameters the names of the parameters used by the technique
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private PredictionTechniqueType(String name, Class predictionTechnique,
			String[] parameters) {
		
		this.name = name;
		this.predictionTechnique = predictionTechnique;
		this.parameters = parameters;
	}

	/**
	 * Gets the class implementing the prediction technique.
	 *
	 * @return the prediction technique
	 */
	public Class<PredictionTechnique> getPredictionTechnique() {
		
		return predictionTechnique;
	}

	/**
	 * Gets the name of the prediction technique.
	 *
	 * @return the name
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Checks for parameters.
	 *
	 * @return true, if successful
	 */
	public boolean hasParameters() {
		
		return parameters != null;
	}
	
	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public String[] getParameters() {
		
		return parameters;
	}

	/**
	 * Create a PredictionTechnique from a string, i.e. a name.
	 *
	 * @param string the string
	 * @return the prediction technique type
	 */
	public static PredictionTechniqueType fromString(String string) {
		
		if (string != null) {
			for (PredictionTechniqueType p : PredictionTechniqueType.values()) {
				if (string.equals(p.name)) {
					return p;
				}
			}
		}
		return null;
	}
}
