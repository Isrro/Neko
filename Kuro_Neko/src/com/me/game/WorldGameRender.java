package com.me.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.me.objetos.Moneda;
import com.me.objetos.Gato;
import com.me.objetos.PlataformaSingle;
import com.me.objetos.Tortuga;
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

		renderBackground(delta);
		oCam.update();
		batcher.setProjectionMatrix(oCam.combined);
		
		batcher.begin();
		//fondo esta hasta atras
		batcher.disableBlending();
		batcher.draw(Assets.fondo, oCam.position.x - Screens.WORLD_WIDTH /2f, oCam.position.y - Screens.WORLD_HEIGHT / 2f, Screens.WORLD_WIDTH,
				Screens.WORLD_HEIGHT);
		batcher.enableBlending();
		dibujarGato();
		dibujarPlataformas();
		dibujarMonedas();
		dibujarTortugaS();
		dibujarPlataformas();
		dibujarMonedas();

		batcher.end();
		renderBox.render(oWorld.oWorldBox, oCam.combined);
	}


	private void dibujarTortugaS() {
int length = oWorld.arrTortugas.size;

	for(int i = 0; i < length;i++){
	Tortuga oTor = oWorld.arrTortugas.get(i);
	TextureRegion keyframe;

	//true es que la aniimacion se repeteria muchas veces
	keyframe = Assets.nyancat.getKeyFrame(oTor.state_time, true);

	if(oTor.velocidad.x > 0)
    batcher.draw(keyframe, oTor.posicion.x - 0.2f, oTor.posicion.y - 0.2f, 0.4f , .4f );
	else
	    batcher.draw(keyframe, oTor.posicion.x + 0.2f, oTor.posicion.y - 0.2f, - 0.4f , .4f );
}
	}

	private void dibujarMonedas() {
int length = oWorld.arrMonedas.size;
		
		for(int i = 0; i < length;i++){
			Moneda oMon = oWorld.arrMonedas.get(i);
			TextureRegion keyframe;

			//true es que la aniimacion se repeteria muchas veces
			keyframe = Assets.btnSelDerecha;

		    batcher.draw(keyframe, oMon.posicion.x - 0.2f, oMon.posicion.y - 0.2f, 0.4f , .4f );
		}
	}

	private void dibujarPlataformas() {
		int lenght = oWorld.arrplatSing.size;
		for(int i = 0; i < lenght; i++)
		{
			PlataformaSingle obj = oWorld.arrplatSing.get(i);
			TextureRegion keyframe = Assets.plataforma;

		    batcher.draw(keyframe, obj.posicion.x - 0.4f, obj.posicion.y - 0.1f, .8f , .2f );
		}
		
		
	}

	private void dibujarGato()
	{
		TextureRegion keyframe;
		
		
		if(oWorld.OGato.state == Gato.State.saltando)
		{
			keyframe = Assets.monkey.getKeyFrame(oWorld.OGato.statetime, true);
		}
		else if (oWorld.OGato.state == Gato.State.cayendo)
		{
			keyframe = Assets.monkey.getKeyFrame(oWorld.OGato.statetime, true);
		}
		else if(oWorld.OGato.state ==Gato.State.muerto)
		{
			keyframe = Assets.monkey.getKeyFrame(oWorld.OGato.statetime, false);
		}
		else
			keyframe = Assets.monkey.getKeyFrame(oWorld.OGato.statetime, true);
		
		if(oWorld.OGato.lado== 1)
				batcher.draw(keyframe, oWorld.OGato.position.x -0.20f, oWorld.OGato.position.y-0.3f, 0.7f, .7f);
		else 
				batcher.draw(keyframe, oWorld.OGato.position.x +0.20f, oWorld.OGato.position.y-0.3f, -0.7f, .7f);
	
		
	}
	private void renderBackground(float delta) {
		Assets.parallaxFondo.render(delta);

	}
}
