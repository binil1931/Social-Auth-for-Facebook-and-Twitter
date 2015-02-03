package com.example.socialauthcustom;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {


	SocialAuthAdapter adapter;
	RelativeLayout bar;

	Button btn_twitter;
    Button btn_facebook;
    int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		btn_twitter = (Button) findViewById(R.id.btn_twitter);
		btn_twitter.setBackgroundResource(R.drawable.twitter);
		
		btn_facebook = (Button) findViewById(R.id.btn_facebook);
		btn_facebook.setBackgroundResource(R.drawable.facebook);
		
		adapter = new SocialAuthAdapter(new ResponseListener());

		btn_twitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				adapter.authorize(MainActivity.this, Provider.TWITTER);
			}
		});
		
		btn_facebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=1;
				adapter.authorize(MainActivity.this, Provider.FACEBOOK);	
			}
		});

	}

	/**
	 * Listens Response from Library
	 * 
	 */

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			// Variable to receive message status
			Log.d("Share-Bar", "Authentication Successful");

			// Get name of provider after authentication
			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			
			Log.d("Share-Bar", "Provider Name = " + providerName);
			Toast.makeText(MainActivity.this, providerName + " connected", Toast.LENGTH_SHORT).show();

		if(i == 0)
			adapter.updateStatus("tweet Checking", new MessageListener(), false);   
	      
		else
		{
			adapter.updateStatus("powerfull app", new MessageListener(), false);   
		}
		}

		@Override
		public void onError(SocialAuthError error) {
			error.printStackTrace();
			Log.d("Share-Bar", error.getMessage());
		}

		@Override
		public void onCancel() {
			Log.d("Share-Bar", "Authentication Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Share-Bar", "Dialog Closed by pressing Back Key");

		}
	}

	// To get status of message after authentication
	private final class MessageListener implements SocialAuthListener<Integer> {
		@Override
		public void onExecute(String provider, Integer t) {
			Integer status = t;
			if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
				Toast.makeText(MainActivity.this, "Message posted on " + provider, Toast.LENGTH_LONG).show();
			else
				Toast.makeText(MainActivity.this, "Message not posted on" + provider, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}
}
