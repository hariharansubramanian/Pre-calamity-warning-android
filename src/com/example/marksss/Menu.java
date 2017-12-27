package com.example.marksss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu  extends Activity {
	 
	 
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.menu);
			
			
			ImageButton mainButton = (ImageButton) findViewById(R.id.earthquakeButton);
			final Context context = this;
			mainButton.setOnClickListener(new OnClickListener() {
				
			 @Override
			    public void onClick(View v) {
			 Intent intent=new Intent(context,MapHighRisk.class);
			    startActivity(intent);

			 }
			});}
		
			
			
		/*	Handler handler;
			Runnable delayRunnable;
			final Context context = this;
			handler = new Handler();
			delayRunnable = new Runnable() {

			     @Override
			     public void run() {
			    // TODO Auto-generated method stub  
			    	 
					    Intent intent = new Intent(context, MapHighRisk.class);
			             startActivity(intent);
			    }
			};      
			handler.postDelayed(delayRunnable, 2000);
			
		*/
			
			
			
			
		


			
	}
	 