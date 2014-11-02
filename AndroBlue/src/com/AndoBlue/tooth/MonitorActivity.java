package com.AndoBlue.tooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;










import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MonitorActivity extends MyActivity  {

	private static final int REQUEST_DISCOVERY = 0x1;

	private final String TAG = "MonitorActivity";
	private Handler _handler = new Handler();
	private final int maxlength = 2048;
	private BluetoothDevice device = null;
	private BluetoothSocket socket = null;
	
	private OutputStream outputStream;
	private InputStream inputStream;
	
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	private OnTouchListener OnTouchListener;
	public static boolean canRead = true;

	public static StringBuffer hexString = new StringBuffer();
	//movimiento
	private List<String> values = new ArrayList<String>();
	private boolean wait = true;
	private SensorManager smng;				//objeto de admon de sensor
	private Sensor magnetometro;			//objeto que vincula al sensor magnético
	private Sensor acelerometro;			//objeto que vincula al acelerómetro
	protected Vibrator vibr;
	private int xcuad = 3;
	private int ycuad = 3;
	String txt3;
	String txt2;
	int Port;
	EditText enviarvalor;
	int cont=0;
	int mensaje;
	ProgressDialog progressDoalog;
	
	public TextView imagenMostrar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.monitor);
		enviarvalor= (EditText)findViewById(R.id.editText1);
		progressDoalog = new ProgressDialog(MonitorActivity.this);
		progressDoalog.setMax(100);
		progressDoalog.setMessage("Getting all the packets....");
		progressDoalog.setTitle("Conecting devices..");
		progressDoalog
				.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDoalog.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					while (progressDoalog.getProgress() <= progressDoalog
							.getMax()) {
						Thread.sleep(200);
						handle.sendMessage(handle.obtainMessage());
						if (progressDoalog.getProgress() == progressDoalog
								.getMax()) {
							progressDoalog.dismiss();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	
//termina movimiento
		BluetoothDevice finalDevice = this.getIntent().getParcelableExtra(
				BluetoothDevice.EXTRA_DEVICE);
		SocketApplication app = (SocketApplication) getApplicationContext();
		device = app.getDevice();
		Log.d(TAG, "test1");
		if (finalDevice == null) {
			if (device == null) {
				Log.d(TAG, "test2");
				Intent intent = new Intent(this, SearchDeviceActivity.class);
				startActivity(intent);
				finish();
				return;
			}
			Log.d(TAG, "test4");
		} else if (finalDevice != null) {
			Log.d(TAG, "test3");
			app.setDevice(finalDevice);
			device = app.getDevice();
		}
		new Thread() {
			public void run() {
				connect(device);
			};
		}.start();
		
		
	}
	
	/* after select, connect to device */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode != REQUEST_DISCOVERY) {
			finish();
			return;
		}
		if (resultCode != RESULT_OK) {
			finish();
			return;
		}
		final BluetoothDevice device = data
				.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		new Thread() {
			public void run() {
				connect(device);
			};
		}.start();
	}

	protected void onDestroy() {
		super.onDestroy();
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			Log.e(TAG, ">>", e);
		}
	}
	Handler handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressDoalog.incrementProgressBy(3);
			
		}
	};
	protected void connect(BluetoothDevice device) {
		try {
			Log.d(TAG, "³¢ÊÔÁ¬½Ó");
			// Create a Socket connection: need the server's UUID number of
			// registered
			Method m = device.getClass().getMethod("createRfcommSocket",
					new Class[] { int.class });
			socket = (BluetoothSocket) m.invoke(device, 1);
			socket.connect();
			Log.d(TAG, ">>Client connectted");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			int read = -1;
			final byte[] bytes = new byte[2048];
			while (true) {
				synchronized (obj1) {
					read = inputStream.read(bytes);
					Log.d(TAG, "read:" + read);
					if (read > 0) {
						final int count = read;
						String str = SamplesUtils.byteToHex(bytes, count);
//						Log.d(TAG, "test1:" + str);
						String hex = hexString.toString();
						if (hex == "") {
							hexString.append("<--");
						} else {
							if (hex.lastIndexOf("<--") < hex.lastIndexOf("-->")) {
								hexString.append("\n<--");
							}
						}
						hexString.append(str);
						hex = hexString.toString();
//						Log.d(TAG, "test2:" + hex);
						if (hex.length() > maxlength) {
							try {
								hex = hex.substring(hex.length() - maxlength,
										hex.length());
								hex = hex.substring(hex.indexOf(" "));
								hex = "<--" + hex;
								hexString = new StringBuffer();
								hexString.append(hex);
							} catch (Exception e) {
								e.printStackTrace();
								Log.e(TAG, "e", e);
							}
						}
						_handler.post(new Runnable() {
							public void run() {
							}
						});
					}
				}
			}

		} catch (Exception e) {
			Log.e(TAG, ">>", e);
			Toast.makeText(getBaseContext(),
					getResources().getString(R.string.ioexception),
					Toast.LENGTH_SHORT).show();
			return;
		} finally {
			if (socket != null) {
				try {
					Log.d(TAG, ">>Client Socket Close");
					socket.close();
					socket = null;
					// this.finish();
					return;
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
				}
			}
		}
	}
	



	public void onBluetooth(View view){
		txt3=	enviarvalor.getText().toString().trim();
		new Thread( new enviarUDP()).start();	
		enviarvalor.setText("");
		
	}

	

	
	
	protected class enviarUDP implements Runnable{
		public void run() {
			
			//imagenMostrar.setText(lienzo2.x);
			try {
				if (outputStream != null || inputStream!=null) {
					synchronized (obj2) {
						outputStream.write(txt3.getBytes());
					}
				} else {
					Toast.makeText(getBaseContext(),
							"failed to send ... 7",
							Toast.LENGTH_SHORT).show();
				}
			} 		catch (IOException e) {
						Log.e(TAG, ">>", e);
							e.printStackTrace();
				}	
			
		}
		
		
	}	
}