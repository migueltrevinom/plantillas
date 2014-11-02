package funciones;


import hand.ControlApp.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ResizeActivity extends Activity implements OnClickListener {

	
	
	private Button btnSelect;
	private Button btnResize;
	private ImageView imageview;
	String filePath="";
	
	  private String imagepath=null;
	  private ProgressDialog dialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resize);
		
		btnSelect= (Button)findViewById(R.id.button1);
		btnResize= (Button)findViewById(R.id.button2);
		imageview = (ImageView)findViewById(R.id.imageView1);
		
		btnSelect.setOnClickListener(this);
		btnResize.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resize, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	if(arg0==btnSelect){
		
		  Intent intent = new Intent();
          intent.setType("image/*");
          intent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
		
	}
	else if(arg0==btnResize){
		 
	}
		
		
	}
	
	   @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	         
	        if (requestCode == 1 && resultCode == RESULT_OK) {
	            //Bitmap photo = (Bitmap) data.getData().getPath(); 
	           
	            Uri selectedImageUri = data.getData();
	            imagepath = getPath(selectedImageUri);
	            Bitmap bitmap=BitmapFactory.decodeFile(imagepath);
	            Bitmap reducida= scaleDown(bitmap, 640, true);
	            
	            storeImage(reducida, "m2");
	           // imageview.setRotation(90);
	            imageview.setImageBitmap(bitmap);
	            
	         
	            
	            
	           // messageText.setText("Uploading file path:" +imagepath);
	             
	        }
	    }

	  public String getPath(Uri uri) {
                String[] projection = { MediaStore.Images.Media.DATA };
                Cursor cursor = managedQuery(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
	  
	  
	  
	   //para hacer mas pequeña la imagen
	   public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
		        boolean filter) {
		    /*float ratio = Math.min(
		            (float) maxImageSize / realImage.getWidth(),
		            (float) maxImageSize/realImage.getHeight());
		    int width = Math.round((float) ratio * realImage.getWidth());
		    int height = Math.round((float) ratio * realImage.getHeight());
*/
		    Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, 640,
		            640, filter);
		  
		    return newBitmap;
		}
		  
	   
	   
	   private boolean storeImage(Bitmap imageData, String filename) {
			//get path to external storage (SD card)
			String iconsStoragePath = Environment.getExternalStorageDirectory() + "/DCIM/Camera/m/x//";
			File sdIconStorageDir = new File(iconsStoragePath);

			
			try {
			 filePath = sdIconStorageDir.toString() + filename+".png";
				FileOutputStream fileOutputStream = new FileOutputStream(filePath);

			
				BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

				//choose another format if PNG doesn't suit you
				imageData.compress(CompressFormat.PNG, 100, bos);
				//create storage directories, if they don't exist
				sdIconStorageDir.mkdirs();

				bos.flush();
				bos.close();

			} catch (FileNotFoundException e) {
				Log.w("TAG", "Error saving image file: " + e.getMessage());
				return false;
			} catch (IOException e) {
				Log.w("TAG", "Error saving image file: " + e.getMessage());
				return false;
			}

			return true;
		}
	

}
