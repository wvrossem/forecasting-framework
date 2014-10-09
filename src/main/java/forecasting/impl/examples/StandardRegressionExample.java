package main.java.forecasting.impl.examples;

import main.java.forecasting.api.PredictionException;
import main.java.forecasting.api.PredictionValues;
import main.java.forecasting.impl.regression.StandardRegression;

public class StandardRegressionExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try { 
			StandardRegression model = new StandardRegression(null);
			
			double trainingSet[] = {
					12, 14, 16, 15, 13, 17, 12, 
					16, 13, 17, 18, 16, 17, 15,
					17, 15, 17, 18, 17, 17, 18
			};
			
			model.train(trainingSet);
			
			PredictionValues predictions = model.predict(5);
			
			for(double prediction : predictions.getValues()) {
				System.out.println(prediction);
			}
		} catch (PredictionException e) {
			e.printStackTrace();
		}
		
		
	}

}
