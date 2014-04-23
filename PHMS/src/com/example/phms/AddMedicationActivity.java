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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
	private int conCount = 0;
	dbHelper mDbHelper;
	String [] currentConflictions = {" "," "," "," "," "," "," "," "," "," "};
	public static String strSeparator = "__,__";
	int numC = 0;
	String conflictionList;
	private Button viewConButton;
	private ListView cList;

	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medication);
		name = (EditText) findViewById(R.id.edit_name);
	    times_per = (EditText) findViewById(R.id.edit_times_per);
		dosage = (EditText) findViewById(R.id.edit_dosage);
	    unit = (EditText) findViewById(R.id.edit_unit);
		conflictions = (EditText) findViewById(R.id.edit_conflictions);
		mDbHelper = new dbHelper(getBaseContext());
		viewConButton = (Button) findViewById(R.id.view_con_button);
		
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
		this.finish();
	}
	
	public void confirmMedication(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Medication");
		alertDialog.setMessage("Are you sure you want to save this medication?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  
		    	  String a = name.getText().toString();
		    	  int b = Integer.parseInt(times_per.getText().toString());
		    	  int c = Integer.parseInt(dosage.getText().toString());
		    	  String d = unit.getText().toString();
		    	  conflictionList = convertArrayToString(currentConflictions);
		    	  
		    	  Medicine currentMedicine = new Medicine(a,a,b,c,d,conflictionList,(count++ + ""),conCount);
		  		  mDbHelper.addMedicine(currentMedicine);
		  		  mDbHelper.close();
		  		  conCount = 0;
		    	  endActivity();
		 
		    } });
		alertDialog.setButton(BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		    	 
		 
		    } });
		
		alertDialog.show();
		
	}
	
	public void viewConflictions (View view) {
		Bundle b=new Bundle();
		b.putStringArray("key", currentConflictions);
		Intent intent = new Intent(this, ConflictionActivity.class);
		intent.putExtras(b);
	    startActivity(intent);
	}
	
	public void addConfliction(View view) {
		

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Add Confliction");
		alertDialog.setMessage("Are you sure you want to add this Confliction?");
		alertDialog.setButton(BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  String e = conflictions.getText().toString();
		    	  currentConflictions[conCount] = e;
		    	  conCount++;
		 

		 
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
	
	@Override
	public void onBackPressed() {
	
		endActivity();
	    
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

}
