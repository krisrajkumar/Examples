package com.example.fragmentsexample;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MyFragments extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View myFragmentView = inflater.inflate(R.layout.fragmentlayout, container,false);
		return myFragmentView;

	}
}
