package problems;

import logic.NeuralNetwork;
import utils.CSVReader2;

public class BankProblem {
	
	public static final int MAX_CYCLES = 99;
	public static final int BATCH_LENGTH = 40000;
	public static final int VALIDATION_LENGTH = 4000;
	public static final int TEST_LENGTH = 1211;
	
	public static final int MAX_AGE = 95; 
	public static final int MIN_AGE = 18;
	public static final int MAX_JOB = 12; 
	public static final int MIN_JOB = 1;
	public static final int MAX_MARITAL = 3; 
	public static final int MIN_MARITAL = 1;
	public static final int MAX_EDUCATION = 4; 
	public static final int MIN_EDUCATION = 1;
	public static final int MAX_DEFAULT = 2; 
	public static final int MIN_DEFAULT = 1;
	public static final int MAX_BALANCE = 102127; 
	public static final int MIN_BALANCE = -8019;
	public static final int MAX_HOUSING = 2; 
	public static final int MIN_HOUSING = 1;
	public static final int MAX_LOAN = 2; 
	public static final int MIN_LOAN = 1;
	public static final int MAX_CONTACT = 3; 
	public static final int MIN_CONTACT = 1;
	public static final int MAX_DAY = 31; 
	public static final int MIN_DAY = 1;
	public static final int MAX_MONTH = 12; 
	public static final int MIN_MONTH = 1;
	public static final int MAX_DURATION = 4918; 
	public static final int MIN_DURATION = 0;
	public static final int MAX_CAMPAIGN = 63; 
	public static final int MIN_CAMPAIGN = 1;
	public static final int MAX_PDAYS = 871; 
	public static final int MIN_PDAYS = -1;
	public static final int MAX_PREVIOUS = 275; 
	public static final int MIN_PREVIOUS = 0;
	public static final int MAX_POUTCOME = 4; 
	public static final int MIN_POUTCOME = 1;
	public static final int MAX_Y = 2; 
	public static final int MIN_Y = 1;
	
		
	public static void main(String[] args) {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		double data[][] = CSVReader2.getData();
		
		double lastError = 10000;
		double newError = 0;
		
		double[][] trainBatch = new double[BATCH_LENGTH][16];
		double[][] targetBatch = new double[BATCH_LENGTH][1];
		
		for(int cycle = 0; cycle < MAX_CYCLES; cycle++){
			
			//Training
			
			batchBuild(data,trainBatch,targetBatch, 0, BATCH_LENGTH); // Se arma el lote
			
			neuralNetwork.trainBatch(trainBatch, targetBatch, BATCH_LENGTH); // Se entrena		
			
			// Validation
			
			batchBuild(data,trainBatch,targetBatch,BATCH_LENGTH,BATCH_LENGTH + VALIDATION_LENGTH);
			
			newError = neuralNetwork.calculateErrorBatch(trainBatch, targetBatch, VALIDATION_LENGTH);
			
			/*
			if (newError <= lastError){ // no anda bien pq el error no es monótono decreciente, sino que oscila
				lastError = newError;
				neuralNetwork.saveState(); // For early-stopping
			}else{ // Stop simulation and go back
				neuralNetwork.goBackState();
				cycle = MAX_CYCLES;
			}
			*/
			
		}
		
		// Test
		
		batchBuild(data,trainBatch,targetBatch,BATCH_LENGTH + VALIDATION_LENGTH, BATCH_LENGTH + VALIDATION_LENGTH + TEST_LENGTH);
		
		// Completar
		
	}
	
	public static void batchBuild(double[][] data, double[][] trainBatch, double[][] targetBatch, int min, int max){
		int index = 0;
		for(int i=min; i < max ; i++){
			
			double dataAge = data[0][i];
			double dataJob = data[1][i];
			double dataMarital = data[2][i];
			double dataEducation = data[3][i];
			double dataDefault = data[4][i];
			double dataBalance = data[5][i];
			double dataHousing = data[6][i];
			double dataLoan = data[7][i];
			double dataContact = data[8][i];
			double dataDay = data[9][i];
			double dataMonth = data[10][i];
			double dataDuration = data[11][i];
			double dataCampaign = data[12][i];
			double dataPdays = data[13][i];
			double dataPrevious = data[14][i];
			double dataPoutcome = data[15][i];
			double dataTarget = data[16][i];
			
			trainBatch[index][0] = normalize(dataAge, MIN_AGE, MAX_AGE);
			trainBatch[index][1] = normalize(dataJob, MIN_JOB, MAX_JOB);
			trainBatch[index][2] = normalize(dataMarital, MIN_MARITAL, MAX_MARITAL);
			trainBatch[index][3] = normalize(dataEducation, MIN_EDUCATION, MAX_EDUCATION);
			trainBatch[index][4] = normalize(dataDefault, MIN_DEFAULT, MAX_DEFAULT);
			trainBatch[index][5] = normalize(dataBalance, MIN_BALANCE, MAX_BALANCE);
			trainBatch[index][6] = normalize(dataHousing, MIN_HOUSING, MAX_HOUSING);
			trainBatch[index][7] = normalize(dataLoan, MIN_LOAN, MAX_LOAN);
			trainBatch[index][8] = normalize(dataContact, MIN_CONTACT, MAX_CONTACT);
			trainBatch[index][9] = normalize(dataDay, MIN_DAY, MAX_DAY);
			trainBatch[index][10] = normalize(dataMonth, MIN_MONTH, MAX_MONTH);
			trainBatch[index][11] = normalize(dataDuration, MIN_DURATION, MAX_DURATION);
			trainBatch[index][12] = normalize(dataCampaign, MIN_CAMPAIGN, MAX_CAMPAIGN);
			trainBatch[index][13] = normalize(dataPdays, MIN_PDAYS, MAX_PDAYS);
			trainBatch[index][14] = normalize(dataPrevious, MIN_PREVIOUS, MAX_PREVIOUS);
			trainBatch[index][15] = normalize(dataPoutcome, MIN_POUTCOME, MAX_POUTCOME);
			targetBatch[index][0] = normalize(dataTarget, MIN_Y, MAX_Y);
		
			/*for(int j=0;j<16;j++)
				System.out.print(trainBatch[index][j] + " ");
			System.out.println();*/
			
			index ++;
		}		
		
	}
		
	public static double normalize(double data, double lowerLimit, double upperLimit){
		return (data - lowerLimit)/(upperLimit - lowerLimit);
	}
	
	public static double desnormalize(double data, double lowerLimit, double upperLimit){
		return (upperLimit - lowerLimit)*data + lowerLimit;
	}
	
}
