package brainvita.twinwaves.hoc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import andy.nasa.models.Diseases;
import andy.nasa.models.ExampleDataSource;
import andy.nasa.view.ContactListActivity;
import andy.nasa.view.ContactListActivityTwo;

public class forecst extends Activity {

	ImageView temperature;
	ImageView rainfall;
	ImageView dipression;
	ImageView drought;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.forecasts);
		temperature=(ImageView)findViewById(R.id.temperature);
		rainfall=(ImageView)findViewById(R.id.rainfall);
		dipression=(ImageView)findViewById(R.id.dipression);
		drought=(ImageView)findViewById(R.id.drought);
		
		temperature.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://iridl.ldeo.columbia.edu/maproom/Global/Forecasts/Flexible_Forecasts/temperature.html";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
dipression.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://www.mesonet.org/index.php/agriculture/category/agriculture_essentials/dispersion/dispersion_forecast_map";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
drought.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String url = "http://drought.unl.edu/MonitoringTools/USDroughtMonitor.aspx";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
});
		
		rainfall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://trmm.gsfc.nasa.gov/trmm_rain/Events/wkbig.qt";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		
	}
	

	}