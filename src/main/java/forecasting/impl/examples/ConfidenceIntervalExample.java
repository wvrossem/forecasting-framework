package main.java.forecasting.impl.examples;

import main.java.forecasting.api.Parameters;
import main.java.forecasting.api.PredictionException;
import main.java.forecasting.impl.confidenceIntervals.ConfidenceIntervalTechnique;
import main.java.forecasting.impl.confidenceIntervals.PredictionValuesWithConfidence;
import main.java.forecasting.impl.regression.StandardRegression;

public class ConfidenceIntervalExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try { 
			ConfidenceIntervalTechnique model = 
				new ConfidenceIntervalTechnique(5, StandardRegression.class, new Parameters());
			
			double trainingSet[] = {
					12, 14, 16, 15, 13, 17, 12, 
					16, 13, 17, 18, 16, 17, 15,
					17, 15, 17, 18, 17, 17, 18
			};
			
			model.train(trainingSet);
			
			PredictionValuesWithConfidence predictions = model.predict(5);
			
			for(double prediction : predictions.getValues()) {
				System.out.println("Prediction : " + prediction);
			}
			
			System.out.println("Left side of confidence interval : " + predictions.getLeft());
			System.out.println("Right side of confidence interval : " + predictions.getRight());
		} catch (PredictionException e) {
			e.printStackTrace();
		}
		
		
	}

}
