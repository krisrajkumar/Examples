package com.example.examples;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AnotherActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		// Retriving the values using Bundle
		Bundle bundle = intent.getExtras();
		String value = bundle.getString("value");
		Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
		
	
	}
}
