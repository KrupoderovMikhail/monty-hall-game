package com.krupoderov.montyhall;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.krupoderov.montyhall.managers.GameManager;
import com.krupoderov.montyhall.managers.InputManager;

public class Monty implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	// viewport width and height
	private float w, h;

	@Override
	public void create () {
		// set our viewport to window dimensions
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		// instantiate the camera and set the viewport
		camera = new OrthographicCamera(w, h);
		// center the camera at w/2, h/2
		camera.setToOrtho(false);

		batch = new SpriteBatch();
		// initialize the game
		GameManager.initialize(w, h);
	}

	@Override
	public void dispose () {
		// dispose the batch and the texture
		batch.dispose();
		GameManager.dispose();
	}

	@Override
	public void render () {
		// Clear the screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// set the spritebatch's drawing view to the camera's view
		batch.setProjectionMatrix(camera.combined);
		InputManager.handleInput(camera);

		// render the game objects
		batch.begin();
		GameManager.renderGame(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}



	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
