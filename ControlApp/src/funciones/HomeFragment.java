package funciones;

import hand.ControlApp.R;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;







import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements OnClickListener {
	 final static String ARG_POSITION = "position";
	    int mCurrentPosition = -1;
	    private static final int RESULT_SETTINGS = 1;
	    private ListView mDrawerList;
	    
	    String[] web={"functions","system","settings","help"};
	    Fragment fragment;
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        GridView gridView = (GridView) rootView.findViewById(R.id.homegridview);
        gridView.setAdapter(new homeGrid(rootView.getContext())); 

        
        // uses the view to get the context instead of getActivity().
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            	
            	
            	if( position==0){
            		 fragment = new FunctionsFragment();
            		FragmentManager fragmentManager = getFragmentManager();
        			int count = fragmentManager.getBackStackEntryCount();
        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        			fragmentTransaction.replace(R.id.frame_container, fragment)
        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        			        .addToBackStack(String.valueOf(count))
        			        .commit();
        					getActivity().setTitle("Functions");
            	}
            	if( position==1){
            		 fragment = new SystemFragment();
             		FragmentManager fragmentManager = getFragmentManager();
         			int count = fragmentManager.getBackStackEntryCount();
         			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         			fragmentTransaction.replace(R.id.frame_container, fragment)
         			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
         			        .addToBackStack(String.valueOf(count))
         			        .commit();
         					getActivity().setTitle("System");
            		
            	}
            	if(position==2){
            		 fragment = new SettingsFragment();
              		FragmentManager fragmentManager = getFragmentManager();
          			int count = fragmentManager.getBackStackEntryCount();
          			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          			fragmentTransaction.replace(R.id.frame_container, fragment)
          			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          			        .addToBackStack(String.valueOf(count))
          			        .commit();
          					getActivity().setTitle("Settings");
            		
            	}
            	if(position==3){
            	fragment = new HelpFragment();
            		FragmentManager fragmentManager = getFragmentManager();
        			int count = fragmentManager.getBackStackEntryCount();
        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        			fragmentTransaction.replace(R.id.frame_container, fragment)
        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        			        .addToBackStack(String.valueOf(count))
        			        .commit();
    							getActivity().setTitle("Help");
            	}
                Toast.makeText(view.getContext(),web[+ position], Toast.LENGTH_SHORT).show();
            }
        });

        return rootView; 
    }	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}
	
	  // Alternative variant for API 5 and higher
	  public void onBackPressed() {
		  View view = null;
			FragmentManager fragmentManager = getFragmentManager();
		  if (fragmentManager.getBackStackEntryCount() <= 1) {
		      ((Activity) view.getContext()).finish();   
		  	getActivity().setTitle("ControlApp");
		        return;
		    }
		    
		    
	  }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	

	private void showUserSettings() {
		
	}
	

}
