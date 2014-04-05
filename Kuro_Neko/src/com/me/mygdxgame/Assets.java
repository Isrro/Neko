package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {

	public static AtlasRegion fondoMenu;
	public static AtlasRegion fondoJuego;
	public static AtlasRegion btnEmpezar;
	public static AtlasRegion btnSelDerecha;
	public static AtlasRegion btnSelIzquierda;
	public static AtlasRegion Kuro;
	public static void cargar()
	{
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/kuro.txt"));
		Kuro = atlas.findRegion("kuro01");
		fondoMenu = atlas.findRegion("background");
		fondoJuego = atlas.findRegion("fondo");
		btnEmpezar = atlas.findRegion("btnEmpezar");
		btnSelDerecha = atlas.findRegion("btnSelDerecha");
		btnSelIzquierda = atlas.findRegion("btnSelIzquierda");
	}
}
