package de.bg.fhdw.bfwi413a.karthago.activities.config;

import android.app.Activity;
import android.widget.Button;
import android.widget.Spinner;
import de.bg.fhdw.bfwi413a.karthago.R;


public class Gui {
	//DECALRE GUI-ELEMENTS
	Spinner spn_sorttyper;
	Spinner userlist;
	Button changeUser;
	Button deleateUser;
	Button info;
	
	public Gui(Activity activity){
		activity.setContentView(R.layout.activity_config);
		
		//INITIALIZE GUI-ELEMENTS
		spn_sorttyper = (Spinner) activity.findViewById(R.id.spn_sort);
		userlist = (Spinner) activity.findViewById(R.id.spn_userlist_config);
		changeUser = (Button) activity.findViewById(R.id.btn_changeUser);
		deleateUser = (Button) activity.findViewById(R.id.btn_deleateUser);
		info = (Button) activity.findViewById(R.id.info);
		
	}
	//GETTER OF GUI-ELEMENTS
	public Spinner getSpn_sorttyper() {
		return spn_sorttyper;
	}

	public Button getChangeUser() {
		return changeUser;
	}

	public Button getDeleateUser() {
		return deleateUser;
	}

	public Button getInfo() {
		return info;
	}

	public Spinner getUserlist() {
		return userlist;
	}	
	
}
