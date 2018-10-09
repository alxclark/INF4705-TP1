package com.inf4705.tp1.calculator;

import com.inf4705.tp1.model.Matrix;

public class StrassenMatrixCalculator extends MatrixCalculator {
	//for the normal strassen the cutoff is 1 aka as only a number
	protected int getCutoff() {
		return 1;
	}

	@Override
	public Matrix multiply(Matrix matrix1, Matrix matrix2) {
		int n = matrix1.getDimensionY();
		int m = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
		Matrix result = new Matrix(n, n);

		if(n != m) {
			//handle odd matrices
			Matrix effectiveA = new Matrix(m, m);
			Matrix effectiveB = new Matrix(m, m);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					effectiveA.setDataAt(i, j, matrix1.getDataAt(i, j));
					effectiveB.setDataAt(i, j, matrix2.getDataAt(i, j));
				}
			}

			Matrix effectiveResult = doMultiply(effectiveA, effectiveB);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					result.setDataAt(i, j, effectiveResult.getDataAt(i, j));
				}
			}
		}else{
			result = doMultiply(matrix1, matrix2);
		}
		return result;
	}

	private Matrix doMultiply(Matrix a, Matrix b) {
		int n = a.getDimensionY();

		if (n <= getCutoff()) {//finish the recursion with a normal operation
			StandardMatrixCalculator calculator = new StandardMatrixCalculator();
			return calculator.multiply(a, b);
		} else {
			int newSize = n / 2;
			//create all the new sub-matrixs
			Matrix a11 = new Matrix(newSize, newSize);
			Matrix a12 = new Matrix(newSize, newSize);
			Matrix a21 = new Matrix(newSize, newSize);
			Matrix a22 = new Matrix(newSize, newSize);

			Matrix b11 = new Matrix(newSize, newSize);
			Matrix b12 = new Matrix(newSize, newSize);
			Matrix b21 = new Matrix(newSize, newSize);
			Matrix b22 = new Matrix(newSize, newSize);

			Matrix aResult;
			Matrix bResult;

			//divide in submatrixes
			for (int i = 0; i < newSize; i++) {
				for (int j = 0; j < newSize; j++) {
					a11.setDataAt(i, j, a.getDataAt(i, j));
					a12.setDataAt(i, j, a.getDataAt(i, j + newSize));
					a21.setDataAt(i, j, a.getDataAt(i + newSize, j));
					a22.setDataAt(i, j, a.getDataAt(i + newSize, j + newSize));

					b11.setDataAt(i, j, b.getDataAt(i, j));
					b12.setDataAt(i, j, b.getDataAt(i, j + newSize));
					b21.setDataAt(i, j, b.getDataAt(i + newSize, j));
					b22.setDataAt(i, j, b.getDataAt(i + newSize, j + newSize));
				}
			}

			aResult = add(a11, a22);
			bResult = add(b11, b22);
			Matrix p1 = doMultiply(aResult, bResult);

			aResult = add(a21, a22);
			Matrix p2 = doMultiply(aResult, b11);

			bResult = sub(b12, b22);
			Matrix p3 = doMultiply(a11, bResult);

			bResult = sub(b21, b11);
			Matrix p4 = doMultiply(a22, bResult);

			aResult = add(a11, a12);
			Matrix p5 = doMultiply(aResult, b22);

			aResult = sub(a21, a11);
			bResult = add(b11, b12);
			Matrix p6 = doMultiply(aResult, bResult);

			aResult = sub(a12, a22);
			bResult = add(b21, b22);
			Matrix p7 = doMultiply(aResult, bResult);

			//compose result matrix
			Matrix c12 = add(p3, p5);
			Matrix c21 = add(p2, p4);

			aResult = add(p1, p4);
			bResult = add(aResult, p7);
			Matrix c11 = sub(bResult, p5);

			aResult = add(p1, p3);
			bResult = add(aResult, p6);
			Matrix c22 = sub(bResult, p2);

			return regroupFinalMatrix(c11, c12, c21, c22, n, newSize);
		}
	}

	private Matrix regroupFinalMatrix(Matrix c11, Matrix c12, Matrix c21, Matrix c22, int n, int newSize){
		Matrix c = new Matrix(n, n);
		for (int i = 0; i < newSize; i++) {
			for (int j = 0; j < newSize; j++) {
				c.setDataAt(i, j, c11.getDataAt(i, j));
				c.setDataAt(i, j + newSize, c12.getDataAt(i, j));
				c.setDataAt(i + newSize, j, c21.getDataAt(i, j));
				c.setDataAt(i + newSize, j + newSize, c22.getDataAt(i, j));
			}
		}
		return c;
	}
}
