/***********************************************************************************
 * ----------       SELECTION-ACTIVITY - WRITTEN BY: AN-NAM PHAM              ----------
 * 
 * This activity provides the functionality of the selection of the different cardfiles.
 * 
 * The main functions are:
 * 		- Providing the user with various cardfiles.
 * 		- Opening the learn mode with the chosen cardfile.
 * 		- Informing the user if cardfile contains no questions or if all questions are not available yet (timestamp)
 * 
 * 
 *  This classes are necessary for this activity:
 * 		- Navigation (back to menu or forward to learn mode)
 * 		- SessionManagement (writeCardfileID, getCardfileID, getUserDetails)
 * 		- DatabaseHandler (gets the ID and the answertype of the question, that has to be executed)
 * 
 * 
 **********************************************************************************/

package de.bg.fhdw.bfwi413a.karthago.activities.selection;

/**********************************************************************************
 * ----------       INIT-CLASS (SELECTION)- WRITTEN BY: AN-NAM PHAM         -----------
 *
 * The Init Class initializes the activity and its classes:
 * 		- Gui
 * 		- Data
 * 		- ApplicationLogic 
 * 		- EventtoListenerMapping
 * 
 * It also reacts if the back button of the device is pressed.
 * 
 * The onSaveInstanceState method is not used, because the implementation is obsolete.
 * 
 * In case that the back-button of the device is pressed, the user will be navigated back to the menu activity.
 * 
 ************************************************************************************/

// IMPORTS FOR NEEDED CLASSES
import de.bg.fhdw.bfwi413a.karthago.Navigation;
import de.bg.fhdw.bfwi413a.karthago.activities.selection.Data;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Init extends Activity{

	//DECLARE VARIABLES FOR MVC-CLASSES
	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//INITIALIZE MVC-CLASSES
		initData();
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
	}
	
	//METHOD FOR RECREATING ACTIVITY
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	//METHOD FOR INITIALIZING DATA
	private void initData() {
		mData = new Data(this);
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
	
	//LISTENER FOR BACK BUTTON
	public boolean onKeyDown(int keycode, KeyEvent event){
		//CALL THE "BACK-BUTTON" EVENT
		  if(keycode==KeyEvent.KEYCODE_BACK){
			  //CALL MENU-ACTIVITY
		   Navigation.startActivityMenu(mData.getmActivity());
		  }
		 return false;
		 }
	
	//DISABLE BUTTON IF NO QUESTIONS ARE AVAILABLE (TIMESTAMP OR NO QUESTIONS IN CARDFILE)
	public void disableAllButtons(){
		mGui.diasableButtons();
	}
	
}
