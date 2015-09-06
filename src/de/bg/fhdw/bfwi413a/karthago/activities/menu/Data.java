package de.bg.fhdw.bfwi413a.karthago.activities.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Data {
	
	//Not used, because obsolete
	//private static final String KEY_MENU_ID = "M"; //Um den Zustand der Activity zu erhalten
	//private int mMenuId;
	//private final int DEFAULT_MENU_ID = 0;
	
	private Activity mActivity;
	
	public Data(Activity activity, Bundle savedInstanceState){
		Intent intent;
		
		mActivity = activity;
		if ( savedInstanceState == null ) {
			intent = mActivity.getIntent();
			//Not used, because obsolete
			//mMenuId = intent.getIntExtra(Navigation.KEY_MENU_ID, DEFAULT_MENU_ID);
		}
		else {
			//Not used, because obsolete
			//restoreDataFromBundle(savedInstanceState);
		}
	}


	public Activity getmActivity() {
		return mActivity;
	}
	
	//Not used, because obsolete
	
//	public int getmMenuId() {
//	return mMenuId;
//  }

	// save and restore data
	
//	public void saveDataInBundle(Bundle bundle) {
//		bundle.putInt(KEY_MENU_ID, mMenuId);
//	}
//	
//	public void restoreDataFromBundle(Bundle bundle) {
//		mMenuId = bundle.getInt(KEY_MENU_ID);
//	}

}
