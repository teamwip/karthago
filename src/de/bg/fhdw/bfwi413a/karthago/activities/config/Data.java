package de.bg.fhdw.bfwi413a.karthago.activities.config;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import de.bg.fhdw.bfwi413a.karthago.R;
import de.bg.fhdw.bfwi413a.karthago.SessionManagement;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;

public class Data {
	//DECLARE NECESSARY OBJECTS
	private Activity mActivity;
	private ArrayAdapter<CharSequence> adapter_sort;
	private ArrayAdapter<String> dataAdapter;
	
	DatabaseHandler dbHelper;
	SessionManagement session;

	public Data(Activity activity, Bundle savedInstanceState) {
		//INITIALIZE OBJECTS
		mActivity = activity;
		dbHelper = new DatabaseHandler(activity);
		session = new SessionManagement(activity);
		
		//INITIALIZE ADAPTER-SPINNER
		adapter_sort = ArrayAdapter.createFromResource(activity, R.array.spn_sort, R.layout.spinner_item);
		adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		loadUserlistfromDatabse();
		
	}

	public Activity getmActivity() {
		return mActivity;
	}

	public ArrayAdapter<CharSequence> getAdapter_sort() {
		return adapter_sort;
	}

	public void loadUserlistfromDatabse() {
		// SPINNER ELEMENTS - DATABASE USERLIST
		List<String> users = dbHelper.getUserList();
		
		//CREATE ADAPTER
		dataAdapter = new ArrayAdapter<String>(mActivity, R.layout.spinner_item, users);

		// SET DROP DOWN LAYOUT STYLE
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	public ArrayAdapter<String> getDataAdapter() {
		return dataAdapter;
	}

}
