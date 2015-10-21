# multilayer-perceptron
Simple implementation of a multilayer percepton (MLP) with backpropagation and some example problems.

# Use

Online training:

	NeuralNetwork nn = new NeuralNetwork();
	
	double[] input = {1.0, 1.0};
	double[] target = {0.5};

	//Training phase, Repeat this many times with all your data until the error is low.
	nn.trainOnline(input, target); 
	
	//Test it!
	nn.evaluate(input);
	
For batch training see the XOR problem example.

You can change the number of neurons inside each layer from the NeuralNetwork class.

# Example Problems
- XOR Problem
- Two groups clasiffy

# Authors
- Gutiérrez, Tomás  (Twitter: @Tomasg182)
- Paciuk, Sebastián

Inteligencia Computacional 2015 - Universidad Tecnológica Nacional Facultad Regional Santa Fe
