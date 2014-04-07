package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MedicationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medication, menu);
		return true;
	}
	
	public void returnMenu(View view) {
		Intent intent = new Intent(this, MainMenuActivity.class);
	    startActivity(intent);
	}
	
	public void addMedication(View view) {
		Intent intent = new Intent(this, AddMedicationActivity.class);
	    startActivity(intent);
	}

}
