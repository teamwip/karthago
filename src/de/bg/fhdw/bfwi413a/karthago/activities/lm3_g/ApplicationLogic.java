/**********************************************************************************************
 * ----------       APPLICATIONLOGIC-CLASS (LM3)- WRITTEN BY: LEONIE SCHIBURR       -----------
 *********************************************************************************************/

/**
 * The ApplicationLogic Class provides the program logic for the learn mode.
 * It initializes the Gui with the data from the xml and database, like question and level, and
 * it reacts to the user input and validates its answer and executes the logic for right or wrong
 * answers.
 * 
 * Methods and Variables are commented in the Code.
 * 
 *  */

package de.bg.fhdw.bfwi413a.karthago.activities.lm3_g;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

public class ApplicationLogic {
	
	//DECLARE VARIABLES FOR DATA AND MVC-CLASSES
	private Gui mGui;
	private Data mData;
	Context mContext;
	String mQuestionText;
	String mTextForLevel;
	ArrayList<String> mCorrectAnswers;
	String mUser;
    String mCardfile;
	
  //CONSTRUCTOR
	public ApplicationLogic(Gui gui, Data data, Context context) {
		mGui = gui;
		mData = data;
		mContext = context;
		//Initialize data to gui elements
		initDataToGui();
	}
	
	//METHOD TO INITIALZE GUI WITH DATA FROM XML AND DATABASE
	private void initDataToGui() {
		//Initialize Data from XML to TextView and Button
	    mTextForLevel = "Level: " + mData.getDbHandler().getCurrentLevelForQuestionId(mData.getQuestionID());
	    mGui.setLevelText(mTextForLevel);
	    mQuestionText = mData.getQuestionText();
	    mGui.setQuestion(mQuestionText);
		mCorrectAnswers = mData.getCorrectAnswers();
		mUser = mData.getSession().getUserDetails();
		mCardfile = mData.getSession().getCardfileID();
		
	}

	//METHOD TO CHECK USER ANSWER 
	public void onButtonClicked() {
		//Logic for Answering Process
		
		//build alert dialog with answer and positive and negative buttons
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("Lösung");
		TextView corAns = new TextView(mContext);
		for(int i = 0; i < mCorrectAnswers.size(); i++){
			//set correct answer to TextView
			corAns.append(mCorrectAnswers.get(i).toString());
		}
		corAns.setGravity(Gravity.CENTER_HORIZONTAL);
		builder.setView(corAns);
		//add buttons
		builder.setPositiveButton("Habs gewusst!", new DialogInterface.OnClickListener() {
			//if user knew the answer, he should click "Habs gewusst!"
	           public void onClick(DialogInterface dialog, int id) {
	        	 //Save boolean value --> if user answer is wrong = false, if right = true
	        	boolean rightORwrong = true;
	        	//Save event --> Differentiates between "correct" and "incorrect"
	        	String event_name = "correct";
	        	//Set new level and timestamp according to rightORwrong
	        	long tstamp = new Date().getTime();
				mData.getDbHandler().IncreaseOrDecreaseLevelAndSetNewTimestamp(rightORwrong, mData.getQuestionID(), tstamp);
				//For statistics
				mData.getDbHandler().insertEvent(event_name, tstamp, mUser, mCardfile);
				//close dialog
				dialog.cancel();
				//finish current Activity
				mData.getActivity().finish();
				//start new Activity (next question or back to selection)
				mData.getApplicationLogicSelection().startSingleQuestion(mContext);
	           }
	       });
		builder.setNegativeButton("Falsche Antwort!", new DialogInterface.OnClickListener() {
			//if user didn't knew the answer, he should click "Falsche Antwort!"
	           public void onClick(DialogInterface dialog, int id) {
	        	 //Save boolean value --> if user answer is wrong = false, if right = true
	        	   boolean rightORwrong = false;
	        	 //Save event --> Differentiates between "correct" and "incorrect"
	        	   String event_name = "incorrect";
	        	 //Set new level and timestamp according to rightORwrong
		        	long tstamp = new Date().getTime();
					mData.getDbHandler().IncreaseOrDecreaseLevelAndSetNewTimestamp(rightORwrong, mData.getQuestionID(), tstamp);
					//For statistics
					mData.getDbHandler().insertEvent(event_name, tstamp, mUser, mCardfile);
					//close dialog
					dialog.cancel();
					//finish current Activity
					mData.getActivity().finish();
					//start new Activity (next question or back to selection)
					mData.getApplicationLogicSelection().startSingleQuestion(mContext);
	           }
	       });
		
		// Create the AlertDialog
		builder.show();
		
	}

	
}
