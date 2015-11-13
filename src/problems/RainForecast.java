package problems;

import logic.NeuralNetwork;
import utils.CSVReader;

public class RainForecast {
	
	public static final int MAX_CYCLES = 9999; 
	public static final int MONTHS_IN_A_YEAR = 12; 
	public static final int MAX_RAIN = 1000; 
	
	public static final int MAX_YEAR = 2015; 
	public static final int MIN_YEAR = 2002; 
	
	public static void main(String[] args) {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double data[][] = CSVReader.getData();
		int YEARS = data[0].length/12-1;
		int yearOut = YEARS; //Año que dejamos afuera, Leave-one-out
		double lastError = 10000;
		double newError = 0;
		for(int cycle = 0; cycle < MAX_CYCLES; cycle++){
			for(int year = 0; year < YEARS; year++){
				double[][] trainBatch = new double[MONTHS_IN_A_YEAR][2];
				double[][] targetBatch = new double[MONTHS_IN_A_YEAR][1];	
				for(int month=0; month < MONTHS_IN_A_YEAR; month++){
					
					double dataMonth = data[0][year*MONTHS_IN_A_YEAR + month];
					double dataYear = data[1][year*MONTHS_IN_A_YEAR + month];
					double dataTarget = data[2][year*MONTHS_IN_A_YEAR + month];
					
					trainBatch[month][0] = normalize(dataMonth, 1, MONTHS_IN_A_YEAR);
					trainBatch[month][1] = normalize(dataYear, MIN_YEAR, MAX_YEAR);
					targetBatch[month][0] = normalize(dataTarget, 0, MAX_RAIN);
				}
				if(year != yearOut){
					//Training
					neuralNetwork.trainBatch(trainBatch, targetBatch, MONTHS_IN_A_YEAR);
				}else{
					//Validation
					newError = neuralNetwork.calculateErrorBatch(trainBatch, targetBatch, MONTHS_IN_A_YEAR);
					if (newError <= lastError){ // no anda bien pq el error no es monótono decreciente, sino que oscila
						lastError = newError;
						neuralNetwork.saveState(); // For early-stopping
					}else{
						// Stop simulation and go back
						neuralNetwork.goBackState();
						cycle = MAX_CYCLES;
					}
				}			
			}
			//System.out.println("Year left out: "+yearOut);
			yearOut--;
			if(yearOut < 0){
				yearOut = YEARS;
			}
		}
		
		//TEST! Con el año 2015
		
		int TEST_YEAR = 2015;
		for(int month=0;month<MONTHS_IN_A_YEAR;month++){
			
			double[] output = new double[1];
			double[] target = new double[1];
			
			int indexData = (TEST_YEAR - MIN_YEAR-1)*MONTHS_IN_A_YEAR + month;
			
			output[0] = consult(neuralNetwork, data[0][indexData], data[1][indexData]);
			target[0] = data[2][indexData];
			
			double error = target[0]-output[0];
			double lse = neuralNetwork.calculateError(target, output);
			
			System.out.println("Validation error 2015 month: "+(month+1)+" error="+error+" lse="+lse);
		}
		
	}
	
	public static double consult(NeuralNetwork neuralNetwork, double testMonth, double testYear){
		double[] testInput = {normalize(testMonth, 1, MONTHS_IN_A_YEAR), normalize(testYear, MIN_YEAR, MAX_YEAR)};
		double[] result = neuralNetwork.evaluate(testInput);
		
		return desnormalize(result[0], 0, MAX_RAIN);
	}
	
	public static double normalize(double data, double lowerLimit, double upperLimit){
		return (data - lowerLimit)/(upperLimit - lowerLimit);
	}
	
	public static double desnormalize(double data, double lowerLimit, double upperLimit){
		return (upperLimit - lowerLimit)*data + lowerLimit;
	}
		
}
