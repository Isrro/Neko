package com.me.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.me.mygdxgame.Assets;
import com.me.game.WorldGameRender;
import com.me.mygdxgame.Main_Kuro_Neko;
import com.me.screens.Screens;

public class GameScreen extends Screens{


	public enum State {ready,running,gameover}
	WorldGame oWorld;
	WorldGameRender renderer;
	public State state;
	boolean jump;
	
	public GameScreen(Main_Kuro_Neko game) {
		super(game);
		oWorld = new WorldGame();
		renderer = new WorldGameRender(batcher,oWorld);
		state = State.ready;	
		
	}

	@Override
	public void update(float delta) {
		switch (state) {
		case ready:
			updateready(delta);
			break;
		case running:
			updaterunning(delta);
			break;
		default:
		case gameover:
			updategameover(delta);
			break;
		}
	}

	private void updategameover(float delta) {
		// TODO Auto-generated method stub
		
	}

	private void updaterunning(float delta) {
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE))
			jump = true;
		 
		float acel_x = 0;
		if(Gdx.input.isKeyPressed(Keys.A))
		{
		    acel_x = -1;
		    oWorld.OGato.lado=0;
		}
		else if(Gdx.input.isKeyPressed(Keys.D))
		{
			acel_x = 1;
			oWorld.OGato.lado=1;
		}
		oWorld.update(delta,acel_x,jump);
		
		jump = false;
	}

	private void updateready(float delta) {
		if(Gdx.input.isTouched())
		{
			state = State.running;
		}
		
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
				renderer.render(delta);
				oCam.update();
				batcher.setProjectionMatrix(oCam.combined);
				batcher.begin();
				
				switch (state) {
				case ready:
					drawready(delta);
					break;
				case running:
					drawrunning(delta);
					break;
				default:
				case gameover:
					drawgameover(delta);
					break;
				}
				batcher.end();
	}

	private void drawgameover(float delta) {
		// TODO Auto-generated method stub
		
	}

	private void drawrunning(float delta) {
		// TODO Auto-generated method stub
		
	}

	private void drawready(float delta) {
		// TODO Auto-generated method stub
		
	}

}
