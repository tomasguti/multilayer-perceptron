package utils;

public class Utils {
	
	public static int length(double[] array){
		int i = 0;
		while(true){
			try{
				array[i] = array[i];
			}catch(Exception e){
				return i;
			}
			i++;
		}
	}
	
	public static void print(String label, double[] array){
		System.out.print(label+" [ ");
		for(int i = 0; i < length(array); i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("]");
	}

}
