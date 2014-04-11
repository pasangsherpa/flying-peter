package com.pasang;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AndroidApplication {

	/** The view to show the ad. */
	private AdView adView;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID_TOP = "ca-app-pub-5874746849730992/5452438061";
	private static final String AD_UNIT_ID_BOTTOM = "ca-app-pub-5874746849730992/4254906463";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		// Create the layout
		RelativeLayout layout = new RelativeLayout(this);

		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		// Create the libgdx View
		View gameView = initializeForView(new FPGame(), cfg);

		// Create and setup the AdMob view
		AdView adViewTop = new AdView(this);
		adViewTop.setAdSize(AdSize.BANNER);
		adViewTop.setAdUnitId(AD_UNIT_ID_TOP);

		AdView adViewBottom = new AdView(this);
		adViewBottom.setAdSize(AdSize.BANNER);
		adViewBottom.setAdUnitId(AD_UNIT_ID_BOTTOM);

		TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String uid = tManager.getDeviceId();

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice(uid)
				.build();

		// Start loading the ad in the background.
		adViewTop.loadAd(adRequest);
		adViewBottom.loadAd(adRequest);

		// Add the libgdx view
		layout.addView(gameView);

		// Add the AdMob view
		RelativeLayout.LayoutParams adParamsTop = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParamsTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		adParamsTop.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		RelativeLayout.LayoutParams adParamsBottom = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParamsBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParamsBottom.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		layout.addView(adViewTop, adParamsTop);
		layout.addView(adViewBottom, adParamsBottom);

		// Hook it all up
		setContentView(layout);

	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	/** Called before the activity is destroyed. */
	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}