package problems;

import logic.NeuralNetwork;
import utils.Utils;

public class ProblemLinealFunction {
public static final int MAX_CYCLES = 99; 
	
	public static void main(String[] args) {
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double[] v21 = {2.0,1.0};
		double[] v31 = {3.0,1.0};
		double[] v32 = {3.0,1.0};
		
		double[] v12 = {1.0,2.0};
		double[] v13 = {1.0,3.0};
		double[] v23 = {2.0,3.0};
		
		double[] r21 = {0.0};
		double[] r31 = {0.0};
		double[] r32 = {0.0};
		
		double[] r12 = {1.0};
		double[] r13 = {1.0};
		double[] r23 = {1.0};
		
		
		System.out.println("Simulación en curso...");
		
		for(int i=0; i < MAX_CYCLES; i++){

			neuralNetwork.trainOnline(v21, r21);
			neuralNetwork.trainOnline(v31, r31);
			neuralNetwork.trainOnline(v32, r32);
			
			neuralNetwork.trainOnline(v12, r12);
			neuralNetwork.trainOnline(v13, r13);
			neuralNetwork.trainOnline(v23, r23);
		
		}
		
		double[] result;

		result = neuralNetwork.evaluate(v23);
		Utils.print("Result 2,3", result);
		
		result = neuralNetwork.evaluate(v21);
		Utils.print("Result 2,1", result);
		
		double[] test1 = {6.0,1.0};
		result = neuralNetwork.evaluate(test1);
		Utils.print("Result 6,1", result);
		
		double[] test2 = {1.0,1.0};
		result = neuralNetwork.evaluate(test2);
		Utils.print("Result 1,1", result);

		
	}
}
