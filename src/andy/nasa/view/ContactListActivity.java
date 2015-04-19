package andy.nasa.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import brainvita.twinwaves.hoc.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import andy.nasa.adapter.ExampleContactAdapter;
import andy.nasa.main.widget.ContactItemComparator;
import andy.nasa.main.widget.ContactItemInterface;
import andy.nasa.main.widget.ContactListView;
import andy.nasa.models.ExampleContactItem;
import andy.nasa.models.ExampleDataSource;
import andy.nasa.widget.ActionBar;

public class ContactListActivity extends Activity implements TextWatcher {

	private ExampleContactListView listview;
	
	private EditText searchBox;
	private String searchString;
	
	private Object searchLock = new Object();
	boolean inSearchMode = false;
	
	private final static String TAG = "com.ngohung.view.ContactListActivity";
	
	List<ContactItemInterface> contactList;
	List<ContactItemInterface> filterList;
	private SearchListTask curSearchTask = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

		actionBar.setTitle("All Crops");
		actionBar.setHomeLogo(R.drawable.ic_launcher);
        
		
		filterList = new ArrayList<ContactItemInterface>();
		contactList = ExampleDataSource.getSampleContactList();
		
		ExampleContactAdapter adapter = new ExampleContactAdapter(this, R.layout.example_contact_item, contactList);
		
		listview = (ExampleContactListView) this.findViewById(R.id.listview);
		listview.setFastScrollEnabled(true);
		listview.setAdapter(adapter);
		
		// use this to process individual clicks
		// cannot use OnClickListener as the touch event is overrided by IndexScroller
		// use last touch X and Y if want to handle click for an individual item within the row
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				List<ContactItemInterface> searchList = inSearchMode ? filterList : contactList ;
				
				float lastTouchX = listview.getScroller().getLastTouchDownEventX();
				/*if(lastTouchX < 45 && lastTouchX > -1){
					Toast.makeText(ContactListActivity.this, "User image is clicked ( " + searchList.get(position).getItemForIndex()  + ")", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(ContactListActivity.this, "Nickname: " + searchList.get(position).getItemForIndex() , Toast.LENGTH_SHORT).show();
				*/
				Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(searchList.get(position).getURL()));
	            startActivity(i);
			}
		});
		
		
		searchBox = (EditText) findViewById(R.id.input_search_query);
		searchBox.addTextChangedListener(this);
    }

    @Override
	public void afterTextChanged(Editable s) {
		

		searchString = searchBox.getText().toString().trim().toUpperCase();

		if(curSearchTask!=null && curSearchTask.getStatus() != AsyncTask.Status.FINISHED)
		{
			try{
				curSearchTask.cancel(true);
			}
			catch(Exception e){
				Log.i(TAG, "Fail to cancel running search task");
			}
			
		}
		curSearchTask = new SearchListTask();
		curSearchTask.execute(searchString); // put it in a task so that ui is not freeze
    }
    
    
    @Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
    	// do nothing
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// do nothing
	}
    
	private class SearchListTask extends AsyncTask<String, Void, String> {

		
		
		@Override
		protected String doInBackground(String... params) {
			filterList.clear();
			
			String keyword = params[0];
			
			inSearchMode = (keyword.length() > 0);

			if (inSearchMode) {
				// get all the items matching this
				for (ContactItemInterface item : contactList) {
					ExampleContactItem contact = (ExampleContactItem)item;
					
					if ((contact.getNickName().toUpperCase().indexOf(keyword) > -1) ) {
						filterList.add(item);
					}

				}


			} 
			return null;
		}
		
		protected void onPostExecute(String result) {
			
			synchronized(searchLock)
			{
			
				if(inSearchMode){
					
					ExampleContactAdapter adapter = new ExampleContactAdapter(ContactListActivity.this, R.layout.example_contact_item, filterList);
					adapter.setInSearchMode(true);
					listview.setInSearchMode(true);
					listview.setAdapter(adapter);
				}
				else{
					ExampleContactAdapter adapter = new ExampleContactAdapter(ContactListActivity.this, R.layout.example_contact_item, contactList);
					adapter.setInSearchMode(false);
					listview.setInSearchMode(false);
					listview.setAdapter(adapter);
				}
			}
			
		}
	}
	
    
}
