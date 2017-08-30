package com.gamestuffs.levels;
import com.gamestuffs.states.GameState;
import com.gamestuffs.states.GameStateManager;
public abstract class Level {
	protected int enemies;
	protected GameState gs;
	public abstract void init();
	public abstract void update();
}
