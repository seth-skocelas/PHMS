package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class RecoverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recover, menu);
		return true;
	}
	
	public void onRecover(View view) {
		this.finish();
	}
	public void onCancel(View view){
		this.finish();
	}
	
	

}
