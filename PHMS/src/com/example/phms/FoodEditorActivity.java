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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FoodEditorActivity extends Activity {
	private Button addFoodButton;
	final Context context = this;
	private TextView displayTotal;
	private TextView todayIntake;
	// private TextView displayCalorieText;
	// private String combined;
	// public static ArrayList<String> foodBucket;
	public String food_main;
	private ListView list;
	private ArrayList<HashMap<String, Object>> hlist;
	protected dbHelper db;
	private static int count=0;
	private int numberToBeDeleted; //not really a number
	//private dbHelper mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_editor);
		list = (ListView) findViewById(R.id.food_list);
    	list.setEmptyView(findViewById(R.id.empty));
    	//mDbHelper = new dbHelper(context);
    	db = new dbHelper(this);
		//displayCalorieText = (TextView) findViewById(R.id.displayCalories);
		addFoodButton = (Button) findViewById(R.id.add_food_button);
		
		//new code goes here
		hlist = db.getAllFood();		
		//setting the todayIntake string
		ArrayList<String> constants = db.getFConstants("jesus");
		double total = 0;
		todayIntake = (TextView) findViewById(R.id.display_calories);
		for (int a = 0; a<hlist.size();a++)
        {
            Map<String, Object> tmpData = (HashMap<String, Object>) hlist.get(a);
            double result= Double.parseDouble(tmpData.get("quan").toString()) * Double.parseDouble(tmpData.get("cal").toString());
            	 total+=result;
            
           
        }
		String display_today_calories = getString(R.string.calorie_today);
		display_today_calories = String.format(display_today_calories, total);
		todayIntake.setText(display_today_calories);
		db.updateFConstatnts("jesus", constants.get(0), constants.get(1), total+"", constants.get(3));
//ending new code
		populateListViewFromDB(hlist);
		
		addFoodButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.food_object, null);

 
				 final AlertDialog alert = new AlertDialog.Builder(context)
 
				.setView(promptsView)
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.create();
 
				 
				final EditText food = (EditText) promptsView.findViewById(R.id.food_text);
				final EditText quantity = (EditText) promptsView.findViewById(R.id.quantity_text);
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
						double quanD = 0;
						double calD=0;
						try {
							quanD = Double.parseDouble(quantity.getText().toString());
							calD = Double.parseDouble(calories.getText().toString());
							String string = (new Time()).toString();
							
							Food food_saved = new Food(food.getText().toString(),quantity.getText().toString(), calories.getText().toString(), string);
							db.addFood(food_saved);
							
							double result = quanD*calD;
							displayTotal = (TextView) findViewById(R.id.total);
							String newTotal = getString(R.string.total_food);
							newTotal = String.format(newTotal, result);
							displayTotal.setText(newTotal);
								
							} catch (NumberFormatException e) 
							{
								//Toast.makeText(FoodEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
							}
							//changing the value of string total here
						
							
							
						if (food.getText().toString().equals("") || quantity.getText().toString().equals("") || calories.getText().toString().equals("")){
							Toast.makeText(FoodEditorActivity.this, "You must fill all fields.", Toast.LENGTH_LONG).show();
						}
						else
						{
							alert.dismiss();
							onResume();
							//populateListViewFromDB(hlist);
						}
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
		
		//regular click
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){
				//setting this final so I can use in the onClick
				final int index = position;
				//values for parsing
				TextView v = (TextView)findViewById(R.id.foodTV);
				TextView v1 = (TextView)findViewById(R.id.quanTV);
				TextView v2 = (TextView)findViewById(R.id.calTV);
				//this is the value aka reading
				String content1 =v.getText().toString();
				//this is the vital sign
				String content2 =v1.getText().toString();
				String content3 =v2.getText().toString();
				
				//layout code
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.food_object, null);
				
				 final AlertDialog alert = new AlertDialog.Builder(context)
				 	.setView(promptsView)
				 	.setPositiveButton("OK", null)
				 	.setNegativeButton("Cancel", null)
				 	.create();
				 //TextView vital = (TextView) promptsView.findViewById(R.id.vital);
				 final EditText food_reading = (EditText) promptsView.findViewById(R.id.food_text);
				 final EditText quantity_reading = (EditText) promptsView.findViewById(R.id.quantity_text);
				 final EditText calorie_reading = (EditText) promptsView.findViewById(R.id.calorie_text);
				 //getting the appropriate values for the string
				 String str = parent.getItemAtPosition(position).toString();
				 String delims = "[,=}]";
				 String[] tokens = str.split(delims);
				 food_reading.setText(new StringBuilder(tokens[5]));
				 quantity_reading.setText(new StringBuilder(tokens[3]));
				 calorie_reading.setText(new StringBuilder(tokens[7]));
				//Toast.makeText(getApplicationContext(), str , Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(), str1 , Toast.LENGTH_LONG).show();
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
								double quanD = Double.parseDouble(quantity_reading.getText().toString());
								double calD = Double.parseDouble(calorie_reading.getText().toString());
								int id=0;
				                for (int a =0; a<hlist.size();a++)
				                {
				                    Map<String, Object> tmpData = (HashMap<String, Object>) hlist.get(a);
				                   // Set<String> key = tmpData.keySet();	
				                    if (a==index){
				                    	 id = Integer.parseInt(tmpData.get("id").toString());
				                    	 //Toast.makeText(FoodEditorActivity.this, id+"" , Toast.LENGTH_LONG).show();
				                    	 
				                    }
				                   
				                }
								
								Food food_saved = new Food(food_reading.getText().toString(),quantity_reading.getText().toString(), calorie_reading.getText().toString(), (id+""));
								
								
								db.updateFood(food_saved);
								onResume();
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
		getMenuInflater().inflate(R.menu.food_editor, menu);
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
        final int index = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(
                FoodEditorActivity.this);
    
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
        		
                for (int a =0; a<hlist.size();a++)
                {
                    Map<String, Object> tmpData = (HashMap<String, Object>) hlist.get(a);
                    Set<String> key = tmpData.keySet();
                    if (a==index){
                    	 numberToBeDeleted = Integer.parseInt(tmpData.get("id").toString());
                    	 db.deleteFood(numberToBeDeleted);
                    	 
                    }
                   
                }
                hlist.remove(index);
                onResume();
                //populateListViewFromDB(hlist);
                //list.remove(index);
      
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
    
    //sets the listview
	private void populateListViewFromDB(ArrayList<HashMap<String, Object>> mylistData) {
		
		String[] columnTags = new String[] {"food", "quan", "cal"};
		int[] columnIds = new int[] {R.id.foodTV, R.id.quanTV, R.id.calTV};
		//ArrayList<HashMap<String, Object>> mylistData = db.getAllFood();
		SimpleAdapter arrayAdapter = new SimpleAdapter(FoodEditorActivity.this, mylistData, R.layout.display_items, columnTags , columnIds);
		list.setAdapter(arrayAdapter);

	}
	@Override
	public void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}

}