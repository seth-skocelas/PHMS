package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class AddMedicationActivity extends Activity {
	private static final int BUTTON_POSITIVE = -1;
	private static final int BUTTON_NEGATIVE = -2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medication);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_medication, menu);
		return true;
	}
	
	public void cancelMedication(View view) {
		Intent intent = new Intent(this, MedicationActivity.class);
	    startActivity(intent);
	}
	
	public void confirmMedication(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Medication");
		alertDialog.setMessage("Are you sure you want to save this medication?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	  startActivity(new Intent(getBaseContext(),MedicationActivity.class));
		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	  startActivity(new Intent(getBaseContext(),MedicationActivity.class));
		 
		    } });
		
		alertDialog.show();
		
	}
	
	public void addConfliction(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Confliction");
		alertDialog.setMessage("Are you sure you want to add this Confliction?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 

		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 

		 
		    } });
		
		alertDialog.show();
		
	}
	

}
