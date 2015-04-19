package brainvita.twinvaves.utility;

import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Utility implements SPConstants{

	public static SharedPreferences sp;
	public static final int  Splash_Wait_Time=1000;
	public static int ScreenWidth;
    public static int ScreenHeight;
    public static float ScreenScale;
    public static String picture_upload_url="http://www.nasaspaceppscont2015.comuf.com/savetofile.php";
    public static String data_upload_url="http://www.nasaspaceppscont2015.comuf.com/create_disease_record.php";
    
	
	public static void initializeSettings()
	{
		if(getKeyValue(INITIALIZED_SETTINGS).equals("FALSE") || getKeyValue(INITIALIZED_SETTINGS).equals(""))
		{
			insertNameValue(INITIALIZED_SETTINGS,"TRUE");
			//insertNameValue(LOGGED_IN, "TRUE");
		}
	}
	
	public static SharedPreferences obtainSharedPreferences()
	{
		return sp;
	}
	public static void insertNameValue(String Key,String Value)
	{
		SharedPreferences sp=obtainSharedPreferences();
		SharedPreferences.Editor spe=sp.edit();
		spe.putString(Key, Value);
		spe.commit();
	}
	public static boolean isKeyPresent(String Key)
	{
	
		SharedPreferences sp=obtainSharedPreferences();
		if(sp!=null)
			return sp.contains(Key);
		else
			return false;
	}
	public static void removeKey(String Key)
	{
		SharedPreferences sp=obtainSharedPreferences();
		SharedPreferences.Editor spe=sp.edit();
		spe.remove(Key);
		spe.commit();
	}
	public static String getKeyValue(String Key)
	{
		if(isKeyPresent(Key))
		{
			SharedPreferences sp=obtainSharedPreferences();
			return sp.getString(Key,"");
		}
		else 
			return "";
	}
}
