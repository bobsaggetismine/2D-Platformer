package com.gamestuffs.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.engine.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Brodie's 2D platformer";
		config.useGL30 = false;
		config.resizable = false;
		config.backgroundFPS = 10;
		config.foregroundFPS = 120;
		config.width = 1280;
		config.height = 720;
		config.addIcon("Textures/icon.png",FileType.Internal);
		new LwjglApplication(new Game(), config);
	}
}
