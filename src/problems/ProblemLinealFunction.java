package problems;

import logic.NeuralNetwork;
import utils.Utils;

public class ProblemLinealFunction {
public static final int MAX_CYCLES = 999; 
	
	public static void main(String[] args) {
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double[] v21 = {2,1};
		double[] v31 = {3,1};
		double[] v32 = {3,1};
		
		double[] v12 = {1,2};
		double[] v13 = {1,3};
		double[] v23 = {2,3};
		
		double[] r21 = {0};
		double[] r31 = {0};
		double[] r32 = {0};
		
		double[] r12 = {1};
		double[] r13 = {1};
		double[] r23 = {1};
		
		
		System.out.println("Simulación en curso...");
		
		for(int i=0; i < MAX_CYCLES; i++){

			neuralNetwork.train(v21, r21);
			neuralNetwork.train(v31, r31);
			neuralNetwork.train(v32, r32);
			
			neuralNetwork.train(v12, r12);
			neuralNetwork.train(v13, r13);
			neuralNetwork.train(v23, r23);
		
		}
		
		double[] result;

		result = neuralNetwork.evaluate(v23);
		Utils.print("Result 2,3", result);
		
		result = neuralNetwork.evaluate(v21);
		Utils.print("Result 2,1", result);
		
		double[] test1 = {6,1};
		result = neuralNetwork.evaluate(test1);
		Utils.print("Result 6,1", result);
		
		double[] test2 = {1,1};
		result = neuralNetwork.evaluate(test2);
		Utils.print("Result 1,1", result);

		
	}
}
