package com.example.phms;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.Toast;

//import DialogInterface.OnClickListener;
public class DietActivity extends Activity {
	final Context context = this;
	private Button button;
	private RadioGroup selectionList;
	private RadioButton radio_button_test;
	private int num;
	private TextView displayingCalories;
	private RadioButton little;
	private RadioButton light;
	private RadioButton moderate;
	private RadioButton heavy;
	private RadioButton veryHeavy;
	private ListView foodListDisplay;
	private ListView exerciseListDisplay;
	private String text;
	private int resultClicked;
	private View promptsView;
	protected dbHelper db;
	//private TextView emptyview;
	//private ListView foodList;
	
	//private EditText result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet);
		db = new dbHelper(this);
		button = (Button) findViewById(R.id.edit_diet);
		//selectionList = (RadioGroup) findViewById(R.id.radioGroup1);
		displayingCalories = (TextView) findViewById(R.id.suggestedCalorie);

		foodListDisplay = (ListView) findViewById(R.id.food_list_diet);
		exerciseListDisplay = (ListView) findViewById(R.id.exercise_list_diet);

		button.setOnClickListener(new OnClickListener() {
		
		 
		@Override
		public void onClick(View arg0) {
		 
			// get prompts.xml view
			LayoutInflater li = LayoutInflater.from(context);
			promptsView = li.inflate(R.layout.edit_suggested_calorie, null);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			//RadioGroup r = (RadioGroup) findViewById(R.id.radioGroup1);
			//r.setOnCheckedChangeListener(DietActivity.this);
			alertDialogBuilder.setView(promptsView);
			// set dialog message
			alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
					
				    public void onClick(DialogInterface dialog,int id) {
						little = (RadioButton) promptsView.findViewById(R.id.radioButton_little_to_no);
						light = (RadioButton) promptsView.findViewById(R.id.radioButton_light);
						moderate = (RadioButton) promptsView.findViewById(R.id.radioButton_moderate);
						heavy = (RadioButton) promptsView.findViewById(R.id.radioButton_heavy);
						veryHeavy = (RadioButton) promptsView.findViewById(R.id.radioButton_very_heavy);
				    	
				    	
				    	selectionList = (RadioGroup)promptsView.findViewById(R.id.radioGroup1);
						int selectedId = selectionList.getCheckedRadioButtonId();
				    	RadioButton radioSelectionButton = (RadioButton)promptsView.findViewById(selectedId);
				    	//code where the if statements and formulas go
				    	text = radioSelectionButton.getText().toString();
				    	
				    	//resultClicked = radioSelectionButton.getId();
				    	check(text);
				    	//Toast.makeText(DietActivity.this, resultClicked.toString(), Toast.LENGTH_SHORT).show();
				    	//String test = getString(R.string.little_to_noExcersice);
				    	//Toast.makeText(DietActivity.this, light.getText().toString(), Toast.LENGTH_SHORT).show();
				    	//.if (text.equals(test))
				    		//Toast.makeText(DietActivity.this, "this works", Toast.LENGTH_SHORT).show();
				    	//code for radiobutton
				    	onResume();
				    	dialog.cancel();
				    	
				    }
				  })
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    	onResume();
				    	dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
					}
			});
		//list listener
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.display_items, FoodEditorActivity.foodBucket);
		
		//ListView list = (ListView) findViewById(R.id.food_list_diet);
		//LayoutInflater li = LayoutInflater.from(context);
		//View empty = li.inflate(R.layout.empty, null);
		foodListDisplay.setEmptyView(findViewById(R.id.empty));
		exerciseListDisplay.setEmptyView(findViewById(R.id.empty2));
		//list.setEmptyView(empty);
		//list.setAdapter(adapter);
		//populate
		populateFood();
		//populateListView(exerciseListDisplay);
		
		
	}
	//food editor activity
	
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
    
	public void display_food_editor(View view) {
		Intent intent = new Intent(this, FoodEditorActivity.class);
	    startActivity(intent);
	}
	//exercise editor activity
	public void display_exercise_editor(View view) {
		Intent intent = new Intent(this, ExerciseEditorActivity.class);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diet, menu);
		return true;
	}

	
	public void check(String checkedId) {
		// TODO Auto-generated method stub
		//String newCalorieIntake =getString(R.string.suggestedCalorie_test);
		if (checkedId.equals(little.getText().toString())){
			//newCalorieIntake = String.format(newCalorieIntake,  0);
			Toast.makeText(DietActivity.this, little.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(light.getText().toString())){
			//newCalorieIntake = String.format(newCalorieIntake,1);
			Toast.makeText(DietActivity.this, light.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(moderate.getText().toString())){
			//newCalorieIntake = String.format(newCalorieIntake,2);
			Toast.makeText(DietActivity.this, moderate.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(heavy.getText().toString())){
			//newCalorieIntake = String.format(newCalorieIntake,3);
			Toast.makeText(DietActivity.this, heavy.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(veryHeavy.getText().toString())){
			//newCalorieIntake = String.format(newCalorieIntake,4);
			Toast.makeText(DietActivity.this, veryHeavy.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		//displayingCalories.setText(newCalorieIntake);
	}
	
	private void populateFood() {
		
		String[] columnTags = new String[] {"food", "cal"};
		int[] columnIds = new int[] {R.id.vitalTV, R.id.valueTV};
		ArrayList<HashMap<String, Object>> mylistData = db.getAllFood();
		SimpleAdapter arrayAdapter = new SimpleAdapter(DietActivity.this, mylistData, R.layout.display_vital_signs, columnTags , columnIds);
		foodListDisplay.setAdapter(arrayAdapter);

	}
	
	//Designed to reload the activity if the user presses the back button from either the FoodEditor
	//activity or the ExerciseEditorActivity and then clicks either cancel or OK. So that you may see
	//the new entries in the tables
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}

}
