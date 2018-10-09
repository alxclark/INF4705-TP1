package com.inf4705.tp1.model;

import com.inf4705.tp1.Logger;

public class Matrix {
	private int dimensionX;
	private int dimensionY;
	private int[][] data;

	public Matrix(int size) {
		dimensionX = (int) Math.pow(2, size);
		dimensionY = (int) Math.pow(2, size);
		data = new int[dimensionX][dimensionY];
	}

	public Matrix(int dimensionX, int dimensionY) {
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;

		data = new int[dimensionX][dimensionY];
	}

	public void addLine(int lineCount, int[] numberValues) {
		for (int i = 0; i < numberValues.length; i++) {
			data[lineCount][i] = numberValues[i];
		}
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public int getDataAt(int x, int y) {
		return data[x][y];
	}

	public void setDataAt(int x, int y, int data) {
		this.data[x][y] = data;
	}

	public boolean validateLineSize(int length) {
		return dimensionX == length;
	}

	public boolean hasPlaceForMoreLines(int lineCount) {
		return lineCount < dimensionY;
	}

	public void print() {
		Logger.blankLine();
		Logger.logLine("Matrix: ");
		for (int i = 0; i < dimensionY; i++) {
			for (int j = 0; j < dimensionX; j++) {
				Logger.log(data[i][j] + " ");
			}
			Logger.log("\n");
		}
	}

	public boolean canMultiply(Matrix matrix2) {
		return dimensionY == matrix2.dimensionX;
	}

	public boolean canAdd(Matrix matrix2) {
		return dimensionX == matrix2.getDimensionX() && dimensionY == matrix2.getDimensionY();
	}
}
