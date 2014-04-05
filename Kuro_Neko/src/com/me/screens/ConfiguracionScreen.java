package com.me.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.Assets;
import com.me.mygdxgame.Main_Kuro_Neko;

public class ConfiguracionScreen extends Screens{
	
ImageButton btnSelIzquierda;

	public ConfiguracionScreen(final Main_Kuro_Neko game) {
		super(game);
		btnSelIzquierda = new ImageButton(new TextureRegionDrawable(Assets.btnSelIzquierda));
		btnSelIzquierda.setPosition(0,0);
		
		btnSelIzquierda.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnSelIzquierda.setY(btnSelIzquierda.getY() -3);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				btnSelIzquierda.setY(btnSelIzquierda.getY() + 3);
				game.setScreen(new MainMenuScreen(game));
			}
		});
		stage.addActor(btnSelIzquierda);
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

	}

}
