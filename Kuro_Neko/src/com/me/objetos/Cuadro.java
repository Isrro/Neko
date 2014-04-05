package com.me.objetos;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.screens.Screens;

public class Cuadro {
	public enum State
	{
		Rompible, Normal, Rompiendo;
	}
	
	public Vector2 posicion;
	public State state;
	public float state_time;
	public Vector2 velocidad;
	public Cuadro(float x, float y)
	{
		posicion = new Vector2( x , y);
		velocidad = new Vector2(x , y);
		state = State.Normal;
		state_time = 0;
	}
	public Cuadro(float x, float y,Random Oran)
	{
		posicion = new Vector2( x , y);
		if(Oran.nextBoolean())
		{
			state = State.Normal;
		}		
		else
			state = State.Rompible;
		
		if(state == State.Normal)
		{
			velocidad = new Vector2(2, 0);
		}
		else 
			velocidad = new Vector2(0,0);
		state_time = 0;
	}
	public void update(Body body, float delta)
	{
		//actualizando las posiciones
		posicion.x = body.getPosition().x;
		posicion.y = body.getPosition().y;
		//Le damos la velocidad, a la derecha
		// velociad es mayor que 8
		// aqui es para que se muevan las plataformas y no se salga de las medidas del mundo
		if(posicion.x > Screens.WORLD_WIDTH - .27f)
		{
			velocidad.x *= -1;
		}
		else if(posicion.x < 0.25)
		{
			velocidad.x *= -1;
		}

		body.setLinearVelocity(velocidad);
		state_time += delta;
	}
	public void Hit()
	{
		if(state == State.Rompible)
		{
			state = State.Rompiendo;
			state_time = 0;
		}
	}
}
