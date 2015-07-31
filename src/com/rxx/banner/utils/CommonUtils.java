package com.rxx.banner.utils;

import java.io.File;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * 
 * @author Ran
 *
 */
public class CommonUtils {

	/**获取当前版本号*/
	public static int getVersonCode(Context mContext){
		int versonCode=0;
		try {
			PackageManager mPackageManager=mContext.getPackageManager();
			PackageInfo mPackageInfo=mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
			versonCode=mPackageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versonCode;
	}
	
	/**获取当前版本名*/
	public static String getVersonName(Context mContext){
		String versonCode="";
		try {
			PackageManager mPackageManager=mContext.getPackageManager();
			PackageInfo mPackageInfo=mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
			versonCode=mPackageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versonCode;
	}
	
	
	/**获取设备IMEI*/
	public static String getIMEI(Context mContext){
		String imei="";
		imei =((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		return imei;
	}
	
	/**获取mac地址*/
	public static String getMAC(Context mContext){
		String mac="";
		mac= ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
		return mac==null?"":mac;
	}
	
	/**获取手机sdk版本*/
	public static int getSDK(){
		return android.os.Build.VERSION.SDK_INT;
	}
	
	/**获取手机系统版本*/
	public static String getSystemInformation(){
		return android.os.Build.VERSION.RELEASE;
	}
	
	/**获取手机信息*/
	public static String getPhoneName(){
		return android.os.Build.MANUFACTURER+"."+android.os.Build.MODEL;
	}
	
	/**获取WiFi路由器名称*/
	public static String getWifiName(Context mContext){
		return ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getBSSID();
	}
	
	/**获取路由器mac*/
	public static String getWifiMac(Context mContext){
		return ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getSSID();
	}
	
	/**2：已root 1未root*/
	public static int isRoot() {
		int isRoot = 1;
		try {
			if ((!new File("/system/bin/su").exists())
					&& (!new File("/system/xbin/su").exists())) {
				isRoot = 1;
			} else {
				isRoot = 2;
			}
		} catch (Exception e) {
		}
		return isRoot;
	}
	
	public static int getWIndowWidth(Context mContext){
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();

	}
	
	public static int getWindowHeigh(Context mContext){
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}
	
}
