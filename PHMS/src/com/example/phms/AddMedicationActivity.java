package com.example.phms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddMedicationActivity extends Activity {
	private static final int BUTTON_POSITIVE = -1;
	private static final int BUTTON_NEGATIVE = -2;

	public EditText name;
	public EditText times_per;
	public EditText dosage;
	public EditText unit;
	public EditText conflictions;
	final Context context = this;
	private static int count=0;
	private int conCount = 0, timeCount = 0;
	dbHelper mDbHelper;
	String [] currentConflictions = {" "," "," "," "," "," "," "," "," "," "};
	String [] alarmDays = {" "," "," "," "," "," "," "," "};
	String [] currentTimes = {" "," "," "," "," "};
	public static String strSeparator = "__,__";
	int numC = 0, i;
	String conflictionList, daysList, timesList, cTime, username = "Test";
	private Button viewConButton, viewTimeButton, addTimeButton;
	private ListView cList, tList;
	private ArrayList<HashMap<String, Object>> allMed;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medication);
		name = (EditText) findViewById(R.id.edit_name);
		dosage = (EditText) findViewById(R.id.edit_dosage);
	    unit = (EditText) findViewById(R.id.edit_unit);
		conflictions = (EditText) findViewById(R.id.edit_conflictions);
		mDbHelper = new dbHelper(getBaseContext());
		viewConButton = (Button) findViewById(R.id.view_con_button);
		viewTimeButton = (Button) findViewById(R.id.view_times_button);
		addTimeButton = (Button) findViewById(R.id.add_time_button);
		
		viewConButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.confliction_object, null);
				cList = (ListView) promptsView.findViewById(R.id.con_list);
				setCon();
				
				
				cList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			        @Override
			        public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
			        	//Log.d("in onLongClick");
		                String str=cList.getItemAtPosition(index).toString();
		                removeItemFromList(index);
		                //Toast.makeText(getApplicationContext(),str , Toast.LENGTH_LONG).show();
		                //Log.d("long click : " +str);
		               return true;
			        		}
					});
				
				

 
				final AlertDialog alert = new AlertDialog.Builder(context)
 
				.setView(promptsView)
				.setNegativeButton("Cancel", null)
				.create();
				
				
 
				
				alert.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {

				
					
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
		
		
		viewTimeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.view_time_object, null);
				tList = (ListView) promptsView.findViewById(R.id.time_list);
				setTimes();
				
				
				tList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			        @Override
			        public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
			        	//Log.d("in onLongClick");
		                String str=tList.getItemAtPosition(index).toString();
		                removeTimeFromList(index);
		                //Toast.makeText(getApplicationContext(),str , Toast.LENGTH_LONG).show();
		                //Log.d("long click : " +str);
		               return true;
			        		}
					});
				
				

 
				final AlertDialog alert = new AlertDialog.Builder(context)
 
				.setView(promptsView)
				.setNegativeButton("Cancel", null)
				.create();
				
				
 
				
				alert.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {

				
					
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
		
		
		
		addTimeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.dt_object, null);
				final CheckBox sunday = (CheckBox) promptsView.findViewById(R.id.Sunday);
				final CheckBox monday = (CheckBox) promptsView.findViewById(R.id.Monday);
				final CheckBox tuesday = (CheckBox) promptsView.findViewById(R.id.Tuesday);
				final CheckBox wednesday = (CheckBox) promptsView.findViewById(R.id.Wednesday);
				final CheckBox thursday = (CheckBox) promptsView.findViewById(R.id.Thursday);
				final CheckBox friday = (CheckBox) promptsView.findViewById(R.id.Friday);
				final CheckBox saturday = (CheckBox) promptsView.findViewById(R.id.Saturday);
				final CheckBox everyday = (CheckBox) promptsView.findViewById(R.id.Everyday);
				final TimePicker time = (TimePicker) promptsView.findViewById(R.id.time);
				
				if (alarmDays[0].equals("sunday"))
					sunday.setChecked(true);
				if (alarmDays[1].equals("monday"))
					monday.setChecked(true);
				if (alarmDays[2].equals("tuesday"))
					tuesday.setChecked(true);
				if (alarmDays[3].equals("wednesday"))
					wednesday.setChecked(true);
				if (alarmDays[4].equals("thursday"))
					thursday.setChecked(true);
				if (alarmDays[5].equals("friday"))
					friday.setChecked(true);
				if (alarmDays[6].equals("saturday"))
					saturday.setChecked(true);
				if (alarmDays[7].equals("everyday"))
					everyday.setChecked(true);
				
				
				
					
			
 
				final AlertDialog alert = new AlertDialog.Builder(context)
 
				.setView(promptsView)
				.setPositiveButton("Add", null)
				.setNegativeButton("Cancel", null)
				.create();
				
				
 
				
				alert.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {

				
					
					Button cancel = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
					cancel.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) 
						{
               
							alert.dismiss();
						}
					});
					
					Button add = alert.getButton(AlertDialog.BUTTON_POSITIVE);
					add.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) 
						{
							for (i = 0; i < 8; i++)
								alarmDays[i] = " ";
							
							if (sunday.isChecked()) {
								alarmDays[0] = "sunday";
							}
							if (monday.isChecked()) {
								alarmDays[1] = "monday";
							}
							if (tuesday.isChecked()) {
								alarmDays[2] = "tuesday";
							}
							if (wednesday.isChecked()) {
								alarmDays[3] = "wednesday";
							}
							if (thursday.isChecked()) {
								alarmDays[4] = "thursday";
							}
							if (friday.isChecked()) {
								alarmDays[5] = "friday";
							}
							if (saturday.isChecked()) {
								alarmDays[6] = "saturday";
							}
							if (everyday.isChecked()) {
								alarmDays[7] = "everyday";
							}
							
							int hour = time.getCurrentHour();
							int min = time.getCurrentMinute();
							
							if (min < 10)
								cTime = hour + ":0" + min;
							else
								cTime = hour + ":" + min;
								
							if (timeCount <=4 ) {
								currentTimes[timeCount] = cTime;
								timeCount++;
							}
							else
					    		  Toast.makeText(AddMedicationActivity.this, "Only 5 alarms can be stored.", Toast.LENGTH_LONG).show();
               
							alert.dismiss();
						}
					});
				}
			});
				
			alert.show();	
			}
			
			
			
		});
		
	}
	
	
	
		
		
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_medication, menu);
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
	    		alertDialog.setButton(BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 
	    		    	  Intent i = getBaseContext().getPackageManager()
	    		    	             .getLaunchIntentForPackage( getBaseContext().getPackageName() );
	    		    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		    	startActivity(i);
	    		    } });
	    		alertDialog.setButton(BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
	    		      public void onClick(DialogInterface dialog, int which) {
	    		 

	    		    	  
	    		    } });
	    		alertDialog.show();
	    		break;
	    	
	    	}
	    	
	  		return true;
	  	}
	
	public void cancelMedication(View view) {
		endActivity(view);
	}
	
	public void confirmMedication(final View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Medication");
		alertDialog.setMessage("Are you sure you want to save this medication?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  if (name.getText().toString().equals("") || dosage.getText().toString().equals("") || unit.getText().toString().equals("")){
						Toast.makeText(AddMedicationActivity.this, "You must fill in Name, Dosage and Unit.", Toast.LENGTH_LONG).show();
					}
		    	  else {
		    	  
		    		  String a = name.getText().toString();
		    		  int c = Integer.parseInt(dosage.getText().toString());
		    		  String d = unit.getText().toString();
		    		  conflictionList = convertArrayToString(currentConflictions);
		    		  timesList = convertArrayToString(currentTimes);
		    		  daysList = convertArrayToString(alarmDays);

		    	  
		    	  
		    		  Medicine currentMedicine = new Medicine(username,a,1,c,d,conflictionList,(new Time()).toString(),conCount,
		    			  									timesList, timeCount, daysList);
		  		  
		    		  boolean foundConfliction = checkConflictions(a);
		    	  
		    		  if (!foundConfliction) {
		    			  mDbHelper.addMedicine(currentMedicine);
		    			  mDbHelper.close();
		    			  conCount = 0;
		    			  timeCount = 0;
		    			  endActivity(view);
		    		  	}
		    		  
		    	  		}
		 
		    	  	} });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	 
		 
		    } });
		
		alertDialog.show();
		
	}
