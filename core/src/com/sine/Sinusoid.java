package com.sine;

import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

public class Sinusoid extends Polyline {	
	
	float waveWidth, amplitude;
	float tempAmplitude, tempWaveWidth;
	boolean ampChange, waveWidthChange;
	
	public Sinusoid(int waveWidth, int amplitude) {
		this.amplitude = amplitude;
		this.waveWidth = waveWidth;
		setOrigin(0,0);
		ampChange = false;
		waveWidthChange = false;
	}
	
	public Vector2 getLastPosition(){
		float[] p =getTransformedVertices().clone(); 
		return new Vector2(p[p.length - 2], p[p.length - 1]);
	}
	
	public void updateWave(float amountMove) {
		if(amountMove == 0)
			return;
		
		adjustments();
		
		
		System.out.println(waveWidth);
		float oldRotation = getRotation();
		setRotation(0);
		setVertices(SinusoidFactory.updateVertices(amplitude, waveWidth, getTransformedVertices().clone(), amountMove));	
		rotate(oldRotation);
	}
	
	public void adjustments(){
		if(ampChange)
		adjustAmplitude();
		if(waveWidthChange)
		adjustWaveWidth();
	}
	private void adjustAmplitude(){
		System.out.println(amplitude);
		if(Math.abs(1-(amplitude/tempAmplitude)) < .01)
			ampChange = false;
		 float sign = (amplitude < tempAmplitude) ? 1 :  -1; 
		 amplitude += sign*0.2;
	}
	private void adjustWaveWidth(){
		if(Math.abs(1-(waveWidth/tempWaveWidth)) < .01)
			waveWidthChange = false;
		 float sign = (waveWidth < tempWaveWidth) ? 1 :  -1; 
		 waveWidth += sign*.05;
	}
	

	public void setWaveWidth(float waveWidth) {
		this.tempWaveWidth = waveWidth;
		waveWidthChange = true;
	}

	public void setAmplitude(float amplitude) {
		this.tempAmplitude = amplitude;
		ampChange = true;
	}

	
}
