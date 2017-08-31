package com.game.states;

import java.awt.Window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{

	private Texture texture;
	private Sprite sprite;
	private GameStateManager gsm;
	public MenuState(GameStateManager gsm){
		this.gsm=gsm;
		texture = new Texture("Textures/background.png");
		sprite = new Sprite(texture,255,225);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(sprite,0,0,1280,720);
		batch.end();
	}
	public void init(){
		
	}
	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
			gsm.setState(new GameState(gsm));
		}
	}	
}
