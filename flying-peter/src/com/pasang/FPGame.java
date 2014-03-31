package com.pasang;

import com.badlogic.gdx.Game;
import com.pasang.Helpers.AssetLoader;
import com.pasang.Screens.SplashScreen;

public class FPGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
