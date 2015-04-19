package brainvita.twinwaves.hoc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import brainvita.twinvaves.utility.GPSData;
import brainvita.twinvaves.utility.GPSData.MyLocationListener;
import brainvita.twinvaves.utility.MultipartEntity;
import brainvita.twinvaves.utility.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class RegisterActivity extends Activity {

	ImageView profile_image;
	Button register;
	EditText fullname;
	EditText mobile_number;
	private static final int REQUEST_CAMERA = 15;
	private static final int SELECT_FILE = 14;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		Utility.ScreenWidth=getWindowManager().getDefaultDisplay().getWidth();
		Utility.ScreenHeight=getWindowManager().getDefaultDisplay().getHeight();
		
		
		profile_image=(ImageView)findViewById(R.id.profile_image);
		register=(Button)findViewById(R.id.register);
		fullname=(EditText)findViewById(R.id.username);
		mobile_number=(EditText)findViewById(R.id.mobile_number);
		
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria(); 
        criteria.setAccuracy(Criteria.ACCURACY_COARSE); 
        String provider = lm.getBestProvider(criteria, true);
		Location l=lm.getLastKnownLocation(provider);
		if(l!=null)
		{
			GPSData.setLatitude(l.getLatitude());
			GPSData.setLongitude(l.getLongitude());
			Toast.makeText(getApplicationContext(),"LAT:"+l.getLatitude()+" -- LONG:"+l.getLongitude() , Toast.LENGTH_LONG).show();
		}
		
	    MyLocationListener locationListener = new MyLocationListener(getApplicationContext());
	    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

	    
	    
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Get the dimensions of the View
			    int targetW = profile_image.getWidth();
			    int targetH = profile_image.getHeight();
			    
			    String mCurrentPhotoPath=Utility.getKeyValue("PICTURE_PATH");
			    
				// Get the dimensions of the bitmap
			    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			    bmOptions.inJustDecodeBounds = true;
			    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
			    int photoW = bmOptions.outWidth;
			    int photoH = bmOptions.outHeight;

			    // Determine how much to scale down the image
			    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

			    // Decode the image file into a Bitmap sized to fill the View
			    bmOptions.inJustDecodeBounds = false;
			    bmOptions.inSampleSize = scaleFactor << 1;
			    bmOptions.inPurgeable = true;

			    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
			    
			    try {
					sendPhoto(bitmap);
					sendProfileDetails();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		profile_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSelectPicDialog();
	        	
			}
		});
	}

	private void sendPhoto(Bitmap bitmap) throws Exception {
		new UploadTask().execute(bitmap);
	}
	
	private void sendProfileDetails() throws Exception {
		new UploadData().execute();
	}
	
	
	protected void showSelectPicDialog()
	{
		LayoutInflater inflater = getLayoutInflater();
	    final View view = inflater.inflate( R.layout.selectpicdailog,null);
	    final Context context=this;
	    final Dialog dialog=new Dialog(this, R.style.translucentdialog);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    final Window window = dialog.getWindow();
	    
	    window.setLayout(Utility.ScreenWidth,Utility.ScreenHeight);
	    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	    window.setBackgroundDrawable(new ColorDrawable(Color.argb(0x44,0xcc,0xcc,0xcc)));
	    LinearLayout ll = new LinearLayout(this);
	    ll.setOrientation(LinearLayout.VERTICAL);

	    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	    		Utility.ScreenWidth,Utility.ScreenHeight);

	    dialog.setContentView(view,layoutParams);
	    final TextView photolib=(TextView)view.findViewById(R.id.btn_photolib);
		final TextView camera=(TextView)view.findViewById(R.id.btn_cam);
		final TextView cancel=(TextView)view.findViewById(R.id.btn_cancel);
		
		
		camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment
                        .getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, REQUEST_CAMERA);
                
			}
		});
		
		photolib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        SELECT_FILE);

			}
			
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
	    dialog.show();
	   
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CAMERA) 
		{
            if (resultCode == Activity.RESULT_OK)
            {
            	Utility.insertNameValue("DEF_IMAGE_LOAD","TRUE");
            	Utility.insertNameValue("Default","");
				
            	Toast.makeText(getApplicationContext(),"IN AR RC_OK",Toast.LENGTH_SHORT).show();
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                    
 
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);
 
                    // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    profile_image.setImageBitmap(bm);
 
                    String path = android.os.Environment
                            .getExternalStorageDirectory().getAbsolutePath();
                    f.delete();
                    OutputStream fOut = null;
                    
                    File file = new File(path,"defaultimg.jpg");
                    
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Utility.insertNameValue("PICTURE_PATH",file.getAbsolutePath());
                } catch (Exception e) {
                	Toast.makeText(getApplicationContext(),"IN AR RC_EXCEPTION",Toast.LENGTH_SHORT).show();
                    
                    e.printStackTrace();
                }
            }
		}
        else if (requestCode == SELECT_FILE) {
//                Uri selectedImageUri = data.getData();
//                
//                String tempPath = getPath(selectedImageUri,CalendarView.this);
//                Bitmap bm;
//                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
//                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
//                calimage.setImageBitmap(bm);
        	Utility.insertNameValue("DEF_IMAGE_LOAD","TRUE");
        	Utility.insertNameValue("Default","");
			
        	Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Utility.insertNameValue("PICTURE_PATH",picturePath);
            profile_image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
       }
        
   }

	
	class UploadData extends AsyncTask<Void, Void, String> {
		 
	    protected String doInBackground(Void ...data ) {
	        DefaultHttpClient httpclient = new DefaultHttpClient();
	        try {
	        	HttpPost httppost = new HttpPost(
                                Utility.data_upload_url);
                // Add your data
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("disease", fullname.getText().toString().trim()));
                nameValuePairs.add(new BasicNameValuePair("crop", mobile_number.getText().toString().trim()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    try {
	                HttpResponse response = httpclient.execute(httppost);
	                HttpEntity responseEntity = response.getEntity();
	                if(responseEntity!=null) {
	                    String str_response = EntityUtils.toString(responseEntity);
	                    return str_response;
	                }
	                
	            } catch (ClientProtocolException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }   
	        catch(Exception e )
	        {
	        	
	        }
	        finally {

	        }
	        return null;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        // TODO Auto-generated method stub
	        super.onPostExecute(result);
	        //Toast.makeText(RegisterActivity.this,result, Toast.LENGTH_LONG).show();
	    }

	    
	}
	
	 class UploadTask extends AsyncTask<Bitmap, Void, Void> {
		 
	    protected Void doInBackground(Bitmap... bitmaps) {
	        if (bitmaps[0] == null)
	            return null;

	        Bitmap bitmap = bitmaps[0];
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // convert Bitmap to ByteArrayOutputStream
	        InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convert ByteArrayOutputStream to ByteArrayInputStream

	        DefaultHttpClient httpclient = new DefaultHttpClient();
	        try {
	            HttpPost httppost = new HttpPost(Utility.picture_upload_url); // server

	            MultipartEntity reqEntity = new MultipartEntity();
	            reqEntity.addPart("myFile",
	                    System.currentTimeMillis() + ".jpg", in);
	            httppost.setEntity(reqEntity);

	            HttpResponse response = null;
	            try {
	                response = httpclient.execute(httppost);
	            } catch (ClientProtocolException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            try {
	                if (response != null)
	                    Log.i("", "response " + response.getStatusLine().toString());
	            } finally {

	            }
	        } finally {

	        }

	        if (in != null) {
	            try {
	                in.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	        if (stream != null) {
	            try {
	                stream.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	        return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        // TODO Auto-generated method stub
	        super.onPostExecute(result);
	        Toast.makeText(RegisterActivity.this,"data uploaded successfully", Toast.LENGTH_LONG).show();
	    }
	}
}
