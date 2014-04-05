package com.me.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.me.objetos.Gato;
import com.me.mygdxgame.Assets;
import com.me.screens.Screens;
import com.me.game.WorldGame;

public class WorldGameRender {
	SpriteBatch batcher;
	OrthographicCamera oCam;
	Box2DDebugRenderer renderBox;
	WorldGame oWorld;
	
	public WorldGameRender(SpriteBatch batcher, WorldGame oWorld) {
		oCam = new OrthographicCamera(Screens.WORLD_WIDTH, Screens.WORLD_HEIGHT);
		oCam.position.set(Screens.WORLD_WIDTH/2f,Screens.WORLD_HEIGHT/2f, 0);
		this.batcher = batcher;
		this.oWorld = oWorld;
		renderBox = new Box2DDebugRenderer();		
		
	}
	
	public void render(float delta)
	{
		//QUe la camara siga a Mario
		oCam.position.x = oWorld.OGato.position.x;
		//limite a la camara para que no se vaya hasta bajo
		if(oCam.position.y < Screens.WORLD_HEIGHT/ 2f)
			oCam.position.y = Screens.WORLD_HEIGHT/2f;
		//------------------------------
		oCam.update();
		batcher.setProjectionMatrix(oCam.combined);
		
		batcher.begin();
		//fondo esta hasta atras
		batcher.disableBlending();
		batcher.draw(Assets.fondoJuego, oCam.position.x - Screens.WORLD_WIDTH /2f, oCam.position.y - Screens.WORLD_HEIGHT / 2f, Screens.WORLD_WIDTH,
				Screens.WORLD_HEIGHT);
		batcher.enableBlending();
		dibujarGato();
		batcher.end();
		renderBox.render(oWorld.oWorldBox, oCam.combined);
	}

	private void dibujarGato() {
		TextureRegion keyframe;
		if(oWorld.OGato.state == Gato.State.saltando)
		{
			keyframe = Assets.Kuro;
		}
		else if (oWorld.OGato.state == Gato.State.cayendo)
		{
			keyframe = Assets.Kuro;
		}
		else
			keyframe = Assets.Kuro;
		if(oWorld.OGato.velocidad.x > 0)
		batcher.draw(keyframe, oWorld.OGato.position.x -0.35f, oWorld.OGato.position.y-0.4f, 0.7f, 1f);
		else 
			batcher.draw(keyframe, oWorld.OGato.position.x +0.35f, oWorld.OGato.position.y-0.4f, -0.7f, 1f);
		
		
	}
}
