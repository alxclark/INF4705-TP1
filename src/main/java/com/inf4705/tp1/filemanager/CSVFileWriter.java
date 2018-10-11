package com.inf4705.tp1.filemanager;

import java.io.*;

public class CSVFileWriter {
	private static final String SEPARATOR = ",";
	private static final String FILE_HEADER = "algo,dimension,time,f(x),rapport";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public void appendResultToFile(String resultFilePath, String algo, int matrixDimension, long temps) {
		File resultFile = new File(resultFilePath);
		if (!resultFile.exists()) {
			printHeader(resultFile);
		}
		try (FileWriter fr = new FileWriter(resultFile, true)) {
			double fx = Math.pow(matrixDimension, (Math.log(7) / Math.log(2)));
			double rapport = temps / fx;
			fr.write(algo + SEPARATOR + matrixDimension + SEPARATOR + temps + SEPARATOR + fx + SEPARATOR + rapport);
			fr.write(NEW_LINE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printHeader(File file) {
		try (FileWriter fr = new FileWriter(file, true)) {
			file.createNewFile();
			fr.write(FILE_HEADER);
			fr.write(NEW_LINE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
