package funciones;

import hand.ControlApp.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SystemFragment extends Fragment {
	
	public SystemFragment(){}
	String[] web={"shutdown","restart","lockscreen", "sleep"};
	boolean wifistate;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_system, container, false);
         
        GridView gridView = (GridView) rootView.findViewById(R.id.systemgridview);
        gridView.setAdapter(new systemGrid(rootView.getContext())); 
        
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
      			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	
            	if(position==0){if(MainActivity.Conection=="Disconnected"  && wifistate==true){
        			UdpConection.txt3= "System,"+web[0]+",";
        	    	new Thread( new UdpConection()).start();
        	    	UdpConection.txt3="";
        		}if(MainActivity.Conection=="Connected"){
        		MainActivity.mCommandService.write(("XSystem,"+web[0]+",").getBytes());
        		}
            		
            	}
            	if( position==1){
            		MainActivity.mCommandService.write(("XSystem,"+web[1]+",").getBytes());
            		
            	}
            	if (position==2){
            		MainActivity.mCommandService.write(("XSystem,"+web[2]+",").getBytes());
            		
            	}
            	if (position==3){
            		MainActivity.mCommandService.write(("XSystem,"+web[3]+",").getBytes());
            	}
                Toast.makeText(view.getContext(),web[+ position], Toast.LENGTH_SHORT).show();
            }
        });
        
        return rootView;
    }
}
