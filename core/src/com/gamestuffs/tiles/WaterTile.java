package com.gamestuffs.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WaterTile extends Tile{

	private Texture texture;
	private Sprite sprite;
	
	
	public WaterTile(String source, int x, int y) {
		texture = new Texture(Gdx.files.internal(source));
		sprite = new Sprite(texture,29,1,27,27);
		this.x = x;
		this.y = y;
		isSolid=false;
		bounds = new Rectangle(x,y,27,27);
	}
	public void update() {
		
	}
	public int getX(){
		return x;	
	}
	public void render(SpriteBatch batch) {
		batch.draw(sprite,x,y);
	}
	@Override
	public void dispose() {
		texture.dispose();
	}

}