/* Old, not in use	
	
	public void viewConflictions (View view) {
		Bundle b=new Bundle();
		b.putStringArray("key", currentConflictions);
		Intent intent = new Intent(this, ConflictionActivity.class);
		intent.putExtras(b);
	    startActivity(intent);
	}
	
	*/
	
	public void addConfliction(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Confliction");
		alertDialog.setMessage("Are you sure you want to add this Confliction?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  if (conCount > 9)
		    		  Toast.makeText(AddMedicationActivity.this, "Only 10 Conflictions can be stored.", Toast.LENGTH_LONG).show();
		    	  else {
		    	  String e = conflictions.getText().toString();
		    	  boolean foundNameConfliction = checkNames(e);
		    	  if (!foundNameConfliction) {
		    		  currentConflictions[conCount] = e;
		    		  conCount++;
		    	  }
		    	  }

		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 

		 
		    } });
		
		alertDialog.show();
		
	}
	
	public static String convertArrayToString(String[] array){
	    String str = "";
	    for (int i = 0;i<array.length; i++) {
	        str = str+array[i];
	        // Do not append comma at the end of last element
	        if(i<array.length-1){
	            str = str+strSeparator;
	        }
	    }
	    return str;
	}
	public static String[] convertStringToArray(String str){
	    String[] arr = str.split(strSeparator);
	    return arr;
	}
	
	
	
	
	public void endActivity(View view) {
	
		Intent intent = new Intent(this, MedicationActivity.class);
	    startActivity(intent);
		AddMedicationActivity.this.finish();
	}
	
	public void endActivity() {
		
		Intent intent = new Intent(this, MedicationActivity.class);
	    startActivity(intent);
		AddMedicationActivity.this.finish();
	}
	
	public void setCon() {
		ArrayAdapter<String> itemsAdapter = 
			    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentConflictions);
		cList.setAdapter(itemsAdapter);
	}
	
	public void setTimes() {
		ArrayAdapter<String> itemsAdapter = 
			    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentTimes);
		tList.setAdapter(itemsAdapter);
	}
	
	@Override
	public void onBackPressed() {
	
		endActivity();
	    
	}
	


    private void removeTimeFromList(int position) {
        //final int deletePosition = position;
        final int index = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(
                AddMedicationActivity.this);
    
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
        		if (index == 4) 
        			currentTimes[4] = " ";
        		else {
        			for (int a = index + 1; a <= 4; a++)
                    {
        				currentTimes[a-1] = currentTimes[a];
                    }
        			currentTimes[4] = " ";
        		}
                
                   
                timeCount--;
                setTimes();
                
      
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
    
    private void removeItemFromList(int position) {
        //final int deletePosition = position;
        final int index = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(
                AddMedicationActivity.this);
    
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
        		if (index == 9) 
        			currentConflictions[9] = " ";
        		else {
        			for (int a = index + 1; a <= 9; a++)
                    {
        				currentConflictions[a-1] = currentConflictions[a];
                    }
        			currentConflictions[9] = " ";
        		}
                
                   
                conCount--;
                setCon();
                
      
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
    
    boolean checkConflictions(String a) {
    	
        allMed = mDbHelper.getAllMedicine();
    	  
    	  
    	  for (HashMap<String, Object> h : allMed) {
    		  String name = (String) h.get("name");
    		  String thisUser = (String) h.get("user");
    		  String con = (String) h.get("con");
    		  if (thisUser.equals(username)) {
    			  String [] cons = convertStringToArray(con);
    			  for (i = 0; i < 10; i++) {
    				  if (a.toLowerCase().equals(cons[i].toLowerCase())) {
    					  Toast toast=Toast.makeText(getApplicationContext(), 
    		              "Confliction with " + name + " - Medicine not added",Toast.LENGTH_LONG );
    		              toast.setGravity(Gravity.CENTER, 0, 0);
    		              toast.show();
    		              
    		              return true;
    				  	}
    			  	}
    			  
    		  	}
    	  	}
      	return false;
      }
    
    
    boolean checkNames(String a) {
    	
        allMed = mDbHelper.getAllMedicine();
    	  
    	  
    	  for (HashMap<String, Object> h : allMed) {
    		  String name = (String) h.get("name");
    		  String thisUser = (String) h.get("user");
    		  if (thisUser.toLowerCase().equals(username.toLowerCase())) {
    			  if (a.toLowerCase().equals(name.toLowerCase())) {
    				  Toast toast=Toast.makeText(getApplicationContext(), 
        		      "Confliction with " + name + " - Confliction not added",Toast.LENGTH_LONG );
        		       toast.setGravity(Gravity.CENTER, 0, 0);
        		       toast.show();
    				   return true;
    			  			}
    				  
    		  				} 		              
   
    				  	}

      		return false;
      }

}
