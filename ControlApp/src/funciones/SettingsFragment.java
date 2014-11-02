package funciones;

import hand.ControlApp.R;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class SettingsFragment extends Fragment{
	
	public SettingsFragment(){}
	static TextView ip,port,bwifistate;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
         
       
        ip=(TextView) rootView.findViewById(R.id.html);
        port=(TextView) rootView.findViewById(R.id.portTxt);
        bwifistate=(TextView) rootView.findViewById(R.id.bTxtWifi);
        
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
		AbsoluteSizeSpan textSpan = new AbsoluteSizeSpan(40);
		StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
		StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
		
		
	
		SpannableStringBuilder ssb1= new SpannableStringBuilder("Ip Address: "+sharedPrefs.getString("prefIpAddress", "0.0.0.0"));
		ssb1.setSpan(textSpan, 0, ssb1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb1.setSpan(boldSpan, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		
		//**********************************
		String state="";
		if(sharedPrefs.getBoolean("prefWifiState", false)==true){
			state="Wifi Activated";
		}else{
			state="Wifi Desactivated";	
		}
		
		SpannableStringBuilder ssb2= new SpannableStringBuilder("\nState: "+state);
		ssb2.setSpan(textSpan, 0, ssb2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb2.setSpan(boldSpan, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//*********************************
		SpannableStringBuilder ssb3= new SpannableStringBuilder("\nPort: "+sharedPrefs.getString("prefPortAddress", "0").toString());
		ssb3.setSpan(textSpan, 0, ssb3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb3.setSpan(boldSpan, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//********************************
		port.setText(ssb3, BufferType.SPANNABLE);
		ip.setText(ssb1, BufferType.SPANNABLE);
		bwifistate.setText(ssb2, BufferType.SPANNABLE);
        return rootView;
    }


	
	
	

}
