package com.game.userinterface;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.entity.Player;
import com.game.items.Item;

public class Inventory extends UIObject{
	private Player player;
	private Sprite[] sprites;
	private static Sprite DEFAULT;
	public Inventory(String source,Player player)
	{
		Texture texture = new Texture(Gdx.files.internal(source));
		DEFAULT  = new Sprite(texture,169,29,27,27);
		this.player = player;
		sprites = new Sprite[8];
		int i  = 0;
		for(Item e : player.getInventory())
		{
			sprites[i] = e.getSrite();
			++i;
		}
		for(int j = 0; j < sprites.length; ++j)
		{
			if(sprites[j] == null)
			{
				sprites[j] = DEFAULT;
			}
		}
	}
	public void update()
	{
		int i =0;
		for(Item e : player.getInventory())
		{
			sprites[i] = e.getSrite();
			++i;
		}
		
	}
	public void draw(SpriteBatch batch)
	{
		int offset  =0;
		for(int i =0; i < sprites.length;++i)
		{
			batch.draw(sprites[i],player.getX()+offset,25);
			offset+=32;
		}
	}
}
