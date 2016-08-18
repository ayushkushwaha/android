package com.example.freetechforyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.Space;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		getActionBar().hide();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent i = new Intent(Splash.this, Login.class);
				startActivity(i);

			}
		}, 4000);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
