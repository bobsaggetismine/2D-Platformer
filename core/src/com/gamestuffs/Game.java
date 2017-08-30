package com.gamestuffs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.gamestuffs.entity.Player;
import com.gamestuffs.states.GameStateManager;
import com.gamestuffs.tiles.DirtTile;
import com.gamestuffs.tiles.Tile;
import com.gamestuffs.tiles.WaterTile;
import com.gamestuffs.world.World;

public class Game extends ApplicationAdapter {
	public static final boolean DEVELOPER_MODE=true;
	SpriteBatch batch;
	GameStateManager gsm;
	
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
	}
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		Gdx.graphics.setTitle("FPS: "+Gdx.graphics.getFramesPerSecond()+" Rendered with: "+Gdx.gl.glGetString(GL30.GL_RENDERER));
	}
	public void dispose () {
		batch.dispose();
	}
}
