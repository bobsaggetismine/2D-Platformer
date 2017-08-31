package com.game.levels;

import java.util.Random;

import com.game.entity.Enemy;
import com.game.entity.GrenadeLauncherPickup;
import com.game.entity.GunPickup;
import com.game.entity.HealthPickup;
import com.game.states.GameState;

public class Level2 extends Level{
	
	private Random rand;
	
	public Level2(GameState gs){
		enemies = 100;
		this.gs = gs;
		rand = new Random();
		init();
	}
	public void init() {
		for (int i =0; i < enemies; i++){
			gs.entities.add(new Enemy(rand.nextInt(500),400,gs.getGsm()));
		}
		gs.entities.add(new GunPickup(1500,505,gs.getGsm()));
		gs.entities.add(new HealthPickup(1900,513,gs.getGsm()));
		gs.entities.add(new GrenadeLauncherPickup(1600,513,gs.getGsm()));
	}
	public void update(){
		if (updating){
			
		}
	}
	@Override
	public void setUpdating(boolean x) {
		updating = x;
	}
}
