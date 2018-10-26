package com.game.userinterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UIObject {
	public abstract void draw(SpriteBatch batch);
	public abstract void update();
}
