package logic;

import utils.Utils;

public class Neuron {
	
	public static final double LEARNING_RATE = 0.1; //0.5
	public static final double INITIAL_WEIGHT = 0.0;
	
	private int NUMBER_OF_INPUTS;
	public ActivationFunction function;
	
	private double[] input;
	private double output = 0.0;
	
	public double[] weights;
	private double[] partialWeights; //Used on batch training.
	private double[] saveWeights;
	
	private double bias = 0.0;
	private double partialBias = 0.0;
	private double saveBias = 0.0;
	
	private double delta = 0.0;
	
	public int index; // N�mero de neurona dentro de su capa.

	public Neuron(int index, int activationFunctionType) {
		this.index = index;
		function = new ActivationFunction(activationFunctionType);
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
		output = function.value(weigthedSum + bias);
		return output;
	}
	
	public void outputLayerNeuronDelta(double target){
		// output*(1-output) es la derivada de la sigmoidal, podemos usar la lineal.
		//Gradiente de error.
		delta = (target-output)*function.derived(output);
		partialIncrements();
	}
	
	public void hiddenLayerNeuronDelta(Layer nextLayer){
		// Sumatoria de los errores en la siguiente capa, por los nuevos pesos del v�nculo con esta neurona.
		double sum = 0.0;
		for(Neuron neuron : nextLayer.neurons){
			sum += neuron.delta*neuron.weights[index];
		}
		//Gradiente de error.
		delta = sum*function.derived(output);
		partialIncrements();
	}
	
	public void partialIncrements(){
		//Incremento parcial de los pesos para el patr�n
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
	
	public void saveState(){
		if(Utils.length(saveWeights) == 0){
			saveWeights = new double[NUMBER_OF_INPUTS];
		}
		for(int i=0; i < NUMBER_OF_INPUTS; i++){
			saveWeights[i] = weights[i];
		}
		saveBias = bias;
	}
	
	public void goBackState(){
		for(int i=0; i < NUMBER_OF_INPUTS; i++){
			weights[i] = saveWeights[i];
		}
		bias = saveBias;
	}
}
