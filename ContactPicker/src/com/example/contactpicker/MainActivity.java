package com.example.contactpicker;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.provider.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static EditText contact = null;
	private static Button pick = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contact = (EditText)findViewById(R.id.contactName);
		pick = (Button)findViewById(R.id.chooseContact);
		
		pick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Trying to find contacts ...", Toast.LENGTH_SHORT).show();
		
				// Contacts.CONTENT_URI - got deprecated after api level 15 
				
					Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);
					startActivityForResult(contactPickerIntent, 100);
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 100){
			if(requestCode == 100){
				Bundle bundle = data.getExtras();
				Set<String> keys  = bundle.keySet();
				Iterator<String> iterator = keys.iterator();
				while(iterator.hasNext()){
					String key = iterator.next();
					Log.v("Key", key + "["+bundle.get(key)+"]");
				}
				Uri result = data.getData();
				Log.v("URI",result.toString());
				String id = result.getLastPathSegment();
				Cursor cursor = getContentResolver().query(Email.CONTENT_URI, null, Email.CONTACT_ID+"=?", new String[]{id}, null);
			
				cursor.moveToFirst();
				String columns[] = cursor.getColumnNames();
				for(String column:columns){
					int index = cursor.getColumnIndex(column);
					Log.v("Column Names", column + "= "+cursor.getString(index));
				}
				
				if(cursor.moveToFirst()){
					int emailcol = cursor.getColumnIndex(Email.DATA);
					String email = cursor.getString(emailcol);
					contact.setText(email);
					}
			}
		}
		else{
			Log.v("Error", "No output found");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
