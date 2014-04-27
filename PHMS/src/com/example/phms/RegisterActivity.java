package com.example.phms;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	
	public EditText firstname;
	public EditText lastname;
	public EditText username;
	public EditText password;
	public EditText email;
	public EditText docemail;
	public EditText age;
	public EditText gender;
	public EditText inches;
	public EditText feet;
	public EditText weight;
	public EditText answer;
	dbHelper mDbHelper;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		firstname = (EditText) findViewById(R.id.edit_first_name);
	    lastname = (EditText) findViewById(R.id.edit_last_name);
		username = (EditText) findViewById(R.id.edit_username);
	    password = (EditText) findViewById(R.id.edit_password);
		email = (EditText) findViewById(R.id.edit_email);
		docemail = (EditText) findViewById(R.id.edit_doc_email);
		age = (EditText) findViewById(R.id.edit_age);
		gender = (EditText) findViewById(R.id.edit_gender);
		inches = (EditText) findViewById(R.id.edit_in);
		feet = (EditText) findViewById(R.id.edit_ft);
		weight = (EditText) findViewById(R.id.edit_weight);
		answer = (EditText) findViewById(R.id.edit_answer);
		mDbHelper = new dbHelper(getBaseContext());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void onCancel(View view) {
		this.finish();
	}
	
	public void onRegister(View view) {
		
		int g=0;
		String a = firstname.getText().toString();
		String b = lastname.getText().toString();
		String c = username.getText().toString();
		String d = password.getText().toString();
		String e = email.getText().toString();
		String f = docemail.getText().toString();
		try{
		g = Integer.parseInt(age.getText().toString());
		}
		catch (NumberFormatException exception){
			Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();
		}
		int h = Integer.parseInt(feet.getText().toString());
		int i = Integer.parseInt(inches.getText().toString());
		int j = Integer.parseInt(weight.getText().toString());
		String k = gender.getText().toString();
		String l = answer.getText().toString();
		

		//Toast.makeText(RegisterActivity.this, age.getText().toString(), Toast.LENGTH_LONG).show();
		Users currentUser = new Users(a,b,c,d,e,f,g,h,i,j,k,l);
		mDbHelper.addUser(currentUser);
		mDbHelper.setFoodConstants(c, "s", "t", "i", "b");
		//Toast.makeText(RegisterActivity.this, c, Toast.LENGTH_LONG).show();
							
						
							
		this.finish();
		
		
	}
	
	
	

}
