package de.bg.fhdw.bfwi413a.karthago.activities.menu;

import android.app.Activity;
import android.os.Bundle;

public class Data {
	
	private Activity mActivity;
	
	public Data(Activity activity, Bundle savedInstanceState){
		mActivity = activity;
	}

	public Activity getmActivity() {
		return mActivity;
	}
}
