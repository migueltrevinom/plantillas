package funciones;


import hand.ControlApp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {

	EditText ServerIp;
	EditText ServerPort;
	public int log=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ServerIp= (EditText) findViewById(R.id.ipText);
		ServerPort= (EditText)findViewById(R.id.puertoText);
		
		//el siguiente codigo es para poder generar el boton de back en el app icon
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //termina
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.login_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	onBackPressed();
	    	return true;
	    	
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	
	private void openSettings() {
		// TODO Auto-generated method stub
		
	}
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
            finish();

    }

	public void Send(View view)
	{
		String ip="";
		String port="";
		
		if(ServerIp.getText().toString().isEmpty() || ServerPort.getText().toString().isEmpty()){
			
			Toast.makeText(getBaseContext(), "ERRROR: you didnt enter your IP or Port",Toast.LENGTH_LONG).show();
		}else{
		try {
            File myFile = new File("/sdcard/mysdfile.txt");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = 
                                    new OutputStreamWriter(fOut);
            ip=  ServerIp.getText().toString().trim();
            port= ServerPort.getText().toString().trim();
            
            myOutWriter.append(ip+","+port+","+"1");
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getBaseContext(),
                    "Done :Conected.",
                    Toast.LENGTH_SHORT).show();
            
            //xd
            onBackPressed();
    		
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
		
	
		
		}
		}
	
	
	
	
	
	
	
	
	
	
	
}