package main.java.forecasting.impl.regression;

import main.java.forecasting.api.PredictionException;
import main.java.forecasting.api.PredictionTechnique;
import main.java.forecasting.api.PredictionValues;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * The Abstract Class Regression.
 * 
 * Groups some common functionalities used by different regression
 * techniques such as the different matrices, t and the calculation
 * of matrix w.
 * 
 * @author Wouter Van Rossem
 */
public abstract class Regression implements PredictionTechnique {
	
	/** The x,y and w matrices. */
	protected RealMatrix x;
	protected RealMatrix y;
	protected RealMatrix w;
	
	/** The int t, representing time. */
	protected int t;
	
	/** The trained. */
	protected boolean trained;
	
	/**
	 * Calculate the w matrix using the following formula:
	 * 
	 *  w = (xT * x)^-1 * xT * y
	 *      [   a  ]
	 *      [   b      ]
	 */
	protected void calcWeights() {
		/** xT */
		RealMatrix xTransposed = x.transpose();
				
		/**
		 *  a = (xT * x)
		 */
		RealMatrix a = xTransposed.multiply(x);
		
		/**
		 *  b = (xT * x)^-1
		 *    = a^-1
		 */
		RealMatrix b = new LUDecomposition(a).getSolver().getInverse();
		
		/** w = b * xT * y */
		w = b.multiply(xTransposed).multiply(y);
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.PredictionTechnique#predict(int)
	 */
	public abstract PredictionValues predict(int nrOfPredictions) throws PredictionException;

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.PredictionTechnique#train(double[])
	 */
	public abstract boolean train(double[] trainingSet) throws PredictionException;
	
}
