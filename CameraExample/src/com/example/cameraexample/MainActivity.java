package com.example.cameraexample;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int CAMERA_REQUEST = 10, GALLERY_REQUEST = 11, CONTACTS_REQUEST = 12;
	private static ImageView capture = null;
	private static Button take = null,gallery = null,contact = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		capture = (ImageView)findViewById(R.id.photo);
		take = (Button)findViewById(R.id.camera);
		gallery = (Button)findViewById(R.id.gallery);
		contact = (Button)findViewById(R.id.contact);
		
		take.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}
		});
		
		gallery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(galleryIntent, GALLERY_REQUEST);
			}
		});
		contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent contactIntent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);
				startActivityForResult(contactIntent, CONTACTS_REQUEST);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		String phone="";
	    Cursor contacts=null;

		
		if(requestCode  == CAMERA_REQUEST){
			if(resultCode == Activity.RESULT_OK){
				Bitmap photo = (Bitmap)data.getExtras().get("data");
				capture.setImageBitmap(photo);
			}
			else{
				Toast.makeText(getApplicationContext(), "Camera Cancelled", Toast.LENGTH_SHORT).show();
			}
		}
		else if(requestCode == GALLERY_REQUEST){
			if(resultCode == Activity.RESULT_OK){
				Uri galleryUri = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(galleryUri, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				capture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			}
			else{
				Toast.makeText(getApplicationContext(), "Operation Cancelled", Toast.LENGTH_SHORT).show();
			}
		}
		else if(requestCode == CONTACTS_REQUEST){
			if(resultCode == Activity.RESULT_OK){
				Uri result = data.getData();
	            
	             String id = result.getLastPathSegment(); 
	                 
	             contacts=getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id },  null);
	                //gets index of phone no
	             int phoneIdx = contacts.getColumnIndex(Phone.DATA); 
	             if (contacts.moveToFirst()) 
	             {
	                   //gets the phone no  
	                   phone = contacts.getString(phoneIdx);  
	                   Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
	                } 
	             else 
	             {  
	             Toast.makeText(this, "error", 100).show();
	                }  

			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
