package com.game.tiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Tile {
	public Rectangle bounds;
	protected int x,y;
	public boolean isSolid;
	protected Texture texture;
	protected Sprite sprite;
	public abstract void update();
	public abstract void render(SpriteBatch batch);
	public abstract void dispose();
	public abstract int getX();
}
