package brainvita.twinwaves.hoc;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LanguageSelectionActivity extends Activity {

	ImageView language_hindi;
	ImageView language_english;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.language_selection);
		language_hindi=(ImageView)findViewById(R.id.language_hindi);
		language_english=(ImageView)findViewById(R.id.language_english);
		
		language_hindi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		language_english.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
}
