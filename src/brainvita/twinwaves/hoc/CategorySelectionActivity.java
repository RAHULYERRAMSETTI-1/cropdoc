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

public class CategorySelectionActivity extends Activity {

	ImageView post_a_problem;
	ImageView experiences;
	ImageView crop_details;
	ImageView crop_diseases;
	ImageView forecst;
	ImageView soil;
	ImageView research;
	ImageView climate;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.category_selection);
		post_a_problem=(ImageView)findViewById(R.id.post_a_problem);
		experiences=(ImageView)findViewById(R.id.experiences);
		crop_details=(ImageView)findViewById(R.id.crop_details);
		crop_diseases=(ImageView)findViewById(R.id.crop_diseases);
		soil=(ImageView)findViewById(R.id.soil);
		climate=(ImageView)findViewById(R.id.climate);
		research=(ImageView)findViewById(R.id.research);
		forecst=(ImageView)findViewById(R.id.forecst);
		post_a_problem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(i);
			}
		});
		
		experiences.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Experiences.class);
				startActivity(i);
			}
		});
crop_details.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),ContactListActivity.class);
				startActivity(i);
			}
		});
crop_diseases.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(getApplicationContext(),ContactListActivityTwo.class);
		startActivity(i);
	}
});
soil.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String url = "http://www.pestwatch.psu.edu/sweetcorn/tool/tool.html";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
});
forecst.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(getApplicationContext(),forecst.class);
		startActivity(i);
	}
});
research.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String url = "https://earthdata.nasa.gov/data/near-real-time-data";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
});
climate.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String url = "http://idn.ceos.org/climdiag/Home.do?Portal=climatediagnostics&MetadataType=0&lbnode=mdlb3";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
});
	}
	

}
