package com.example.phms;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

//import DialogInterface.OnClickListener;
public class DietActivity extends Activity {
	final Context context = this;
	private Button button;
	private RadioGroup selectionList;
	private RadioButton radio_button_test;
	private int num;
	private TextView displayingCalories;
	private TextView todayCalories;
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
	//private TextView suggestedCalorieDisplay;
	protected dbHelper db;
	ArrayList<String> constants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet);
		db = new dbHelper(this);
		//using this with test user
		displayingCalories = (TextView) findViewById(R.id.suggestedCalorie);
		constants = db.getFConstants("jesus");
		String suggestedView = getString(R.string.suggestedCalorie_test);
		//Toast.makeText(DietActivity.this, c, Toast.LENGTH_SHORT).show();
		
		if (!constants.get(0).equals("s")){
			suggestedView = String.format(suggestedView, Double.parseDouble(constants.get(0)));
			displayingCalories.setText(suggestedView);
			
		}
		
		todayCalories = (TextView) findViewById(R.id.todaysCalorie);
		String displayingToday = getString(R.string.today_calories);
		double calToday=0;
		if (!constants.get(2).equals("i") && !constants.get(3).equals("b")){
			calToday = Double.parseDouble(constants.get(2)) - Double.parseDouble(constants.get(3));
		}
		else if (!constants.get(2).equals("i") && constants.get(3).equals("b")){
			calToday = Double.parseDouble(constants.get(2));
		}
		else if(constants.get(2).equals("i") && !constants.get(3).equals("b")){
			calToday = 0-Double.parseDouble(constants.get(3));
		}
		displayingToday = String.format(displayingToday, calToday);
		todayCalories.setText(displayingToday);
		
		
		
		
		button = (Button) findViewById(R.id.edit_diet);
		
		

		foodListDisplay = (ListView) findViewById(R.id.food_list_diet);
		exerciseListDisplay = (ListView) findViewById(R.id.exercise_list_diet);

		button.setOnClickListener(new OnClickListener() {
		
		 
		@Override
		public void onClick(View arg0) {
		 
			// get prompts.xml view
			LayoutInflater li = LayoutInflater.from(context);
			promptsView = li.inflate(R.layout.edit_suggested_calorie, null);
			little = (RadioButton) promptsView.findViewById(R.id.radioButton_little_to_no);
			light = (RadioButton) promptsView.findViewById(R.id.radioButton_light);
			moderate = (RadioButton) promptsView.findViewById(R.id.radioButton_moderate);
			heavy = (RadioButton) promptsView.findViewById(R.id.radioButton_heavy);
			veryHeavy = (RadioButton) promptsView.findViewById(R.id.radioButton_very_heavy);
			TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
	    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
	    	if (!constants.get(0).equals("s")){
	    		suggestedViewPrompt = String.format(suggestedViewPrompt, Double.parseDouble(constants.get(0)));
	    		textDis.setText(suggestedViewPrompt);
				
			}

			little.setOnClickListener(	new OnClickListener (){
				 public void onClick(View v) {
					 	//settingSuggestedCalorie(1.2);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, workOutValue(1.2));
				    		textDis.setText(suggestedViewPrompt);
					 }
					});
			light.setOnClickListener(	new OnClickListener (){
				 public void onClick(View v) {
					 	//settingSuggestedCalorie(1.2);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, workOutValue(1.375));
				    		textDis.setText(suggestedViewPrompt);
					 }
					});
			moderate.setOnClickListener(	new OnClickListener (){
				 public void onClick(View v) {
					 	//settingSuggestedCalorie(1.2);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, workOutValue(1.55));
				    		textDis.setText(suggestedViewPrompt);
					 }
					});
			heavy.setOnClickListener(	new OnClickListener (){
				 public void onClick(View v) {
					 	//settingSuggestedCalorie(1.2);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, workOutValue(1.725));
				    		textDis.setText(suggestedViewPrompt);
					 }
					});
			veryHeavy.setOnClickListener(	new OnClickListener (){
				 public void onClick(View v) {
					 	//settingSuggestedCalorie(1.2);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, workOutValue(1.9));
				    		textDis.setText(suggestedViewPrompt);
					 }
					});
			

			
			
			
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

						
					
				   
						
						
						
				    	selectionList = (RadioGroup)promptsView.findViewById(R.id.radioGroup1);
				    	int selectedId = selectionList.getCheckedRadioButtonId();
				    	RadioButton radioSelectionButton = (RadioButton)promptsView.findViewById(selectedId);
				    	//code where the if statements and formulas go
				    	text = radioSelectionButton.getText().toString();
				    	onResume();
				    	check(text);
				    	TextView textDis = (TextView) promptsView.findViewById(R.id.textDisplay);
				    	String suggestedViewPrompt = getString(R.string.suggestedCalorie_test);
				    	if (!constants.get(0).equals("s")){
				    		suggestedViewPrompt = String.format(suggestedViewPrompt, Double.parseDouble(constants.get(0)));
				    		textDis.setText(suggestedViewPrompt);
							
						}
				    	//onResume();
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

		foodListDisplay.setEmptyView(findViewById(R.id.empty));
		exerciseListDisplay.setEmptyView(findViewById(R.id.empty2));

		populateFood();
		populateExercise();
		
		
		//adding code for when you click an element of the food list
		foodListDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){

				 String str = parent.getItemAtPosition(position).toString();
				 String delims = "[,=}]";
				 String[] tokens = str.split(delims);
				 String settingFood = getString(R.string.display_food);
				 String settingQuan = getString(R.string.display_quantity);
				 String settingCal = getString(R.string.display_calories);
				 settingQuan = String.format(settingQuan, tokens[3]);
				 settingFood = String.format(settingFood, tokens[5]);
				 settingCal = String.format(settingCal, tokens[7]);

                 Toast toast=Toast.makeText(getApplicationContext(), 
                		 settingFood + "\n" 
 						+ settingQuan + "\n"
 						+ settingCal,Toast.LENGTH_LONG );
                 toast.setGravity(Gravity.CENTER, 0, 0);
                 toast.show();
			}

		});
		
		exerciseListDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){

				 String str = parent.getItemAtPosition(position).toString();
				 String delims = "[,=}]";
				 String[] tokens = str.split(delims);
				 String settingEx = getString(R.string.display_exercise);
				 String settingBurn = getString(R.string.display_burned);
				 settingEx = String.format(settingEx, tokens[5]);
				 settingBurn = String.format(settingBurn, tokens[3]);
				 //Toast.makeText(DietActivity.this, str, Toast.LENGTH_SHORT).show();
                 Toast toast=Toast.makeText(getApplicationContext(), 
                		 settingEx + "\n" 
 						+ settingBurn,Toast.LENGTH_LONG );
                 toast.setGravity(Gravity.CENTER, 0, 0);
                 toast.show();
			}

		});
		
		
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
    	case R.id.action_exerciseEditorActivity:
    		Intent intent3 = new Intent(this, ExerciseEditorActivity.class);
    	    startActivity(intent3);
    	    break;
    	case R.id.action_foodEditorActivity:
    		Intent intent4 = new Intent(this, FoodEditorActivity.class);
    	    startActivity(intent4);
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
			settingSuggestedCalorie(1.2);
			//Toast.makeText(DietActivity.this, little.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(light.getText().toString())){
			settingSuggestedCalorie(1.375);
			//Toast.makeText(DietActivity.this, light.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(moderate.getText().toString())){
			settingSuggestedCalorie(1.55);
			//Toast.makeText(DietActivity.this, moderate.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(heavy.getText().toString())){
			settingSuggestedCalorie(1.725);
			//Toast.makeText(DietActivity.this, heavy.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		else if (checkedId.equals(veryHeavy.getText().toString())){
			settingSuggestedCalorie(1.9);
			//Toast.makeText(DietActivity.this, veryHeavy.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		//displayingCalories.setText(newCalorieIntake);
	}
	
	private void settingSuggestedCalorie(double constant){
		double BMR=0;
		double tIntake =0;
		Users user = db.getUserFoodDetails("jesus");
		if ("M".equals(user.getGender()))
		{
			BMR = 88.362
				   + (13.397 * (user.getWeight()*0.453592))
				   + (4.799 * (user.getHeightFeet()*12+user.getHeightInches())*2.54)
				   + (5.677 * user.getAge());
		tIntake = BMR * constant;
		db.updateFConstatnts("jesus", tIntake+"", "t", "i", "b");
		} 
		if ("F".equals(user.getGender()))
		{
			BMR = 447.593
				   + (9.247 * (user.getWeight()*0.453592))
				   + (3.098 * (user.getHeightFeet()*12+user.getHeightInches())*2.54)
				   + (4.330 * user.getAge());
		tIntake = BMR * constant;
		db.updateFConstatnts("jesus", tIntake+"", "t", "i", "b");
		} 
		
		onResume();
		
	}
	
	
	private double workOutValue(double constant){
		double BMR=0;
		double tIntake =0;
		Users user = db.getUserFoodDetails("jesus");
		if ("M".equals(user.getGender()))
		{
			BMR = 88.362
				   + (13.397 * (user.getWeight()*0.453592))
				   + (4.799 * (user.getHeightFeet()*12+user.getHeightInches())*2.54)
				   + (5.677 * user.getAge());
		tIntake = BMR * constant;
		} 
		if ("F".equals(user.getGender()))
		{
			BMR = 447.593
				   + (9.247 * (user.getWeight()*0.453592))
				   + (3.098 * (user.getHeightFeet()*12+user.getHeightInches())*2.54)
				   + (4.330 * user.getAge());
		tIntake = BMR * constant;
		} 
		return tIntake;
		
	}
	private void populateFood() {
		
		String[] columnTags = new String[] {"food", "cal"};
		int[] columnIds = new int[] {R.id.name_viewTV, R.id.cal_viewTV};
		ArrayList<HashMap<String, Object>> mylistData = db.getAllFood();
		SimpleAdapter arrayAdapter = new SimpleAdapter(DietActivity.this, mylistData, R.layout.diet_food_and_exercise_list_display, columnTags , columnIds);
		foodListDisplay.setAdapter(arrayAdapter);

	}
	
	private void populateExercise() {
		
		String[] columnTags = new String[] {"exercise", "burned"};
		int[] columnIds = new int[] {R.id.name_viewTV, R.id.cal_viewTV};
		ArrayList<HashMap<String, Object>> mylistData = db.getAllEx();
		SimpleAdapter arrayAdapter = new SimpleAdapter(DietActivity.this, mylistData, R.layout.diet_food_and_exercise_list_display, columnTags , columnIds);
		
		exerciseListDisplay.setAdapter(arrayAdapter);

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
