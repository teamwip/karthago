/**********************************************************************************
 * ----------       LOGIN-ACTIVITY - WRITTEN BY: PATRICK KÜNZL           ----------
 * 
 * Diese Activity hat die Funktion zur Darstellung der Freitext-Fragen der App.
 * 
 * Klassen, mit die diese App kommuniziert sind:
 * 	- SessionManagement (Usersession verwalten)
 *  - DatabaseHandler (Einpflegen der ConfigActivity)
 *  
 * 
 * Nähere Informationen zu den Methoden siehe im Quellcode!
 * 
 *********************************************************************************/

package de.bg.fhdw.bfwi413a.karthago.activities.config;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import de.bg.fhdw.bfwi413a.karthago.Navigation;

public class Init extends Activity {
	//DECLARE NECESSARY OBJECTS
	Gui mGui;
	Data mData;
	ApplicationLogic mApplicationLogic;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	//INITIALIZE DATA-OBJECT
	private void initData(Bundle savedInstanceState) {
		mData = new Data(this, savedInstanceState);
	}
	//INITIALIZE GUI-OBJECT
	private void initGui() {
		mGui = new Gui(this);
	}
	//INITIALIZE APPLICATION-OBJECT
	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mGui, mData, Init.this);
	}
	//INITIALIZE NEW EVENT TO LISTENER MAPPING
	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui, mApplicationLogic);
	}

	//KEYDOWN EVENT
	public boolean onKeyDown(int keycode, KeyEvent event) {
		// GET "BACK" EVENT
		if (keycode == KeyEvent.KEYCODE_BACK) {
			// CALL MENU-ACTIVITY
			Navigation.startActivityMenu(mData.getmActivity());
		}
		return false;
	}

}
