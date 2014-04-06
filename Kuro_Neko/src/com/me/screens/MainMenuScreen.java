package com.me.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.Assets;
import com.me.mygdxgame.Main_Kuro_Neko;
import com.me.objetos.Gato;
import com.me.game.GameScreen;
import com.me.screens.ConfiguracionScreen;

public class MainMenuScreen extends Screens {

	Gato oGato;
	ImageButton btnJugar,btnSelDerecha;
	
	public MainMenuScreen(final Main_Kuro_Neko game) {
		super(game);
		
		oGato = new Gato(SCREEN_WIDTH/2f, 90);
		btnJugar = new ImageButton(new TextureRegionDrawable(Assets.btnEmpezar));
		btnJugar.setPosition(SCREEN_WIDTH  - btnJugar.getWidth() , 50);
		
		btnSelDerecha = new ImageButton(new TextureRegionDrawable(Assets.btnSelDerecha));
		btnSelDerecha.setPosition(SCREEN_WIDTH -100f, SCREEN_HEIGHT -100f);
		btnSelDerecha.setSize(100f, 100f);
		btnJugar.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnJugar.setY(btnJugar.getY() - 3);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnJugar.setY(btnJugar.getY() + 3);
				game.setScreen(new GameScreen(game));
				
			}
		});
		btnSelDerecha.addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnSelDerecha.setY(btnSelDerecha.getY()- 3);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnSelDerecha.setY(btnSelDerecha.getY() + 3);
				game.setScreen(new ConfiguracionScreen(game) );
				
			}
		});
		stage.addActor(btnSelDerecha);
		stage.addActor(btnJugar);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(float delta) {
		batcher.begin();
		batcher.draw(Assets.fondo, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		batcher.end();

		Assets.parallaxFondo.render(delta);

		oCam.update();
		batcher.setProjectionMatrix(oCam.combined);

		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.Kuro.getKeyFrame(oGato.statetime, true), oGato.position.x - 39, oGato.position.y - 25.5f, 78, 51);		
		batcher.end();
		
	}

}
