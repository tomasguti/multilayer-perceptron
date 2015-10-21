package problems;

import logic.NeuralNetwork;
import utils.Utils;

public class ProblemXOR {
	
	public static final int MAX_CYCLES = 999; 
	public static final int BATCH_LENGHT = 4;
	
	public static void main(String[] args) {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double[][] inputs = {	{0,0},
								{0,1},
								{1,0},
								{1,1}};
		
		double[][] outputs = {	{0},
								{1},
								{1},
								{0}};
		
		System.out.println("Simulación en curso...");
		
		for(int i=0; i < MAX_CYCLES; i++){
			neuralNetwork.trainBatch(inputs, outputs, BATCH_LENGHT);
		}
		
		neuralNetwork.printWeights();
		
		double[] result;
		result = neuralNetwork.evaluate(inputs[0]);
		Utils.print("Result FF", result);
		result = neuralNetwork.evaluate(inputs[1]);
		Utils.print("Result FV", result);
		result = neuralNetwork.evaluate(inputs[2]);
		Utils.print("Result VF", result);
		result = neuralNetwork.evaluate(inputs[3]);
		Utils.print("Result VV", result);
	}
}
