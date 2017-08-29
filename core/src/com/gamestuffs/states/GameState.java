package com.gamestuffs.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamestuffs.entity.Enemy;
import com.gamestuffs.entity.Entity;
import com.gamestuffs.entity.GrenadeLauncherPickup;
import com.gamestuffs.entity.GunPickup;
import com.gamestuffs.entity.HealthPickup;
import com.gamestuffs.entity.Player;
import com.gamestuffs.userinterface.HealthBar;
import com.gamestuffs.world.World;

public class GameState extends State{
	
	public World map;
	public List<Entity> entities;
	private GameStateManager gsm;
	private HealthBar healthBar;
	private Player player;
	
	private Random rand;
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		entities = new ArrayList<Entity>();
		map = new World("World1.txt",gsm);
		rand = new Random(500 );
		player =new Player("Textures/download.png",750,500,gsm);
		entities.add(player);
		healthBar = new HealthBar(player);
		for (int i =0;i<10;++i){
			entities.add(new Enemy(rand.nextInt(500),400,this.gsm));
		}
		entities.add(new GunPickup(1500,505,this.gsm));
		entities.add(new HealthPickup(1900,513,this.gsm));
		entities.add(new GrenadeLauncherPickup(1600,513,this.gsm));
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
		if (Math.random() > 0.99){
			entities.add(new Enemy(rand.nextInt(500),400,this.gsm));
			((Enemy)entities.get(entities.size()-1)).init();
		}
		
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
	}
	public void init(){
		for (int i = 0; i < entities.size();i++){
			if (entities.get(i).getId() == 3){
				((Enemy)entities.get(i)).init();
			}
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
}
