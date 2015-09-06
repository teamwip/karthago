/**************************************************************************************************
 * ----------       LEARN-MODE-2-FREE TEXT-ACTIVITY - WRITTEN BY: FRANZISKA PLATE        ----------
 *
 * This Activity is for the Free Text Questions of the cardfile.
 *
 * The main Functions are:
 * - Provide the information for the current question from the xmlfile and the database
 * - Initialize the Activity with gui elements, question data, listeners and logic
 * - Interaction with user and providing answering process
 *
 * Classes, with which this Activity communicates, are:
 * 	- Navigation (Call to start the next Activity)
 *   	- SessionManagement (Get information like userDetails and cardfileId)
 *   	- DatabaseHandler (Retrieve data like the questionId from the database)
 *
 **************************************************************************************************/

package de.bg.fhdw.bfwi413a.karthago.activities.lm2_ft;


//IMPORTS NECESSARY CLASSES AND PACKAGES
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import de.bg.fhdw.bfwi413a.karthago.Navigation;

//Init class inherit from Activity class
public class Init extends Activity{
	
	//DECLARE VARIABLES FOR MVC-CLASSES
	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	//METHOD FOR CREATION OF ACTIVITY
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
	}

	//METHOD FOR RECREATING ACTIVITY (OBSOLETE --> RECREATION VIA DATABASE)
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	//METHOD FOR INITIALIZING DATA
	private void initData(Bundle savedInstanceState) {
		mData = new Data(this, savedInstanceState, getApplicationContext());
	}
	
	//METHOD FOR INITIALIZING GUI
	private void initGui() {
		mGui = new Gui(this);
	}
	
	//METHOD FOR INITIALIZING APPLICATIONLOGIC
	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mGui, mData, getApplicationContext());
	}
	
	//METHOD FOR INITIALIZING EVENTTOLISTENERMAPPING
	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui, mApplicationLogic);
	}
	
	//LISTENER FOR BACK BUTTON (KEYCODE_BACK)
	public boolean onKeyDown(int keycode, KeyEvent event){
		//CATCH BACK-BUTTON EVENT
		if(keycode==KeyEvent.KEYCODE_BACK){
		   //CALL MENU-ACTIVITY
		   Navigation.startActivityMenu(mData.getmActivity());
		}
		return false;
	}
}
