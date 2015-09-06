package de.bg.fhdw.bfwi413a.karthago.activities.login;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import de.bg.fhdw.bfwi413a.karthago.R;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;

public class Data {

	//Not used, because obsolete
	//private static final String KEY_LOGIN_ID = "M"; //Um den Zustand der Activity zu erhalten
	//private int mLoginId;
	//private final int DEFAULT_LOGIN_ID = 0;
	
	private Activity mActivity;
	DatabaseHandler mdbHandler;
	private List<String> users;
	private ArrayAdapter<String> dataAdapter;
	private Intent intent;
	
	public Data(Activity activity, Bundle savedInstanceState){
		//INITIALIZE OBJECTS
		mdbHandler = new DatabaseHandler(activity);
		mActivity = activity;
		if ( savedInstanceState == null ) {
			intent = mActivity.getIntent();
			//Not used, because obsolete
			//mLoginId = intent.getIntExtra(Navigation.KEY_LOGIN_ID, DEFAULT_LOGIN_ID);
		}
		else {
			//Not used, because obsolete
			//restoreDataFromBundle(savedInstanceState);
		}
		
		//LOAD USERLIST FROM DATABASE
		loadUserlistFromDatabase();
		
	}
	
	//METHOD TO LOAD USERLIST FROM DATABASE
	public void loadUserlistFromDatabase(){
		//GET USERS
		users = mdbHandler.getUserList();
		//CONFIGURE ADAPTER
		dataAdapter = new ArrayAdapter<String>(mActivity,
                R.layout.spinner_item, users);
		//SET DROPDOWNVIEW
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	

	public Activity getmActivity() {
		return mActivity;
	}
	
	//Not used, because obsolete
	
	//public int getmMenuId() {
	//	return mLoginId;
	//}
	
	// save and restore data
	
	//public void saveDataInBundle(Bundle bundle) {
		//bundle.putInt(KEY_LOGIN_ID, mLoginId);
	//}
	
	//public void restoreDataFromBundle(Bundle bundle) {
		//mLoginId = bundle.getInt(KEY_LOGIN_ID);
	//}

	//GETTER
	public ArrayAdapter<String> getDataAdapter() {
		return dataAdapter;
	}

}
