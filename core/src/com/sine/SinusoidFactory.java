package com.sine;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class SinusoidFactory {
	
	private static final int MINAMPLITUDE = 10;
	
	public static Sinusoid createSinusoid(Vector2 origin, int waveLength, int maxAmplitude, int waveWidth ){
		int amplitude = new Random().nextInt(maxAmplitude - MINAMPLITUDE + 1) + MINAMPLITUDE;
		amplitude = 50;
		return new Sinusoid(origin, getVertices(waveWidth, waveLength, amplitude), waveWidth, amplitude);
	}
	
	private static float sineFunction(double x, float waveWidth, float amplitude) {
		return (float) ((float) amplitude*Math.sin((waveWidth / 100.0) * x));
	}
	
	public static float[] getVertices(int waveWidth, int waveLength, int amplitude) {
		if(waveLength %2 != 0)
			waveLength++;
		int twiceWaveLength = waveLength*2;
		float[] points = new float[twiceWaveLength];
		int x = -waveLength/2;
		for (int i = 0, n = twiceWaveLength ; i < n ; i+=2) {
			points[i] = x ;
			points[i+1] =sineFunction(x, waveWidth, amplitude);
			x++;
		}
		return points;
	}
	
	public static float[] updateVertices(float amplitude, float waveWidth, float[] oldVertices, float amountMove) {
		float[] points = new float[oldVertices.length];
		LinkedList<Float> tempList = new LinkedList<Float>();
		for (int i=0; i < oldVertices.length ; i++)
		    tempList.add(oldVertices[i]);
		
		if(amountMove > 0){
			float oldFinalX = oldVertices[oldVertices.length-2];
			for(int i = 1; i <= amountMove; i++){
				tempList.removeFirst();
				tempList.removeFirst();
				tempList.add(i + oldFinalX);
				tempList.add(sineFunction(i+ oldFinalX, waveWidth, amplitude));
			}
		}else if(amountMove < 0){
			float oldFirstX = oldVertices[0];
			for(int i = -1; i >= amountMove; i--){
				tempList.removeLast();
				tempList.removeLast();
				tempList.addFirst(sineFunction(i+ oldFirstX, waveWidth, amplitude));
				tempList.addFirst(i + oldFirstX);
			}
		}
		
		for(int i = 0; i < oldVertices.length; i++){
			points[i] = tempList.get(i);
		}
		
		return points;
	}
	

}
