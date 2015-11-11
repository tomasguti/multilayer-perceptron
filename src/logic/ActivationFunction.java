package logic;

import org.apache.commons.math3.util.FastMath;

public class ActivationFunction {
	public static final int LINEAR = 0;
	public static final int SIGMOID = 1;
	public static final int SIGN = 2;
	public static final int HYPERBOLIC_TANGENT = 3;
	
	public int type;
	
	public double value(double x){
		switch(type){
			case SIGMOID:
				return 1 / (1 + FastMath.exp(-x));
			case SIGN:
				return x;
			case HYPERBOLIC_TANGENT:
				return x;
			default: //LINEAR
				return x;
		}
	}
	
	public double derive(double x){
		switch(type){
			case LINEAR:
				return x;
			case SIGMOID:
				return x*(1-x);
			case SIGN:
				return x;
			case HYPERBOLIC_TANGENT:
				return x;
			default: //LINEAR
				return 1;
		}
	}

}
