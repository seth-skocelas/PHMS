package com.example.phms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MedicationActivity extends Activity {

	private ListView list;
	private ArrayList<HashMap<String, Object>> hlist;
	protected dbHelper db;
	private static int count=0;
	private int numberToBeDeleted;
	public Medicine m;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication);
		list = (ListView) findViewById(R.id.med_list);
    	list.setEmptyView(findViewById(R.id.empty));
    	//mDbHelper = new dbHelper(context);
    	db = new dbHelper(this);
    	hlist = db.getAllMedicine();
    	populateListViewFromDB(hlist);
    	
    	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){
				
				final int index = position;

				 HashMap <String,Object> h = (HashMap<String, Object>) parent.getItemAtPosition(position);
				 m = new Medicine();
				 m.setName((String)h.get("name"));
				 m.setUserName((String)h.get("user"));
				 m.setConflictions((String)h.get("con"));
				 m.setDays((String)h.get("days"));
				 m.setTimes((String)h.get("times"));
				 m.setUnit((String)h.get("unit"));
				 m.setID((String)h.get("id"));
				 m.setDosage((Integer)h.get("quant"));
				 m.setTimeCount((Integer)h.get("time_count"));
				 m.setCount((Integer)h.get("con_count"));
				 
				 

				 
				
				 /*

                 Toast toast=Toast.makeText(getApplicationContext(), 
                		 m.getName(),Toast.LENGTH_LONG );
                 toast.setGravity(Gravity.CENTER, 0, 0);
                 toast.show();
                 
                 */
				 
                 editMedication(findViewById(R.id.med_list));

                 
                 
			}

		});
    	
    	
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
		getMenuInflater().inflate(R.menu.medication, menu);
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
	 
	private void populateListViewFromDB(ArrayList<HashMap<String, Object>> mylistData) {
			
			String[] columnTags = new String[] {"name", "quant", "unit"};
			int[] columnIds = new int[] {R.id.nameTV, R.id.quantTV, R.id.unitTV};
			//ArrayList<HashMap<String, Object>> mylistData = db.getAllFood();
			SimpleAdapter arrayAdapter = new SimpleAdapter(MedicationActivity.this, mylistData, R.layout.display_med_items, columnTags , columnIds);
			list.setAdapter(arrayAdapter);

		}

	
	public void returnMenu(View view) {
		this.finish();
	}
	
	public void addMedication(View view) {
		Intent intent = new Intent(this, AddMedicationActivity.class);
	    startActivity(intent);
	    this.finish();
	    

	}
	
	public void editMedication(View view) {
		Intent intent = new Intent(this, EditMedicationActivity.class);
		intent.putExtra("myobj",m);
	    startActivity(intent);
	    this.finish();
	    

	}
	
	
    private void removeItemFromList(int position) {
        //final int deletePosition = position;
        final int index = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(
                MedicationActivity.this);
    
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
                    	 db.deleteMedicine(numberToBeDeleted);
                    	 
                    }
                   
                }
                hlist.remove(index);
                populateListViewFromDB(hlist);
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
	
	
	
	

}
