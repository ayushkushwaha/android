package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

public class DB_operations {

	private Configs config;
	private SQLiteDatabase dataBase;
	ContentValues values = new ContentValues();
	boolean flag = true;

	// insert record
	public boolean insertQuery(int id, String name, String email, String pass,
			String gender, String country, Context context) {
		try {

			config = new Configs(context);
			dataBase = config.getWritableDatabase();

			// values.put(Configs.ID, id);
			values.put(Configs.NAME, name);
			values.put(Configs.EMAIL, email);
			values.put(Configs.PASSWORD, pass);
			values.put(Configs.GENDER, gender);
			values.put(Configs.COUNTRY, country);

			// insert
			dataBase.insert(Configs.TABLE_NAME, null, values);

		} catch (SQLiteException e) {

			flag = false;
		} catch (Exception e) {

		} finally {

			dataBase.close();
		}

		return flag;
	}

	// Update record
	public void updateQuery(int id, String name, String email, String pass,
			Context context) {
		try {
			config = new Configs(context);
			dataBase = config.getWritableDatabase();
			values.put(Configs.ID, id);
			values.put(Configs.NAME, id);
			values.put(Configs.EMAIL, id);
			values.put(Configs.PASSWORD, id);

			// update db table for a given ID
			dataBase.update(Configs.TABLE_NAME, values, Configs.ID + "=" + id,
					null);
		} catch (SQLiteException e) {
			Toast.makeText(context,
					"Error occurred while updating the table!!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context,
					"Error occurred while updating the table!!",
					Toast.LENGTH_SHORT).show();
		} finally {
			dataBase.close();
		}
	}

	// Delete record to SQLite db table
	public void deleteQuery(int id, Context context) {
		try {
			config = new Configs(context);
			dataBase = config.getWritableDatabase();

			// Delete

			dataBase.delete(Configs.TABLE_NAME, Configs.ID + "=" + id, null);

		} catch (SQLiteException e) {
			Toast.makeText(context,
					"Error occurred while deleting record for the table!!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context,
					"Error occurred while deleting record for the table!!",
					Toast.LENGTH_SHORT).show();
		} finally {
			dataBase.close();
		}
	}

	// Select record
	public int SelectQuery(Context context, String email, String password) {

		int count = 0;
		try {
			config = new Configs(context);
			dataBase = config.getWritableDatabase();
			Cursor mCursor = dataBase.rawQuery("SELECT COUNT(*) FROM "
					+ Configs.TABLE_NAME + " WHERE EMAIL = '" + email
					+ "' AND PASSWORD = '" + password + "'", null);

			mCursor.moveToFirst();
			count = mCursor.getInt(0);

			mCursor.close();

			// dataBase.execSQL("DELETE from orders");
		} catch (SQLiteException e) {
			Toast.makeText(context,
					"Error occurred while Selecting records for the table!!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context,
					"Error occurred while Selecting records for the table!!",
					Toast.LENGTH_SHORT).show();
		} finally {
			dataBase.close();
		}
		return count;
	}
	
	public int Get_User_ID(Context context, String email, String password) {

		int UID = 0;
		try {
			config = new Configs(context);
			dataBase = config.getWritableDatabase();
			Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
					+ Configs.TABLE_NAME + " WHERE EMAIL = '" + email
					+ "' AND PASSWORD = '" + password + "';", null);
			
			if(mCursor.moveToFirst())
			{
				UID = mCursor.getInt(0);
			}

			mCursor.close();
		} catch (SQLiteException e) {
			Toast.makeText(context,
					"Error occurred while Selecting records for the table!!"+e,
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(context,
					"Error occurred while Selecting records for the table!!"+e,
					Toast.LENGTH_SHORT).show();
		} finally {
			dataBase.close();
		}
		return UID;
	}
	
	public Cursor Get_User_Profile(Context context, int UID) {
		
		Cursor cursor = null;
		try {
		
			config = new Configs(context);
			dataBase = config.getWritableDatabase();
		
			String query = "SELECT * FROM users WHERE _id = "+UID+" ;";
			cursor = dataBase.rawQuery(query, null);
			
		} catch (Exception ex) {
			Toast.makeText(context, "Error in select " + ex.toString(),
					Toast.LENGTH_SHORT).show();
		} finally {
			//dataBase.close();
		}
		return cursor;
	}


}
