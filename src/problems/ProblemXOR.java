package problems;

import logic.NeuralNetwork;
import utils.Utils;

public class ProblemXOR {
	
	public static final int MAX_CYCLES = 999; 
	
	public static void main(String[] args) {
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double[] ff = {0,0};
		double[] fv = {0,1};
		double[] vf = {1,0};
		double[] vv = {1,1};
		
		double[] tff = {0};
		double[] tfv = {1};
		double[] tvf = {1};
		double[] tvv = {0};
		
		/*
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
		*/
		
		System.out.println("Simulación en curso...");
		
		for(int i=0; i < MAX_CYCLES; i++){
			/*
			neuralNetwork.train(v21, r21);
			neuralNetwork.train(v31, r31);
			neuralNetwork.train(v32, r32);
			
			neuralNetwork.train(v12, r12);
			neuralNetwork.train(v13, r13);
			neuralNetwork.train(v23, r23);
			*/
			
			
			neuralNetwork.train(ff, tff);
			neuralNetwork.train(vf, tvf);
			neuralNetwork.train(fv, tfv);
			neuralNetwork.train(vv, tvv);
			
		}
		
		double[] result;
		/*
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
		*/
		
		result = neuralNetwork.evaluate(ff);
		Utils.print("Result FF", result);
		
		result = neuralNetwork.evaluate(fv);
		Utils.print("Result FV", result);
		
		result = neuralNetwork.evaluate(vf);
		Utils.print("Result VF", result);
		
		result = neuralNetwork.evaluate(vv);
		Utils.print("Result VV", result);
		
	}
}
