package com.game.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Item {
	
	public float useSpeed;
	public abstract void action(int modifier);
	public abstract void dispose();
	public abstract Sprite getSrite();
	public int ammo;
}
