package com.inf4705.tp1.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.omg.CORBA.MARSHAL;

import com.inf4705.tp1.InvalidMatrixFormatException;
import com.inf4705.tp1.Logger;
import com.inf4705.tp1.Model.Matrix;

public class FileReader {
	private Scanner scanner;

	public void resetScanner(){
		scanner = null;
	}

	public Matrix readMatrixFromFile(String filePath) throws InvalidMatrixFormatException {
		try {
			System.out.println("Reading file at: " + filePath);
			File file = new File(filePath);
			scanner = new Scanner(file);

			int size = Integer.parseInt(scanner.nextLine());
			Matrix matrix = new Matrix(size);
			int lineCount = 0;
			while (scanner.hasNextLine()) {
				if(!matrix.hasPlaceForMoreLines(lineCount)){
					throw new InvalidMatrixFormatException();
				}

				int[] numberValues = readLine();
				if(!matrix.validateLineSize(numberValues.length)){
					throw new InvalidMatrixFormatException();
				}

				matrix.addLine(lineCount, numberValues);
				lineCount++;
			}
			return matrix;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int[] readLine() {
		String line = scanner.nextLine();
		Logger.log(line);
		String[] values = line.split(" ");
		return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
	}
}
