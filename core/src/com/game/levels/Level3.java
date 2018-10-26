package com.game.levels;

import java.util.Random;

import com.game.engine.Game;
import com.game.entity.Enemy;
import com.game.entity.Entity;
import com.game.entity.HealthPickup;
import com.game.entity.LargeZombie;
import com.game.entity.MagicPickup;
import com.game.entity.MgPickup;
import com.game.states.GameState;

public class Level3 extends Level{
	
	private Random rand;
	private boolean over = false;
	public Level3(GameState gs){
		enemies = 200;
		this.gs = gs;
		rand = new Random();
		init();
	}
	public void init() {
		for (int i =0; i < Game.MAX_ENTITIES/4; i++){
			gs.entities.add(new Enemy(rand.nextInt(500),400,gs.getGsm()));
			gs.entities.add(new LargeZombie(rand.nextInt(500),400,gs.getGsm()));
			enemies--;
		}
		gs.entities.add(new HealthPickup(1900,350,gs.getGsm()));
		gs.entities.add(new MagicPickup((int)gs.getPlayer().getX()+50,350,gs.getGsm()));
	}
	public void update(){
		if(Math.random()*7 > (double)enemies/100.0 && enemies > 0 && gs.entities.size() <= Game.MAX_ENTITIES/2) {
			gs.entities.add(new Enemy(rand.nextInt(4000),400,gs.getGsm()));
			enemies--;
		}
	}
	@Override
	public void setUpdating(boolean x) {
		updating = x;
	}
}
