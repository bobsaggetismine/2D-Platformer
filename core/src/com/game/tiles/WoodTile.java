package com.game.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WoodTile extends Tile{

	public WoodTile(String source,int x,int y){
		texture = new Texture(Gdx.files.internal(source));
		sprite = new Sprite(texture,141,29,27,27);
		this.x = x;
		this.y = y;
		isSolid=true;
		bounds = new Rectangle(x,y,27,27);
	}
	
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite,x,y);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	@Override
	public int getX() {
		return x;
	}

}
