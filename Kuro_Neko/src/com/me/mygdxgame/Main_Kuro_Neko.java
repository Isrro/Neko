package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.me.screens.MainMenuScreen;

public class Main_Kuro_Neko extends Game{
	
	@Override
	public void create() {		
	Assets.cargar();
	setScreen(new MainMenuScreen(this));
	}
}
