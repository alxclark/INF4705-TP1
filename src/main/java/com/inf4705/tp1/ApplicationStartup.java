package com.inf4705.tp1;

import java.time.Duration;
import java.time.LocalDateTime;

import com.inf4705.tp1.FileReader.FileReader;
import com.inf4705.tp1.Model.Matrix;

import sun.rmi.runtime.Log;

public class ApplicationStartup {
	public static void main(String[] args) {
		String algorithmArgument = args[0];
		String matrixLocation1 = args[1];
		matrixLocation1 = matrixLocation1.replace("\\", "\\\\");

		String matrixLocation2 = args[2];
		matrixLocation2 = matrixLocation2.replace("\\", "\\\\");

		boolean withTime = (args.length > 3) ? "-t".equals(args[3]) : false;
		LocalDateTime timeStarted = LocalDateTime.now();

		try {
			FileReader fileReader = new FileReader();
			Matrix matrix1 = fileReader.readMatrixFromFile(matrixLocation1);
			fileReader.resetScanner();;
			Matrix matrix2 = fileReader.readMatrixFromFile(matrixLocation2);

			//Calculator. algo, matrix1, matrix2

			if(withTime){
				LocalDateTime timeEnded = LocalDateTime.now();
				Logger.log(Duration.between(timeStarted, timeEnded).toMillis() + "milliseconds");
			}
		}catch (InvalidMatrixFormatException e){
			Logger.log("Invalid matrix format!");
		}
	}
}
