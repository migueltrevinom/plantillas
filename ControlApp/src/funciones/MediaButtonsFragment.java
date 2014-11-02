package funciones;



import hand.ControlApp.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MediaButtonsFragment extends Fragment implements KeyListener{
	
	public MediaButtonsFragment(){}
	String[] web={"backward","previous","stop", "play-pause", "forward","next","volumedown","mute","volumeup"};
	boolean wifistate;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_mediabuttons, container, false);
         
        GridView gridView = (GridView) rootView.findViewById(R.id.gridmediabuttons);
        gridView.setAdapter(new mediaButtonsGrid(rootView.getContext())); 

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
      			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	
            	if(position==0){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[0]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[0]+",").getBytes());
            		}
            	}
            	if( position==1){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[1]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[1]+",").getBytes());
            		}
            		
            	}
            	if (position==2){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[2]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[2]+",").getBytes());
            		}
            		
            	}
            	if (position==3){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[3]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[3]+",").getBytes());
            		}
            	}
            	if (position==4){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[4]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[4]+",").getBytes());
            		}
            	}
            	if (position==5){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[5]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[5]+",").getBytes());
            		}
            	}
            	if (position==6){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[6]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[6]+",").getBytes());
            		}
            	}
            	if (position==7){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[7]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[7]+",").getBytes());
            		}
            	}
            	if (position==8){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Media,"+web[8]+",";
            	    	new Thread( new UdpConection()).start();
            	    	UdpConection.txt3="";
            		}if(MainActivity.Conection=="Connected"){
            		MainActivity.mCommandService.write(("XMedia,"+web[8]+",").getBytes());
            		}
            	}
            	
               // Toast.makeText(view.getContext(),web[+ position], Toast.LENGTH_SHORT).show();
            }
        });
        
        return rootView;
    }

	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean onKeyDown(View view, Editable text, int keyCode,
			KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == event.KEYCODE_VOLUME_UP) {
			MainActivity.mCommandService.write("volumeup".getBytes());
			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			MainActivity.mCommandService.write("volumedown".getBytes());
			return true;
		}
		return false;
	}
	@Override
	public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onKeyOther(View view, Editable text, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clearMetaKeyState(View view, Editable content, int states) {
		// TODO Auto-generated method stub
		
	}
	
	
}
