package com.example.locationexample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
     TextView tvlongitude,tvlatitude,tvspeed;
     Button start,stop;
     LocationManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvlongitude = (TextView)findViewById(R.id.etLongitude);
		tvlatitude =(TextView)findViewById(R.id.etLatitude);
		tvspeed = (TextView)findViewById(R.id.etSpeed);
		start = (Button)findViewById(R.id.bStart);
		stop =(Button)findViewById(R.id.bStartService);
		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void start(View v){
		try
		{
		    LocationListener listener=new LocationListener() {
		        @Override
		        public void onLocationChanged(Location location) {
		            double lat= location.getLatitude();
		            double lon = location.getLongitude();
		            double speed =location.getSpeed();
		            tvlongitude.setText("Longi"+lon);
		            tvlatitude.setText("Lati"+lat);
		            tvspeed.setText("Speed"+speed);
		            //do something with this values
		        }

		        @Override
		        public void onStatusChanged(String provider, int status, Bundle extras) {
		            Log.d("DATA","STATUS CHANGED "+provider);
		        }

		        @Override
		        public void onProviderEnabled(String provider) {
		            Log.d("DATA","PROVIDER ENABLED "+provider);
		        }

		        @Override
		        public void onProviderDisabled(String provider) {
		            Log.d("DATA","PROVIDER DISENABLED "+provider);
		        }
		    };
		    String provider=LocationManager.GPS_PROVIDER;
		    manager.requestLocationUpdates(provider,3000,2,listener);
		}catch (SecurityException e)
		{
		   Log.e("DATA","Could Not Fetch The Location");
		}
	}
	public void start_service(View v){
		//manager.removeUpdates(listener)
		Intent service = new Intent(this,LocationService.class);
		startService(service);
	}
	public void check(View v){
		//manager.removeUpdates(listener)
		String  data = Utility.readFRomFile(this,"data.txt");
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();
	}

	
}
