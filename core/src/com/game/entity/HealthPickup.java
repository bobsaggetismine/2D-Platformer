package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.states.GameState;
import com.game.states.GameStateManager;

public class HealthPickup extends Entity{
	private Texture texture;
	private Sprite sprite;
	private GameStateManager gsm;
	private Rectangle bounds;
	public HealthPickup(int x, int y, GameStateManager gsm){
		bounds = new Rectangle(x+7,y,11,9);
		this.gsm = gsm;
		this.x = x;
		this.y = y;
		texture = new Texture("Textures/download.png");
		sprite = new Sprite(texture,1,29,27,27);
	}
	public int getId() {
		return 5;
	}
	public void update(){
		for (int i = 0;i< ((GameState)gsm.getState()).entities.size();i++){
			if (((GameState)gsm.getState()).entities.get(i).getId() == 1){
				if (((Player)((GameState)gsm.getState()).entities.get(i)).hitsLeft(bounds) || ((Player)((GameState)gsm.getState()).entities.get(i)).hitsRight(bounds)){
					dispose();
					((GameState)gsm.getState()).removeEntity(this);
					((GameState)gsm.getState()).getPlayer().heal(50);
				}
			}
		}
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite, x, y);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

}
