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
	String questionText;
	String textForLevel;
	ArrayList<String> mCorrectAnswers;
	String user;
    String cardfile;
	
  //CONSTRUCTOR
	public ApplicationLogic(Gui gui, Data data, Context context) {
		mGui = gui;
		mData = data;
		mContext = context;
		//INITIALIZE DATA TO GUI ELEMENTS
		initDataToGui();
	}
	
	//METHOD TO INITIALZE GUI WITH DATA FROM XML AND DATABASE
	private void initDataToGui() {
		//Inits Data from XML to TextView and Button
	    textForLevel = "Level: " + mData.getDbhandler().getCurrentLevelForQuestionId(mData.getQuestionID());
	    mGui.setLeveltext(textForLevel);
	    questionText = mData.getQuestionText();
	    mGui.setQuestion(questionText);
		mCorrectAnswers = mData.getCorrectAnswers();
		user = mData.getSession().getUserDetails();
		cardfile = mData.getSession().getCardfileID();
		
	}

	//METHOD TO CHECK USER ANSWER 
	public void onButtonClicked() {
		//Logic for Answering Process
		
		//BUILD ALERT DIALOG WITH ANSWER AND WRONG OR RIGHT BUTTONS
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("Das ist die Lösung. Hast du es gewusst?");
		TextView corAns = new TextView(mContext);
		for(int i = 0; i < mCorrectAnswers.size(); i++){
			//SET CORRECT ANSWERS TO TEXTVIEW
			corAns.append(mCorrectAnswers.get(i).toString());
		}
		corAns.setGravity(Gravity.CENTER_HORIZONTAL);
		builder.setView(corAns);
		//ADD BUTTONS
		builder.setPositiveButton("Ich habs gewusst!", new DialogInterface.OnClickListener() {
			//if user knew the answer, he should click "Habs gewusst!"
	           public void onClick(DialogInterface dialog, int id) {
	        	 //Saves boolean value --> if user answer is wrong = false, if right = true
	        	boolean rightORwrong = true;
	        	//Saves event --> Differentiates between "correct" and "incorrect"
	        	String event_name = "correct";
	        	//Sets new level and timestamp according to rightORwrong
	        	long tstamp = new Date().getTime();
				mData.getDbhandler().IncreaseOrDecreaseLevelAndSetNewTimestamp(rightORwrong, mData.getQuestionID(), tstamp);
				//For statistics
				mData.getDbhandler().insertEvent(event_name, tstamp, user, cardfile);
				//closes dialog
				dialog.cancel();
				//Finishes current Activity
				mData.getmActivity().finish();
				//starts new Activity (next question or back to selection)
				mData.getApplicationLogicSelection().startSingleQuestion(mContext);
	           }
	       });
		builder.setNegativeButton("Leider nicht...", new DialogInterface.OnClickListener() {
			//if user didn't knew the answer, he should click "Falsche Antwort!"
	           public void onClick(DialogInterface dialog, int id) {
	        	 //Saves boolean value --> if user answer is wrong = false, if right = true
	        	   boolean rightORwrong = false;
	        	 //Saves event --> Differentiates between "correct" and "incorrect"
	        	   String event_name = "incorrect";
	        	 //Sets new level and timestamp according to rightORwrong
		        	long tstamp = new Date().getTime();
					mData.getDbhandler().IncreaseOrDecreaseLevelAndSetNewTimestamp(rightORwrong, mData.getQuestionID(), tstamp);
					//For statistics
					mData.getDbhandler().insertEvent(event_name, tstamp, user, cardfile);
					//closes dialog
					dialog.cancel();
					//Finishes current Activity
					mData.getmActivity().finish();
					//starts new Activity (next question or back to selection)
					mData.getApplicationLogicSelection().startSingleQuestion(mContext);
	           }
	       });
		
		// Create the AlertDialog
		builder.show();
		
	}

	
}
