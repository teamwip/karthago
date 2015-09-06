/**********************************************************************************
 * ----------       DATA-CLASS (LM2)- WRITTEN BY: FRANZISKA PLATE       -----------
 *********************************************************************************/

/**
 * The Data Class provides the necessary data, like the question, answers, user and session,
 * from the XML and the database.
 *
 * It also provides getters for the variables to use the information in the other classes.
 *
 * Methods and Variables are commented in the Code.
 *
 *  */

package de.bg.fhdw.bfwi413a.karthago.activities.lm2_ft;

//IMPORTS NECESSARY CLASSES AND PACKAGES
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import de.bg.fhdw.bfwi413a.karthago.SessionManagement;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;
import de.bg.fhdw.bfwi413a.karthago.xml.Results;
import de.bg.fhdw.bfwi413a.karthago.xml.XMLDomParserAndHandler;


public class Data {
	
	//Not used, because obsolete
//	private static final String KEY_LM2_ID = "M";
//	private int mLM2Id;
//	private final int DEFAULT_LM2_ID = 0;
	
	//DECLARE ACTIVITY VARIABLE
	//This variable is necessary to get the current activity, for methods like finish() and getIntent()
	//which are used in other classes of the activity
	private Activity mActivity;
	
	private XMLDomParserAndHandler xmlhandler;
	private Results result = new Results();
	private String questionText;
	private ArrayList<String> correctAnswers;
	private String questionID;
	private de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic ApplicationLogicSelection;
	private DatabaseHandler dbhandler;
	private String userAnswer;
	private SessionManagement session;
	
	//CONSTRUCTOR
	public Data(Activity activity, Bundle savedInstanceState, Context context){
		Intent intent;
		
		mActivity = activity;
		if ( savedInstanceState == null ) {
			intent = mActivity.getIntent();
			//Not used, because obsolete
			//mLM2Id = intent.getIntExtra(Navigation.KEY_LM2_ID, DEFAULT_LM2_ID);
		}
		else {
			//Not used, because obsolete
			//restoreDataFromBundle(savedInstanceState);
		}
		
		//INITIALIZE HELPER CLASSES AND DATA
		//gets current questionID
		questionID = mActivity.getIntent().getExtras().getString("currentQuestionId");
		//to get information from the xml file
		xmlhandler = new XMLDomParserAndHandler(context);
		//create new ArrayList for correctAnswers
		correctAnswers = new ArrayList<String>();
		//gets the questions and Answers for the specific questionID
		result = xmlhandler.questionAndAnswersForFTAndGQuestions(questionID);
		//get questionText form the database
		questionText = result.getQuestionForFT();
		//get all correct answers
		correctAnswers = result.getCorrectAnswersForFT();
		//create new database objekt
		dbhandler = new DatabaseHandler(context);
        //to call the next question with the help of the selection application logic and the method startSingleQuestion
        ApplicationLogicSelection = new de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic();
        //create new session with userDetails and cardfileId
        session = new SessionManagement(context);
	}

	//GETTERS
	public Activity getmActivity() {
		return mActivity;
	}
	
	public Results getResult() {
		return result;
	}

	public String getQuestionText() {
		return questionText;
	}

	public ArrayList<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public String getQuestionID() {
		return questionID;
	}

	public de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic getApplicationLogicSelection() {
		return ApplicationLogicSelection;
	}

	public DatabaseHandler getDbhandler() {
		return dbhandler;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public SessionManagement getSession() {
		return session;
	}
	
//	public int getmMenuId() {
//		return mLM2Id;
//	}
	
	// save and restore data

//	public void saveDataInBundle(Bundle bundle) {
//		bundle.putInt(KEY_LM2_ID, mLM2Id);
//	}
//	
//	public void restoreDataFromBundle(Bundle bundle) {
//		mLM2Id = bundle.getInt(KEY_LM2_ID);
//	}
	
}
