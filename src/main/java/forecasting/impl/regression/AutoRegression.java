package main.java.forecasting.impl.regression;

import java.util.ArrayList;
import java.util.List;

import main.java.forecasting.api.Parameters;
import main.java.forecasting.api.PredictionException;
import main.java.forecasting.api.PredictionValues;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * The Class AutoRegression.
 * 
 * Extends the abstract class Regression and implements
 * the train and predict methods.
 * 
 * @author Wouter Van Rossem
 */
public class AutoRegression extends Regression {
	
	/** Default value for the parameter p. */
	private static final int DEFAULT_P = 5;
	
	/** The predict value for a prediction on time t. */
	private List<Double> predictValues;
	
	/** The parameter p. */
	private int p;
	
	/**
	 * Instantiates a new auto regression.
	 *
	 * @param parameters the parameters
	 */
	public AutoRegression(Parameters parameters) {
		
		try {
			this.p = (int) parameters.getParameter("p");
		} catch (NullPointerException e) {
			this.p = DEFAULT_P;
		}
		
		this.predictValues = new ArrayList<Double>();
		this.trained       = false;
	}
	
	/**
	 * Predict a value for time t.
	 * 
	 * We first check if the value is already in the 
	 * prediction value list. If not we calculate it.
	 * This method is called recursively.
	 *
	 * @param t the time t for which to predict a value
	 * @return the predicted value
	 */
	private double predictT (int t) {
		
		double val;
		
		try {
			val = predictValues.get(t);
			return val;
		} catch (IndexOutOfBoundsException e) {
			
			val = w.getEntry(0, 0);
			for (int i = 1; i<w.getRowDimension(); i++) {
				val += w.getEntry(i, 0) * predictT(t-1);
			}
			predictValues.add(val);
			return val;
		}
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
		
		for (int i=0; i<nrOfPredictions; i++) {
			predictions[i] = predictT(t+i);
		}
		
		return new PredictionValues(predictions);
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.regression.Regression#train(double[])
	 */
	@Override
	public boolean train(double[] trainingSet) throws PredictionException {
		
		constructX(trainingSet);
		constructY(trainingSet);
		
		// TODO Remove debug
		System.out.println(x.toString());
		System.out.println(y.toString());
		
		calcWeights();
		
		for (double val : trainingSet) {
			predictValues.add(val);
		}
		
		t = trainingSet.length;
		
		trained = true;
		
		return false;
	}
	
	/**
	 * Construct the x matrix.
	 *
	 * @param data the data
	 */
	private void constructX(double[] data) {
		
		// Dimensions of matrix X
		int rowDim = data.length-p;
		int colDim = p+1;
		
		x = new Array2DRowRealMatrix(rowDim, colDim);
		
		// Construct first row, all 1
		for (int i=0; i<rowDim; i++) {
			x.setEntry(i, 0, 1);
		}
		
		int n;
		
		// Set other columns
		for (int i=1; i<colDim; i++) {		
			n=i-1;
			for (int z=0; z<rowDim; z++) {
				x.setEntry(z, i, data[n++]);
			}		
		}
	}
	
	/**
	 * Construct the y matrix.
	 *
	 * @param data the data
	 */
	private void constructY(double[] data) {
		
		int rowDim = data.length-p;
		
		y = new Array2DRowRealMatrix(rowDim, 1);
		
		for (int i=0; i<rowDim; i++) {
			y.setEntry(i, 0, data[i+p]);
		}
	}
}
