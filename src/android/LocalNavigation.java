package com.sucsoft.navigation;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.xwalk.core.extension.JsStubGenerator.TAG;

public class LocalNavigation extends CordovaPlugin {

	private static String ACTION = "navigation";
	private String navType = "auto";
	private String lng = "";
	private String lat = "";
	private String myLng = "";
	private String myLat = "";

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		Navigation nav = new Navigation(cordova.getActivity());
		/** action */
		if(!action.equals(ACTION)){
			return false;
		}
		/** args*/
		JSONObject jsonObject = args.getJSONObject(0);

		if(jsonObject.has("type")){
			navType = jsonObject.getString("type");
		}
		if(jsonObject.has("toLng")){
			lng = jsonObject.getString("toLng");
		}
		if(jsonObject.has("toLat")){
			lat = jsonObject.getString("toLat");
		}
		if(jsonObject.has("myLng")){
			myLng = jsonObject.getString("myLng");
		}
		if(jsonObject.has("myLat")){
			myLat = jsonObject.getString("myLat");
		}
		Log.i(TAG, "navType: " + navType);
		Log.i(TAG, "lng: " + lng);
		Log.i(TAG, "lat: " + lat);
		Log.i(TAG, "myLng: " + myLng);
		Log.i(TAG, "myLat: " + myLat);

		/**launch**/
		if("auto".equals(navType)){
			nav.autoNavigation(lng, lat, myLng, myLat);
		}
		else if("amap".equals(navType)){
			nav.AMapNavigation(lng, lat);
		}
		else if("baidu".equals(navType)){
			nav.BDMapNavigation(lng, lat);
		}
		else if("web".equals(navType)){
			nav.AmapWebNavigation(lng, lat, myLng, myLat);
		}

//		nav.startNavigation(lng, lat, myLng, myLat);
		callbackContext.success("success");

		return true;
	}

}
