package de.bg.fhdw.bfwi413a.karthago.activities.statistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Data {
	
	//Not used, because obsolete
//	private static final String KEY_STATISTICS_ID = "ST"; //Um den Zustand der Activity zu erhalten
//	private int mStatisticsId;
//	private final int DEFAULT_STATISTICS_ID = 0;
	
	private Activity mActivity;
	
	public Data(Activity activity, Bundle savedInstanceState){
		Intent intent;
		
		mActivity = activity;
		if ( savedInstanceState == null ) {
			intent = mActivity.getIntent();
			//Not used, because obsolete
			//mStatisticsId = intent.getIntExtra(Navigation.KEY_STATISTICS_ID, DEFAULT_STATISTICS_ID);
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
//	return mStatisticsId;
//  }
	// save and restore data
	
//	public void saveDataInBundle(Bundle bundle) {
//		bundle.putInt(KEY_STATISTICS_ID, mStatisticsId);
//	}
//	
//	public void restoreDataFromBundle(Bundle bundle) {
//		mStatisticsId = bundle.getInt(KEY_STATISTICS_ID);
//	}

}
