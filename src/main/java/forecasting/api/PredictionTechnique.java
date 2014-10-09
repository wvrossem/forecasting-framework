package main.java.forecasting.api;


/**
 * The Interface for a PredictionTechnique.
 *
 * @author Wouter Van Rossem
 */
public interface PredictionTechnique {
	
	/**
	 * Train the prediction technique using a training set.
	 *
	 * @param trainingSet the training set
	 * @return true, if successful
	 * @throws PredictionException the prediction exception
	 */
	public boolean train(double[] trainingSet) throws PredictionException;
	
	/**
	 * Predict values using the trained prediction technique.
	 *
	 * @param nrOfPredictions the number of predictions
	 * @return the prediction values
	 * @throws PredictionException the prediction exception
	 */
	public PredictionValues predict(int nrOfPredictions) throws PredictionException;
	
}
