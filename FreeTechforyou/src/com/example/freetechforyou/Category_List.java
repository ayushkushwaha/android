package com.example.freetechforyou;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import External.ServiceHandler;
import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class Category_List extends Activity {

	private ProgressDialog pDialog;
	ListView list;

	// URL to get contacts JSON
	private static String url = "http://www.softlabinnovations.com/apps/coupon-json.php";

	// JSON Node names
	private static String TAGS = "travel";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_LOGO = "logo";
	private static final String TAG_DESC = "desc";
	private static final String TAG_DATE = "date";
	private static final String TAG_CODE = "code";

	// contacts JSONArray
	JSONArray JSON_DATA = null;

	ArrayList<String> Category;
	ArrayList<String> Date;
	ArrayList<String> Name;
	ArrayList<String> Desc;
	ArrayList<String> Code;
	ArrayList<Integer> Logo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category__list);

		Intent i = getIntent();
		TAGS = i.getStringExtra("CATEGORY");

		Category = new ArrayList<String>();
		Date = new ArrayList<String>();
		Name = new ArrayList<String>();
		Desc = new ArrayList<String>();
		Code = new ArrayList<String>();
		Logo = new ArrayList<Integer>();

		list = (ListView) findViewById(R.id.ListView);
		// ListView lv = getListView();

		// Listview on item click listener
		// lv.setOnItemClickListener(new OnItemClickListener() {

		new GetData().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Category_List.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			jsonStr = jsonStr.replace("﻿", "");
			jsonStr = jsonStr.replace(">", "");
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject("{" + jsonStr + "}");

					// Getting JSON Array node
					JSON_DATA = jsonObj.getJSONArray(TAGS);

					// looping through All Contacts
					for (int i = 0; i < JSON_DATA.length(); i++) {
						JSONObject c = JSON_DATA.getJSONObject(i);

						Category.add(c.getString(TAG_ID));
						Name.add(c.getString(TAG_NAME));

						if (c.getString(TAG_LOGO).toLowerCase()
								.contains("paytm")) {
							Logo.add(R.drawable.paytm);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("airbnb")) {
							Logo.add(R.drawable.airbnb);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("bookmyshow")) {
							Logo.add(R.drawable.bookmyshow);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("housing")) {
							Logo.add(R.drawable.housing);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("zomato")) {
							Logo.add(R.drawable.zomato);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("flipkart")) {
							Logo.add(R.drawable.flipkart);
						} else if (c.getString(TAG_LOGO).toLowerCase()
								.contains("redbus")) {
							Logo.add(R.drawable.redbus);
						} else {
							Logo.add(R.drawable.paytm);
						}

						Desc.add(c.getString(TAG_DESC));
						Date.add(c.getString(TAG_DATE));
						Code.add(c.getString(TAG_CODE));

					}
				} catch (JSONException e) {
					Log.i("JSON Error", e + "");
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();

			list.setAdapter(new Coupons_Data(Category_List.this, Category,
					Date, Name, Desc, Code, Logo));

		}

	}

}
