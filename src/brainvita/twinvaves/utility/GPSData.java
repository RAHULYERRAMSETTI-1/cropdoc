package brainvita.twinvaves.utility;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.NoCopySpan.Concrete;
import android.widget.Toast;

public class GPSData {
    private static double latitude;
    /**
     * @return the latitude
     */
    public static double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public static void setLatitude(double l) {
        latitude = l;
    }

    private static double longitude;
    /**
     * @return the longitude
     */
    public static double getLongitude() {
        return longitude;
    }

    /**
     * @param d the longitude to set
     */
    public static void setLongitude(double d) {
        longitude = d;
    }
    
    public static class MyLocationListener implements LocationListener {
    	
    	Context c;
    	
    	public MyLocationListener(Context c)
    	{
    		this.c=c;
    	}
    	
        public void onLocationChanged(Location location) {
            setLongitude(location.getLongitude());
            setLatitude(location.getLatitude());
            Toast.makeText(c,"LAT:"+getLatitude()+" -- LONG:"+getLongitude() , Toast.LENGTH_LONG).show();
            
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}


