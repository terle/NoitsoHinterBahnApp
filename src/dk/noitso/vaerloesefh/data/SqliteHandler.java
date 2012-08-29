package dk.noitso.vaerloesefh.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHandler extends SQLiteOpenHelper {
	private final static String CREATE_USER_TABLE = "CREATE TABLE users (id INTEGER, name TEXT, PRIMARY KEY (id, name))";
	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "hinterbahn_db";
	private final static String TAG = SqliteHandler.class.getSimpleName();
	
	public SqliteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public boolean insertUser(String username) {
		try {
			SQLiteDatabase sqlite = this.getWritableDatabase();

			ContentValues initialValues = new ContentValues();
			initialValues.put("name", username);

			sqlite.insert("users", null, initialValues);
			sqlite.close();
			return true;
		} catch (SQLException sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
			return false;
		}
	}
	
	public boolean deleteUser(String username) {
		try {
			SQLiteDatabase sqlite = this.getWritableDatabase();

			sqlite.delete("users", "name='" + username + "'", null);
			sqlite.close();
			return true;
		} catch (SQLException sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
			return false;
		}
	}
	
	public void deleteAllUsers() {
		try {
			SQLiteDatabase sqlite = this.getWritableDatabase();

			sqlite.delete("users", "", null);
			sqlite.close();
		} catch (SQLException sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
		}
	}
	
	public boolean editUser(String newUsername, String oldUsername) {
		try {
			SQLiteDatabase sqlite = this.getWritableDatabase();

			ContentValues initialValues = new ContentValues();
			initialValues.put("name", newUsername);

			String where_clause = "name='" + oldUsername + "'";
			sqlite.update("users", initialValues, where_clause, null);
			sqlite.close();
			return true;
		} catch (SQLException sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
			return false;
		}
	}
	
	public List<String> getUsers() {
		List<String> userList = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String getAllUsers = "SELECT * FROM users";
		Cursor cursor = db.rawQuery(getAllUsers, null);
		
		cursor.moveToFirst();
		
		if(cursor.getCount() > 0) {
			while(!cursor.isAfterLast()) {
				userList.add(cursor.getString(cursor.getColumnIndex("name")));
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return userList;
	}
}
