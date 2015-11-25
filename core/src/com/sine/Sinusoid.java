package com.sine;

import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

public class Sinusoid extends Polyline {	
	
	float waveWidth, amplitude;
	boolean changeFlag = false;
	
	public Sinusoid(Vector2 origin, float[] vertices, int waveWidth, int amplitude) {
		this.amplitude = amplitude;
		this.waveWidth = waveWidth;
		setVertices(vertices);
		setPosition(origin.x, origin.y);
		setOrigin(0, 0);
	}
	
	public Vector2 getLastPosition(){
		float[] p =getTransformedVertices().clone(); 
		return new Vector2(p[p.length - 2], p[p.length - 1]);
	}
	
	public void updateWave(float amountMove) {
		if(amountMove == 0)
			return;
		
		if(changeFlag){
			System.out.println("hgere " +  amplitude);
			if(amplitude < 100){
				amplitude += .5;
			}else{
				changeFlag = !changeFlag;
			}
			
		}
		
		float oldRotation = getRotation();
		setRotation(0);
		setVertices(SinusoidFactory.updateVertices(amplitude, waveWidth, getTransformedVertices().clone(), amountMove));	
		rotate(oldRotation);
	}
	
	
}
