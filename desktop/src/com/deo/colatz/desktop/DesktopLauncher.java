package com.deo.colatz.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deo.colatz.Main;

import static com.deo.colatz.Main.HEIGHT;
import static com.deo.colatz.Main.WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.pauseWhenBackground = false;
		config.width = WIDTH;
		config.height = HEIGHT;
		config.fullscreen = true;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.vSyncEnabled = true;
		config.forceExit = true;
		config.title = "Collatz conjecture visualiser";
		new LwjglApplication(new Main(), config);
	}
}
