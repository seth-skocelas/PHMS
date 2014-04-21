package com.example.phms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddMedicationActivity extends Activity {
	private static final int BUTTON_POSITIVE = -1;
	private static final int BUTTON_NEGATIVE = -2;

	public EditText name;
	public EditText times_per;
	public EditText dosage;
	public EditText unit;
	public EditText conflictions;
	dbHelper mDbHelper;
	String [] currentConflictions = new String[10];
	public static String strSeparator = "__,__";
	int numC = 0;
	String conflictionList;

	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medication);
		name = (EditText) findViewById(R.id.edit_name);
	    times_per = (EditText) findViewById(R.id.edit_times_per);
		dosage = (EditText) findViewById(R.id.edit_dosage);
	    unit = (EditText) findViewById(R.id.edit_unit);
		conflictions = (EditText) findViewById(R.id.edit_conflictions);
		mDbHelper = new dbHelper(getBaseContext());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_medication, menu);
		return true;
	}
	
	 @Override
	  	public boolean onOptionsItemSelected(MenuItem item){
	    	// same as using a normal menu
	    	switch(item.getItemId()) {
	    	case R.id.action_settings:
	    		Intent intent = new Intent(this, SettingsActivity.class);
	    	    startActivity(intent);
	    		break;
	    	case R.id.action_search:
	    		Intent intent2 = new Intent(this, SearchActivity.class);
	    	    startActivity(intent2);
	    		break;
	    	case R.id.action_logout:
	    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	    		alertDialog.setTitle("Logout");
	    		alertDialog.setMessage("Are you sure you want to logout?");
	    		alertDialog.setButton(BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 
	    		    	  Intent i = getBaseContext().getPackageManager()
	    		    	             .getLaunchIntentForPackage( getBaseContext().getPackageName() );
	    		    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		    	startActivity(i);
	    		    } });
	    		alertDialog.setButton(BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 

	    		    	  
	    		    } });
	    		alertDialog.show();
	    		break;
	    	
	    	}
	    	
	  		return true;
	  	}
	
	public void cancelMedication(View view) {
		this.finish();
	}
	
	public void confirmMedication(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Medication");
		alertDialog.setMessage("Are you sure you want to save this medication?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  
		    	  String a = name.getText().toString();
		    	  int b = Integer.parseInt(times_per.getText().toString());
		    	  int c = Integer.parseInt(dosage.getText().toString());
		    	  String d = unit.getText().toString();
		    	  conflictionList = convertArrayToString(currentConflictions);
		    	  
		    	  Medicine currentMedicine = new Medicine(a,a,b,c,d,conflictionList);
		  		  mDbHelper.addMedicine(currentMedicine);
		  		  mDbHelper.close();
		 
		    	  AddMedicationActivity.this.finish();
		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	 
		 
		    } });
		
		alertDialog.show();
		
	}
	
	public void addConfliction(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Confliction");
		alertDialog.setMessage("Are you sure you want to add this Confliction?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  String e = conflictions.getText().toString();
		    	  currentConflictions[numC] = e;
		    	  numC++;
		 

		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 

		 
		    } });
		
		alertDialog.show();
		
	}
	
	public static String convertArrayToString(String[] array){
	    String str = "";
	    for (int i = 0;i<array.length; i++) {
	        str = str+array[i];
	        // Do not append comma at the end of last element
	        if(i<array.length-1){
	            str = str+strSeparator;
	        }
	    }
	    return str;
	}
	public static String[] convertStringToArray(String str){
	    String[] arr = str.split(strSeparator);
	    return arr;
	}
	

}
