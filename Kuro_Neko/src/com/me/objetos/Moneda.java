package com.me.objetos;

import com.badlogic.gdx.math.DelaunayTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Moneda {
public enum State
{
	Normal, Agarrada
}
	public State state;
	public Vector2 posicion;
	public float state_time;
	
	public Moneda(float x, float y){
		posicion = new Vector2(x,y);
		state = State.Normal;
	}
	public void update(Body  body, float delta) {
		//actualizando las posiciones
				posicion.x = body.getPosition().x;
				posicion.y = body.getPosition().y;
				state_time += delta;
	}
	public void Hit(){
		state_time = 0;
		state = State.Agarrada;		
	}
}
