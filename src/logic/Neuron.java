package logic;

import org.apache.commons.math3.analysis.function.Sigmoid;
import utils.Utils;

public class Neuron {
	
	public static final double LEARNING_RATE = 0.1; //0.5
	public static final double INITIAL_WEIGHT = 0.5;
	
	private int NUMBER_OF_INPUTS;
	public int activationFunctionType;
	
	private double[] input;
	private double output = 0.0;
	
	public double[] weights;
	private double[] partialWeights; //Used on batch training.
	
	private double bias = 0.0;
	private double partialBias = 0.0;
	
	private double delta = 0.0;
	
	public int index; // Número de neurona dentro de su capa.

	public Neuron(int index, int activationFunctionType) {
		this.index = index;
		this.activationFunctionType = activationFunctionType;
	}
	
	public void setInput(double[] input, int size){
		this.input = input;
		NUMBER_OF_INPUTS = size;
		if(Utils.length(weights) == 0){
			//Only the first time we set the input.
			weights = new double[NUMBER_OF_INPUTS];
			partialWeights = new double[NUMBER_OF_INPUTS];
			for(int i=0; i < NUMBER_OF_INPUTS; i++){
				weights[i] = Math.random();
				//weights[i] = INITIAL_WEIGHT; // El problema del XOR no functiona con esto.
			}
		}
	}
	
	public double getOutput(){
		double weigthedSum = 0.0;
		for(int i=0; i < NUMBER_OF_INPUTS; i++){
			weigthedSum += input[i] * weights[i];
		}
		Sigmoid s = new Sigmoid();
		output = s.value(weigthedSum + bias);
		return output;
	}
	
	public void outputLayerNeuronDelta(double target){
		// output*(1-output) es la derivada de la sigmoidal, podemos usar la lineal.
		//Gradiente de error.
		delta = (target-output)*output*(1-output);
		partialIncrements();
	}
	
	public void hiddenLayerNeuronDelta(Layer nextLayer){
		// Sumatoria de los errores en la siguiente capa, por los nuevos pesos del vínculo con esta neurona.
		double sum = 0.0;
		for(Neuron neuron : nextLayer.neurons){
			sum += neuron.delta*neuron.weights[index];
		}
		//Gradiente de error.
		delta = sum*output*(1-output);
		partialIncrements();
	}
	
	public void partialIncrements(){
		//Incremento parcial de los pesos para el patrón
		for(int i=0; i < NUMBER_OF_INPUTS; i++){
			partialWeights[i] += delta * input[i];
		}
		partialBias += delta;
	}

	public void updateWeights(){
		for(int i=0; i < NUMBER_OF_INPUTS; i++){
			weights[i] = weights[i] + LEARNING_RATE * partialWeights[i];
		}
		bias = bias + LEARNING_RATE * partialBias;
		partialWeights = new double[NUMBER_OF_INPUTS];
		partialBias = 0.0;
	}

}
