package com.game.world;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.entity.Bullet;
import com.game.entity.Enemy;
import com.game.entity.Grenade;
import com.game.entity.LargeZombie;
import com.game.entity.Player;
import com.game.entity.SlowSpell;
import com.game.states.GameState;
import com.game.states.GameStateManager;
import com.game.tiles.DirtTile;
import com.game.tiles.Tile;
import com.game.tiles.WaterTile;
import com.game.tiles.WoodTile;

public class World {
	Tile[][] map;
	public int width,height;
	String source;
	final int SCALE = 27;
	GameStateManager gsm;
	public World(String source,GameStateManager gsm){
		
		this.gsm = gsm;
		this.source = source;
		init();
	}
	private void init(){
		FileHandle mapFile = Gdx.files.internal(source);
		Scanner in = new Scanner(mapFile.read());
		this.width = in.nextInt();
		this.height = in.nextInt();
		map = new Tile[height][width];
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				int holder = in.nextInt();
				if (holder==0){
					map[i][j] = null;
				}else if (holder == 1){
					map[i][j] = new DirtTile("Textures/download.png",j*SCALE,i*SCALE);
				}else if (holder == 2){
					map[i][j] = new WaterTile("Textures/download.png",j*SCALE,i*SCALE);
				}else if (holder == 3){
					map[i][j] = new WoodTile("Textures/download.png",j*SCALE,i*SCALE);
				}
			}	
		}
		in.close();
	}
	public void render(SpriteBatch batch){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null)
					if  (!(map[i][j].getX() > ((GameState)gsm.getState()).getPlayer().getX() + ((GameState)gsm.getState()).getPlayer().getCamera().viewportWidth/2)&& !(map[i][j].getX()+SCALE < ((GameState)gsm.getState()).getPlayer().getX() - ((GameState)gsm.getState()).getPlayer().getCamera().viewportWidth/2))
							map[i][j].render(batch);
	
			}
		}
	}
	public boolean checkForPlayerColisionBottom(Player player){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (player.hitsBotom(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionBottom(Enemy enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsBotom(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionTop(Enemy enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsTop(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionRight(Enemy enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsRight(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionLeft(Enemy enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsLeft(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkBulletColision(Bullet bullet){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid){
					if (map[i][j].bounds.overlaps(bullet.getBounds())) return true;
				}
			}
		}
		return false;
	}
	public boolean checkGrenadeColision(Grenade grenade){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid){
					if (map[i][j].bounds.overlaps(grenade.getBounds())) return true;
				}
			}
		}
		return false;
	}
	public boolean checkForPlayerColisionLeft(Player player){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (player.hitsLeft(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionRight(Player player){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (player.hitsRight(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionTop(Player player){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (player.hitsTop(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public void dispose(){
		for (int i = 0; i < map.length;i++){
			for (int j = 0; j < map[i].length;++j){
				if (map[i][j] != null){
					map[i][j].dispose();
				}
			}
		}
	}
	public boolean checkBulletColision(SlowSpell slowSpell) {
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid){
					if (map[i][j].bounds.overlaps(slowSpell.getBounds())) return true;
				}
			}
		}
		return false;
	}
	public boolean checkForPlayerColisionBottom(LargeZombie enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsBotom(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionTop(LargeZombie enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsTop(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionRight(LargeZombie enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsRight(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
	public boolean checkForPlayerColisionLeft(LargeZombie enemy){
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if (map[i][j] != null && map[i][j].isSolid)
				if (enemy.hitsLeft(map[i][j].bounds)) return true;
			}
		}
		return false;
			
	}
}
