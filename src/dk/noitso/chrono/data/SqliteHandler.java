package dk.noitso.chrono.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHandler extends SQLiteOpenHelper {
	private final static String CREATE_USER_TABLE = "CREATE TABLE users (id INTEGER, name TEXT, total_time_ms INTEGER DEFAULT 0, PRIMARY KEY (id), UNIQUE(name))";
	private final static String CREATE_TIME_TABLE = "CREATE TABLE time_table (fk_user_id INTEGER, time_in_ms INTEGER DEFAULT 0" +
			", obstruction_number TEXT, FOREIGN KEY(fk_user_id) REFERENCES users(id), PRIMARY KEY (fk_user_id, obstruction_number))";
	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "hinterbahn_db";
	private final static String TAG = SqliteHandler.class.getSimpleName();
	
	public SqliteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_TIME_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public boolean insertUser(String username) {

		SQLiteDatabase sqlite = this.getWritableDatabase();
		try {
			Log.i(SqliteHandler.class.getSimpleName(), "Starting to insert user...");

			ContentValues initialValues = new ContentValues();
			initialValues.put("name", username);
	
			sqlite.insertOrThrow("users", null, initialValues);
			sqlite.close();
			Log.d(TAG, "UserId is: " + this.getUserId(username));
			addUserToTimeTable(username);
			
			return true;
		} catch (SQLiteConstraintException sqlerror) {
			Log.w(SqliteHandler.class.getSimpleName(), "Couldn't insert user... returning false!");
			return false;
		} catch (Exception e) {
			// Catching everything else... just in case.
			Log.e(TAG, e.getMessage());
			return false;
		}
	} 
	
	private boolean addUserToTimeTable(String username) {
		int userId = this.getUserId(username);

		SQLiteDatabase db = this.getWritableDatabase();
		try {
			Log.i(SqliteHandler.class.getSimpleName(), "Starting to insert user in timetable...");
			
			db.beginTransaction();
			for(int i = 1; i < 21; i++) {
				ContentValues values = new ContentValues();
				values.put("fk_user_id", userId);
				values.put("obstruction_number", i);
				db.insertOrThrow("time_table", null, values);
				Log.i(TAG, "Inserted #" + i);
			}
			db.setTransactionSuccessful();			
			return true;
		} catch (SQLiteConstraintException sqlerror) {
			Log.w(SqliteHandler.class.getSimpleName(), "Couldn't insert userdata into timetable... returning false!");
			Log.e(SqliteHandler.class.getSimpleName(), sqlerror.getMessage());
			db.close();
			return false;
		} catch (Exception e) {
			// Catching everything else... just in case.
			e.printStackTrace();
			db.close();
			return false;
		} finally {
			db.endTransaction();
			db.close();
		}
	}
	
	public boolean deleteUser(String username) {
		try {
			int userId = this.getUserId(username);
			SQLiteDatabase sqlite = this.getWritableDatabase();

			sqlite.delete("users", "name='" + username + "'", null);
			sqlite.delete("time_table", "fk_user_id=" + userId, null);
			sqlite.close();
			return true;
		} catch (Exception sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
			return false;
		}
	}
	
	public void deleteAllUsers() {
		try {
			SQLiteDatabase sqlite = this.getWritableDatabase();

			sqlite.delete("users", "", null);
			sqlite.delete("time_table", null, null);
			sqlite.close();
		} catch (Exception sqlerror) {
			Log.e(TAG, sqlerror.getMessage());
		}
	}
	
	public boolean editUser(String newUsername, String oldUsername) {
		try {
			Log.i(SqliteHandler.class.getSimpleName(), "Starting to edit user...");

			SQLiteDatabase sqlite = this.getWritableDatabase();

			ContentValues initialValues = new ContentValues();
			initialValues.put("name", newUsername);

			String where_clause = "name='" + oldUsername + "'";
			int success = sqlite.update("users", initialValues, where_clause, null);
			if(success == 0) {
				throw new SQLiteConstraintException();
			}
			sqlite.close();
			return true;
		} catch (SQLiteConstraintException sqlerror) {
			Log.w(SqliteHandler.class.getSimpleName(), "Couldn't edit user... return false!");
			return false;
		} catch (Exception e) {
			// Catching everything else... just in case.
			Log.e(TAG, e.getMessage());
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

	public boolean addTimeForObstruction(String username, int timeInMs, int obstructionNumber) {
		try {
			Log.i(SqliteHandler.class.getSimpleName(), "Starting to insert time for obstruction and user...");

			int userId = this.getUserId(username);
			SQLiteDatabase sqlite = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("time_in_ms", timeInMs);
			int success = sqlite.update("time_table", values, "fk_user_id=" + userId + " AND obstruction_number=" + obstructionNumber, null);
			Log.i(TAG, "DataBase was written to");
			if(success == 0) {
				throw new SQLiteConstraintException();
			}
			sqlite.close();
			return true;
		} catch (SQLiteConstraintException sqlerror) { // If we get here, the row is already inserted for the given user. So let's update instead.
			Log.w(SqliteHandler.class.getSimpleName(), "Couldn't update the timetable... returning false!");
			Log.e(TAG, sqlerror.getMessage());
			return false;
		} catch (Exception e) {
			// Catching everything else... just in case.
			Log.e(TAG, e.getMessage());
			return false;
		}
	}
	
	public boolean addTimeToUser(String username, int totalTimeMs) {
		try {
			Log.i(SqliteHandler.class.getSimpleName(), "Starting to insert total time for and user...");

			SQLiteDatabase sqlite = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("total_time_ms", totalTimeMs);
			
			int success = sqlite.update("users", values, "name='" + username + "'", null);
			sqlite.close();
			if(success == 0) {
				throw new SQLiteConstraintException();
			}
			return true;
		} catch (SQLiteConstraintException sqlerror) {
			Log.w(SqliteHandler.class.getSimpleName(), "Couldn't insert in the timetable... returning false!");
			return false;
		} catch (Exception e) {
			// Catching everything else... just in case.
			Log.e(TAG, e.getMessage());
			return false;
		}
	}
	
	private int getUserId(String username) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		String getUserId = "SELECT id FROM users WHERE name='" + username + "'";
		Cursor cursor = db.rawQuery(getUserId, null);
		
		cursor.moveToFirst();
		
		int id = 0;
		if(cursor.getCount() > 0) { // We should always get a user returned at this moment, but just in case.
			id = cursor.getInt(cursor.getColumnIndex("id"));
		} else {
		}
		cursor.close();
		db.close();
		return id;
	}
	
	public List<User> getUsersAndTimes(boolean isForSharingData) {
		List<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String whereTimeIsNotZero = "";
		if(!isForSharingData) {
			whereTimeIsNotZero = " WHERE total_time_ms > 0";
		}
		
		String getAllUsers = "SELECT * FROM users" + whereTimeIsNotZero + " ORDER BY total_time_ms ASC";
		Cursor cursor = db.rawQuery(getAllUsers, null);
		
		cursor.moveToFirst();
		
		User user = null;
		if(cursor.getCount() > 0) {
			while(!cursor.isAfterLast()) {
				user = new User();
				user.setName(cursor.getString(cursor.getColumnIndex("name")));
				user.setTotalTimeInMs(cursor.getInt(cursor.getColumnIndex("total_time_ms")));
				userList.add(user);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return userList;
	}

	public List<User> getUsersAndObstructionTimes(int obstructionNumber, boolean isForSharingData) {
		List<User> userList = new ArrayList<User>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String whereTimeIsNotZero = "";
		if(!isForSharingData) {
			whereTimeIsNotZero = " AND time_in_ms > 0";
		}
		String getTimesAndUsers = "SELECT time_table.*, users.name FROM time_table, users WHERE obstruction_number = " + 
				obstructionNumber + whereTimeIsNotZero + " AND fk_user_id = users.id ORDER BY time_in_ms ASC";
		Cursor cursor = db.rawQuery(getTimesAndUsers, null);
		
		cursor.moveToFirst();
		
		User user = null;
		if(cursor.getCount() > 0) {
			while(!cursor.isAfterLast()) {
				user = new User();
				user.setName(cursor.getString(cursor.getColumnIndex("name")));
				user.setTotalTimeInMs(cursor.getInt(cursor.getColumnIndex("time_in_ms")));
				userList.add(user);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return userList;
	}
}
