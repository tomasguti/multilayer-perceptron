package logic;

import org.apache.commons.math3.util.FastMath;

import utils.Utils;

public class NeuralNetwork {

	public static final int INPUT_LAYER_SIZE = 2;
	public static final int HIDDEN_LAYER_SIZE = 2; 
	public static final int OUTPUT_LAYER_SIZE = 1; 
	
	//private Layer inputLayer;
	private Layer hiddenLayer;
	private Layer outputLayer;

	public NeuralNetwork() {
		hiddenLayer = new Layer(HIDDEN_LAYER_SIZE, Layer.ACTIVATION_FUNCTION_SIGMOID);
		outputLayer = new Layer(OUTPUT_LAYER_SIZE, Layer.ACTIVATION_FUNCTION_SIGMOID);
	}
	
	public void trainBatch(double[][] inputs, double[][] targets, int batchLenght){
		double error = 0.0;
		double[] output;
		//Forward Pass
		for(int i = 0; i < batchLenght; i++){
			output = evaluate(inputs[i]);
			outputLayer.outputLayerDeltas(targets[i]);
			hiddenLayer.hiddenLayerDeltas(outputLayer);
			error += calculateError(targets[i], output);
		}
		hiddenLayer.updateWeights();
		outputLayer.updateWeights();
		System.out.println("Error = "+error);
	}
	
	public void trainOnline(double[] input, double[] target){
		//Forward Pass
		double[] output = evaluate(input);
		//Backpropagation
		outputLayer.outputLayerDeltas(target);
		outputLayer.updateWeights();
		hiddenLayer.hiddenLayerDeltas(outputLayer);
		hiddenLayer.updateWeights();
		System.out.println("Error = "+calculateError(target, output));
	}
	
	public double[] evaluate(double[] input){
		hiddenLayer.setInput(input, INPUT_LAYER_SIZE);
		outputLayer.setInput(hiddenLayer.getOutput(), HIDDEN_LAYER_SIZE);
		return outputLayer.getOutput();
	}
	
	public double calculateError(double[] target, double[] output){
		//MSE
		double error = 0.0;
		for(int i=0; i < OUTPUT_LAYER_SIZE; i++){
			error += FastMath.pow(target[i] - output[i], 2)/2;
		}
		return error;
	}
	
	public void printWeights(){
		System.out.println("");
		for(Neuron hiddenNeuron : hiddenLayer.neurons){
			Utils.print("Hidden W", hiddenNeuron.weights);
		}
		System.out.println("");
		for(Neuron outputNeuron : outputLayer.neurons){
			Utils.print("Output W", outputNeuron.weights);
		}
		System.out.println("");
	}
	
}
