package com.example.phms;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class VitalSignsActivity extends Activity {
	
	private ListView list;
	final Context context = this;
	Double readValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vital_signs);
		list = (ListView) findViewById(R.id.vital_list);
		//list.setLongClickable(true);
		populateListView();
		register();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vital_signs, menu);
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

	private void populateListView() {
		//this is going to be done with the database
		ArrayList<HashMap<String, Object>> mylistData = new ArrayList<HashMap<String, Object>>();	 
		String[] columnTags = new String[] {"vital", "value"};
		int[] columnIds = new int[] {R.id.vitalTV, R.id.valueTV};
		//adding elements to the list
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("vital", "Temperature");
		map.put("value", "N/A");
		mylistData.add(map);
		map = new HashMap<String, Object>();
		map.put("vital", "Blood Pressure");
		map.put("value", "N/A");
		mylistData.add(map);
		map = new HashMap<String, Object>();
		map.put("vital", "Pulse Rate");
		map.put("value", "N/A");
		mylistData.add(map);
		map = new HashMap<String, Object>();
		map.put("vital", "Respiratory Rate");
		map.put("value", "N/A");
		mylistData.add(map);
		map = new HashMap<String, Object>();
		map.put("vital", "Cholesterol");
		map.put("value", "N/A");
		mylistData.add(map);
		SimpleAdapter arrayAdapter = new SimpleAdapter(VitalSignsActivity.this, mylistData, R.layout.display_vital_signs, columnTags , columnIds);
		list.setAdapter(arrayAdapter);
	
	}
	private void register(){
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){
				//values for parsing
				TextView v = (TextView)findViewById(R.id.vitalTV);
				TextView v1 = (TextView)findViewById(R.id.valueTV);
				//this is the value aka reading
				String content1 =v.getText().toString();
				//this is the vital sign
				String content2 =v1.getText().toString();
				
				//layout code
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.vital_edit, null);
				
				 final AlertDialog alert = new AlertDialog.Builder(context)
				 	.setView(promptsView)
				 	.setPositiveButton("OK", null)
				 	.setNegativeButton("Cancel", null)
				 	.create();
				 TextView vital = (TextView) promptsView.findViewById(R.id.vital);
				 
				 //getting the appropriate values for the string
				 String str = parent.getItemAtPosition(position).toString();
				 String delims = "vital=";
				 String[] tokens = str.split(delims);
				 StringBuilder str1 = new StringBuilder(tokens[1]);
				 str1.deleteCharAt(str1.length()-1);
				 vital.setText(str1);
				 final EditText reading = (EditText) promptsView.findViewById(R.id.reading);
				 //Double readValue=0.0;
				 
				 //this is where the edit menu shows up
					alert.setOnShowListener(new DialogInterface.OnShowListener() {

						@Override
						public void onShow(DialogInterface dialog) {
						//this is the OK button
						Button b = alert.getButton(AlertDialog.BUTTON_POSITIVE);
						b.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View view) 
							{
								
								
								try {
									
									readValue = Double.parseDouble(reading.getText().toString());
									
									/*map.put("quan", quanD);
									map.put("cal", calD);
									mylistData.add(map);
									SimpleAdapter arrayAdapter = new SimpleAdapter(ExerciseEditorActivity.this, mylistData, R.layout.display_exercise_items, columnTags , columnIds);
									list.setAdapter(arrayAdapter);
									//double result = quanD*calD;
									displayTotal = (TextView) findViewById(R.id.total);
									String newTotal = getString(R.string.total_exercise);
									newTotal = String.format(newTotal, calD);
									displayTotal.setText(newTotal);
										*/
									} catch (NumberFormatException e) 
									{
										//Toast.makeText(FoodEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
									}
									//changing the value of string total here
								
									
									
						/*if (exercise.getText().toString().equals("")  || calories.getText().toString().equals(""))
									Toast.makeText(ExerciseEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
								else
									*/
								alert.dismiss();
							}
						});
							//the cancel button
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
				 
				 //this is where it ends
				 
				 

				//testing to see if I can get the values
				
				
				//Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(),str1 , Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(), content2, Toast.LENGTH_SHORT).show();
				//Toast.makeText(getApplicationContext(),"test " + readValue , Toast.LENGTH_LONG).show();
			}
			
			
		
		});
		
		//this is where it would go
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
	
    private void removeItemFromList(int position) {
        //final int deletePosition = position;
        
        AlertDialog.Builder alert = new AlertDialog.Builder(
                VitalSignsActivity.this);
    
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setNegativeButton("YES", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TOD O Auto-generated method stub
                    
                    // main code on after clicking yes
                    //list.remove(deletePosition);
                    //adapter.notifyDataSetChanged();
                    //adapter.notifyDataSetInvalidated();
      
            }
        });
        alert.setPositiveButton("CANCEL", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
      
        alert.show();
      
    }
//}
}

