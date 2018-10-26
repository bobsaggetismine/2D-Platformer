package com.game.levels;
import com.game.states.GameState;
import com.game.states.GameStateManager;
public abstract class Level {
	protected int enemies;
	protected GameState gs;
	protected boolean updating;
	public abstract void init() throws InterruptedException;
	public abstract void update();
	public abstract void setUpdating(boolean x);
}
