package com.inf4705.tp1.exception;

public class InvalidMatrixesDimensionsException extends MatrixMultiplicatorException {
	public InvalidMatrixesDimensionsException(){
		super("The size of the matrixes does match. (MxN) x (NxP) = (M x P) but for this TP matrixes are square");
	}
}
