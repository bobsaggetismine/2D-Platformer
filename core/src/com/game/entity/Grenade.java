package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.states.GameState;
import com.game.states.GameStateManager;

public class Grenade extends Entity{
	private Rectangle bounds;
	private int xVel;
	private int yVel;
	private Texture texture;
	private Sprite sprite;
	private GameStateManager gsm;
	
	public Grenade(float x, float y,int dir,GameStateManager gsm){
		this.gsm = gsm;
		super.x = x;
		super.y = y;
		bounds = new Rectangle(x+9,y+12,4,3);
		texture = new Texture(Gdx.files.internal("Textures/download.png"));
		sprite = new Sprite(texture,86,29,26,26);
		xVel = Bullet.DEFAULT_BULLET_SPEED*dir;
	}
	public void update(){
		for (int i = 0;i< ((GameState)gsm.getState()).entities.size();i++){
			if (((GameState)gsm.getState()).entities.get(i).getId() == 1){
				if (x > ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().position.x + ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().viewportWidth/2 ||x < ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().position.x - ((Player)((GameState)gsm.getState()).entities.get(i)).getCamera().viewportWidth/2){
					((GameState)gsm.getState()).removeEntity(this);
				}
			}else if (((GameState)gsm.getState()).entities.get(i).getId() == 3){
				if (((Enemy)((GameState)gsm.getState()).entities.get(i)).hitsLeft(bounds) ||((Enemy)((GameState)gsm.getState()).entities.get(i)).hitsRight(bounds)){
					((Enemy)((GameState)gsm.getState()).entities.get(i)).damage(Integer.MAX_VALUE);
					((GameState)gsm.getState()).removeEntity(this);
					((GameState)gsm.getState()).addEntity(new Bullet(x,y,1,this.gsm,Bullet.DEFAULT_BULLET_DAMAGE));
					((GameState)gsm.getState()).addEntity(new Bullet(x,y,-1,this.gsm,Bullet.DEFAULT_BULLET_DAMAGE));
				}
			}else if (((GameState)gsm.getState()).map.checkGrenadeColision(this)){
				((GameState)gsm.getState()).removeEntity(this);
			}
		}
		x+=xVel;
		bounds.x +=xVel;
		y-=1;
		bounds.y -=1;
	}
	public Rectangle getBounds(){
		return bounds;
	}
	public int getId() {
		return 6;
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite,x,y);
		
	}
	@Override
	public void dispose() {
		texture.dispose();
		
	}
	
	
}
