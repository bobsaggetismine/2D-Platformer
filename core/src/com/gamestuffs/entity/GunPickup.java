package com.gamestuffs.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gaestuffs.items.Gun;
import com.gamestuffs.states.GameState;
import com.gamestuffs.states.GameStateManager;
public class GunPickup extends Entity{
	private Texture texture;
	private Sprite sprite;
	private GameStateManager gsm;
	private Rectangle bounds;
	public GunPickup(int x,int y, GameStateManager gsm){
		texture = new Texture("Textures/download.png");
		sprite = new Sprite(texture,113,1,27,27);
		this.x = x;
		this.y = y;
		this.gsm =gsm;
		bounds = new Rectangle(x,y,27,27);
	}
	public int getId() {
		return 4;
	}
	public void render(SpriteBatch batch) {
		batch.draw(sprite, x, y);
	}
	public void dispose() {
		texture.dispose();
	}
	public void pickUp(){
		((GameState)(gsm.getState())).entities.remove(this);
	}
	public void update(){
		for (int i = 0;i< ((GameState)gsm.getState()).entities.size();i++){
			if (((GameState)gsm.getState()).entities.get(i).getId() == 1){
				if (((Player)((GameState)gsm.getState()).entities.get(i)).hitsLeft(bounds) || ((Player)((GameState)gsm.getState()).entities.get(i)).hitsRight(bounds)){
					dispose();
					((GameState)gsm.getState()).removeEntity(this);
					((Player)((GameState)gsm.getState()).entities.get(i)).pickup(new Gun(((Player)((GameState)gsm.getState()).entities.get(i)), gsm,20));
				}
			}
		}
	}
}
