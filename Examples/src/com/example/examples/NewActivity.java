package com.example.examples;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class NewActivity extends Activity {
	public static Button main;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        main = (Button)findViewById(R.id.main);
     
        
        Intent intent = new Intent();
        // Passing data to the Intent using putExtra )
        intent.putExtra("str", "apex-new value");
        
   
        // Passing the '5' as result code to the returning intent
        setResult(5, intent);
       
        // Retrieving store shared preference value
        SharedPreferences pref2 = this.getSharedPreferences("MyPref", 0);
		
		Log.v("New Activity", "New one");
		// Getting values 
		String data = pref2.getString("data", "No Data");
		Log.v("SharedPreferenceValue", data);
        
        finish(); 
        
        
	}
}
