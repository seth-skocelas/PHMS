package com.example.phms;

import java.io.IOException;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddAlertActivity extends Activity {

	TimePicker time_picker;
	final Context context = this;
	private Button setButton,cancelButton;
	private Toast mToast;
	private CheckBox sun,mon,tues,wed,thur,fri,sat;
	private View promptsView;
	private int selectHours;
	private int selectMinutes;
	
	
	// opens add_alert xml
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_alert);
		
		time_picker = (TimePicker) findViewById(R.id.timePicker1);
		setButton = (Button)findViewById(R.id.btnset1);
		cancelButton = (Button)findViewById(R.id.btncancel2);
		selectHours = time_picker.getCurrentHour();
		selectMinutes = time_picker.getCurrentMinute();
		sun = (CheckBox) findViewById(R.id.Sunday);
		mon = (CheckBox) findViewById(R.id.Monday);
		tues = (CheckBox) findViewById(R.id.Tuesday);
		wed = (CheckBox) findViewById(R.id.Wednesday);
		thur = (CheckBox) findViewById(R.id.Thursday);
		fri = (CheckBox) findViewById(R.id.Friday);
		sat = (CheckBox) findViewById(R.id.Saturday);

		
		
		setButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				
					//Toast.makeText(AddAlertActivity.this, ""+selectHours+":"+selectMinutes, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(AddAlertActivity.this, AlarmReceiverActivity.class);
					PendingIntent pi = PendingIntent.getActivity(AddAlertActivity.this, 2, 
							intent, PendingIntent.FLAG_CANCEL_CURRENT);
					AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
					am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 5*1000, pi); 
					// the time is 5 seconds in type long
					//use for test
					
					if (mToast != null)
					{
						mToast.cancel();
					}
					
					mToast = Toast.makeText(getApplicationContext(),
							"Alarm Activity set in:"+5+" seconds",
							Toast.LENGTH_LONG);
					mToast.show();
				
	
				
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
	}
	
	public void cancel(View view) {
		Intent intent = new Intent(this, AlertActivity.class);
	    startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diet, menu);
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
	
	public void setTime(View view){
		//create radio group buttons for when to repeat alarm
	}

}
