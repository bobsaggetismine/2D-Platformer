package com.gamestuffs.levels;

import java.util.Random;

import com.gamestuffs.entity.Enemy;
import com.gamestuffs.entity.GrenadeLauncherPickup;
import com.gamestuffs.entity.GunPickup;
import com.gamestuffs.entity.HealthPickup;
import com.gamestuffs.states.GameState;
import com.gamestuffs.states.GameStateManager;
public class Level1 extends Level{

	Random rand;
	
	public Level1(GameState gs){
		enemies = 25;
		this.gs = gs;
		rand=new Random();
	}
	@Override
	public void init() {
		for (int i =0; i < enemies; i++){
			gs.entities.add(new Enemy(rand.nextInt(500),400,gs.getGsm()));
		}
		gs.entities.add(new GunPickup(1500,505,gs.getGsm()));
		gs.entities.add(new HealthPickup(1900,513,gs.getGsm()));
		gs.entities.add(new GrenadeLauncherPickup(1600,513,gs.getGsm()));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
}
