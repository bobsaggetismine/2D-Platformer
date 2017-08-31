package com.game.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.entity.Enemy;
import com.game.entity.Entity;
import com.game.entity.Player;
import com.game.levels.Level;
import com.game.levels.Level1;
import com.game.userinterface.HealthBar;
import com.game.world.World;

public class GameState extends State{
	
	public World map;
	public List<Entity> entities;
	private GameStateManager gsm;
	private HealthBar healthBar;
	private Player player;
	private Level level;
	private Random rand;
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		entities = new ArrayList<Entity>();
		map = new World("World1.txt",gsm);
		rand = new Random(500 );
		player =new Player("Textures/download.png",750,500,gsm);
		entities.add(player);
		healthBar = new HealthBar(player);
		level = new Level1(this);
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		map.render(batch);
		for (int i = 0; i < entities.size();++i){
			entities.get(i).render(batch);
		}
		batch.end();
		healthBar.draw();
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
			else{
				entities.get(i).update();
			}
		}
		level.update();     
	}
	public void init(){
		level.init();
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
