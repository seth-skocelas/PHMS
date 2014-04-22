//commactivity.java
package com.example.phms;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CommunicationActivity extends Activity {
	
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communication);
		
		
		btn = (Button)findViewById(R.id.sms);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(CommunicationActivity.this, "reach", Toast.LENGTH_LONG).show();
				sendSms();
				Toast.makeText(CommunicationActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
				
			}
		});
		
	}
	
	public void sendSms(){
		
		  PendingIntent pi = PendingIntent.getActivity(this, 0,
		            new Intent(this, CommunicationActivity.class), 0);                
		        SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage("8178995567", null, "hello", pi, null);
		        Toast.makeText(CommunicationActivity.this, "Your sms has successfully sent!",Toast.LENGTH_LONG).show();
		
		/*
		try{
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("8178995567", null, "hello", null, null);
			//Toast.makeText(CommunicationActivity.this, "Your sms has successfully sent!",
			//		Toast.LENGTH_LONG).show();

		}catch(Exception ex){
			ex.printStackTrace();
			Toast.makeText(CommunicationActivity.this,"Your sms has failed...",
					Toast.LENGTH_LONG).show();
		}
		*/
	}






}
