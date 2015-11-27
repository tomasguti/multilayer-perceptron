package logic;

import org.apache.commons.math3.util.FastMath;

public class ActivationFunction {
	public static final int LINEAR = 0;
	public static final int SIGMOID = 1;
	public static final int HYPERBOLIC_TANGENT = 2;
	
	public int type;
	
	public ActivationFunction(int type) {
		super();
		this.type = type;
	}

	public double value(double x){
		switch(type){
			case SIGMOID:
				return 1 / (1 + FastMath.exp(-x));
			case HYPERBOLIC_TANGENT:
				return FastMath.tanh(x);
			default: //LINEAR
				if(x>1){
					return 1;
				}else if(x<0){
					return 0;
				}
				return x;
		}
	}
	
	public double derived(double x){
		switch(type){
			case SIGMOID:
				return x*(1-x);
			case HYPERBOLIC_TANGENT:
				return 1-FastMath.tanh(x)*FastMath.tanh(x);
			default: //LINEAR
				return 1;
		}
	}

}
