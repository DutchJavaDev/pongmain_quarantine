package com.pong.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pong.game.PongMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		config.allowSoftwareMode = false;
		config.fullscreen = false;
		config.pauseWhenBackground = false;
		config.pauseWhenMinimized = false;
		config.vSyncEnabled = true;
		config.foregroundFPS = 60;
		config.useGL30 = true;
		new LwjglApplication(new PongMain(), config);
	}
}
