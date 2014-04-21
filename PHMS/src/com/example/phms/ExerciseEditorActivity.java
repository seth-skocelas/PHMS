package com.example.phms;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseEditorActivity extends Activity {
	private Button addexerciseButton;
	final Context context = this;
	private TextView displayTotal;
	private TextView todayIntake;
	// private TextView displayCalorieText;
	// private String combined;
	// public static ArrayList<String> foodBucket;
	public String exercise_main;
	private ListView list;
	private ArrayList<HashMap> hlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_editor);
		list = (ListView) findViewById(R.id.exercise_list);
		list.setEmptyView(findViewById(R.id.empty));
		//displayCalorieText = (TextView) findViewById(R.id.displayCalories);
		addexerciseButton = (Button) findViewById(R.id.add_exercise_button);
		//foodBucket = new ArrayList<String>();
		addexerciseButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.exercise_object, null);
				
		    	
 
				 final AlertDialog alert = new AlertDialog.Builder(context)
 
				.setView(promptsView)
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.create();
 
				final EditText exercise = (EditText) promptsView.findViewById(R.id.exercise_text);
				//final EditText quantity = (EditText) promptsView.findViewById(R.id.quantity_text);
				final EditText calories = (EditText) promptsView.findViewById(R.id.calorie_text);
				
				// set dialog message
				
				
				alert.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {

				Button b = alert.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) 
					{
						ArrayList<HashMap<String, Object>> mylistData = new ArrayList<HashMap<String, Object>>();	 
						String[] columnTags = new String[] {"exercise", "cal"};
							 
						int[] columnIds = new int[] {R.id.exTV, R.id.calTV};
						HashMap<String,Object> map = new HashMap<String, Object>();
							
							
						//Adding elements to the list right here
						map.put("exercise", exercise.getText().toString());
						//double quanD = 0;
						double calD=0;
						try {
							//quanD = Double.parseDouble(quantity.getText().toString());
							calD = Double.parseDouble(calories.getText().toString());
							//map.put("quan", quanD);
							map.put("cal", calD);
							mylistData.add(map);
							SimpleAdapter arrayAdapter = new SimpleAdapter(ExerciseEditorActivity.this, mylistData, R.layout.display_exercise_items, columnTags , columnIds);
							list.setAdapter(arrayAdapter);
							//double result = quanD*calD;
							displayTotal = (TextView) findViewById(R.id.total);
							String newTotal = getString(R.string.total_exercise);
							newTotal = String.format(newTotal, calD);
							displayTotal.setText(newTotal);
								
							} catch (NumberFormatException e) 
							{
								//Toast.makeText(FoodEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
							}
							//changing the value of string total here
						
							
							
				if (exercise.getText().toString().equals("")  || calories.getText().toString().equals(""))
							Toast.makeText(ExerciseEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
						else
							alert.dismiss();
					}
				});
					
					Button cancel = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
					cancel.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) 
						{
               
							alert.dismiss();
						}
					});
				}
			});
				
			alert.show();	
			}
		});
		
	    
		//long click
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	        @Override
	        public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
	        	//Log.d("in onLongClick");
                String str=list.getItemAtPosition(index).toString();
                removeItemFromList(index);
                //Toast.makeText(getApplicationContext(),str , Toast.LENGTH_LONG).show();
                //Log.d("long click : " +str);
               return true;
	        		}
			});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise_editor, menu);
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
	
    private void removeItemFromList(int position) {
        //final int deletePosition = position;
        
        AlertDialog.Builder alert = new AlertDialog.Builder(
                ExerciseEditorActivity.this);
    
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TOD O Auto-generated method stub
                    
                    // main code on after clicking yes
                    //list.remove(deletePosition);
                    //adapter.notifyDataSetChanged();
                    //adapter.notifyDataSetInvalidated();
      
            }
        });
        alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
      
        alert.show();
      
    }

}