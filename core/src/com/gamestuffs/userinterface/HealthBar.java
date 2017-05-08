package com.gamestuffs.userinterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gamestuffs.entity.Player;

public class HealthBar extends UIObject{
	private Player player;
	private ShapeRenderer renderer;
	public HealthBar(Player player){
		this.player = player;
		renderer = new ShapeRenderer();
	}
	public void draw() {
		//renderer.setProjectionMatrix(player.getCamera().combined);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.RED);
		renderer.rect(player.getCamera().viewportWidth/2 - 40,player.getCamera().viewportHeight-50, 100, 5);
		renderer.setColor(Color.WHITE);
		renderer.rect(player.getCamera().viewportWidth/2 - 40,player.getCamera().viewportHeight-50,player.getHealth(),5);
		renderer.end();
	}
}
