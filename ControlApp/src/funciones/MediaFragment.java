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

public class MediaFragment extends Fragment {
	
	public MediaFragment(){}
	String[] web={"WindowsMediaPlayer","VlcPlayer"};
	Fragment fragment;
	boolean wifistate;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_media, container, false);
         
        GridView gridView = (GridView) rootView.findViewById(R.id.gridmediaa);
        gridView.setAdapter(new mediaGrid(rootView.getContext())); 
        

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
      			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	
            	if(position==0){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "FuncionMediaPlayer.class,";
            	    	new Thread( new UdpConection()).start();
            		}if(MainActivity.Conection=="Connected"){
            			MainActivity.mCommandService.write("xFuncionMediaPlayer.class,".getBytes());
            		}
            		
            		fragment= new MediaButtonsFragment();
					FragmentManager fragmentManager = getFragmentManager();
	        			int count = fragmentManager.getBackStackEntryCount();
	        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        			fragmentTransaction.replace(R.id.frame_container, fragment)
	        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
	        			        .addToBackStack(String.valueOf(count))
	        			        .commit();
	        					getActivity().setTitle("Windows Media Player");
            		
            	}
            	if( position==1){
            		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
            			UdpConection.txt3= "FuncionVLC.class,";
            	    	new Thread( new UdpConection()).start();
            		}if(MainActivity.Conection=="Connected"){
            			MainActivity.mCommandService.write("xFuncionVLC.class,".getBytes());
            		}
            		
            		fragment= new MediaButtonsFragment();
					FragmentManager fragmentManager = getFragmentManager();
	        			int count = fragmentManager.getBackStackEntryCount();
	        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        			fragmentTransaction.replace(R.id.frame_container, fragment)
	        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
	        			        .addToBackStack(String.valueOf(count))
	        			        .commit();
	        					getActivity().setTitle("Vlc Media Player");
            	}
            	
              // Toast.makeText(view.getContext(),web[+ position], Toast.LENGTH_SHORT).show();
            }
        });
        
        return rootView;
    }
}