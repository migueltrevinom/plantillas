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


public class systemGrid extends BaseAdapter {
private Context mContext;

public  systemGrid(Context c) {
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
            int height=display.getHeight();
            int weight= display.getWidth();
           // Toast.makeText(mContext, w+"width "+ h+"height", Toast.LENGTH_LONG).show();
            
            if((weight>=480 && weight<720) || (height<=480 )  ){
            	imageView.setLayoutParams(new GridView.LayoutParams(200,200));
            } 
            else if ( weight>=720 && weight<1280 ){
            	imageView.setLayoutParams(new GridView.LayoutParams(300,300));
            }
            else if (weight>1280 ){
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
  R.drawable.shutdown, R.drawable.restart, R.drawable.lockscreenicon, R.drawable.sleep
};


}