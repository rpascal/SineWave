package com.sine;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class SinusoidFactory {
	
	private static int amplitude;
	private static final int MINAMPLITUDE = 10;
	
	public static Sinusoid createSinusoid(Vector2 origin, int waveLength, int maxAmplitude, int waveWidth ){
		amplitude = new Random().nextInt(maxAmplitude - MINAMPLITUDE + 1) + MINAMPLITUDE;
		return new Sinusoid(origin, getVertices(waveWidth, waveLength));
	}
	
	private static float sineFunction(double x, int waveWidth) {
		return (float) ((float) amplitude*Math.sin((waveWidth / 100.0) * x));
	}
	
	private static float[] getVertices(int waveWidth, int waveLength) {
		if(waveLength %2 != 0)
			waveLength++;
		float[] points = new float[waveLength];
		int x = 0;
		for (int i = 0, n = waveLength ; i < n ; i+=2) {
			points[i] = x ;
			points[i+1] =sineFunction(x, waveWidth);
			x++;
		}
		return points;
	}

}
