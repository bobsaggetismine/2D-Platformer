package com.game.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation {
	private int speed, index;
	private long lastTime,timer;
	private Sprite[] frames;
	
	public Animation(int speed, Sprite[] frames){
		this.speed=speed;
		this.frames=frames;
		index=0;
		timer=0;
		lastTime = System.currentTimeMillis();
		
	}
	public void tick(){
		timer += System.currentTimeMillis()-lastTime;
		lastTime=System.currentTimeMillis();
		if (timer>speed){
			index++;
			timer=0;
			if (index>=frames.length){
				index=0;
			}
		}
	}
	public Sprite getCurrentFrame(){
		return frames[index];
	}
	public Sprite[]getFrame(){
		return frames;
	}
}
