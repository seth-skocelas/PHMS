package com.example.phms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class ConflictionActivity extends Activity {
	
	public ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confliction_object);
		list = (ListView) findViewById(R.id.con_list);
		list.setEmptyView(findViewById(R.id.empty));
		Bundle b=this.getIntent().getExtras();
		String[] conflictions = b.getStringArray("key");
		ArrayAdapter<String> itemsAdapter = 
			    new ArrayAdapter<String>(ConflictionActivity.this, android.R.layout.simple_list_item_1, conflictions);
		list.setAdapter(itemsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confliction, menu);
		return true;
	}

}
