package main.java.forecasting.impl.regression;

import java.security.Policy.Parameters;

import main.java.forecasting.api.PredictionException;
import main.java.forecasting.api.PredictionValues;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * The Class StandardRegression.
 * 
 * Extends the abstract class Regression and implements
 * the train and predict methods.
 *
 * @author Wouter Van Rossem
 */
public class StandardRegression extends Regression {
	
	/**
	 * Instantiates a new standard regression.
	 *
	 * @param parameters the parameters
	 */
	public StandardRegression(Parameters parameters) {
		
		trained = false;
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.regression.Regression#predict(int)
	 */
	@Override
	public PredictionValues predict(int nrOfPredictions) throws PredictionException {
		
		if (!trained) {
			throw new PredictionException();
		}
		
		double[] predictions = new double[nrOfPredictions];
		
		for (int i=0; i<nrOfPredictions ;i++) {
			/**
			 *  v(t+1) = w0*1 + w1*t
			 */
			predictions[i] = w.getEntry(0, 0) + ( w.getEntry(1, 0) * t++);
		}
	
		return new PredictionValues(predictions);
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.regression.Regression#train(double[])
	 */
	@Override
	public boolean train(double[] trainingSet) throws PredictionException {
		
		y = new Array2DRowRealMatrix(trainingSet);
		x = new Array2DRowRealMatrix(trainingSet.length, 2);
		
		for (int i=0; i<trainingSet.length; i++) {
			x.setEntry(i, 0, 1);
			x.setEntry(i, 1, i);
		}
		
		t = trainingSet.length;

		calcWeights();
		
		trained = true;
		
		return true;
	}
}
