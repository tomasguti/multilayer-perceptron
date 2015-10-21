# multilayer-perceptron
Simple implementation of a multilayer percepton (MLP) with backpropagation and some example problems.

# Use
	NeuralNetwork nn = new NeuralNetwork();

	//Training phase, Repeat this many times with all your data until the error is low.
	nn.train(input, target); 
	
	//Test it!
	nn.evaluate(input);

# Example Problems
- XOR Problem
- Two groups clasiffy
