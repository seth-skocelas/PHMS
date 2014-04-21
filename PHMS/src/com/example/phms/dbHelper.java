package com.example.phms;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbHelper extends SQLiteOpenHelper
{
/*************************
Class variables
*************************/

	private static final int version = 1;	
	private static final String DATABASE_NAME="PHMSDB.db";	
	private static final String TAG = "PHMSDB";
//class variables

	private static final String TABLE_USER = "USER";	
	private static final String KEY_FNAME = "first_name";
	private static final String KEY_LNAME = "last_name";
	private static final String KEY_USERNAME = "user_name";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_DOCNAME = "doctor_name";
	private static final String KEY_AGE = "age";
	private static final String KEY_GENDER = "gender";
	private static final String KEY_HEIGHT_FT = "height_feet";
	private static final String KEY_HEIGHT_IN = "height_inches";
	static final String KEY_WEIGHT = "weight";
	static final String KEY_ANSWER = "security_answer";
	
	private static final String TABLE_FOOD = "FOOD";
	private static final String KEY_USER = "user_name";
	private static final String KEY_NAME = "food_name";
	private static final String KEY_QUANTITY = "quantity";
	private static final String KEY_CALORIES_PER = "calories_per";
	private static final String KEY_TIME = "time";
	public static final String[] ALL_FOOD_KEYS = new String[] {KEY_USER, KEY_NAME, KEY_QUANTITY, KEY_CALORIES_PER, KEY_TIME};
	
	private static final String TABLE_EXERCISE = "EXERCISE";
	//static final String KEY_USER = "user_name";
	static final String KEY_EX_NAME = "food_name";
	static final String KEY_CALORIES_BURNED = "calories_per";
	
	private static final String TABLE_MEDICINE = "MEDICINE";
	static final String KEY_MUSER = "user_name";
	static final String KEY_MNAME = "medicine_name";
	static final String KEY_TIMES_PER = "times_per";
	static final String KEY_DOSAGE = "dosage";
	static final String KEY_UNIT = "unit";
	static final String KEY_CONFLICTIONS = "conflictions";
	
	private static final String SQL_CREATE_USER_TABLE =
		    "CREATE TABLE " + TABLE_USER + " (" 
		    		+ KEY_FNAME + " TEXT NOT NULL," + KEY_LNAME + " TEXT NOT NULL," + KEY_USERNAME + " TEXT PRIMARY KEY," 
		    		+ KEY_PASSWORD + " TEXT NOT NULL," + KEY_EMAIL + " TEXT NOT NULL," + KEY_DOCNAME + " TEXT NOT NULL," 
		    		+ KEY_AGE + " INT NOT NULL," + KEY_GENDER + " TEXT NOT NULL," + KEY_HEIGHT_FT + " INT NOT NULL," 
		    		+ KEY_HEIGHT_IN + " INT NOT NULL," + KEY_WEIGHT + " INT NOT NULL," + KEY_ANSWER + " TEXT NOT NULL" + " )";

	private static final String SQL_CREATE_FOOD_TABLE = 
			"CREATE TABLE " + TABLE_FOOD + " ("
					+ KEY_USER + " TEXT," + KEY_NAME + " TEXT," + KEY_QUANTITY + " TEXT," + KEY_CALORIES_PER + " TEXT," 
					+ KEY_TIME + " TEXT" + " )";
	
	private static final String SQL_CREATE_EX_TABLE = 
			"CREATE TABLE " + TABLE_EXERCISE + " ("
					+ KEY_USER + " TEXT," + KEY_EX_NAME + " TEXT," + KEY_CALORIES_BURNED + " INT," 
					+ KEY_TIME + " TEXT" + " )";
	
	private static final String SQL_CREATE_MEDICINE_TABLE = 
			"CREATE TABLE " + TABLE_MEDICINE + " ("
					+ KEY_MUSER + " TEXT PRIMARY KEY," + KEY_MNAME + " TEXT," + KEY_TIMES_PER + " INT," + KEY_DOSAGE + " INT," 
					+ KEY_UNIT + " TEXT," + KEY_CONFLICTIONS + " TEXT" + " )";
	

//constructor
	public dbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, version);
	}
		@Override
    	public void onCreate(SQLiteDatabase db)
    	{
    		db.execSQL(SQL_CREATE_USER_TABLE);
    		db.execSQL(SQL_CREATE_FOOD_TABLE);
    		db.execSQL(SQL_CREATE_EX_TABLE);
    		db.execSQL(SQL_CREATE_MEDICINE_TABLE);
    	}
    	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    	{
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
    		onCreate(db);
    	}
    	
    	//getting all the food elements ready to be displayed in ListView
        public  ArrayList<HashMap<String, Object>> getAllFood() {
        	

    		ArrayList<HashMap<String, Object>> mylistData = new ArrayList<HashMap<String, Object>>();
    		//HashMap<String,Object> map = new HashMap<String, Object>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
         
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
         
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                	HashMap<String,Object> map = new HashMap<String, Object>();
                    Food f = new Food();
                    //f.setID(Integer.parseInt(cursor.getString(0)));
                    f.setName(cursor.getString(1));
                    f.setQuantity(cursor.getString(2));
                    f.setCaloriesPer(cursor.getString(3));
                    // Adding contact to list
                    map.put("food", f.getName());
    				map.put("quan", f.getQuantity());
    				map.put("cal", f.getCaloriesPer());
    				mylistData.add(map);
                } while (cursor.moveToNext());
            }
         
            // return contact list
            return mylistData;
        }
    
        
   //} We don't need all this commented out stuff apparently
   /* public dbHelper(Context ctx) 
    {
    	this.mCtx = ctx;
    	this.mDBHelper = new DatabaseHelper(this.mCtx);
    }
    public dbHelper open() throws SQLException 
    {
    	//this.mDBHelper = new DatabaseHelper(this.mCtx);
    	this.mDb = this.mDBHelper.getWritableDatabase();
    	return this;
    }
    public void close() 
    {
    	this.mDBHelper.close();
    }
    
    */
        
        
    
        
    public void addUser(Users user)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_FNAME, user.getFName());
    	cv.put(KEY_LNAME, user.getLName());
    	cv.put(KEY_USERNAME, user.getUserName());
    	cv.put(KEY_PASSWORD, user.getPassword());
    	cv.put(KEY_EMAIL, user.getEmail());
    	cv.put(KEY_DOCNAME, user.getDocName());
    	cv.put(KEY_AGE, user.getAge());
    	cv.put(KEY_GENDER, user.getGender());
    	cv.put(KEY_HEIGHT_FT, user.getHeightFeet());
    	cv.put(KEY_HEIGHT_IN, user.getHeightInches());
    	cv.put(KEY_WEIGHT, user.getWeight());
    	cv.put(KEY_ANSWER, user.getAnswer());
    	db.insert(TABLE_USER, null, cv);
    	db.close();
    }
       
    
    //adding a food object
    public void addFood(Food food)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues cv = new ContentValues();
    	
    	cv.put(KEY_NAME, food.getName());
		cv.put(KEY_QUANTITY, food.getQuantity());
		cv.put(KEY_CALORIES_PER, food.getCaloriesPer());
		
		// Inserting Row
        db.insert(TABLE_FOOD, null, cv);
        db.close(); // Closing database connection
    	//return this.mDb.insert(TABLE_FOOD, null, cv);
    }
    
    public void addMedicine (Medicine medicine) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues cv = new ContentValues();
    	
    	cv.put(KEY_MUSER, medicine.getUserName());
    	cv.put(KEY_MNAME, medicine.getName());
    	cv.put(KEY_TIMES_PER, medicine.getTimesPer());
    	cv.put(KEY_DOSAGE, medicine.getDosage());
    	cv.put(KEY_UNIT, medicine.getUnit());
    	cv.put(KEY_CONFLICTIONS, medicine.getConflictions());
    	
    	db.insert(TABLE_MEDICINE, null, cv);
    	
    }
    
    /*
    public long addExercise(Exercise ex)
    {
    	ContentValues cv = new ContentValues();
    	
    	cv.put(KEY_NAME, ex.getName());
		cv.put(KEY_NAME, ex.getCaloriesBurned());
    	
    	return this.mDb.insert(TABLE_EXERCISE, null, cv);
    }*/
/*******Methods for FOOD*******************/
		// Delete a row from the database, by rowId (primary key)

}