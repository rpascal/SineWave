package com.sine;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SinusoidFactory {
	
	private static final int MINAMPLITUDE = 10;
	private static final int BUFFERFACTOR = 8;
	
	public static Sinusoid createSinusoid(Vector2 origin, int waveLength, int maxAmplitude, int waveWidth ){
		int amplitude = new Random().nextInt(maxAmplitude - MINAMPLITUDE + 1) + MINAMPLITUDE;
		amplitude = 50;

		Sinusoid sine = new Sinusoid(waveWidth, amplitude);  
		sine.rotate(90);
		sine.setPosition(origin.x, origin.y);
		sine.setVertices(getVertices(waveWidth, waveLength, amplitude));
		return sine;
	}
	
	public static Sinusoid createSinusoid(WavePositions wavePosition){
		float height =  Gdx.graphics.getHeight();
		float width =  Gdx.graphics.getWidth();
		int waveWidth = 4;
		int amplitude = 0;
		int rotation = 0;
		Vector2 startingPosition = new Vector2();
		
		switch (wavePosition){
		case HORIZONTALBOTTOM:
		case HORIZONTALTOP:
			amplitude = new Random().nextInt((int) (getMaxAmplitudeForWave(height) - MINAMPLITUDE + 1)) + MINAMPLITUDE;
			amplitude = (int) getMaxAmplitudeForWave(height);
			break;
		case VERTICALLEFT:
		case VERTICALRIGHT:
			amplitude = new Random().nextInt((int) (getMaxAmplitudeForWave(width) - MINAMPLITUDE + 1)) + MINAMPLITUDE;
			amplitude = (int) getMaxAmplitudeForWave(width);
			rotation = 90;
			break;
		}
		
		startingPosition = getDefaultStartingPostions(width).rotate(wavePosition.getDegree());
		Sinusoid sine = new Sinusoid(waveWidth, amplitude);  
		sine.rotate(rotation);
		sine.setPosition(startingPosition.x, startingPosition.y);
		sine.setVertices(getVertices(waveWidth, width, amplitude));
		return sine;
	}
	

	public static float getMaxAmplitudeForWave(float size){
		float buffer = size / BUFFERFACTOR;
		float middleOfScreenHeight = size / 2;
		System.out.println((middleOfScreenHeight - buffer)/2 - buffer);
		return ((middleOfScreenHeight - buffer)/2);
	}	
	
	
	public static Vector2 getDefaultStartingPostions(float size){
		float buffer = size / BUFFERFACTOR;
		float middleOfScreenHeight = size / 2;
		float heightOfSineFunctionsArea = middleOfScreenHeight - buffer;
		return new Vector2(0,buffer + (heightOfSineFunctionsArea/2) );
	}	
	
	
	private static float sineFunction(double x, float waveWidth, float amplitude) {
		return (float) ((float) amplitude*Math.sin((waveWidth / 100.0) * x));
	}
	
	public static float[] getVertices(float waveWidth, float waveLength, float amplitude) {
		if(waveLength %2 != 0)
			waveLength++;
		int twiceWaveLength = (int) (waveLength*2);
		float[] points = new float[twiceWaveLength];
		float x = -waveLength/2;
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
