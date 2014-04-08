package com.me.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.me.screens.Screens;
import com.me.objetos.Cuadro;
import com.me.objetos.Gato;
import com.me.objetos.Moneda;
import com.me.objetos.PlataformaSingle;
import com.me.objetos.Tortuga;

public class WorldGame {

	public enum State
	{
		Running, GameOver
	}
	
State state;
Gato OGato;
World oWorldBox;
Array<Body> arrBodies;
Array<Cuadro> arrCuadros;
Array<PlataformaSingle>arrplatSing;
Array<Moneda> arrMonedas;
Array<Tortuga> arrTortugas;
Random Oran;
int monedas;
public float time = 5;

public WorldGame()
{
	state = State.Running;
	arrBodies = new Array<Body>();
	arrCuadros = new Array<Cuadro>();
	arrplatSing= new Array<PlataformaSingle>();
	arrMonedas = new Array<Moneda>();
	arrTortugas = new Array<Tortuga>();
	Oran = new Random();
	oWorldBox = new World(new Vector2(0, -10),true);
	////
    oWorldBox.setContactListener(new Colisiones());
    ///

	crearGato();
	crearParedIzquierda();
	crearPlataformaSingle(3, 1.5f, true);
	crearPlataformaSingle(5, 1.5f, false);
	crearPlataforma();
	craerCaja(1,1.5f);
	crearMonedas();
	crearTortugas();
}
private void crearTortugas() {
	//inicializar la tortuga
	//para declarar el cuerpo necesitamos una definicion

	float x = Oran.nextFloat() * Screens.WORLD_WIDTH;
	float y = Oran.nextFloat() * Screens.WORLD_HEIGHT;
	
		Tortuga oTortuga = new Tortuga(x, y,3);
			BodyDef bd = new BodyDef();
			bd.position.x = oTortuga.posicion.x;
			bd.position.y = oTortuga.posicion.y;
			
			bd.type = BodyType.KinematicBody;		
			//creamos el cuerpo
			Body oBody = oWorldBox.createBody(bd);
			
			//haremos una linea
			CircleShape shape = new CircleShape();
			shape.setRadius(0.1f);
			//necestamos una fixture
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.friction = 0;
			fixture.isSensor = true;
			oBody.createFixture(fixture);
			//para que no rote el cuadro
			oBody.setUserData(oTortuga);
			arrTortugas.add(oTortuga);
	
}
private void crearMonedas() {
	float x = Oran.nextFloat() * Screens.WORLD_WIDTH - .3f;
	float y = 1.5f;

	Moneda oMon = new Moneda(x, y);
	
	arrMonedas.add(oMon);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	bd.position.x = oMon.posicion.x;
	bd.position.y = oMon.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	CircleShape shape = new CircleShape();
	shape.setRadius(.2f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	//para que cuando choque no lo tome como otra plataforma e impulse a nuestro personaje
	// que la moneda no choque con nada pero aun asi reciba eventos de colisiones
	fixDef.isSensor = true;

	oBody.createFixture(fixDef);

	oBody.setUserData(oMon);


}

private void craerCaja(float x, float y) {
	Cuadro oCuad = new Cuadro(x, y);
	
	arrCuadros.add(oCuad);

	BodyDef bd = new BodyDef();
	bd.type = BodyType.KinematicBody;
	// para que el body siga la posicion de la plataforma, declaraas
	// public la posicion para poder usarla aqui
	bd.position.x = oCuad.posicion.x;
	bd.position.y = oCuad.posicion.y;

	Body oBody = oWorldBox.createBody(bd);

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(.5f, .1f);

	FixtureDef fixDef = new FixtureDef();
	fixDef.shape = shape;
	fixDef.friction = 0;

	oBody.createFixture(fixDef);

	oBody.setUserData(oCuad);	
}

private void crearParedIzquierda() {
	//inicializar piso
			//para declarar el cuerpo necesitamos una definicion
			BodyDef bd = new BodyDef();
			bd.position.x = 0;
			bd.position.y = 0;		
			bd.type = BodyType.StaticBody;		
			//creamos el cuerpo
			Body oBody = oWorldBox.createBody(bd);		
			//haremos una linea
			EdgeShape shape = new EdgeShape();
			shape.set(0,Screens.WORLD_HEIGHT,0, 0);
			//necestamos una fixture
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.friction = 0;
			oBody.createFixture(fixture);
			oBody.setUserData("pared");
	
}

private void crearPlataformaSingle(float x , float y,boolean estado) 
{
	    PlataformaSingle oPlata = new PlataformaSingle(x, y,estado);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.x = x;
		bd.position.y = y;

		Body oBody = oWorldBox.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.4f,  .1f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = 0;

		oBody.createFixture(fixDef);
		oBody.setUserData(oPlata);
		arrplatSing.add(oPlata);
	}

private void crearPlataforma() {
	
	
	//para declarar el cuerpo necesitamos una definicion
	BodyDef bd = new BodyDef();
	bd.position.x = 10;
	bd.position.y = 0.6f;
	
	bd.type = BodyType.StaticBody;
	
	//creamos el cuerpo
	Body oBody = oWorldBox.createBody(bd);
	
	//haremos un rectangulo
	PolygonShape shape = new PolygonShape();
	shape.setAsBox(10f, 0.1f);
	//necestamos una fixture
	FixtureDef fixture = new FixtureDef();
	fixture.shape = shape;
	fixture.friction = 0;
	oBody.createFixture(fixture);
	//para que no rote el cuadro
	oBody.setFixedRotation(true);
	oBody.setUserData("pta");
	
	
}

private void crearGato() {
OGato = new Gato(Screens.WORLD_WIDTH/ 2,Screens.WORLD_HEIGHT/2);
	
	//para declarar el cuerpo necesitamos una definicion
	BodyDef bd = new BodyDef();
	bd.position.x = OGato.position.x;
	bd.position.y = OGato.position.y;
	
	bd.type = BodyType.DynamicBody;
	
	//creamos el cuerpo
	Body oBody = oWorldBox.createBody(bd);
	
	//haremos un rectangulo
	PolygonShape shape = new PolygonShape();
	shape.setAsBox(0.15f, 0.3f);
	//necestamos una fixture
	FixtureDef fixture = new FixtureDef();
	fixture.shape = shape;
	oBody.createFixture(fixture);
	//para que no rote el cuadro
	oBody.setFixedRotation(true);
	oBody.setUserData(OGato);
	
}
public void update(float delta,float acel_x,boolean jump) {
	oWorldBox.step(delta, 4, 8);
	oWorldBox.getBodies(arrBodies);
	time -= delta;
	int lenght = arrBodies.size;
	for(int i = 0; i < lenght;i++)
	{
		Body body = arrBodies.get(i);
		if(body.getUserData() instanceof Gato)
		{
			updateGato(delta,body,acel_x,jump);
		}
		if(body.getUserData() instanceof Cuadro)
		{
			updateCuadro(delta,body);
		}
		if(body.getUserData() instanceof PlataformaSingle)
		{
			updatePlataSing(delta,body);
		}
		if(body.getUserData() instanceof Moneda)
		{
			updateMonedas(delta,body);
		}
		if(body.getUserData() instanceof Tortuga)
		{
			updateTortuga(delta,body);
		}
		if(OGato.state == Gato.State.muerto)
		{
			state = State.GameOver;
		}
		
	}
}

private void updateTortuga(float delta, Body body) {
	Tortuga obj = (Tortuga) body.getUserData();
		obj.update(body, delta);
}
private void updateMonedas(float delta, Body body) {
	Moneda obj = (Moneda) body.getUserData();
// el .3 es por el tiempo qeu dura la animacion
	if (obj.state == Moneda.State.Agarrada) {
	// destruye el cuerpo
	oWorldBox.destroyBody(body);
	crearMonedas();
	// quita el obejto del arreglo
	arrMonedas.removeValue(obj, true);
	//aqui tambien podria ir el int monedas
	return;
	}
	obj.update(body, delta);

}

private void updatePlataSing(float delta, Body body) {
	// TODO Auto-generated method stub
	PlataformaSingle obj = (PlataformaSingle) body.getUserData();
	obj.update(body, delta);
	
	if(obj.state == PlataformaSingle.State.rompiendo)// && obj.state_time>.3f)
	{
		oWorldBox.destroyBody(body);		
		arrplatSing.removeValue(obj, true);
	}
	
	
}

private void updateCuadro(float delta, Body body) {

	
}

public void updateGato(float delta, Body body, float acel_x,boolean jump) {
	OGato.update(delta, body, acel_x,jump,time);	
	
	
}
public class Colisiones implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		// Choca pelota con el piso
		// sacando los objetos que chocarn del contact
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		Gdx.app.log("colicionP", "  ");
		if (a.getBody().getUserData() instanceof Gato) {
			ContactoPersonaje(a, b);
		} else if (b.getBody().getUserData() instanceof Gato) {
			ContactoPersonaje(b, a);
		}
	}

