package com.example.phms;

//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertActivity extends Activity {

	private Button button;


	
	public void addAlert(View view) {
		Intent intent = new Intent(this, AddAlertActivity.class);
	    startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alert, menu);
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
    		    	  
    		    	  Intent i = getBaseContext().getPackageManager()
    		    	             .getLaunchIntentForPackage( getBaseContext().getPackageName() );
    		    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		    	startActivity(i);
    		 
    		    } });
    		alertDialog.setButton(-2,"Cancel", new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {
    		 

    		    	  
    		    } });
    		alertDialog.show();
    		break;
    	
    	}
    	
  		return true;
  	}

	/*
	 * start here
	 */

}
