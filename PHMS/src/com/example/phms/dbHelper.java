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
	static final String KEY_EX_NAME = "ex_name";
	static final String KEY_CALORIES_BURNED = "calories_burned";
	
	private static final String TABLE_MEDICINE = "MEDICINE";
	static final String KEY_MUSER = "user_name";
	static final String KEY_MNAME = "medicine_name";
	static final String KEY_TIMES_PER = "times_per";
	static final String KEY_DOSAGE = "dosage";
	static final String KEY_UNIT = "unit";
	static final String KEY_CONFLICTIONS = "conflictions";
	static final String KEY_ID = "id";
	
	
	private static final String TABLE_FCONSTANTS = "FCONSTANTS";
	static final String KEY_SUGGESTED = "suggested";
	static final String KEY_TODAY = "today";
	static final String KEY_INTAKE = "intake";
	static final String KEY_BURNED = "burned";

	
	
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
					+ KEY_USER + " TEXT," + KEY_EX_NAME + " TEXT," + KEY_CALORIES_BURNED + " TEXT," 
					+ KEY_TIME + " TEXT" + " )";
	
	private static final String SQL_CREATE_MEDICINE_TABLE = 
			"CREATE TABLE " + TABLE_MEDICINE + " ("
					+ KEY_MUSER + " TEXT," + KEY_MNAME + " TEXT," + KEY_TIMES_PER + " INT," + KEY_DOSAGE + " INT," 
					+ KEY_UNIT + " TEXT," + KEY_CONFLICTIONS + " TEXT," + KEY_ID + " TEXT" + " )";
	
	private static final String SQL_CREATE_FCONSTANTS_TABLE = 
			"CREATE TABLE " + TABLE_FCONSTANTS + " ("
					+ KEY_USER + " TEXT," + KEY_SUGGESTED + " TEXT," +  KEY_TODAY + " TEXT," + KEY_INTAKE + " TEXT," + KEY_BURNED + " TEXT" + ")";
	

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
    		db.execSQL(SQL_CREATE_FCONSTANTS_TABLE);
    	}
    	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    	{
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FCONSTANTS);
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
                    f.setTimeStamp(cursor.getString(4));
                    // Adding contact to list
                    map.put("food", f.getName());
    				map.put("quan", f.getQuantity());
    				map.put("cal", f.getCaloriesPer());
    				map.put("id", f.getTimeStamp());
    				mylistData.add(map);
                } while (cursor.moveToNext());
            }
         
            // return contact list
            return mylistData;
        }
        
        public  ArrayList<HashMap<String, Object>> getAllEx() {
        	

    		ArrayList<HashMap<String, Object>> mylistData = new ArrayList<HashMap<String, Object>>();
    		//HashMap<String,Object> map = new HashMap<String, Object>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE;
         
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
         
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                	HashMap<String,Object> map = new HashMap<String, Object>();
                    Exercise ex = new Exercise();
                    //f.setID(Integer.parseInt(cursor.getString(0)));
                    ex.setName(cursor.getString(1));
                    ex.setCaloriesBurned(cursor.getString(2));
                    ex.setTimeStamp(cursor.getString(3));
                    // Adding contact to list
                    map.put("exercise", ex.getName());
    				map.put("burned", ex.getCaloriesBurned());
    				map.put("id", ex.getTimeStamp());
    				mylistData.add(map);
                } while (cursor.moveToNext());
            }
         
            // return contact list
            return mylistData;
        }
        public  ArrayList<String> getFConstants(String user) {
        	
    		ArrayList<String> mylistData = new ArrayList<String>();
            String selectQuery = "SELECT  * FROM " + TABLE_FCONSTANTS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                	if (cursor.getString(0).equals(user)){
                		mylistData.add(cursor.getString(1));
                		mylistData.add(cursor.getString(2));
                		mylistData.add(cursor.getString(3));
                		mylistData.add(cursor.getString(4));
                	}
                } while (cursor.moveToNext());
            }
            return mylistData;
        }
        
        //getting elements from the user for the diet activity
        public  Users getUserFoodDetails(String u) {
        	

    		Users user = new Users(u);
            String selectQuery = "SELECT  * FROM " + TABLE_USER;
         
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
         
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                	if (cursor.getString(0).equals(u))
                	{
                		user.setAge(Integer.parseInt(cursor.getString(6)));
                		user.setGender(cursor.getString(7));
                		user.setHeightFeet(Integer.parseInt(cursor.getString(8)));
                		user.setHeightInches(Integer.parseInt(cursor.getString(9)));
                		user.setWeight(Integer.parseInt(cursor.getString(10)));
                		
                	}
                } while (cursor.moveToNext());
            }
         
            // return contact list
            return user;
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
		cv.put(KEY_TIME, food.getTimeStamp());
		
		// Inserting Row
        db.insert(TABLE_FOOD, null, cv);
        db.close(); // Closing database connection
    }
    
    public void setFoodConstants(String user, String suggested, String today, String intake, String burned)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues cv = new ContentValues();
    	
    	cv.put(KEY_USER, user);
		cv.put(KEY_SUGGESTED, suggested);
		cv.put(KEY_TODAY, today);
		cv.put(KEY_INTAKE, intake);
		cv.put(KEY_BURNED, burned);
		
		// Inserting Row
        db.insert(TABLE_FCONSTANTS, null, cv);
        db.close(); // Closing database connection
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
    	cv.put(KEY_ID, medicine.getID());
    	
    	db.insert(TABLE_MEDICINE, null, cv);
    	
    }
    
    public void deleteMedicine(int timeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINE, KEY_ID + " = ?",
                new String[] { String.valueOf(timeStamp) });
        db.close();
    }

    
    public  ArrayList<HashMap<String, Object>> getAllMedicine() {
    	

		ArrayList<HashMap<String, Object>> mylistData = new ArrayList<HashMap<String, Object>>();
		//HashMap<String,Object> map = new HashMap<String, Object>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	HashMap<String,Object> map = new HashMap<String, Object>();
                Medicine m = new Medicine();
                //f.setID(Integer.parseInt(cursor.getString(0)));
                m.setUserName(cursor.getString(0));
                m.setName(cursor.getString(1));
                m.setTimesPer(Integer.parseInt(cursor.getString(2)));
                m.setDosage(Integer.parseInt(cursor.getString(3)));
                m.setUnit(cursor.getString(4));
                m.setConflictions(cursor.getString(5));
                m.setID(cursor.getString(6));
                // Adding contact to list
                map.put("user", m.getUserName());
				map.put("name", m.getName());
				map.put("times", m.getTimesPer());
				map.put("quant", m.getDosage());
				map.put("unit", m.getUnit());
				map.put("con", m.getConflictions());
				map.put("id", m.getID());
				
				mylistData.add(map);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        return mylistData;
    }
    
