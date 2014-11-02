package funciones;



import hand.ControlApp.R;
import hand.ControlApp.R.id;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;

public class TouchActivity extends Activity implements OnGestureListener  {

	private GestureDetector gd;
	
	TextView txt;
	TextView v1;
	TextView v2;
	TextView v3;
	Button btn;
	
	int count=0;
	 int difx=0;
	    int dify;
	    int startx=0;
	    int starty=0;
	    
	    int GLOBAL_TOUCH_POSITION_X = 0;
	    int GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
	   
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);
	
		txt= (TextView) findViewById(R.id.hello);
		v1= (TextView) findViewById(R.id.titulo);
		v2 =(TextView) findViewById(R.id.portTxt);
		v3 = (TextView) findViewById(R.id.html14);	
		 //initialize the Gesture Detector
        gd = new GestureDetector(this);
        
        
      //el siguiente codigo es para poder generar el boton de back en el app icon
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
		
        //termina
	
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		UdpConection.ServerIP= sharedPrefs.getString("prefIpAddress", "0.0.0.0").toString().trim();
				UdpConection.Port =Integer.parseInt(sharedPrefs.getString("prefPortAddress", "0").toString());
		
	
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.touch, menu);
		return true;
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
            finish();

    }
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	   switch (item.getItemId()) 
	   {
	   case android.R.id.home:
		   onBackPressed();
		   MainActivity.mCommandService.write("XExit functionTouch.class".getBytes());
		   return true;
	   
	  
		   
		   default:
			   return super.onOptionsItemSelected(item);
	   }
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    int x = (int)event.getX();
	    int y = (int)event.getY();
	
	    int pointerCount = event.getPointerCount();
	 
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	startx= (int)event.getX();
	        	starty= (int) event.getY();
	        	txt.setText(startx+","+starty);  
	        	
	        case MotionEvent.ACTION_MOVE:
	        	if(x>startx){
	        		difx=x-startx;
	        	}
	        	if(y>starty){
	        		dify=y-starty;
	        	}
	        	if(x<startx){
	        		difx=(startx-x)*-1;
	        	}
	        	if(y<starty){
	        		dify= (starty-y)*-1;
	        	}

	    	    if(pointerCount == 2){
	    	    	if(y>starty){
	    	    		v1.setText("f,ScrollDown");
	    	    	}else{
	    	    		v1.setText("f,ScrollUp");
	    	    	}	    	    }else{
	    	    	v1.setText("0,"+difx+","+dify+",");
	    	    	
	    	    }
	    	    if(MainActivity.Conection=="Disconnected"){
	    	    UdpConection.txt3=(String) v1.getText();
	    	    new Thread(new UdpConection()).start();}
	    	    				if(MainActivity.Conection=="Connected"){
	    	    					MainActivity.mCommandService.write(("0"+v1.getText()).toString().getBytes());
	    	    				}
	       
	    	    				
	    	    				
	    	    case MotionEvent.ACTION_UP:  	
	    }   
	return gd.onTouchEvent(event);
	}
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		 if(MainActivity.Conection=="Disconnected"){
		UdpConection.txt3="f,Right-Click,";
		new Thread(new UdpConection()).start();}
		 				if(MainActivity.Conection=="Connected")
		 				{
		 					MainActivity.mCommandService.write("ff,Right-Click,".getBytes());
		 				}
		}
	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub		
		return false;
	}
	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if(MainActivity.Conection=="Disconnected"){
		UdpConection.txt3="f,Left-Click,";
		new Thread(new UdpConection()).start();
		
		}if(MainActivity.Conection=="Connected")
			{
				MainActivity.mCommandService.write("ff,Left-Click,".getBytes());
			}
		return false;
		
	}
	
}
