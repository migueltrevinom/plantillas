package com.AndoBlue.tooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends MyActivity {
	Button btnSearchDevice;
	Button pruebas;
	//Button btnMonitor;
	Button btnExit;
	/* Get Default Adapter */
	private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnSearchDevice = (Button) findViewById(R.id.Button01);
		btnSearchDevice.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SearchDeviceActivity.class);
				startActivity(intent);
			}
		});
		/*pruebas = (Button) findViewById(R.id.Button02);
		pruebas.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MonitorActivity.class);
				startActivity(intent);
			}
		});

	/*	btnMonitor = (Button) findViewById(R.id.btnMonitor);
		btnMonitor.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				_bluetooth.enable();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MonitorActivity.class);
				startActivity(intent);
			}
		});
*/
		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				dialog();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
			return false;
		}
		return false;
	}

	protected void dialog() {
		AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
		build.setTitle(R.string.message);
		build.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (_bluetooth.isEnabled()) {
							_bluetooth.disable();
						}
						SocketApplication app = (SocketApplication) getApplicationContext();
						app.setDevice(null);
						MainActivity.this.finish();
					}
				});
		build.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.create().show();
	}

	
	public void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	
	
	
}