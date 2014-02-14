package com.example.ahack;



import java.util.Timer;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.TimerTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Anim extends Activity {
	
protected ProgressDialog proDialog;
protected ProgressDialog proDialog2;
	
private String s;
private String s2;


private String spell1;
private String spell2;
	
	protected void startLoading() {
	    proDialog = new ProgressDialog(this);
	    proDialog.setMessage("loading...");
	    proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    proDialog.setCancelable(false);
	    proDialog.show();
	}

	protected void stopLoading() {
	    proDialog.dismiss();
	    proDialog = null;
	}
	
	
	protected void startLoading2() {
	    proDialog2 = new ProgressDialog(this);
	    proDialog2.setMessage("loading...");
	    proDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    proDialog2.setCancelable(false);
	    proDialog2.show();
	}

	protected void stopLoading2() {
	    proDialog2.dismiss();
	    proDialog2 = null;
	}
	
	private int winner=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim);
		Parse.initialize(this, "U4BKQuWySdrU43GCKol9fHGKjc45mdFvR5q9gr1w", "6ERMmM5jnIxFhQFciDoXZDzr3VvYV11F11SUdoLR");
		
		String str= getIntent().getStringExtra("sense");
		Log.d("score",str);
		ParseObject testObject = new ParseObject("HarryPotter");
	   testObject.put("Wand",str );
	    testObject.put("By", "2");
	    startLoading();
	    testObject.saveInBackground();
	    stopLoading();
		final Button bt = (Button) findViewById(R.id.wait);
		bt.setOnClickListener(new OnClickListener() {
			
		
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//here you can start your Activity B.
				  RelativeLayout r= (RelativeLayout) findViewById(R.id.rellayout);
				  r.setBackgroundResource(R.drawable.f);
				  
				  
				  ParseQuery<ParseObject> query = ParseQuery.getQuery("HarryPotter");
				  query.whereEqualTo("By","1");
	//			  query.orderByDescending("CreatedAt");
				  startLoading();
				  query.getFirstInBackground(new GetCallback<ParseObject>() {
					
					@Override
					public void done(ParseObject object, ParseException e) {
						// TODO Auto-generated method stub
						 if (object == null) {
							 stopLoading();
						      Log.d("score", "The getFirst request failed.");
						    } else {
						    	stopLoading();
						      Log.d("score", "Retrieved the object 1.");
						      s = object.getString("Wand");
						      Log.d("score",s);
						    }
					}
				});
			  
				  
				  ParseQuery<ParseObject> q = ParseQuery.getQuery("HarryPotter");
				  q.whereEqualTo("By","2");
//				  q.orderByDescending("CreatedAt");
				  
				  startLoading2();
				  q.getFirstInBackground(new GetCallback<ParseObject>() {
					
					@Override
					public void done(ParseObject obj, ParseException e) {
						// TODO Auto-generated method stub
						 if (obj == null) {
							 stopLoading2();
						      Log.d("score", "The getFirst request failed.");
						    } else {
						    	stopLoading2();
						      Log.d("score", "Retrieved the object 2."+ obj.getString("Wand"));
						      s2 =  obj.getString("Wand");
						      Log.d("score",s2);
						    }
					}
				});
				  
				  TextView t1 = (TextView) findViewById(R.id.str1);
				  TextView t2 = (TextView) findViewById(R.id.str2);
		
				  t1.setText(s);
				  t2.setText(s2);
				  
			
				 // bt.setText("Result");
			}
		});
	
		
		
		
		
		
		
		
		Button bt2 = (Button) findViewById(R.id.resu);
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				  
				TextView tvi1 = (TextView) findViewById(R.id.str1);
				TextView tvi2 = (TextView) findViewById(R.id.str2);
				
				
				
				
				  String st1,st2;
				  int p1=0,p2=0;
				  st1 = (String) tvi1.getText();
				  st2 = tvi2.getText().toString();
				  Log.d("score",st1+" jj "+st2);
				  if (st1.equalsIgnoreCase("x: RIGHT y: UP") )
				  {
					  spell1="Sectumsempra !";
					  Log.d("score","whats up");
					  p1=1;
				  }
				  else if (st1.equalsIgnoreCase("x: LEFT y: UP"))
				  {
					  spell1="Incendium !";
					  p1=2;
				  }
				  else if (st1.equalsIgnoreCase("x: LEFT y: DOWN"))
				  {
					  spell1="Protego !";
					  p1=3;
				  }
				  else if (st1.equalsIgnoreCase("x: RIGHT y: DOWN"))
				  {
					  spell1="avada kedavra  !";
					  p2=4;
				  }
				  if (st2.equalsIgnoreCase("x: RIGHT y: UP") )
				  {
					  spell2="Sectumsempra !";
					  p2=1;
				  }
				  else if (st2.equalsIgnoreCase("x: LEFT y: UP"))
				  {
					  spell2="Incendium !";
					  p2=2;
				  }
				  else if (st2.equalsIgnoreCase("x: LEFT y: DOWN"))
				  {
					  spell2="Protego !";
					  p2=3;
				  }
				  else if (st2.equalsIgnoreCase("x: RIGHT y: DOWN"))
				  {
					  spell2="avada kedavra  !";
					  p2=4;
				  }
				  winner = res_spell(p1,p2);
				  tvi1.setText(spell1);
				  tvi2.setText(spell2);
				Log.d("score", p1+" "+p2);
				
				
				
				RelativeLayout r= (RelativeLayout) findViewById(R.id.rellayout);
				TextView tv = (TextView) findViewById(R.id.winner_s);
				if( winner == 1)
				{
					tv.setText("You Win !");
					r.setBackgroundResource(R.drawable.z2);
				}
				else
				{
					tv.setText("The tables had turned.. Harry, Lord Voldemolt Wins !!");
					r.setBackgroundResource(R.drawable.z);
				}
				
				
				
			}
		});

/*	if(bt.getText().toString().equalsIgnoreCase("Result"))
	{
		bt.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				RelativeLayout r= (RelativeLayout) findViewById(R.id.rellayout);
				TextView tv = (TextView) findViewById(R.id.winner_s);
				if( winner == 1)
				{
					tv.setText("You Win !");
					r.setBackgroundResource(R.drawable.z2);
				}
				else
				{
					tv.setText("The tables had turned.. Harry, Lord Voldemolt Wins !!");
					r.setBackgroundResource(R.drawable.z);
				}
				
				
				
			}
		});
		
	}
*/
		   
				
			
	}
	public int res_spell( int n1, int n2)
	{
		if((n1%2!=0 && n2 %2==0) || (n2%2!=0 && n1%2==0) )
		{
			return 2;
		}
		else 
			return 1;
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
		//Intent intent = new Intent(Anim.this, Go.class);
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	//	intent.putExtra("Exit me", true);
	//	startActivity(intent);
	//	finish();
		int pid = android.os.Process.myPid();
	    android.os.Process.killProcess(pid);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}