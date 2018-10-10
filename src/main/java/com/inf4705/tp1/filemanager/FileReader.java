package com.inf4705.tp1.filemanager;

import java.util.Arrays;
import java.util.Scanner;

import com.inf4705.tp1.InvalidMatrixFormatException;
import com.inf4705.tp1.Logger;
import com.inf4705.tp1.model.Matrix;

public class FileReader {
	public Matrix readMatrixFromFile(Scanner scanner) throws InvalidMatrixFormatException {
		int size = Integer.parseInt(scanner.nextLine());
		Matrix matrix = new Matrix(size);
		int lineCount = 0;
		while (scanner.hasNextLine()) {
			if (!matrix.hasPlaceForMoreLines(lineCount)) {
				throw new InvalidMatrixFormatException();
			}

			int[] numberValues = readLine(scanner);
			if (!matrix.validateLineSize(numberValues.length)) {
				throw new InvalidMatrixFormatException();
			}

			matrix.addLine(lineCount, numberValues);
			lineCount++;
		}
		return matrix;
	}

	public int[] readLine(Scanner scanner) {
		String line = scanner.nextLine();
		Logger.logLine(line);
		String[] values = line.split("\t");
		return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
	}
}
