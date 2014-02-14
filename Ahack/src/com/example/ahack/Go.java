package com.example.ahack;


import java.util.Timer;
import java.util.TimerTask;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class Go extends Activity implements SensorEventListener {
	private int flag_s=0;
    TextView textView;
    StringBuilder builder = new StringBuilder();

    float [] history = new float[2];
    String [] direction = {"NONE","NONE"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        if( getIntent().getBooleanExtra("Exit me", false)){
		       finish();
		       return; // add this to prevent from doing unnecessary stuffs
		   }
		
        Parse.initialize(this, "U4BKQuWySdrU43GCKol9fHGKjc45mdFvR5q9gr1w", "6ERMmM5jnIxFhQFciDoXZDzr3VvYV11F11SUdoLR");
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];

        history[0] = event.values[0];
        history[1] = event.values[1];

        if (xChange > 2){
          direction[0] = "LEFT";
        }
        else if (xChange < -2){
          direction[0] = "RIGHT";
        }

        if (yChange > 2){
          direction[1] = "DOWN";
        }
        else if (yChange < -2){
          direction[1] = "UP";
        }

        builder.setLength(0);
        builder.append("x: ");
        builder.append(direction[0]);
        builder.append(" y: ");
        builder.append(direction[1]);
       
    //    ParseObject testObject = new ParseObject("HarryPotter");
     //   testObject.put("Wand",builder.toString() );
     //   testObject.put("By", "1");
     //   testObject.saveInBackground();
        
    //    Intent intent = new Intent(Go.this,Anim.class);
     //   startActivity(intent);
        textView.setText(builder.toString());
       
     //   Timer timer = new Timer();
	//	timer.schedule(new TimerTask() {

	//	   public void run() {

        if( builder.toString().contains("NONE"))
        {
        	
        }
        else
        {
        //	 ParseObject testObject = new ParseObject("HarryPotter");
         //     testObject.put("Wand",builder.toString() );
        //       testObject.put("By", "1");
       //        testObject.saveInBackground();
			   Intent intent = new Intent(Go.this,Anim.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			   intent.putExtra("sense",builder.toString() );
			   startActivity(intent);
			   finish();
			   
        }	   

		//   }

//		}, 1000);
        
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing to do here
    }
}