package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Comparator;

enum GameState{
	Start,
	Play,
	End
}

public class GDXPlatformer extends ApplicationAdapter {
	public static GDXPlatformer instance;

	ArrayList<GameObject> gameObjects;
	SpriteBatch batch;
	GameState state;

	private InputComponent mainScreenInputComponent;
	private UITextComponent mainScreenTextComponent;

	private Character character;

	private InputComponent endScreenInputComponent;
	private UITextComponent endScreenTextComponent;

	public int Score;
	private UITextComponent scoreComponent;

	public static final int EnemyCount = 3;

	private EnemyComponent[] enemies;

	public GDXPlatformer(){
		instance = this;
		state = GameState.Start;
	}


	@Override
	public void create () {
		gameObjects = new ArrayList<>();

		batch = new SpriteBatch();

		GameObject gameObject = new GameObject("Background");
		TextureComponent texture = new TextureComponent("country field.png");
		texture.SetFixedSize(true, getScreenDimension());
		gameObject.AddComponent(texture);
		gameObjects.add(gameObject);


		character = new Character("Character");
		texture = new TextureComponent("yellowbird.png");
		texture.SetFixedSize(true, getScreenDimension(0.1f), true, false );
		character.AddComponent(texture);
		character.Position.set(Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * 0.4f);
		character.AddComponent(new MoveComponent());
		character.moveComponent.Enabled = false;
		character.AddComponent(new InputComponent(new InputEvent() {
			@Override
			public void OnClick(GameObject parent) {
				((Character)parent).moveComponent.Jump();
			}
		}));
		character.AddComponent(new SceneComponent(0.0f, Gdx.graphics.getHeight()));
		character.inputComponent.Enabled = false;
		ColliderComponent coll = new ColliderComponent( new Circle(0,0, texture.texture.getHeight() * 0.05f));
		character.AddComponent(coll);
		gameObjects.add(character);



		GameObject mainscreenObject = new GameObject("MainScreen");
		mainScreenInputComponent = new InputComponent(new InputEvent() {
			@Override
			public void OnClick(GameObject parent) {
				ChangeGameState(GameState.Play);
			}
		});
		mainScreenTextComponent = new UITextComponent("Press To Start Game!", Color.BLUE, 2);
		mainscreenObject.AddComponent(mainScreenInputComponent);
		mainscreenObject.AddComponent(mainScreenTextComponent);
		mainscreenObject.Position.set(getScreenDimension(0.5f));
		mainscreenObject.PivotPoint.set(0.5f, 0.5f);
		gameObjects.add(mainscreenObject);

		GameObject endScreenObject = new GameObject("EndScreen");
		endScreenInputComponent = new InputComponent(new InputEvent() {
			@Override
			public void OnClick(GameObject parent) {
				ChangeGameState(GameState.Play);
			}
		});
		endScreenInputComponent.Enabled = false;
		endScreenObject.AddComponent(endScreenInputComponent);
		endScreenTextComponent = new UITextComponent("Game Over! Press To Restart!", Color.BLUE, 2);
		endScreenTextComponent.Enabled = false;
		endScreenObject.AddComponent(endScreenTextComponent);
		endScreenObject.Position.set(getScreenDimension(0.5f));
		endScreenObject.PivotPoint.set(0.5f, 0.5f);
		gameObjects.add(endScreenObject);

		GameObject scoreObject = new GameObject("Score");
		scoreComponent = new UITextComponent(String.valueOf(Score), Color.BLUE, 2);
		scoreObject.AddComponent(scoreComponent);
		scoreObject.Position.set( 10, Gdx.graphics.getHeight() / 2);
		scoreObject.PivotPoint.set(0, 0.5f);
		gameObjects.add(scoreObject);

		enemies = new EnemyComponent[EnemyCount];
		for (int i = 0; i < EnemyCount; i++){
			gameObject = new GameObject("Enemy");
			texture = new TextureComponent("redmosq.png");
			texture.SetFixedSize(true, getScreenDimension(0.1f), true, false );
			gameObject.AddComponent(texture);
			enemies[i] = new EnemyComponent(10, Gdx.graphics.getHeight() * 0.1f);
			gameObject.AddComponent(enemies[i]);
			enemies[i].Enabled = false;

			gameObject.Position.x = -1000;
			gameObject.PivotPoint.set(0.5f, 0.5f);
			ColliderComponent collider = new ColliderComponent(new Circle(0,0, Gdx.graphics.getHeight() * 0.05f));
			gameObject.AddComponent(collider);
			coll.RegisteredCollisionObjects.add(collider);

			gameObjects.add(gameObject);
		}

		for(GameObject gameobject : gameObjects)
			for(Component comp : gameobject.components)
				comp.onCreate();
	}

	public void ChangeGameState(GameState state){
		if(this.state == GameState.Start) {
			mainScreenInputComponent.Enabled = false;
			mainScreenTextComponent.Enabled = false;
		}
		else if(this.state == GameState.Play){
			character.inputComponent.Enabled = false;
			character.moveComponent.Enabled = false;
			for(int i = 0; i < EnemyCount; i++){
				enemies[i].Enabled = false;
			}
		}
		else if(this.state == GameState.End){
			endScreenTextComponent.Enabled = false;
			endScreenInputComponent.Enabled = false;
		}

		this.state = state;

		if(this.state == GameState.Start) {

		}
		else if (this.state == GameState.Play){
			Score = -1;
			character.inputComponent.Enabled = true;
			character.moveComponent.Enabled = true;
			for(int i = 0; i < EnemyCount; i++){
				enemies[i].Enabled = true;
				enemies[i].parent.Position.x = -1000;
			}
		}
		else if( this.state == GameState.End){
			endScreenTextComponent.Enabled = true;
			endScreenInputComponent.Enabled = true;
			character.Position.set(Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * 0.4f);
			for(int i = 0; i < EnemyCount; i++)
				enemies[i].parent.Position.x = -1000;
		}



	}

	public void ComponentAdded(){
		for(GameObject obj: gameObjects)
			obj.SortComponent();
	}

	public void GameFinish(){
		ChangeGameState(GameState.End);
	}

	@Override
	public void render () {
		this.scoreComponent.text = String.valueOf(this.Score < 0 ? 0: this.Score / EnemyCount);

		batch.begin();

		for(GameObject gameObject : gameObjects)
			for(Component comp : gameObject.components)
				if(comp.Enabled)
					comp.onFrame(batch);

		batch.end();
	}

	public Vector2 getScreenDimension(){
		return new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public Vector2 getScreenDimension( float multi ){
		return new Vector2(Gdx.graphics.getWidth() * multi,Gdx.graphics.getHeight() * multi);
	}
}
