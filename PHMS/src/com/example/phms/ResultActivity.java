package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
	    		alertDialog.setButton(-1,"OK", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 
	    		    	  startActivity(new Intent(getBaseContext(),LoginActivity.class));
	    		 
	    		    } });
	    		alertDialog.setButton(-2,"Cancel", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 

	    		    	  
	    		    } });
	    		alertDialog.show();
	    		break;
	    	
	    	}
	    	
	  		return true;
	  	}

	
	public void backToResults(View view) {
		
		Intent intent = new Intent(this, SearchActivity.class);
	    startActivity(intent);

	}

}
