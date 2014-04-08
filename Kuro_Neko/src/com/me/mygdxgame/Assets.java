package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.parallax.ParallaxBackground;
import com.me.parallax.ParallaxLayer;

public class Assets {

	public static BitmapFont Font;

	public static AtlasRegion btnEmpezar;
	public static AtlasRegion btnSelDerecha;
	public static AtlasRegion btnSelIzquierda;
	public static Animation Kuro;
	public static Animation monkey;
	public static Animation nyancat;
	public static AtlasRegion fondo;
	public static AtlasRegion fondochico;
	public static AtlasRegion plataforma;
	public static AtlasRegion Kuro_quieto;
	
	public static ParallaxBackground parallaxFondo;
	
	public static void cargar()
	{
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/kuro.txt"));
		
		AtlasRegion K1 = atlas.findRegion("frame0");
		AtlasRegion K2 = atlas.findRegion("frame1");
		AtlasRegion K3 = atlas.findRegion("frame2");
		AtlasRegion K4 = atlas.findRegion("frame3");
		AtlasRegion K5 = atlas.findRegion("frame4");
		
		Kuro = new Animation(0.15f, K1,K2,K3,K4,K5);
		
		
		AtlasRegion c1 = atlas.findRegion("0");
		AtlasRegion c2 = atlas.findRegion("1");
		AtlasRegion c3 = atlas.findRegion("2");
		AtlasRegion c4 = atlas.findRegion("3");
		AtlasRegion c5 = atlas.findRegion("4");
		AtlasRegion c6 = atlas.findRegion("5");
		AtlasRegion c7 = atlas.findRegion("6");
		AtlasRegion c8 = atlas.findRegion("7");
		AtlasRegion c9 = atlas.findRegion("8");
		AtlasRegion c10 = atlas.findRegion("9");
		AtlasRegion c11 = atlas.findRegion("10");
		
		monkey= new Animation(0.05f, c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11);

		
		AtlasRegion G1 = atlas.findRegion("game1");
		AtlasRegion G2 = atlas.findRegion("game2");
		AtlasRegion G3 = atlas.findRegion("game3");
		
		nyancat = new Animation(0.1f, G1,G2,G3);
		
		Font = new BitmapFont();
		Kuro_quieto = atlas.findRegion("frame2");
		plataforma = atlas.findRegion("plataforma");
		btnEmpezar = atlas.findRegion("btnEmpezar");
		btnSelDerecha = atlas.findRegion("siguiente");
		btnSelIzquierda = atlas.findRegion("anterior");
		fondo = atlas.findRegion("main3");
		fondochico = atlas.findRegion("main2");
		
		ParallaxLayer floor = new ParallaxLayer(atlas.findRegion("main2"), new Vector2(24, 0), new Vector2(0, 0), new Vector2(-1, 700), 805, 401);
		ParallaxLayer as[] = new ParallaxLayer[] { floor };

		parallaxFondo = new ParallaxBackground(as, 800, 480, new Vector2(10, 0));

	}
}
