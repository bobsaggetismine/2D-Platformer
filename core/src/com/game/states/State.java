package com.game.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

	
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta);
	public abstract void init();
}
