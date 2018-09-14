package com.inf4705.tp1.Model;

public class Matrix {
	private int dimension;
	private int[][] data;

	public Matrix(int size) {
		dimension = (int) Math.pow(2, size);
		data = new int[dimension][dimension];
	}

	public void addLine(int lineCount, int[] numberValues) {
		for (int i = 0; i < numberValues.length; i++) {
			data[lineCount][i] = numberValues[i];
		}
	}

	public boolean validateLineSize(int length) {
		return dimension == length;
	}

	public boolean hasPlaceForMoreLines(int lineCount) {
		return lineCount < dimension;
	}
}
