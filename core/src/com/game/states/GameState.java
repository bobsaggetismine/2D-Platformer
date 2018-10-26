package com.game.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.entity.Enemy;
import com.game.entity.Entity;
import com.game.entity.LargeZombie;
import com.game.entity.Player;
import com.game.levels.Level;
import com.game.levels.Level1;
import com.game.userinterface.HealthBar;
import com.game.userinterface.Inventory;
import com.game.world.World;

public class GameState extends State{
	
	public World map;
	public List<Entity> entities;
	private GameStateManager gsm;
	private HealthBar healthBar;
	private Inventory inventory;
	private Player player;
	private Level level;
	private Random rand;

	private Sprite Background = new Sprite(new Texture(Gdx.files.internal("Textures/back.png")));
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		entities = new ArrayList<Entity>();
		map = new World("World1.txt",gsm);
		rand = new Random(500 );
		player =new Player("Textures/download.png",750,500,gsm);
		entities.add(player);
		healthBar = new HealthBar(player);
		inventory = new Inventory("Textures/download.png",player);
		level = new Level1(this);
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(Background,0,0,5000,720);
		map.render(batch);
		for (int i = 0; i < entities.size();++i){
			entities.get(i).render(batch);
		}
		healthBar.draw(batch);
		inventory.draw(batch);
		batch.end();
		
	}
	public void addEntity(Entity e){
		entities.add(e);
	}
	public void update(float delta) {	
		for (int i = 0; i < entities.size();++i){
			if (entities.get(i).getId() == 1){
				((Player)(entities.get(i))).update(map);
			}else if (entities.get(i).getId() == 3){
				((Enemy)(entities.get(i))).update(map);
			}
			else if (entities.get(i).getId() == 9){
				((LargeZombie)(entities.get(i))).update(map);
			}
			else{
				entities.get(i).update();
			}
		}
		level.update();
		healthBar.update();
		inventory.update();
	}
	public void init(){
		try {
			level.init();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clearEntities(){
		for (Entity e: entities){
			e.dispose();
		}
		entities.clear();
	}
	public void removeEntity(Entity e){
		e.dispose();
		entities.remove(e);
	}
	public Player getPlayer(){
		return player;
	}
	public GameStateManager getGsm(){
		return gsm;
	}
	public void setLevel(Level level){
		this.level = level;
		
	}
	public Level getLevel(){
		return level;
	}
}
