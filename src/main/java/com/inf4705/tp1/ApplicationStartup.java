package com.inf4705.tp1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.inf4705.tp1.calculator.*;
import com.inf4705.tp1.filemanager.CSVFileWriter;
import com.inf4705.tp1.filemanager.FileReader;
import com.inf4705.tp1.model.Matrix;

public class ApplicationStartup {
	public static void main(String[] args) {
		String algorithmArgument = args[0];
		String matrixLocation1 = args[1];
		matrixLocation1 = matrixLocation1.replace("\\", "/");

		String matrixLocation2 = args[2];
		matrixLocation2 = matrixLocation2.replace("\\", "/");

		boolean withTime = (args.length > 3) ? "-t".equals(args[3]) : false;
		LocalDateTime timeStarted = LocalDateTime.now();

		try {
			Matrix matrix1 = getMatrixFromFile(matrixLocation1);

			Matrix matrix2 = getMatrixFromFile(matrixLocation2);

			MatrixCalculator calculator;
			switch (algorithmArgument) {
				case "conv":
					calculator = new StandardMatrixCalculator();
					break;
				case "strassen":
					calculator = new StrassenMatrixCalculator();
					break;
				case "strassenSeuil":
					calculator = new StrassenSeuilMatrixCalculator();
					break;
				default:
					throw new IllegalArgumentException("Invalid algorithm argument. Options: [conv | strassen | strassenSeuil]");
			}
			Matrix result = calculator.multiply(matrix1, matrix2);
			result.print();

			if (withTime) {
				LocalDateTime timeEnded = LocalDateTime.now();
				Logger.blankLine();
				long time = Duration.between(timeStarted, timeEnded).toMillis();
				Logger.logLine("Operation time: " + time + " milliseconds");

				CSVFileWriter fr = new CSVFileWriter();
				String execPath = System.getProperty("user.dir");
				String[] parentFolderPart = new File(matrixLocation1).getParentFile().getAbsolutePath().replace("\\", "/").split("/");
				String parentFolder = parentFolderPart[parentFolderPart.length - 1];
				fr.appendResultToFile(execPath + "/INF4705-TP1/scripts/resultats/" + parentFolder + "_" + algorithmArgument + ".csv", algorithmArgument, parentFolder,
						matrix1.getDimensionX(), time);
			}
		} catch (IllegalArgumentException e) {
			Logger.logLine(e.getMessage());
		} catch (InvalidMatrixFormatException e) {
			Logger.logLine("Invalid matrix format!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Matrix getMatrixFromFile(String filePath) throws IOException, InvalidMatrixFormatException {
		FileReader fileReader = new FileReader();
		Scanner scanner1 = initScanner(filePath);
		return fileReader.readMatrixFromFile(scanner1);
	}

	private static Scanner initScanner(String filePath) throws IOException {
		Logger.blankLine();
		Logger.logLine("Reading file at: " + filePath);
		File file = new File(filePath);
		return new Scanner(file);
	}
}
