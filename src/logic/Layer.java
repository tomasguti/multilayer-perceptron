package logic;

import java.util.ArrayList;

public class Layer {
	
	public static final int ACTIVATION_FUNCTION_LINEAR = 0;
	public static final int ACTIVATION_FUNCTION_SIGMOID = 1;
	
	public ArrayList<Neuron> neurons;

	public Layer(int neuronsNumber, int activationFunctionType) {
		neurons = new ArrayList<Neuron>();
		for(int i = 0; i < neuronsNumber; i++){
			neurons.add(new Neuron(i, activationFunctionType));
		}
	}
	
	public void setInput(double[] input, int size){
		for(Neuron neuron : neurons){
			neuron.setInput(input, size);
		}
	}
	
	public double[] getOutput(){
		int i = 0;
		double[] output = new double[neurons.size()];
		for(Neuron neuron : neurons){
			output[i] = neuron.getOutput();
			i++;
		}
		return output;
	}
	
	public void outputLayerDeltas(double[] target){
		for(Neuron neuron : neurons){
			neuron.outputLayerNeuronDelta(target[neuron.index]);
		}
	}
	
	public void hiddenLayerDeltas(Layer nextLayer){
		for(Neuron neuron : neurons){
			neuron.hiddenLayerNeuronDelta(nextLayer);
		}
	}
	
	public void updateWeights(){
		for(Neuron neuron : neurons){
			neuron.updateWeights();
		}
	}

}
