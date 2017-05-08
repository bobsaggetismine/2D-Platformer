package com.gaestuffs.items;

import com.badlogic.gdx.Input.Keys;

public abstract class Item {

	public abstract void action(int modifier);
	public abstract void dispose();
}
