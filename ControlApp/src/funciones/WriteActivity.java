package funciones;
/*Editor editor = sharedPrefs.edit();
editor.putString("prefIpAddress", "hola mundo" );
	editor.commit();*/
import hand.ControlApp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivity extends Activity {
private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
private Button btnVoice; 
private EditText txtSearch;

boolean wifistate;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        txtSearch= (EditText)findViewById(R.id.txtmouse);
        inicializarReconocimiento(); //voz
        //el siguiente codigo es para poder generar el boton de back en el app icon
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //termina
       
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		UdpConection.ServerIP= sharedPrefs.getString("prefIpAddress", "0.0.0.0").toString().trim();
				UdpConection.Port =Integer.parseInt(sharedPrefs.getString("prefPortAddress", "0").toString());
			/*Toast.makeText(getBaseContext(),
					"IP:"+array[0]+" Port:"+array[1],
					Toast.LENGTH_SHORT).show();*/
			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
			

      }
        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
         
        }
    	private void inicializarReconocimiento(){
    		btnVoice = (Button) findViewById(R.id.TalkBtn);
    		
    		PackageManager pm = getPackageManager();        
    		List<ResolveInfo> activities = pm.queryIntentActivities(new                    Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);           if (activities.size() != 0)
    		{                btnVoice.setOnClickListener(new OnClickListener()
    		{                  public void onClick(View v) {            
    			startVoiceRecognitionActivity();           
    			}

			        });           } 

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
    		   MainActivity.mCommandService.write("XExit functionWrite.class".getBytes());
    		   return true;
    	   case R.id.action_accept:
    		   funcion(null);
    		   return true;
    	   case R.id.action_micro:
    		   startVoiceRecognitionActivity();
    		   return true;
    	   case R.id.action_enter:
    		   enterKey(null);
    		   return true;
    	   case R.id.action_backspace:
    		   backSpace(null);
    		   return true; 	
    	
    		   
    	   case R.id.scan:
               // Launch the DeviceListActivity to see devices and do scan
           //	Intent serverIntent = new Intent(this, DeviceListActivity.class);
          //     startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
               return true;
    		   
    		   		   
    		   default:
    			   return super.onOptionsItemSelected(item);
    	   }
    	}
    	
    	private void startVoiceRecognitionActivity() {
			// TODO Auto-generated method stub
    		  Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);     
    		  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,                 RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);    
    		  intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "");   
    		  startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE); 
		}
    	
    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {         
    		 if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {           
    		  ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);                        
    		  final CharSequence[] items = new CharSequence[matches.size()];    
    		  for(int i = 0; i < matches.size(); i++){     
    		    items[i] = matches.get(i);    
    		  }                    
    		  AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		  //builder.setTitle(items[item].toString());     
    		  builder.setItems(items, new DialogInterface.OnClickListener() {
    		    public void onClick(DialogInterface dialog, int item) {
    		     txtSearch.setText(items[item].toString());              
    		     }
    		  });
    		         
    		  AlertDialog alert = builder.create();          
    		  alert.show();                      
    		 }

    		 super.onActivityResult(requestCode, resultCode, data);     
    		}
    	
    	public void funcion(View view){	
    		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
    	UdpConection.txt3= "Writexel'ha,"+txtSearch.getText().toString();
    	new Thread( new UdpConection()).start();
    		}
    		if(MainActivity.Conection=="Connected"){
    			txtSearch.setText("xWritexel'ha,"+txtSearch.getText());
    	MainActivity.mCommandService.write(txtSearch.getText().toString().getBytes());
    		}
    	txtSearch.setText("");
    	UdpConection.txt3="";
	
    		}
    	public void backSpace(View view){
    		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
    		UdpConection.txt3="Writexel'ha,Command2-Back";
    		new Thread( new UdpConection()).start();
        	txtSearch.setText("");
        	UdpConection.txt3="";}
    								if(MainActivity.Conection=="Connected"){
    										MainActivity.mCommandService.write("XWritexel'ha,Command2-Back".getBytes());
    									}
    		
        	
    	}
    	public void enterKey(View view){
    		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
    		UdpConection.txt3="Writexel'ha,Command1-Enter";
    		new Thread( new UdpConection()).start();
        	txtSearch.setText("");
        	UdpConection.txt3="";}
    				if(MainActivity.Conection=="Connected"){
    						MainActivity.mCommandService.write("XWritexel'ha,Command1-Enter".getBytes());
    					}		
    	}
    
	
		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.write_actions, menu);
	   
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	
    	}

