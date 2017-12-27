package com.example.marksss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		addListenerOnButton();
	}

 
	public void addListenerOnButton() {
 
		final Context context = this;
		OnClickListener click= new OnClickListener() {

			public void onClick(View arg0) {
				 
			    Intent intent = new Intent(context, Menu.class);
                            startActivity(intent);   
 
			}
		};
 
		Button button = (Button) findViewById(R.id.btn_login);
		button.setOnClickListener(click);
			}
		
}
	
 
