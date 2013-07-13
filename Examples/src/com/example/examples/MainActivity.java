package com.example.examples;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static Button start,implicit,bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button)findViewById(R.id.start);
		implicit = (Button)findViewById(R.id.implicit);
		bundle = (Button)findViewById(R.id.bundle);
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Example for startActivityForResult
				// If we need to get any result from another activity we will be calling startActivityForResult and we have to pass the request code 
				
				startActivityForResult(new Intent(MainActivity.this,NewActivity.class), 0);
		//		startActivityForResult(new Intent(MainActivity.this,NewActivity.class), 1);
		//		startActivityForResult(new Intent(MainActivity.this,NewActivity.class), 2);
		//		startActivityForResult(new Intent(MainActivity.this,NewActivity.class), 3);
			}
		});
		
		implicit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Example for calling implicit intent (Built Android Apps Intent)
				Toast.makeText(getApplicationContext(), "Calling implicit intent", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com")));
				
			}
		});
		
		// SharedPreferences are used for Passing information across Activities within Application
		// Initializing shared preference with "MyPref" string
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		Editor editor = pref.edit();
		Log.v("Assigning Values", "Values");
		// key value pair style 
		editor.putString("data", "APEX DATA");
		editor.commit();
		
		// Check the NewActivity where shared preferences are retrived  
		
		
		
		bundle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,AnotherActivity.class);
				// Bundles are used to pass bulk data/object/Activity state between activity 
				Bundle bundle = new Bundle();
				bundle.putString("value", "New-value using Bundles");
				
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	// StartActivityForResult data's are received on onActivityResult
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		// requestCode - passed during calling intent
		// resultCode - passed while returning the intent  
		if(requestCode == 0){
			if(resultCode == 5){
				Toast.makeText(getApplicationContext(), "Back : "+intent.getStringExtra("str"), Toast.LENGTH_SHORT).show();
			}
		}
	}

}
