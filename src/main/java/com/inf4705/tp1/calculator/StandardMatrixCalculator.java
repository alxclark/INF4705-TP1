package com.inf4705.tp1.calculator;

import com.inf4705.tp1.exception.InvalidMatrixesDimensionsException;
import com.inf4705.tp1.model.Matrix;

public class StandardMatrixCalculator extends MatrixCalculator {
	@Override
	public Matrix multiply(Matrix matrix1, Matrix matrix2) {
		if (!matrix1.canMultiply(matrix2)) {
			throw new InvalidMatrixesDimensionsException();
		}
		Matrix result = new Matrix(matrix1.getDimensionX(), matrix2.getDimensionY());

		for (int i = 0; i < matrix1.getDimensionX(); i++) {
			for (int k = 0; k < matrix2.getDimensionY(); k++) {
				for (int j = 0; j < matrix1.getDimensionY(); j++) {
					int sum = result.getDataAt(i,j);
					sum += (matrix1.getDataAt(i, k) * matrix2.getDataAt(k, j));
					result.setDataAt(i, j, sum);
				}

			}
		}

		return result;
	}
}
