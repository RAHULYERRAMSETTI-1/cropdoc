package brainvita.twinwaves.hoc;

import brainvita.twinvaves.utility.SPConstants;
import brainvita.twinvaves.utility.Utility;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.splash_layout);
		Utility.sp=getSharedPreferences(SPConstants.SP_NAME,Context.MODE_PRIVATE);
		Utility.initializeSettings();
		startThread();
		
	}

	private void startThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(Utility.Splash_Wait_Time);
				} catch (Exception e) {
					// TODO: handle exception
				}

				messgeHandler.sendEmptyMessage(0);
			}
		}).start();
	}

	public Handler messgeHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			//Write the Logic To Switch TO Either LoginOptionsActivity or MainActivity.
			
			Intent i;
			if(Utility.getKeyValue(SPConstants.LOGGED_IN).equals("TRUE"))
			{
				i=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(i);
			}
			else
			{
				i=new Intent(getApplicationContext(),CategorySelectionActivity.class);
				startActivity(i);
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		return super.onKeyDown(keyCode, event);
	}

}
