package de.bg.fhdw.bfwi413a.karthago.activities.login;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import de.bg.fhdw.bfwi413a.karthago.R;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;

public class Data {
	private Activity mActivity;
	DatabaseHandler mdbHandler;
	private List<String> users;
	private ArrayAdapter<String> dataAdapter;
	
	public Data(Activity activity, Bundle savedInstanceState){
		//INITIALIZE OBJECTS
		mdbHandler = new DatabaseHandler(activity);
		mActivity = activity;
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
	
	//GETTER
	public ArrayAdapter<String> getDataAdapter() {
		return dataAdapter;
	}

}
