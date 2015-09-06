/**********************************************************************************
 * ----------       DATA-CLASS (LM3)- WRITTEN BY: LEONIE SCHIBURR       -----------
 *********************************************************************************/

/**
 * The Data Class provides the necessary data, like the question, answers, user and session,
 * from the xml and the database.
 * 
 * It also provides getters for the variables to use the information in the other classes.
 * 
 * Methods and Variables are commented in the Code.
 * 
 *  */

package de.bg.fhdw.bfwi413a.karthago.activities.lm3_g;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import de.bg.fhdw.bfwi413a.karthago.SessionManagement;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;
import de.bg.fhdw.bfwi413a.karthago.xml.Results;
import de.bg.fhdw.bfwi413a.karthago.xml.XMLDomParserAndHandler;

public class Data {
	
	//DECLARE ACTIVITY VARIABLE
			//This variable is necessary to get the current activity, for methods like finish() and getIntent()
			//which are used in other classes of the activity
	private Activity mActivity;
	
	//DECLARE NECESSARY VARIABLES
			//To retrieve the data from the xml file and the database
			//variables for the helper classes like XMLDOMParserAndHandler, Results, SessionManagement and DatabaseHandler
			//The informations are stored in String variables like questionID, UserAnswers a.s.o. which can be requested with getters
	
	XMLDomParserAndHandler mXMLHandler;
	Results mResult = new Results();
	String mQuestionText;
	ArrayList<String> mCorrectAnswers;
	String mQuestionID;
	SessionManagement mSession;
	private de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic mApplicationLogicSelection;
	private DatabaseHandler mDbHandler;
	
	//CONSTRUCTOR
	public Data(Activity activity, Context context){
		
		//initialize mActivity
		mActivity = activity;
		
		//initialize helper classes and data
		//get current questionID
		mQuestionID = mActivity.getIntent().getExtras().getString("currentQuestionId");
		//to get information from the xml file
		mXMLHandler = new XMLDomParserAndHandler(context);
		mCorrectAnswers = new ArrayList<String>();
		//get the questions and Answers for the specific questionID
		mResult = mXMLHandler.questionAndAnswersForFTAndGQuestions(mQuestionID);
		mQuestionText = mResult.getQuestionForFT();
		//save correct answers in a String Array
		mCorrectAnswers = mResult.getCorrectAnswersForFT();
		//to get information from the database 
		mDbHandler = new DatabaseHandler(context);
		//to call the next question with the help of the selection application logic and the method startSingleQuestion
        mApplicationLogicSelection = new de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic();
        //get session for userDetails and cardfileId
		mSession = new SessionManagement(context);
	}

	//GETTERS
	
	public Activity getActivity() {
		return mActivity;
	}
	
	public Results getResult() {
		return mResult;
	}

	public String getQuestionText() {
		return mQuestionText;
	}

	public ArrayList<String> getCorrectAnswers() {
		return mCorrectAnswers;
	}

	public String getQuestionID() {
		return mQuestionID;
	}

	public DatabaseHandler getDbHandler() {
		return mDbHandler;
	}
	
	public SessionManagement getSession() {
		return mSession;
	}
	
	public de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic getApplicationLogicSelection() {
		return mApplicationLogicSelection;
	}

}
