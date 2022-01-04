package com.krupoderov.montyhall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.krupoderov.montyhall.Monty;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Monty Hall";
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		new LwjglApplication(new Monty(), config);
	}
}
