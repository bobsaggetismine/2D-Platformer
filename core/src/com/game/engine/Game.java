package com.game.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.states.GameStateManager;

public class Game extends ApplicationAdapter
{
	public static final boolean DEVELOPER_MODE=false;
	public static final int MAX_ENTITIES = 70;
	SpriteBatch batch;
	GameStateManager gsm;
	public static boolean _PAUSED = false;
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
	}
	public void render () {
		if (Gdx.input.isKeyJustPressed(Keys.P))
		{
			Game._PAUSED = !Game._PAUSED;
		}
		if (!_PAUSED) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
			gsm.update(Gdx.graphics.getDeltaTime());
			gsm.render(batch);
			Gdx.graphics.setTitle("FPS: "+Gdx.graphics.getFramesPerSecond()+" Rendered with: "+Gdx.gl.glGetString(GL30.GL_RENDERER));
		}
	}
	public void dispose () {
		batch.dispose();
	}
}
