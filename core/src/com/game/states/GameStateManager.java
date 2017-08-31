package com.game.states;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
	
	public static final int menuState = 0;
	public static final int gameState = 1;
	Stack<State> states;
	
	public GameStateManager(){
	states = new Stack<State>();
	states.add(new MenuState(this));
	}
	public void update(float delta){
		states.peek().update(delta);
	}
	public void render(SpriteBatch batch){
		states.peek().render(batch);
	}
	public void setState(State newState){
		states.pop();
		states.push(newState);
		states.peek().init();
	}
	public void addState(State state){
		states.push(state);
	}
	public State getState(){
		return states.peek();
	}
	
}
