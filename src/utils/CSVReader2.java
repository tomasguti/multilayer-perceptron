package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;

public class CSVReader2 {
	
	public static int DATA_LENGTH = 45211;
	
	public static void main(String[] args) {
		CSVReader2.getData();
	}

  public static double[][] getData() {

	double[][] data = new double[17][DATA_LENGTH];
	String csvFile = "data/bank-full.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ";";
	String aux ="";
	
	try {

		br = new BufferedReader(new FileReader(csvFile));
		
		int rowIndex = 0;
		while ((line = br.readLine()) != null) {

		    // use separator
			String[] row = line.split(cvsSplitBy);
			
			data[0][rowIndex] = Double.parseDouble(row[0]); //Age
			
			aux = row[1]; //Job
			
			if(aux.compareTo("admin.") == 0){
				data[1][rowIndex] = 1;
			}
			else if(aux.compareTo("blue-collar") == 0){
				data[1][rowIndex] = 2;
			}
			else if(aux.compareTo("entrepreneur") == 0){
				data[1][rowIndex] = 3;
			}
			else if(aux.compareTo("housemaid") == 0){
				data[1][rowIndex] = 4;
			}
			else if(aux.compareTo("management") == 0){
				data[1][rowIndex] = 5;
			}
			else if(aux.compareTo("retired") == 0){
				data[1][rowIndex] = 6;
			}
			else if(aux.compareTo("self-employed") == 0){
				data[1][rowIndex] = 7;
			}
			else if(aux.compareTo("sevices") == 0){
				data[1][rowIndex] = 8;
			}
			else if(aux.compareTo("student") == 0){
				data[1][rowIndex] = 9;
			}
			else if(aux.compareTo("technician") == 0){
				data[1][rowIndex] = 10;
			}
			else if(aux.compareTo("unemployed") == 0){
				data[1][rowIndex] = 11;
			}
			else if(aux.compareTo("unknown") == 0){
				data[1][rowIndex] = 12;
			}
			
			aux = row[2]; //Marital
			
			if(aux.compareTo("divorced") == 0){
				data[2][rowIndex] = 1;
			}
			else if(aux.compareTo("married") == 0){
				data[2][rowIndex] = 2;
			}
			else if(aux.compareTo("single") == 0){
				data[2][rowIndex] = 3;
			}
			
			aux = row[3]; //Education
			
			if(aux.compareTo("primary") == 0){
				data[3][rowIndex] = 1;
			}
			else if(aux.compareTo("secondary") == 0){
				data[3][rowIndex] = 2;
			}
			else if(aux.compareTo("tertiary") == 0){
				data[3][rowIndex] = 3;
			}
			else if(aux.compareTo("unknown") == 0){
				data[3][rowIndex] = 4;
			}
			
			aux = row[4]; //Default
			
			if(aux.compareTo("no") == 0){
				data[4][rowIndex] = 1;
			}
			else if(aux.compareTo("yes") == 0){
				data[4][rowIndex] = 2;
			}
			
			data[5][rowIndex] = Double.parseDouble(row[5]); //Balance
			
			aux = row[6]; //Housing
			
			if(aux.compareTo("no") == 0){
				data[6][rowIndex] = 1;
			}
			else if(aux.compareTo("yes") == 0){
				data[6][rowIndex] = 2;
			}
			
			aux = row[7]; //Loan
			
			if(aux.compareTo("no") == 0){
				data[7][rowIndex] = 1;
			}
			else if(aux.compareTo("yes") == 0){
				data[7][rowIndex] = 2;
			}
			
			aux = row[8]; //Contact
			
			if(aux.compareTo("unknown") == 0){
				data[8][rowIndex] = 1;
			}
			else if(aux.compareTo("cellular") == 0){
				data[8][rowIndex] = 2;
			}
			else if(aux.compareTo("telephone") == 0){
				data[8][rowIndex] = 3;
			}
			
			data[9][rowIndex] = Double.parseDouble(row[9]); //Day
			
			aux = row[10]; //Month
			
			if(aux.compareTo("jan") == 0){
				data[10][rowIndex] = 1;
			}
			else if(aux.compareTo("feb") == 0){
				data[10][rowIndex] = 2;
			}
			else if(aux.compareTo("mar") == 0){
				data[10][rowIndex] = 3;
			}
			else if(aux.compareTo("apr") == 0){
				data[10][rowIndex] = 4;
			}
			else if(aux.compareTo("may") == 0){
				data[10][rowIndex] = 5;
			}
			else if(aux.compareTo("jun") == 0){
				data[10][rowIndex] = 6;
			}
			else if(aux.compareTo("jul") == 0){
				data[10][rowIndex] = 7;
			}
			else if(aux.compareTo("ago") == 0){
				data[10][rowIndex] = 8;
			}
			else if(aux.compareTo("sep") == 0){
				data[10][rowIndex] = 9;
			}
			else if(aux.compareTo("oct") == 0){
				data[10][rowIndex] = 10;
			}
			else if(aux.compareTo("nov") == 0){
				data[10][rowIndex] = 11;
			}
			else if(aux.compareTo("dec") == 0){
				data[10][rowIndex] = 12;
			}
			
			data[11][rowIndex] = Double.parseDouble(row[11]); //Duration
			data[12][rowIndex] = Double.parseDouble(row[12]); //Campaign
			data[13][rowIndex] = Double.parseDouble(row[13]); //Pdays
			data[14][rowIndex] = Double.parseDouble(row[14]); //Previous

			aux = row[15]; //Poutcome
			
			if(aux.compareTo("unknown") == 0){
				data[15][rowIndex] = 1;
			}
			else if(aux.compareTo("failure") == 0){
				data[15][rowIndex] = 2;
			}
			else if(aux.compareTo("other") == 0){
				data[15][rowIndex] = 3;
			}
			else if(aux.compareTo("success") == 0){
				data[15][rowIndex] = 4;
			}
			
			aux = row[16]; //y
			
			if(aux.compareTo("no") == 0){
				data[16][rowIndex] = 1;
			}
			else if(aux.compareTo("yes") == 0){
				data[16][rowIndex] = 2;
			}
			
			/*for(int i=0; i < 17; i++){
				System.out.print(data[i][rowIndex] + " ");
			}
			System.out.print("\n");*/
			rowIndex++;
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	System.out.println("Done loading data.");
	
	return data;
  }

}
