package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.content.DialogInterface;


public class LoginActivity extends Activity {
	
	private static final int BUTTON_POSITIVE = -1;
	private static final int BUTTON_NEGATIVE = -2;
	




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
		
	}
	
	public void startRegister(View view) {
		
		Intent intent = new Intent(this, RegisterActivity.class);
	    startActivity(intent);
	    
	}
	
public void startRecover(View view) {
		
		Intent intent = new Intent(this, RecoverActivity.class);
	    startActivity(intent);
	    
	}
public void displayMenu(View view) {
	Intent intent = new Intent(this, RecoverActivity.class);
    startActivity(intent);
}
public void checkLogin(View view) {
	

	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle("Login");
	alertDialog.setMessage("Login was a success!");
	alertDialog.setButton(BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int which) {
	 
	    	  startActivity(new Intent(getBaseContext(),MainMenuActivity.class));
	 
	    } });
	
	alertDialog.show();
	
}


}
