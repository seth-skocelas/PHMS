package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {
	
	private static final int BUTTON_POSITIVE = -1;
	private static final int BUTTON_NEGATIVE = -2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void displayDiet(View view) {
		Intent intent = new Intent(this, DietActivity.class);
	    startActivity(intent);
	}
	public void displayMenu(View view) {
		Intent intent = new Intent(this, MainMenuActivity.class);
	    startActivity(intent);
	}
	
	public void displayMed(View view) {
		Intent intent = new Intent(this, MedicationActivity.class);
	    startActivity(intent);
	}
	
	public void displaySearch(View view) {
		
		Intent intent = new Intent(this, SearchActivity.class);
	    startActivity(intent);

	}
	public void DisplaySms(View view){
		Intent intent = new Intent(this, CommunicationActivity.class);
		startActivity(intent);
	}
	
	public void LogOut(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Logout");
		alertDialog.setMessage("Are you sure you want to logout?");
		alertDialog.setButton(BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	  startActivity(new Intent(getBaseContext(),LoginActivity.class));
		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 

		    	  
		    } });
		alertDialog.show();
		
	}
	public void startDiet(View view) {
		
		Intent intent = new Intent(this, DietActivity.class);
	    startActivity(intent);

}
	
//displayVitalSigns
	public void displayVitalSigns(View view) {
		
		Intent intent = new Intent(this, VitalSignsActivity.class);
	    startActivity(intent);

}
	
}
