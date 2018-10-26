package com.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.engine.Game;
import com.game.entity.Bullet;
import com.game.entity.Player;
import com.game.entity.SlowSpell;
import com.game.states.GameState;
import com.game.states.GameStateManager;

public class Wand extends Item{
	public int damage;
	private Music pistolShot;
	private GameStateManager gsm;
	private Player owner;
	private Texture texture;
	private Sprite sprite;
	
	public Wand(Player player,GameStateManager gsm,int damage){
		ammo = 75;
		useSpeed = 0.4f;
		this.owner = player;
		this.damage = damage;
		this.gsm =gsm;
		pistolShot = Gdx.audio.newMusic(Gdx.files.internal("Sounds/pistol.mp3"));
		texture = new Texture(Gdx.files.internal("Textures/download.png"));
		sprite = new Sprite(texture,113,57,27,27);
	}
	public void action(int dir) {
		if(ammo >= 1 && ((GameState)gsm.getState()).entities.size() < Game.MAX_ENTITIES)
		{
			pistolShot.stop();
			pistolShot.play();
			((GameState)gsm.getState()).addEntity(new SlowSpell(owner.getX(),owner.getY(),dir,this.gsm,this.damage));
			pistolShot.setVolume(0.001f);
			ammo--;
		}
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
