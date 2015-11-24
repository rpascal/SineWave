package com.sine;

import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

public class Sinusoid extends Polyline {	
	public Sinusoid(Vector2 origin, float[] vertices) {
		setVertices(vertices);
		setPosition(origin.x, origin.y);
	}
}
