package logic;

import org.apache.commons.math3.util.FastMath;

import utils.Utils;

public class NeuralNetwork {

	public static final int INPUT_LAYER_SIZE = 16;
	public static final int HIDDEN_LAYER_SIZE = 16; 
	public static final int OUTPUT_LAYER_SIZE = 1; 
	
	//private Layer inputLayer;
	private Layer hiddenLayer;
	private Layer outputLayer;

	public NeuralNetwork() {
		hiddenLayer = new Layer(HIDDEN_LAYER_SIZE, ActivationFunction.SIGMOID);
		outputLayer = new Layer(OUTPUT_LAYER_SIZE, ActivationFunction.LINEAR);
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
		//System.out.println("Error = "+error);
	}
	
	public double calculateErrorBatch(double[][] inputs, double[][] targets, int batchLenght){
		double error = 0.0;
		double[] output;
		//Forward Pass
		for(int i = 0; i < batchLenght; i++){
			output = evaluate(inputs[i]);
			error += calculateError(targets[i], output);
		}
		
		//System.out.println("Validation Error = "+error);
		return error;
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
	
	public void saveState(){
		hiddenLayer.saveState();
		outputLayer.saveState();
	}
	
	public void goBackState(){
		hiddenLayer.goBackState();
		outputLayer.goBackState();
	}
	
	public void pruning(){
		
	}
}
