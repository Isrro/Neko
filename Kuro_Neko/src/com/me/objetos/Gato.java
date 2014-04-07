package com.me.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Gato {
	public enum State {
		standing,saltando, cayendo, muerto
	}

	public State state;
	public float statetime;
	public boolean jump;
	public int lado;

	public Vector2 position;
	public Vector2 velocidad;

	public Gato(float x, float y) {
		// TODO Auto-generated constructor stub
		position = new Vector2(x,y);
		statetime = 0;
		state = State.cayendo;
		velocidad = new Vector2();

	}

	public void update(float delta, Body body, float acelx, boolean jump) {
		
		
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		velocidad = body.getLinearVelocity();
		if(state !=Gato.State.muerto)
		{
		if(state != State.muerto)
		{
		body.setLinearVelocity(acelx*5,velocidad.y);
		if (jump && state ==State.standing) 
		{
			//jump = false;			
			state = State.saltando;
			statetime = 0;
			//para que sale mas		
			body.setLinearVelocity(velocidad.x, 5);
			
		}
		
		if(state == State.saltando)
			if (velocidad.y < 0 && state != State.cayendo)
			{
				state = State.cayendo;
				statetime = 0;
		}
		}
		}
		statetime+=delta;
		//body.setLinearVelocity(acelx*5,velocidad.y);

	}

	public void jump() {
		if (state == State.cayendo) 
		{				
			state = State.standing;			
		}
						}
	public void hit()
	{
		if(state != State.muerto)
		{
		   state = State.muerto;
		   statetime = 0;
		}
		//siempre que cambiamos de un estado a otro reiniciamos el tiempo 
	}
}
