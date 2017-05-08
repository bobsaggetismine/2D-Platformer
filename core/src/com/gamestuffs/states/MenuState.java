package com.gamestuffs.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{

	Texture texture;
	Sprite background;
	GameStateManager gsm;
	public MenuState(Texture texture,GameStateManager gsm){
		this.gsm =gsm;
		this.texture = texture;
		this.background = new Sprite(texture);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(background,0,0);
		batch.end();
	}
	public void init(){
		
	}
	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
			GameState newState = new GameState(gsm);
			gsm.setState(newState);
		}
	}	
}
