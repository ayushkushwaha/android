package com.example.freetechforyou;

import DAO.DB_operations;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Categories extends Activity {

	ImageButton food;
	ImageButton movies;
	ImageButton accessories;
	ImageButton shopping;
	ImageButton others;
	ImageButton travel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);

		food = (ImageButton) findViewById(R.id.food);
		movies = (ImageButton) findViewById(R.id.movies);
		accessories = (ImageButton) findViewById(R.id.accessories);
		shopping = (ImageButton) findViewById(R.id.shopping);
		others = (ImageButton) findViewById(R.id.others);
		travel = (ImageButton) findViewById(R.id.travel);

		food.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "food");
				startActivity(i);

			}
		});

		movies.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "movies");
				startActivity(i);


			}
		});

		accessories.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "accessories");
				startActivity(i);

			}
		});

		shopping.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "shopping");
				startActivity(i);

			}
		});

		travel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "travel");
				startActivity(i);

			}
		});

		others.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Categories.this, Category_List.class);
				i.putExtra("CATEGORY", "others");
				startActivity(i);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuinf = getMenuInflater();
		menuinf.inflate(R.menu.categories, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.logout) {

			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences("USER_PREF", MODE_PRIVATE);
			Editor editor = pref.edit();
			editor.clear();
			editor.commit();

			Intent i = new Intent(getApplicationContext(), Login.class);
			startActivity(i);

		} else if (id == R.id.exit) {

			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences("USER_PREF", MODE_PRIVATE);
			Editor editor = pref.edit();
			editor.clear();
			editor.commit();

			finish();

		}

		return super.onOptionsItemSelected(item);
	}

}
