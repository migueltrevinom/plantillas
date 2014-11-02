package funciones;
import hand.ControlApp.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class FunctionsFragment extends Fragment {

	String[] web={"speech-keyboard","mouse",  "media", "presentation"};
	Fragment fragment;
	boolean wifistate;
	@Override
	
	    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.fragment_functions,container,false);
	        GridView gridView = (GridView) view.findViewById(R.id.photogridview);
	        gridView.setAdapter(new FunctionsGrid(view.getContext())); 
	        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
	      			wifistate= sharedPrefs.getBoolean("prefWifiState",false);
	        
	        // uses the view to get the context instead of getActivity().
	        
	        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                	
                	if(position==0){
                		if(MainActivity.Conection=="Disconnected"  && wifistate==true){
                			UdpConection.txt3= "FuncionWrite.class,";
                	    	new Thread( new UdpConection()).start();
                		}if(MainActivity.Conection=="Connected"){
                			MainActivity.mCommandService.write("xFuncionWrite.class,".getBytes());
                		}
                		
                		Intent in= new Intent(view.getContext(), WriteActivity.class);
                		startActivity(in);
                		
                		}
                			if(position==1){
                				if(MainActivity.Conection=="Disconnected"  && wifistate==true){
                        			UdpConection.txt3= "FuncionTouch.class,";
                        	    	new Thread( new UdpConection()).start();
                        		}if(MainActivity.Conection=="Connected"){
                        			MainActivity.mCommandService.write("xFuncionTouch.class,".getBytes());
                        		}
                					Intent in= new Intent(view.getContext(),TouchActivity.class);
                						startActivity(in);
                						
                					}
                				
                				if (position==2){	if(MainActivity.Conection=="Disconnected"  && wifistate==true){
                        			UdpConection.txt3= "FuncionMedia.class,";
                        	    	new Thread( new UdpConection()).start();
                        		}if(MainActivity.Conection=="Connected"){
                        			MainActivity.mCommandService.write("xFuncionMedia.class,".getBytes());
                        		}
                					fragment= new MediaFragment();
                					FragmentManager fragmentManager = getFragmentManager();
             	        			int count = fragmentManager.getBackStackEntryCount();
             	        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             	        			fragmentTransaction.replace(R.id.frame_container, fragment)
             	        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
             	        			        .addToBackStack(String.valueOf(count))
             	        			        .commit();
             	        					getActivity().setTitle("Media");
                					
                				}
                				if (position==3){
                					if(MainActivity.Conection=="Disconnected"  && wifistate==true){
                            			UdpConection.txt3= "FuncionPresentation.class,";
                            	    	new Thread( new UdpConection()).start();
                            		}if(MainActivity.Conection=="Connected"){
                            			MainActivity.mCommandService.write("xFuncionPresentation.class,".getBytes());
                            		}
                            		fragment= new PresentationFragment();
                					FragmentManager fragmentManager = getFragmentManager();
             	        			int count = fragmentManager.getBackStackEntryCount();
             	        			android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             	        			fragmentTransaction.replace(R.id.frame_container, fragment)
             	        			        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
             	        			        .addToBackStack(String.valueOf(count))
             	        			        .commit();
             	        					getActivity().setTitle("Presentation");
                            		
                					
                				}
                   // Toast.makeText(view.getContext(), web[+ position], Toast.LENGTH_SHORT).show();
                }
            });
	        return view;
	    }
 @Override
 public void onActivityCreated(Bundle savedInstanceState) {
     super.onActivityCreated(savedInstanceState);
 }
}