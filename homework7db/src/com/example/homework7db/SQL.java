package com.example.homework7db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQL extends SQLiteOpenHelper{

	final static int DB_VERSION = 1;
    final static String DB_NAME = "mydb.db";
    Context context;
	
	//public SQL(Context context, String name, CursorFactory factory, int version) {
	//	super(context, name, factory, version);
		 // Store the context for later use
   //     this.context = context;
        //check if db exists, if not it calls onCreate If it does exists then it checks 
        //the version. If the version is different then it calls the onUpgrade.
//	}
    public SQL (Context context)
    {
    	super(context, DB_NAME,null,DB_VERSION);
    	this.context = context;
    	Log.i("com.example.homework7db.SQL", "-----in SQL CONSTRUCTOR-----");
    }
    
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("com.example.homework7db.SQL", "-----in onCreate()-----");
		
		db.execSQL("CREATE TABLE  IF NOT EXISTS employees ("
				+ " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT NOT NULL, "
				+ "hiredate TEXT NOT NULL, "
			    + "ffood TEXT NOT NULL, "
				+ "fgame TEXT NOT NULL,"
			    + "sfgame TEXT NOT NULL,"
				+ "fcolor TEXT NOT NULL,"
			    + "gender TEXT NOT NULL)"
			      );
		//better to create a txt file with all the scripts if they become complex
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//erase or upgrade database
		
	}

}
