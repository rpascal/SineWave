package com.sine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;


public class Waves extends ApplicationAdapter {
	SpriteBatch batch;
	
	Sinusoid sineWave;
	
	ShapeRenderer shapeRenderer;// = new ShapeRenderer();
	
	
	
	@Override
	public void create () {
	    shapeRenderer = new ShapeRenderer();
	    batch = new SpriteBatch();
	    sineWave = SinusoidFactory.createSinusoid(new Vector2(100, 200),420, 100, 4);
	    //sineWave.rotate(20);
	}
	

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glDisable(GL20.GL_BLEND);

		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.polyline(sineWave.getVertices());
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.circle(100, 200, 5);
		shapeRenderer.end();
	}	
	
	
}
