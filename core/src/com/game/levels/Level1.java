package com.game.levels;

import java.util.Random;

import com.game.entity.Enemy;
import com.game.entity.Entity;
import com.game.entity.GrenadeLauncherPickup;
import com.game.entity.GunPickup;
import com.game.entity.HealthPickup;
import com.game.states.GameState;
public class Level1 extends Level{

	private Random rand;
	private boolean over;
	public Level1(GameState gs){
		updating = true;
		enemies = 25;
		this.gs = gs;
		rand=new Random();
	}
	@Override
	public void init() {
		for (int i =0; i < enemies; i++){
			gs.entities.add(new Enemy(rand.nextInt(500),400,gs.getGsm()));
		}
		gs.entities.add(new GunPickup(1500,400,gs.getGsm()));
		gs.entities.add(new HealthPickup(1900,400,gs.getGsm()));
		gs.entities.add(new GrenadeLauncherPickup(1600,400,gs.getGsm()));
	}

	@Override
	public void update() {
		if (updating){
			over = true;
			for (Entity e: gs.entities){
				if (e.getId() == 3){
					over = false;
				}
			}
			if (over){
				gs.setLevel(new Level2(gs));
			}
		}
	}
	public void setUpdating(boolean x){
		updating = x;
	}

	
}
