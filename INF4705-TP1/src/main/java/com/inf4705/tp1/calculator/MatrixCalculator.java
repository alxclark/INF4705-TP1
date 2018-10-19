package com.inf4705.tp1.calculator;

import com.inf4705.tp1.model.Matrix;

public abstract class MatrixCalculator {
	public abstract Matrix multiply(Matrix matrix1, Matrix matrix2);

	protected Matrix add(Matrix matrix1, Matrix matrix2) {
		Matrix result = new Matrix(matrix1.getDimensionX(), matrix1.getDimensionY());
		for (int i = 0; i < matrix1.getDimensionY(); i++) {
			for (int j = 0; j < matrix1.getDimensionX(); j++) {
				int a = matrix1.getDataAt(i, j);
				int b = matrix2.getDataAt(i, j);
				result.setDataAt(i, j, a + b);
			}
		}
		return result;
	}

	protected Matrix sub(Matrix matrix1, Matrix matrix2) {
		Matrix result = new Matrix(matrix1.getDimensionX(), matrix1.getDimensionY());
		for (int i = 0; i < matrix1.getDimensionY(); i++) {
			for (int j = 0; j < matrix1.getDimensionX(); j++) {
				int a = matrix1.getDataAt(i, j);
				int b = matrix2.getDataAt(i, j);
				result.setDataAt(i, j, a - b);
			}
		}
		return result;
	}
}
