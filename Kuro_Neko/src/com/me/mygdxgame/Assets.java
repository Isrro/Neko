package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.parallax.ParallaxBackground;
import com.me.parallax.ParallaxLayer;

public class Assets {


	public static AtlasRegion btnEmpezar;
	public static AtlasRegion btnSelDerecha;
	public static AtlasRegion btnSelIzquierda;
	public static Animation Kuro;
	public static AtlasRegion fondo;
	
	public static ParallaxBackground parallaxFondo;
	
	public static void cargar()
	{
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/kuro.txt"));
		
		AtlasRegion K1 = atlas.findRegion("kuro");
		AtlasRegion K2 = atlas.findRegion("kuro");
		AtlasRegion K3 = atlas.findRegion("kuro");
		
		Kuro = new Animation(0.3f, K1,K2,K3);
		
		btnEmpezar = atlas.findRegion("btnEmpezar");
		btnSelDerecha = atlas.findRegion("btnSelDerecha");
		btnSelIzquierda = atlas.findRegion("btnSelIzquierda");
		fondo = atlas.findRegion("main3");
		
		ParallaxLayer floor = new ParallaxLayer(atlas.findRegion("main3"), new Vector2(24, 0), new Vector2(0, 0), new Vector2(-1, 700), 1060, 529);
		ParallaxLayer as[] = new ParallaxLayer[] { floor };

		parallaxFondo = new ParallaxBackground(as, 1060, 529, new Vector2(10, 0));

	}
}
