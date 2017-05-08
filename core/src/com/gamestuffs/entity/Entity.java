package com.gamestuffs.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected boolean kills;
	protected float x,y;
	private int ID;
	public abstract int getId();
	public abstract void render(SpriteBatch batch);
	public abstract void dispose();
	public void update(){}
	public Entity(){
		kills = false;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
}