public void addExercise(Exercise ex)

    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues cv = new ContentValues();
    	
    	cv.put(KEY_EX_NAME, ex.getName());
		cv.put(KEY_CALORIES_BURNED, ex.getCaloriesBurned());
		cv.put(KEY_TIME, ex.getTimeStamp());
		
		// Inserting Row
        db.insert(TABLE_EXERCISE, null, cv);
        db.close(); // Closing database connection
    	
    }
/*******Methods for FOOD*******************/
		// Delete a row from the database, by rowId (primary key)
    
    public void deleteFood(int timeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, KEY_TIME + " = ?", new String[] { String.valueOf(timeStamp) });
        db.close();
    }
    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_QUANTITY, food.getQuantity());
        values.put(KEY_CALORIES_PER, food.getCaloriesPer());
        values.put(KEY_TIME, food.getTimeStamp());
     
        // updating row
        return db.update(TABLE_FOOD, values, KEY_TIME + " = ?",
                new String[] { String.valueOf(food.getTimeStamp()) });
    }
    
    public int updateFConstatnts(String user, String s, String t, String i, String b) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_SUGGESTED, s);
        values.put(KEY_TODAY, t);
        values.put(KEY_INTAKE, i);
        values.put(KEY_BURNED, b);
        // updating row
        return db.update(TABLE_FCONSTANTS, values, KEY_USER + " = ?",
                new String[] { String.valueOf(user) });
    }
    
    public int getCount(String value) {
        String countQuery = "SELECT  * FROM " + value;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
 
        // return count
        //if (cursor.getCount()==null)
        return cursor.getCount();
    }



    public void deleteEx(int timeStamp) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_EXERCISE, KEY_TIME + " = ?", new String[] { String.valueOf(timeStamp) });
    db.close();
    }
    
    public int updateEx(Exercise ex) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_EX_NAME, ex.getName());
        values.put(KEY_CALORIES_BURNED, ex.getCaloriesBurned());
        values.put(KEY_TIME, ex.getTimeStamp());
     
        // updating row
        return db.update(TABLE_EXERCISE, values, KEY_TIME + " = ?",
                new String[] { String.valueOf(ex.getTimeStamp()) });
    }
}