package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.me.mygdxgame.Main_Kuro_Neko;

public abstract class Screens extends InputAdapter implements Screen{

	public static final float WORLD_WIDTH = 8;
	public static final float WORLD_HEIGHT = 4.8f;

	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 480;

	public Main_Kuro_Neko game;

	public OrthographicCamera oCam;
	public SpriteBatch batcher;
	public Stage stage;

	public Screens(Main_Kuro_Neko game) {
		this.game = game;
		oCam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		oCam.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
		batcher = new SpriteBatch();
		stage = new Stage(new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT));	

		//para que toda la aplicacion reciba eventos como teclado o tocar pantalla
		InputMultiplexer input = new InputMultiplexer(this,stage);
		Gdx.input.setInputProcessor(input);
	}
	@Override
	public void render(float delta) {

		update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw(delta);

		stage.act(delta);
		stage.draw();

	}

	public abstract void update(float delta);

	public abstract void draw(float delta);

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
