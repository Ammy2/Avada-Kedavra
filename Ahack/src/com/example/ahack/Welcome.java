package com.example.ahack;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;





public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wel_screen);
		MediaPlayer mp = MediaPlayer.create(Welcome.this, R.raw.music);             
        mp.start();
		Button start = (Button) findViewById(R.id.button2);
		Button story = (Button) findViewById(R.id.ready);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Welcome.this, Toss.class);
				startActivity(intent);
			}
		});
		
		story.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent st = new Intent(Welcome.this,Story.class);
				startActivity(st);
			}
		});
		
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
		Intent intent = new Intent(Welcome.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("Exit me", true);
		startActivity(intent);
		finish();
		
	}

}
