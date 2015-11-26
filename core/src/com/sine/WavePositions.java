package com.sine;

public enum WavePositions {
	HORIZONTALTOP(0), HORIZONTALBOTTOM(180), VERTICALLEFT(90), VERTICALRIGHT(270);

	private int degree;

	WavePositions(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

}
