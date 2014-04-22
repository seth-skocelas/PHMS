//repeatingalarm.java
package com.example.phms;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RepeatingAlarmActivity extends Activity {
	private MediaPlayer mMediaPlayer;
	private PowerManager.WakeLock mWakeLock;
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Wake Log");
		mWakeLock.acquire();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON, 
				 WindowManager.LayoutParams.FLAG_FULLSCREEN
				 | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
					| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
				);
		setContentView(R.layout.activity_alarm_screen);
		setContentView(R.layout.activity_alarm_screen);
		
Button stopAlarm = (Button) findViewById(R.id.btnStop);
		
		stopAlarm.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0)
			{
				mMediaPlayer.stop();
				finish();
			}
		});
	}
	private Uri getAlarmUri()
	{
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if (alert ==null)
		{
			alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			if (alert == null)
			{
				alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			}
		}
		
		return alert;
	}
	
	protected void onStop()
	{
		super.onStop();
		mWakeLock.release();
	}

}
