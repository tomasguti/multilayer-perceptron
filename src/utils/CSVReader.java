package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	
	public static int DATA_LENGTH = 156;
	
	public static void main(String[] args) {
		CSVReader.getData();
	}

  public static double[][] getData() {

	double[][] data = new double[3][DATA_LENGTH];
	String csvFile = "data/data.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ";";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		
		int rowIndex = 0;
		while ((line = br.readLine()) != null) {

		    // use separator
			String[] row = line.split(cvsSplitBy);
			
			data[0][rowIndex] = Double.parseDouble(row[0].substring(0, 2)); //Month
			data[1][rowIndex] = Double.parseDouble(row[0].substring(3, 7)); //Year
			data[2][rowIndex] = Double.parseDouble(row[3]); //Rain
			
			System.out.println(data[0][rowIndex] + "/" + data[1][rowIndex] + " " + data[2][rowIndex] );
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
