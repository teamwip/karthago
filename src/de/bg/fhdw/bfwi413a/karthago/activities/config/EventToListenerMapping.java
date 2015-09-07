package de.bg.fhdw.bfwi413a.karthago.activities.config;

//author: Leonie

import android.view.View;
import android.widget.AdapterView;

public class EventToListenerMapping{

	//DECLARE NECESSARY OBJECTS
	private ApplicationLogic mApplicationLogic;
	private Gui mGui;
	
	
	public EventToListenerMapping (Gui gui, ApplicationLogic applicationLogic) {
		//INITIALIZE OBJECTS
		mApplicationLogic = applicationLogic;
		mGui = gui;
		
		//SET ON ITEM SELECTED LISTENER SORT-SPINNER
		mGui.getSpn_sorttyper().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //UPDATE DATABASE
				mApplicationLogic.updateConfigDatabase((int) parent.getItemIdAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            	//METHOD NOT USED
            }
		});
		
		//SET USER CHANGE LISTENER
		mGui.getChangeUser().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//CHANGE USER
				mApplicationLogic.switchUser();
				
			}
		});
		
		//SET USER DELEATE LISTENER
		mGui.getDeleateUser().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//DELEATE USER
				mApplicationLogic.deleateUser();
				
			}
		});
		
		//SET ON INFO BUTTON LISTENER
		mGui.getInfo().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//SHIOW INFO
				mApplicationLogic.info();
				
			}
		});
	}

	
	
}
