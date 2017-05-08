package com.gaestuffs.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gamestuffs.entity.Bullet;
import com.gamestuffs.entity.Player;
import com.gamestuffs.states.GameState;
import com.gamestuffs.states.GameStateManager;

public class Gun extends Item{
	public int damage;
	private Music pistolShot;
	private GameStateManager gsm;
	private Player owner;
	public Gun(Player player,GameStateManager gsm,int damage){
		this.owner = player;
		this.damage = damage;
		this.gsm =gsm;
		pistolShot = Gdx.audio.newMusic(Gdx.files.internal("Sounds/pistol.mp3"));
	}
	public void action(int dir) {
		pistolShot.stop();
		pistolShot.play();
		((GameState)gsm.getState()).addEntity(new Bullet(owner.getX(),owner.getY(),dir,this.gsm,this.damage));
		pistolShot.setVolume(0.001f);
	}
	@Override
	public void dispose() {
		pistolShot.dispose();
	}
	

}
