package problems;

import logic.NeuralNetwork;
import utils.CSVReader;
import utils.Utils;

public class ProblemPredictorLluvia {
	
	public static final int MAX_CYCLES = 999; 
	
	public static void main(String[] args) {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		
		double data[][] = CSVReader.getData();
		int years = data[0].length/12;
		double lote[][] = new double[years][2];
		double targets[][] = new double[years][1];
		double validation[][] = new double[years][3];
		int aux = 0;
		for(int a=0; a<MAX_CYCLES; a++){
			//se crean los lotes de prueba por mes, dejando de a un año afuera y se entrena batch
			for(int i=0; i < years - 1; i++){ // i marca el año que se deja afuera del lote
				for(int j=0; j < 12; j++){ // j es el mes
					for(int k=0; k < years - 1;k++){// se crea el lote para el mes j
						if(k != i){
							lote[k - aux][0] = j/11; // mes
							lote[k - aux][1] = (data[1][k*12 + j] - 2002)/13; //año
							targets[k - aux][0] = data[2][k*12 + j]/557; //lluvia
						}
						else{
							aux = 1;
							validation[j][0] = j/11;
							validation[j][1] = (data[1][k*12 + j] - 2002)/13;
							validation[j][2] = data[2][k*12 + j]/557;
						}
					}
					aux = 0;
					neuralNetwork.trainBatch(lote, targets, years-2);
				}
				// hacer validacion con el año i
			}
			// hacer test con año 2015
		}
	}
		
}
