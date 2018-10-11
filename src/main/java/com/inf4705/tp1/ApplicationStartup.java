package com.inf4705.tp1;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
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

			LocalDateTime timeStarted = LocalDateTime.now();
			Matrix result = calculator.multiply(matrix1, matrix2);
			//result.print();

			if (withTime) {
				LocalDateTime timeEnded = LocalDateTime.now();
				Logger.blankLine();
				long time = Duration.between(timeStarted, timeEnded).toMillis();
				Logger.logLine("Operation time: " + time + " milliseconds");

				CSVFileWriter fr = new CSVFileWriter();
				String scriptFolderPath = new File(getJarPath()).getParent();
				fr.appendResultToFile(scriptFolderPath + "/resultats/results.csv", algorithmArgument, matrix1.getDimensionX(), time);
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

	private static String getJarPath() throws UnsupportedEncodingException {
		URL url = ApplicationStartup.class.getProtectionDomain().getCodeSource().getLocation();
		String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
		String parentPath = new File(jarPath).getParentFile().getPath();
		return parentPath;
	}
}
