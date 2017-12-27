package com.example.marksss;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Landing extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing);
		Handler handler;
		Runnable delayRunnable;
		final Context context = this;
		handler = new Handler();
		delayRunnable = new Runnable() {

		     @Override
		     public void run() {
		    // TODO Auto-generated method stub  
		    	 
				    Intent intent = new Intent(context, Login.class);
		             startActivity(intent);
		    }
		};      
		handler.postDelayed(delayRunnable, 3000);
	}


}