package com.sine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;


public class Waves extends ApplicationAdapter {

	private Sinusoid sineWave;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera cam;
	
	@Override
	public void create () {
		
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        System.out.println(w);
        cam = new OrthographicCamera(w,h);
       // cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);
        cam.update();
	    shapeRenderer = new ShapeRenderer();
	    sineWave = SinusoidFactory.createSinusoid(new Vector2(0, 0),(int)w, 20, 4);
	  // cam.zoom = (float) .42;
	    sineWave.rotate(90);
	    System.out.println(sineWave.getLastPosition());
	    
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		cam.viewportWidth  = width;
		cam.viewportHeight = height;
		cam.update();
	}

	@Override
	public void render () {

		cam.update();
		if(Gdx.input.isKeyJustPressed(Keys.A)){
			sineWave.changeFlag = true;
		}
		if(!Gdx.input.isKeyJustPressed(Keys.Q)){
			int amountMove = -3;
			cam.position.y += amountMove;
			sineWave.updateWave(amountMove);
		}
			
		shapeRenderer.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.polyline(sineWave.getTransformedVertices());
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.circle(00, 00, 5);
		shapeRenderer.end();
	}	
	
	
}