	private void ContactoPersonaje(Fixture Personaje, Fixture otracosa) {
		
		Gato oGato = (Gato) Personaje.getBody().getUserData();

		Object Ootracosa = otracosa.getBody().getUserData();

		if (Ootracosa.equals("pta")) 
		{
			oGato.jump();
		}
		// para que elimine los cuadros rompibles
		else if (Ootracosa instanceof Cuadro) {
			Cuadro obj = (Cuadro) Ootracosa;
			obj.Hit();
			oGato.jump();
		}	
		else if (Ootracosa instanceof PlataformaSingle) {
			PlataformaSingle obj = (PlataformaSingle) Ootracosa;
			obj.hit();
			oGato.jump();
		}	
		else if (Ootracosa instanceof Moneda)
		{
			Moneda obj = (Moneda) Ootracosa;
			obj.Hit();
			monedas++;
		}
		else if (Ootracosa instanceof Tortuga)
		{
			//si choca con la tortuga mandamos llamar el metodo de muerto
			oGato.hit();
		}
	}

	// estan seprados los objetos, despues de toparon
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	// llama antes de que vayan a chocar, pero de que si choquen
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Gdx.app.log("colicionP22", "  ");
		// Choca pelota con el piso
		// sacando los objetos que chocarn del contact
		/*Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		Gdx.app.log("colicion33", "  ");
		
		if (a.getBody().getUserData() instanceof Gato)
		{
			//PreSolveContactoPersonaje(a, b,contact);
		} else if (b.getBody().getUserData() instanceof Gato)
		{
			//PreSolveContactoPersonaje(b, a,contact);
		}*/
	}

	private void PreSolveContactoPersonaje(Fixture Personaje, Fixture otracosa, Contact contacto) {
		//para que la platforma la atraviese cuando haga impacto con ello
		Gato oPersonaje = (Gato) Personaje.getBody().getUserData();

		Object Ootracosa = otracosa.getBody().getUserData();
		 if (Ootracosa instanceof Cuadro) {
			 //tocando por abajo la vamos a atravesar, si la estamos tocando por arriba no la vamos a atravesar
			 //Calcular la posicoin del mono el y - radio 
			float posicion_personaje = oPersonaje.position.y - .3f;
			//Calcular la posicion de la plataforma que seria y + 0.09 y la plataforma mide .1 la mitad y .2 toda en y
			float posicion_plataforma = ((Cuadro) Ootracosa).posicion.y + .09f;
			 if(posicion_personaje < posicion_plataforma){
				 //no existira el concacto y podra atravezar la plataforma desde abajo
				 contacto.setEnabled(false);
			 }
		}
		
	}

	// Ya chocaron, fuerza de colision, direccion.
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
}

}
