package main.java.forecasting.impl.confidenceIntervals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.java.forecasting.api.Parameters;
import main.java.forecasting.api.PredictionException;
import main.java.forecasting.api.PredictionTechnique;
import main.java.forecasting.tools.MathTools;

/**
 * The Class ConfidenceIntervalTechnique.
 * 
 * @author Wouter Van Rossem
 */
public class ConfidenceIntervalTechnique implements PredictionTechnique {
	
	/** The number of resamples to be taken. */
	protected int nr_of_resamples;     
	
	/** The prediction technique to be used. */
	protected Class<PredictionTechnique> predictionTechnique;
	
	/** The parameters to be used by the prediction tehcnique. */
	protected Parameters parameters;
	
	/** The resamples. */
	protected List<PredictionTechnique> resamples;
	
	/**
	 * Instantiates a new confidence interval technique.
	 *
	 * @param nr_of_resamples the nr_of_resamples
	 * @param predictionTechnique the prediction technique
	 * @param parameters the parameters
	 * @throws PredictionException the prediction exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConfidenceIntervalTechnique(int nr_of_resamples, 
			Class predictionTechnique,
			Parameters parameters) throws PredictionException {
		this.nr_of_resamples     = nr_of_resamples;
		this.predictionTechnique = predictionTechnique;
		this.parameters          = parameters;
		this.resamples           = new ArrayList<PredictionTechnique>();
	}
	
	/**
	 * Resample.
	 *
	 * @param trainingSet the training set
	 * @throws PredictionException the prediction exception
	 */
	@SuppressWarnings("rawtypes")
	private void resample(double[] trainingSet) throws PredictionException {
		
		double[] newSet = new double[trainingSet.length];
		
		Random random = new Random();
		
		for (int i=0; i<trainingSet.length; i++) {
			// Set the next value in the new training set to a random int
			// between 0 and the length of the old training set.
			// Or we pick a random element from the old training set and 
			// put it in the new set.
			newSet[i] = trainingSet[random.nextInt(trainingSet.length)];
		}
		
		try {
			// Find the correct constructor for the prediction technique
			// The constructor needs to have the parameters argument
			Constructor constructor = predictionTechnique.getConstructor(new Class[] {Parameters.class});
			
			// Create a new instance of the prediction technique
			// by calling the constructor and providing it the parameters argument
			PredictionTechnique technique 
					= (PredictionTechnique) constructor.newInstance(new Object[] {parameters});
			
			// Train the prediction technique with the new training set we just created
			technique.train(newSet);
			
			// Add the trained technique to the set of resamples
			resamples.add(technique);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.PredictionTechnique#train(double[])
	 */
	public boolean train(double[] trainingSet) throws PredictionException {
		
		for (int i=0; i<nr_of_resamples; i++) {
			resample(trainingSet);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see be.vub.pachubar.prediction.PredictionTechnique#predict(int)
	 */
	public PredictionValuesWithConfidence predict(int nrOfPredictions)
			throws PredictionException {
		
		double[][] predictValues = new double[nr_of_resamples][nrOfPredictions];
		
		for (int i=0; i<nr_of_resamples; i++) {
			predictValues[i] = resamples.get(i).predict(nrOfPredictions).getValues();
		}
		
		double[] medians = new double[nr_of_resamples];
		
		for (int i=0; i<nrOfPredictions; i++) {
			double sum = 0;
			for (int j=0; j<nr_of_resamples; j++) {
				sum += predictValues[j][i];
			}
			medians[i] = sum / nr_of_resamples;
		}
		
		double standDev = MathTools.calcStandardDeviation(medians);
		
		double left  = standDev + 1.96;
		double right = standDev - 1.96;
		
		return new PredictionValuesWithConfidence(medians, left, right);
	}
}
