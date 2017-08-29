package com.gamestuffs.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamestuffs.entity.Bullet;
import com.gamestuffs.entity.Player;
import com.gamestuffs.states.GameState;
import com.gamestuffs.states.GameStateManager;

public class Gun extends Item{
	public int damage;
	private Music pistolShot;
	private GameStateManager gsm;
	private Player owner;
	private Texture texture;
	private Sprite sprite;
	
	public Gun(Player player,GameStateManager gsm,int damage){
		useSpeed = 1f;
		this.owner = player;
		this.damage = damage;
		this.gsm =gsm;
		pistolShot = Gdx.audio.newMusic(Gdx.files.internal("Sounds/pistol.mp3"));
		texture = new Texture(Gdx.files.internal("Textures/download.png"));
		sprite = new Sprite(texture,141,1,27,27);
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
	@Override
	public Sprite getSrite() {
		return sprite;
	}
	

}
