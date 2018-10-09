package com.inf4705.tp1.calculator;

public class StrassenSeuilMatrixCalculator extends StrassenMatrixCalculator {
	private static final int DEFAULT_CUTOFF = 4;

	@Override
	protected int getCutoff() {
		return DEFAULT_CUTOFF;
	}
}
