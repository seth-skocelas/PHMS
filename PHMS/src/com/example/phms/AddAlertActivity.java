
package com.example.phms;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
	private int hour;
	private int min;
	
	
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

		Calendar calendar = Calendar.getInstance();
		hour = time_picker.getCurrentHour();
		min = time_picker.getCurrentMinute();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
		
	
		
		
		setButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)						
			{
					//Toast.makeText(AddAlertActivity.this, " "+time_picker.getCurrentHour()+" "+time_picker.getCurrentMinute(),Toast.LENGTH_LONG).show();
					//Calendar cal = Calendar.getInstance();
					
					//cal.set(2014,4,15, time_picker.getCurrentHour(), time_picker.getCurrentMinute(), 00);
					Calendar calendar = Calendar.getInstance();
					hour = time_picker.getCurrentHour();
					min = time_picker.getCurrentMinute();
			        calendar.set(Calendar.HOUR_OF_DAY,hour);
			        calendar.set(Calendar.MINUTE, min);
			        calendar.set(Calendar.SECOND, 0);
					//when they choose to repeat just once
					if(sun.isChecked() == false && mon.isChecked()== false &&tues.isChecked()==false &&wed.isChecked()==false
							&&thur.isChecked()==false && fri.isChecked()==false && sat.isChecked()==false)
					{
						setAlarm(calendar);
						Toast.makeText(AddAlertActivity.this, " "+time_picker.getCurrentHour()+" "+time_picker.getCurrentMinute(),Toast.LENGTH_LONG).show();
						
					}
					
					
					else
					{
						//this is where if the days they want to repeat are checked
						
					}			
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
	 private void setAlarm(Calendar targetCal){

		// Toast.makeText(AddAlertActivity.this, " "+targetCal,Toast.LENGTH_LONG).show();
		 /* mToast.makeText(getApplicationContext(),
					"Alarm Activity set in:"+" seconds",
					Toast.LENGTH_LONG);*/
		  long time = targetCal.getTimeInMillis();
		  Intent intent = new Intent(AddAlertActivity.this, AlarmReceiverActivity.class);
			PendingIntent pi = PendingIntent.getActivity(AddAlertActivity.this, 1, 
					intent, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, time, pi); 

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
	
	public void setTime(View view){
		//create radio group buttons for when to repeat alarm
	}

}
