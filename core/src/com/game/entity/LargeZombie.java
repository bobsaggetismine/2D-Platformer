package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.states.GameState;
import com.game.states.GameStateManager;
import com.game.world.World;

public class LargeZombie extends Entity {
	
	private Texture texture;
	private Sprite sprite;
	private Rectangle bottom,top,left,right,full;
	private float xVel,yVel;
	private GameStateManager gsm;
	private boolean jumping=false;
	private Player player;
	private int health;
	private float speed;
	private float now=0,hitTimer=0.5f;
	public LargeZombie(int x, int y,GameStateManager gsm){
		this.gsm = gsm;
		super.x = x;
		super.y = y;
		super.kills = true;
		yVel = -3;
		xVel = 0;
		texture = new Texture("Textures/download.png");
		sprite = new Sprite(texture,1,57,27,27);
		left = new Rectangle(x+7,y+3,2,16);
		right = new Rectangle(x+16,y+3,2,16);
		bottom = new Rectangle(x+11,y,4,2);
		full = new Rectangle(x+7,y,9,16);
		top = new Rectangle(x+9,y+16,7,2);
		health = 400;
		speed = 0.9f;
		init();
	}
	@Override
	public void dispose(){
		texture.dispose();
	}
	public void init()
	{
		for (int i =0; i < ((GameState)(gsm.getState())).entities.size();++i){
			if (((GameState)(gsm.getState())).entities.get(i).getId() == 1)
			{
					player = (Player)((GameState)(gsm.getState())).entities.get(i);
			}
		}
	}
	private void checkBounds()
	{
		try{
			if (y > player.getCamera().position.y + player.getCamera().viewportHeight/2 ||y < player.getCamera().position.y - player.getCamera().viewportHeight/2)
			{
				kill();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void update(World map){
		checkBounds();
		now += Gdx.graphics.getDeltaTime();
		if (health <= 0){
			if(Math.random() > 0.965)
				((GameState)gsm.getState()).entities.add(new AmmoPickup((int)this.x,(int)this.y+3,((GameState)gsm.getState()).getGsm()));
			kill();
		}
		if (player.x > x){
			//sprite = new Sprite(texture,169,1,27,27);
			xVel = speed;
		}else if (player.x < x){
			xVel = -speed;
			//sprite = new Sprite(texture,197,1,27,27);
		}else{
			xVel = 0;
		}
		if (yVel>=-2) yVel--;
		if (map.checkForPlayerColisionBottom(this)){
			jumping = false;
			yVel = 0;
		}
		
		if (map.checkForPlayerColisionTop(this)){
			if (yVel > 0)
			yVel = 0;
		}
		
		if (map.checkForPlayerColisionRight(this)){
			if (xVel > 0)
			xVel = 0;
			if (!jumping){
				jump();
			}
		}
		if (map.checkForPlayerColisionLeft(this)){
			if (xVel < 0)
			xVel = 0;
			if (!jumping) jump();
		}
		
		if (player.hitsFull(full) && now > hitTimer)
		{
			player.damage(5);
			now  = 0;
		}
		move();
	}
	private void jump() {
		yVel+=8;
		jumping=true;
	}
	public boolean hitsBotom(Rectangle r){
		if (r.overlaps(bottom)) return true;
		return false;
	}
	public boolean hitsRight(Rectangle r){
		if (r.overlaps(right)) return true;
		return false;
	}
	public boolean hitsLeft(Rectangle r){
		if (r.overlaps(left)) return true;
		return false;
	}
	public boolean hitsTop(Rectangle r){
		if (r.overlaps(top)) return true;
		return false;
	}
	public void move()
	{
		left.y +=yVel;
		bottom.y += yVel;
		top.y+=yVel;
		right.y += yVel;
		left.x +=xVel;
		bottom.x += xVel;
		top.x+=xVel;
		right.x += xVel;
		full.x += xVel;
		full.y += yVel;
		y+=yVel;
		x+=xVel;
	}
	public void kill()
	{
		((GameState)gsm.getState()).removeEntity(this);
	}
	public int getId()
	{
		return 9;
	}
	public void render(SpriteBatch batch) {
		batch.draw(sprite,x,y);
	}
	public void damage(int damage) {
		if (damage > health)
		{
			if(Math.random() > 0.5)
				((GameState)gsm.getState()).entities.add(new AmmoPickup((int)this.x,(int)this.y+3,((GameState)gsm.getState()).getGsm()));
			kill();
		}
		this.health-=damage;
	}
	public void slow()
	{
		if(this.speed <= .4f) return;
		this.speed -= 0.3f;
	}

}
