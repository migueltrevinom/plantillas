package funciones;

import hand.ControlApp.R;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class mediaGrid extends BaseAdapter {
private Context mContext;

public  mediaGrid(Context c) {
    mContext = c;
    
}

public int getCount() {
    return mThumbIds.length;
}

public Object getItem(int position) {
    return null;
}

public long getItemId(int position) {
    return 0;
}

 public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            int h=display.getHeight();
            int w= display.getWidth();
            //Toast.makeText(mContext, w+"width "+ h+"height", Toast.LENGTH_LONG).show();
            
            if((w>=480 && w<720) || (h<=480 )  ){
            	imageView.setLayoutParams(new GridView.LayoutParams(200,200));
            } 
            else if ( w>=720 && w<1280 ){
            	imageView.setLayoutParams(new GridView.LayoutParams(300,300));
            }
            else if (w>1280 ){
            	imageView.setLayoutParams(new GridView.LayoutParams(550,550));
            }
            
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        
        return imageView;
    }

private Integer[] mThumbIds = {
    R.drawable.winmediaplayer, R.drawable.vlcmediaicon
        
};


}