package com.example.ahack;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.music);             
        mp.start();
	
		if( getIntent().getBooleanExtra("Exit me", false)){
		       finish();
		       return; // add this to prevent from doing unnecessary stuffs
		   }
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		   public void run() {

		      //here you can start your Activity B.
			   Intent intent= new Intent(MainActivity.this, Welcome.class);
	            startActivity(intent); 

		   }

		}, 3000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}