package funciones;

import hand.ControlApp.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class PresentationFragment extends Fragment {
	
	public PresentationFragment(){}
	String[] web={"backslider","nextslider"};
	Fragment fragment;
	boolean wifistate;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_ppt, container, false);
         
        GridView gridView = (GridView) rootView.findViewById(R.id.gridppt);
        gridView.setAdapter(new PresentationGrid(rootView.getContext())); 
        

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
      			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	
            	if(position==0){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Presentation,"+web[0]+",";
            	    	new Thread( new UdpConection()).start();
            		}if(MainActivity.Conection=="Connected"){
            			MainActivity.mCommandService.write(("xPresentation,"+web[0]+",").getBytes());
            		} 		
            		
            	}
            	if( position==1){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "Presentation,"+web[1]+",";
            	    	new Thread( new UdpConection()).start();
            		}if(MainActivity.Conection=="Connected"){
            			MainActivity.mCommandService.write(("xPresentation,"+web[1]+",").getBytes());
            		} 
            	}
            	
              // Toast.makeText(view.getContext(),web[+ position], Toast.LENGTH_SHORT).show();
            }
        });
        
        return rootView;
    }
}