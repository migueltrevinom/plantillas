package funciones;

import hand.ControlApp.R;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class HelpFragment extends Fragment implements OnClickListener{
	
	static TextView html, html2, html3, html4, html5, html6,html7,html8,html9,html10,html11, html12, html13,html14, espacio1;
	
	public HelpFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.helppp, container, false);
        html= (TextView)rootView.findViewById(R.id.htmll);
        html2= (TextView)rootView.findViewById(R.id.htmll2);
        html3= (TextView)rootView.findViewById(R.id.htmll3);
        html4 = (TextView)rootView.findViewById(R.id.htmll4);
        html5= (TextView)rootView.findViewById(R.id.html5);
        html6= (TextView)rootView.findViewById(R.id.html6);
        html7= (TextView)rootView.findViewById(R.id.html7);
        html8= (TextView)rootView.findViewById(R.id.html8);
        html9= (TextView)rootView.findViewById(R.id.html9);
        html10= (TextView)rootView.findViewById(R.id.html10);
        html11=	(TextView)rootView.findViewById(R.id.html11);
        html12=	(TextView)rootView.findViewById(R.id.html12);
        html13=	(TextView)rootView.findViewById(R.id.html13);
        html14=	(TextView)rootView.findViewById(R.id.html14);
        espacio1= (TextView)rootView.findViewById(R.id.space1);
        
       // html.setText(Html
			//	.fromHtml("<h2>ControlApp Instructions</h2>"));        
        
       
		AbsoluteSizeSpan headerSpan = new AbsoluteSizeSpan(50);
		AbsoluteSizeSpan textSpan = new AbsoluteSizeSpan(25);
		AbsoluteSizeSpan titulos= new AbsoluteSizeSpan(40);
		StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
		StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
		SpannableStringBuilder ssb1 = new SpannableStringBuilder("ControlApp - Help");
		ssb1.setSpan(headerSpan, 0,17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb1.setSpan(boldSpan, 0, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		
		//******************************
		SpannableStringBuilder ssb2 = new SpannableStringBuilder("ControlApp is an Application that helps you to control your Pc from your smartphone and for the best"
				+ "use of this app you must read this help section");
		ssb2.setSpan(textSpan, 0, ssb2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb2.setSpan(boldSpan, 0, ssb2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//************************************************
		SpannableStringBuilder ssb3= new SpannableStringBuilder("1.-Download Server (Pc version)");
		ssb3.setSpan(titulos, 0, ssb3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb3.setSpan(italicSpan, 0, ssb3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		//***********************************************	
		SpannableStringBuilder ssb4= new SpannableStringBuilder("You can download the server app for your Pc https://www.dropbox.com/s/b1gqezxt3z81fgf/ControlApp.zip");
		ssb4.setSpan(textSpan, 0, ssb4.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		
		URLSpan urlSpan = new URLSpan("http://google.com/") {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(getURL()));
				startActivity(intent);
			}
		};
		ssb4.setSpan(urlSpan, 44, ssb4.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//*******************************************
		SpannableStringBuilder ssb5= new SpannableStringBuilder("2.-Wifi Connection");
		ssb5.setSpan(titulos, 0, ssb5.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb5.setSpan(italicSpan, 0, ssb5.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		//*******************************************
		SpannableStringBuilder ssb6= new SpannableStringBuilder("If you want to use the Wifi option at the computer you will find the Ip and Port of your Pc at the main screen of the application."
				+ "\n for Example: \n Ip: 192.168.1.15 \n Port: 61558 \n you can set that values at your phone in the icon of the Wifi [ ]");
		Bitmap image = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_action_network_wifi);
		ImageSpan imageSpan = new ImageSpan(rootView.getContext(), image);
		ssb6.setSpan(imageSpan, ssb6.length()-2, ssb6.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb6.setSpan(textSpan, 0, ssb6.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//********************************************
		SpannableStringBuilder ssb7= new SpannableStringBuilder("        ");
		Bitmap wifiimagepc = BitmapFactory.decodeResource(getResources(),
				R.drawable.celphone);
		Bitmap wifiimagephone = BitmapFactory.decodeResource(getResources(), R.drawable.wifioptions);
		ImageSpan wifiimg= new ImageSpan(rootView.getContext(), wifiimagepc);
		ImageSpan wifiimg2= new ImageSpan(rootView.getContext(), wifiimagephone);
		ImageSpan wifiimg3= new ImageSpan(rootView.getContext(), wifiimagephone);
		ssb7.setSpan(wifiimg, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb7.setSpan(wifiimg2, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//*****************************************
		SpannableStringBuilder  ssb8= new SpannableStringBuilder("3.-Bluetooth Connection\n"
				+ "If you want to use the Bluetooth connection, you only need to click the icon [ ] and pick your paired device. \n"
				+ "If you havent paired your computer with your phone, you must do it first before use the app. \n..");
		Bitmap blue= BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_bluetooth);
		ImageSpan blu= new ImageSpan(rootView.getContext(), blue);
		ssb8.setSpan(blu, 102, 103, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb8.setSpan(titulos, 0,23, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
		ssb8.setSpan(italicSpan, 0, 23, Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		ssb8.setSpan(textSpan, 23, ssb8.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		Bitmap blueprintscreen = BitmapFactory.decodeResource(getResources(), R.drawable.bluetoothoptions);
		ImageSpan spanBluescreen1= new ImageSpan(rootView.getContext(), blueprintscreen);
		Bitmap blueprintscreen2= BitmapFactory.decodeResource(getResources(), R.drawable.bluecapture);
		ImageSpan spanBluescreen2= new ImageSpan(rootView.getContext(), blueprintscreen2);
		ssb8.setSpan(spanBluescreen2, ssb8.length()-2, ssb8.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb8.setSpan(spanBluescreen1, ssb8.length()-1, ssb8.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//********************************
		SpannableStringBuilder ssb9= new SpannableStringBuilder("4.-Functions:\n.");
		ssb9.setSpan(titulos, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb9.setSpan(italicSpan, 0,12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		Bitmap functions= BitmapFactory.decodeResource(getResources(), R.drawable.apps);	
		ImageSpan functionscreen= new ImageSpan(rootView.getContext(),functions);
		ssb9.setSpan(functionscreen, ssb9.length()-1, ssb9.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//******************************
		SpannableStringBuilder ssb10 = new SpannableStringBuilder("-Write Function : (Keyboard)\n..");
		Bitmap writescreen1= BitmapFactory.decodeResource(getResources(), R.drawable.writeoption);
		Bitmap writescreen2= BitmapFactory.decodeResource(getResources(), R.drawable.writevoice);
		ImageSpan writespan1 = new ImageSpan(rootView.getContext(), writescreen1);
		ImageSpan writespan2 = new ImageSpan(rootView.getContext(), writescreen2);
		ssb10.setSpan(writespan1, ssb10.length()-2, ssb10.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb10.setSpan(writespan2, ssb10.length()-1, ssb10.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb10.setSpan(titulos, 0, ssb10.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		SpannableStringBuilder ssb11= new SpannableStringBuilder("[ ] Delete button: this works as a backspace in your computer, delete your letters 1 by 1.");
		Bitmap backbut= BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_backspace);
		ImageSpan backspan= new ImageSpan(rootView.getContext(), backbut);
		ssb11.setSpan(backspan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		ssb11.setSpan(textSpan, 0, ssb11.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//*******************************
		SpannableStringBuilder ssb12= new SpannableStringBuilder("[ ] Enter button: this works as the Enter button of your computer, it jumps a line or works as 'send' inside your pc, like texting in facebook or any other instant forward message.");
		Bitmap enterbut= BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_forward);
		ImageSpan entersppan= new ImageSpan(rootView.getContext(), enterbut);
		ssb12.setSpan(entersppan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		ssb12.setSpan(textSpan, 0, ssb12.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//******************************
		SpannableStringBuilder ssb13= new SpannableStringBuilder("[ ] Microphone button: this button activate the microphone function of google at your phone, and detects your voice and its translated to words in the app, then it can be send as a normal text message.");
		Bitmap microbut= BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_mic);
		ImageSpan microspan= new ImageSpan(rootView.getContext(), microbut);
		ssb13.setSpan(microspan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		ssb13.setSpan(textSpan, 0, ssb13.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//*******************************************************
		SpannableStringBuilder ssb14= new SpannableStringBuilder("[ ] Send button: this button send the text message that you write on the phone screen or talked by the microphone function, and the computer receive it by bluetooth or wifi, depending of witch way you are connected.");
		Bitmap sendbut= BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_accept);
		ImageSpan sendspan= new ImageSpan(rootView.getContext(), sendbut);
		ssb14.setSpan(sendspan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);	
		ssb14.setSpan(textSpan, 0, ssb14.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		//******************************************************
		
		
		
		
		
		
		
		
		
		html.setText(ssb1, BufferType.SPANNABLE);
		html2.setText(ssb2, BufferType.SPANNABLE);
		html3.setText(ssb3, BufferType.SPANNABLE);
		html4.setText(ssb4, BufferType.SPANNABLE);
		html5.setText(ssb5, BufferType.SPANNABLE);
		html6.setText(ssb6, BufferType.SPANNABLE);
		html7.setText(ssb7, BufferType.SPANNABLE);
		html8.setText(ssb8, BufferType.SPANNABLE);
		html9.setText(ssb9, BufferType.SPANNABLE);
		html10.setText(ssb10, BufferType.SPANNABLE);
		html11.setText(ssb11, BufferType.SPANNABLE);
		html12.setText(ssb12, BufferType.SPANNABLE);
		html13.setText(ssb13, BufferType.SPANNABLE);
		html14.setText(ssb14, BufferType.SPANNABLE);
		Linkify.addLinks(html4, Linkify.WEB_URLS);
		

        return rootView;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
