package com.pasang.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	private static final String PREFS_HIGH_SCORE = "highScore";

	public static Texture texture, logoTexture;
	public static Animation birdAnimation;
	public static TextureRegion bird, birdDown, birdUp, bg, grass, skullUp,
			skullDown, bar, playButtonUp, playButtonDown, appTitle,
			logo, ready, gameOver, highScore, scoreboard, star, noStar, retry;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, whiteFont, shadow;
	public static Preferences prefs;

	public static void load() {
		prefs = Gdx.app.getPreferences("FlyingPeter");
		if (!prefs.contains(PREFS_HIGH_SCORE)) {
			prefs.putInteger(PREFS_HIGH_SCORE, 0);
		}

		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 128);

		texture = new Texture(Gdx.files.internal("data/texture2.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		appTitle = new TextureRegion(texture, 0, 536, 395, 54);
		appTitle.flip(false, true);

		playButtonUp = new TextureRegion(texture, 0, 335, 59, 35);
		playButtonUp.flip(false, true);

		playButtonDown = new TextureRegion(texture, 59, 335, 59, 35);
		playButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 512, 130, 200, 160);
		ready.flip(false, true);

		gameOver = new TextureRegion(texture, 512, 418, 203, 55);
		gameOver.flip(false, true);

		highScore = new TextureRegion(texture, 117, 370, 96, 16);
		highScore.flip(false, true);
		
		retry = new TextureRegion(texture, 117, 388, 66, 16);
		retry.flip(false, true);

		scoreboard = new TextureRegion(texture, 512, 294, 240, 123);
		scoreboard.flip(false, true);

		noStar = new TextureRegion(texture, 397, 512, 45, 45);
		star = new TextureRegion(texture, 397, 559, 45, 45);

		star.flip(false, true);
		noStar.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 512, 512);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 512, 330, 23);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 512, 0, 64, 64);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 512, 0, 64, 64);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 512, 0, 64, 64);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		skullUp = new TextureRegion(texture, 512, 65, 64, 60);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 516, 126, 56, 4);
		bar.flip(false, true);

		coin = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/pow.wav"));
		dead = Gdx.audio.newSound(Gdx.files.internal("data/aah.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);
	}

	public static void setHighScore(int score) {
		prefs.putInteger(PREFS_HIGH_SCORE, score);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger(PREFS_HIGH_SCORE);
	}

	public static void dispose() {
		// Dispose of the textures
		logoTexture.dispose();
		texture.dispose();
		// Dispose sounds
		coin.dispose();
		flap.dispose();
		dead.dispose();
		fall.dispose();
		// Dispose fonts
		font.dispose();
		whiteFont.dispose();
		shadow.dispose();
	}
}
