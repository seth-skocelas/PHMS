//alarmreceiveractivity.java
package com.example.phms;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class AlarmReceiverActivity extends Activity{
	private PowerManager.WakeLock mWakeLock;
	private MediaPlayer mMediaPlayer;
	private Timer timer;
	

	public void onCreate(Bundle savedInstanceState){
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
		
		Button stopAlarm = (Button) findViewById(R.id.btnStop);
		//delaySms();
		stopAlarm.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0)
			{
				mMediaPlayer.stop();
				finish();
				//add toast
				//cancel delay timer
				//timer.cancel();
			}
		});
		playSound(this, getAlarmUri());
	}
	
	private void playSound(Context context, Uri alert)
	{
		mMediaPlayer = new MediaPlayer();
		try
		{
			mMediaPlayer.setDataSource(context, alert);
			final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0)
			{
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mMediaPlayer.prepare();
				mMediaPlayer.start();
			}
			
		}catch (IOException e)
		{
			Log.i("AlarmReceiver", "No audio file found");
		}
	}
	
	private void delaySms(){
		timer = new Timer();
		
		final CommunicationActivity ca = new CommunicationActivity();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				ca.sendSms();
				Toast.makeText(AlarmReceiverActivity.this, "send", Toast.LENGTH_LONG).show();
				
			}
			
		};
		timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
		Toast.makeText(AlarmReceiverActivity.this, "reach delay sms", Toast.LENGTH_LONG).show();
		
		
		
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
