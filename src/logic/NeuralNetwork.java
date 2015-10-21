package logic;

import org.apache.commons.math3.util.FastMath;

import utils.Utils;

public class NeuralNetwork {

	//public static final int INPUT_LAYER_SIZE = 2;
	public static final int HIDDEN_LAYER_SIZE = 2; 
	public static final int OUTPUT_LAYER_SIZE = 1; 
	
	//private Layer inputLayer;
	private Layer hiddenLayer;
	private Layer outputLayer;

	public NeuralNetwork() {
		//inputLayer = new Layer(INPUT_LAYER_SIZE, Neuron.ACTIVATION_FUNCTION_SIGMOID);
		hiddenLayer = new Layer(HIDDEN_LAYER_SIZE, Neuron.ACTIVATION_FUNCTION_SIGMOID);
		outputLayer = new Layer(OUTPUT_LAYER_SIZE, Neuron.ACTIVATION_FUNCTION_SIGMOID);
	}
	
	public void train(double[] input, double[] target){
		
		//Forward Pass
		//double[] output = evaluate(input);
		evaluate(input);

		//Backpropagation
		outputLayer.outputLayerDeltas(target);
		outputLayer.updateWeights();
		
		hiddenLayer.hiddenLayerDeltas(outputLayer);
		hiddenLayer.updateWeights();
		
		//inputLayer.hiddenLayerErrors(hiddenLayer);
		//inputLayer.updateWeights();
		
		//Utils.print("Error=", calculateError(target, output));

	}
	
	public double[] evaluate(double[] input){
		hiddenLayer.setInput(input, Utils.length(input));
		outputLayer.setInput(hiddenLayer.getOutput(), HIDDEN_LAYER_SIZE);
		return outputLayer.getOutput();
	}
	
	public double[] calculateError(double[] target, double[] output){
		//MSE
		double[] errors = new double[OUTPUT_LAYER_SIZE];
		for(int i=0; i < OUTPUT_LAYER_SIZE; i++){
			errors[i] = FastMath.pow(target[i] - output[i], 2)/2;
		}
		return errors;
	}

}
