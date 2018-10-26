package com.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.engine.Game;
import com.game.items.Item;
import com.game.levels.Level1;
import com.game.states.GameState;
import com.game.states.GameStateManager;
import com.game.states.MenuState;
import com.game.world.World;

public class Player extends Entity{
	public static final int DEFAULT_HEALTH=100;
	
	private OrthographicCamera camera;
	private Rectangle bottom,top,left,right,full;
	private Texture texture;
	private Sprite sprite;
	private int jumpHeight = 9;
	private float vY,vX;
	private int ID=1;
	boolean jumping = false;
	private int health;
	public GameStateManager gsm;
	private float ITEM_DELAY = 1f;
	private List<Item> inventory;
	private int itemSelected = 0;
	float now = 0;
	private float speed = 2f;
	public Player(String source,int x, int y,GameStateManager gsm){
		this.gsm = gsm;
		this.x = x;
		this.y = y;
		this.health = DEFAULT_HEALTH;
		camera = new OrthographicCamera(1280,720);
		camera.position.y = 360;
		texture = new Texture(Gdx.files.internal(source));
		sprite = new Sprite(texture,57,1,27,27);
		left = new Rectangle(x+7,y+3,2,16);
		right = new Rectangle(x+16,y+3,2,16);
		bottom = new Rectangle(x+9,y,7,2);
		full = new Rectangle(x+7,y,9,16);
		top = new Rectangle(x+9,y+16,7,2);
		inventory = new ArrayList<Item>();
	}
	public List<Item> getInventory()
	{
		return inventory;
	}
	public void damage(int dmg){
		this.health-=dmg;
	}
	public void heal(int up){
		if (health+up >100) health = 100;
		else health+=up;
	}
	public void checkDeathOccured(World map){
		if (health < 1){
			for (Item i: inventory){
				i.dispose();
			}
			map.dispose();
			((GameState)gsm.getState()).clearEntities();
			((GameState)gsm.getState()).getLevel().setUpdating(false);
			gsm.setState(new MenuState(gsm));
		}
	}
	public void pickup(Item item){
		sprite = new Sprite(texture,141,1,27,27);
		inventory.add(item);
		now = 0;
	}
	private void HandleInput(World map){
		
		if (Gdx.input.isTouched() && Game.DEVELOPER_MODE){
			left.y   +=Gdx.input.getY() - y;
			bottom.y +=Gdx.input.getY() - y;
			top.y    +=Gdx.input.getY() - y;
			right.y  +=Gdx.input.getY() - y;
			
			left.x   +=Gdx.input.getX() - x;
			bottom.x +=Gdx.input.getX() - x;
			top.x    +=Gdx.input.getX() - x;
			right.x  +=Gdx.input.getX() - x;
			x= Gdx.input.getX();
			y= Gdx.input.getY();
		}
		
		if (map.checkForPlayerColisionLeft(this)){
			if (vX <0)
				vX = 0;
		}else {
			if (Gdx.input.isKeyPressed(Keys.A))
				vX = -speed;
		}
		
		if (map.checkForPlayerColisionRight(this)){
			if (vX >0)
				vX = 0;
		}else {
			if (Gdx.input.isKeyPressed(Keys.D))
				vX = speed;
		}
		
		if (!map.checkForPlayerColisionTop(this)){
			if (Gdx.input.isKeyPressed(Keys.SPACE )&& !jumping){
				vY=jumpHeight;
				speed++;
				jumping = true;
			}
		}else {
			if (vY > 0){
				vY=0;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)){
			if ( itemSelected >= inventory.size()-1 )
			{
				itemSelected = 0;
			}
			else
			{
				itemSelected++;
			}
			try{
				sprite = inventory.get(itemSelected).getSrite();
			}catch(Exception e){
				sprite = new Sprite(texture,57,1,27,27);
			}
		}
		if (!map.checkForPlayerColisionBottom(this)){
			if (vY>=-2) vY--;
			
		}else {
			if (speed > 2) speed--;
			jumping = false;
			if (vY < 0)
			vY = 0;
		}
		try{
			ITEM_DELAY = inventory.get(itemSelected).useSpeed;
		}catch(Exception e){
			now = 0;
			return;
		}
		if (now < ITEM_DELAY)
		now += Gdx.graphics.getDeltaTime();
		
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			if (inventory.size() > 0)
				if(now > ITEM_DELAY){
					try{
						inventory.get(itemSelected).action(1);
					}catch(Exception e){}
					now = 0;
				}
		}
		else if (Gdx.input.isKeyPressed(Keys.LEFT)){
			if (inventory.size() > 0)
				if (now > ITEM_DELAY){
					try{
						inventory.get(itemSelected).action(-1);
					}catch(Exception e){}
					now = 0;
				}
		}
		
		
		if (now < ITEM_DELAY)
		now+=Gdx.graphics.getDeltaTime();
	}
	private void moveBounds() {
		left.y +=vY;
		bottom.y += vY;
		top.y+=vY;
		right.y += vY;
		left.x +=vX;
		bottom.x += vX;
		top.x+=vX;
		right.x += vX;
		full.x += vX;
		full.y += vY;
		y+=vY;
		x+=vX;
	}
	public void update(World map){
		HandleInput(map);
		moveBounds();
		checkBounds();
		checkDeathOccured(map);
		vX = 0;
		camera.position.x = x;
		camera.update();
	}
	public void render(SpriteBatch batch){
		batch.setProjectionMatrix(camera.combined);
		batch.draw(sprite,x,y);
	}
	
	//engine functions 
	private void checkBounds() {
		if (y > camera.position.y + camera.viewportHeight/2 ||y < camera.position.y - camera.viewportHeight/2){
			health = 0;
		}
	}
	public void dispose(){
		texture.dispose();
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
	public boolean hitsFull(Rectangle r){
		if (r.overlaps(full)) return true;
		return false;
	}
	//getters and setters
	public int getId() {
		return ID;
	}
	public int getHealth(){
		return health;
	}
	public float getXVel(){
		return vX;
	}
	public Rectangle getBottom(){
		return bottom;
	}
	public OrthographicCamera getCamera(){
		return camera;
	}
	public void giveAmmo(int i) {
		if(inventory.get(itemSelected).ammo < 2000000000)
		inventory.get(itemSelected).ammo += i;
	}
}
