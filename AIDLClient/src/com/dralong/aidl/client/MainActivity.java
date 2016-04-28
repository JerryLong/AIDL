package com.dralong.aidl.client;

import com.dralong.aidl.server.AIDLInterface;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private AIDLInterface mAIDLInterface;
	ServiceConnection mSConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText editText = (EditText) findViewById(R.id.main_set_edit);
		final TextView textView = (TextView) findViewById(R.id.main_set_txt);
		Button btnButton = (Button) findViewById(R.id.main_set_btn);
		btnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					int i = Integer.parseInt(editText.getText().toString());
					Log.i("s", "i  " + i);
					textView.append("序列  " + i + " == "
							+ mAIDLInterface.getName(i) + "\n");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		mSConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				mAIDLInterface = null;
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mAIDLInterface = AIDLInterface.Stub.asInterface(service);
			}
		};
		if (mAIDLInterface == null) {
			// Intent mIntent = new Intent();
			// mIntent.setAction("XXX.XXX.XXX");
			// Intent eintent = new Intent(getExplicitIntent(this,mIntent));
			// context.startService(eintent);
			Intent it = new Intent();
			it.setAction("com.dralong.aidl.server.AIDLServer");
			it.setPackage("com.dralong.aidl.client");
			bindService(it, mSConnection, Service.BIND_AUTO_CREATE);
		}
	}

	protected void onDestroy() {
		unbindService(mSConnection);
	};

}
