package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.states.GameState;
import com.game.states.GameStateManager;

public class SlowSpell extends Entity{
	public static int DEFAULT_BULLET_SPEED = 5;
	public static int DEFAULT_BULLET_DAMAGE = 25;
	private Rectangle bounds;
	private int xVel;
	private Texture texture;
	private Sprite sprite;
	private GameStateManager gsm;
	private int damage;
	private boolean cankill = true;
	public SlowSpell(float x, float y,int dir,GameStateManager gsm,int damage){
		this.damage = damage;
		this.gsm = gsm;
		super.x = x;
		super.y = y;
		bounds = new Rectangle(x+9,y+12,4,3);
		texture = new Texture(Gdx.files.internal("Textures/download.png"));
		sprite = new Sprite(texture,141,57,27,27);
		xVel = Bullet.DEFAULT_BULLET_SPEED*dir;
	}
	public Rectangle getBounds(){
		return bounds;
	}
	public int getId() {
		return 7;
	}
	public void render(SpriteBatch batch) {
		batch.draw(sprite,x,y);
	}
	public void dispose(){
		texture.dispose();
	}
	public void update(){
		for (int i = 0;i< ((GameState)gsm.getState()).entities.size();i++){
			if (((GameState)gsm.getState()).entities.get(i).getId() == 1){
				if (x > ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().position.x + ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().viewportWidth/2 ||x < ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().position.x - ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().viewportWidth/2){
					((GameState)gsm.getState()).removeEntity(this);
				}
			}else if (((GameState)gsm.getState()).entities.get(i).getId() == 3){
				if (((Enemy)((GameState)gsm.getState()).entities.get(i)).hitsLeft(bounds) ||((Enemy)((GameState)gsm.getState()).entities.get(i)).hitsRight(bounds)){
					((Enemy)((GameState)gsm.getState()).entities.get(i)).slow();
					((Enemy)((GameState)gsm.getState()).entities.get(i)).damage(damage);
					((GameState)gsm.getState()).removeEntity(this);
				}
			}else if (((GameState)gsm.getState()).entities.get(i).getId() == 9){
				if (((LargeZombie)((GameState)gsm.getState()).entities.get(i)).hitsLeft(bounds) ||((LargeZombie)((GameState)gsm.getState()).entities.get(i)).hitsRight(bounds)){
					((LargeZombie)((GameState)gsm.getState()).entities.get(i)).slow();
					((LargeZombie)((GameState)gsm.getState()).entities.get(i)).damage(damage);
					((GameState)gsm.getState()).removeEntity(this);
				}
			} 
			else if (((GameState)gsm.getState()).map.checkBulletColision(this)){
				((GameState)gsm.getState()).removeEntity(this);
			}
		}
		x+=xVel;
		bounds.x +=xVel;
	}
}
