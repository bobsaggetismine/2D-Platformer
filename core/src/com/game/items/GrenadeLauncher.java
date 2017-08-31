package com.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.entity.Grenade;
import com.game.entity.Player;
import com.game.states.GameState;
import com.game.states.GameStateManager;

public class GrenadeLauncher extends Item{
	private Music grenadeSound;
	private GameStateManager gsm;
	private Player owner;
	private Texture texture;
	private Sprite sprite;
	
	public GrenadeLauncher(Player player,GameStateManager gsm){
		useSpeed = 3f;
		this.owner = player;
		this.gsm =gsm;
		grenadeSound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/gl.mp3"));
		texture = new Texture("Textures/download.png");
		sprite = new Sprite(texture,29,29,27,27);
	}
	public void action(int dir) {
		grenadeSound.stop();
		grenadeSound.play();
		((GameState)gsm.getState()).addEntity(new Grenade(owner.getX(),owner.getY(),dir,this.gsm));
		grenadeSound.setVolume(0.001f);
		
		
	}
	public void dispose() {
		texture.dispose();
	}
	
	@Override
	public Sprite getSrite() {
		return sprite;
	}


}
