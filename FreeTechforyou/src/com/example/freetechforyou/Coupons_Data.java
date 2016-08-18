package com.example.freetechforyou;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.DB_operations;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Coupons_Data extends BaseAdapter {
	ImageView logo;
	TextView category;
	TextView date;
	TextView name;
	TextView desc;
	TextView code;
	Context context;
	ImageButton share;

	ArrayList<String> Category;
	ArrayList<String> Date;
	ArrayList<String> Name;
	ArrayList<String> Desc;
	ArrayList<String> Code;
	ArrayList<Integer> Logo;

	LayoutInflater inflator = null;

	ArrayList<HashMap<String, String>> dataList;

	public Coupons_Data(Context context, ArrayList<String> Category,
			ArrayList<String> Date, ArrayList<String> Name,
			ArrayList<String> Desc, ArrayList<String> Code,
			ArrayList<Integer> Logo) {

		this.Category = Category;
		this.Date = Date;
		this.Name = Name;
		this.Desc = Desc;
		this.Code = Code;
		this.Logo = Logo;
		this.context = context;
		inflator = ((Activity) context).getLayoutInflater();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Name.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View rowview = inflator.inflate(R.layout.list_view, null);
		category = (TextView) rowview.findViewById(R.id.textView1);
		name = (TextView) rowview.findViewById(R.id.textView3);
		desc = (TextView) rowview.findViewById(R.id.textView4);
		date = (TextView) rowview.findViewById(R.id.textView2);
		logo = (ImageView) rowview.findViewById(R.id.imageView1);
		code = (TextView) rowview.findViewById(R.id.textView5);
		share = (ImageButton) rowview.findViewById(R.id.imageButton1);
		

		category.setText(Category.get(position));
		name.setText(Name.get(position));
		desc.setText(Desc.get(position));
		date.setText(Date.get(position));
		code.setText("CODE: "+Code.get(position));
		logo.setImageResource(Logo.get(position));
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				SharedPreferences pref = context.getSharedPreferences("USER_PREF", context.MODE_PRIVATE);
				Editor editor = pref.edit();
		        int uid = pref.getInt("UID",0);
		        DB_operations DB = new DB_operations();
		        Cursor cursor =  DB.Get_User_Profile(context, uid);
		        String email = "";
		        if(cursor.moveToFirst())
		        {
		        	email = cursor.getString(cursor.getColumnIndex("EMAIL"));
		        	
		        }
		        
				Intent intent = new Intent(Intent.ACTION_SEND);
				
				
				Intent email_intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto", email, null));
				email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"Coupon: "+Name.get(position));
				email_intent.putExtra(android.content.Intent.EXTRA_TEXT,
						"Coupon: "+Name.get(position)+"\n\n"+Desc.get(position)+"\n\n"+"CODE: "+Code.get(position));

				context.startActivity(Intent.createChooser(email_intent, "Send Coupon"));
				
			}
		});
		
		

		return rowview;
	}

	// ///////////////////////////////////////////////////////////////////////

}
