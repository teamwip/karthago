/**********************************************************************************
 * ----------       DATA-CLASS (LM1)- WRITTEN BY: LEONIE SCHIBURR       -----------
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

package de.bg.fhdw.bfwi413a.karthago.activities.lm1_mc;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES
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
	
	//DECLARE ACTIVITY VARIABLE
		//This variable is necessary to get the current activity, for methods like finish() and getIntent()
		//which are used in other classes of the activity
	
	private Activity mActivity;
	
	//DECLARE NECESSARY VARIABLES
		//To retrieve the data from the xml file and the database
		//variables for the helper classes like XMLDOMParserAndHandler, Results, SessionManagement and DatabaseHandler
		//The informations are stored in String variables like questionID, UserAnswers a.s.o. which can be requested with getters
	
	XMLDomParserAndHandler mXMLHandler;
	ArrayList<String> mQuestionAndAnswers;
	ArrayList<String> mCorrectAnswers;
	Results mResult = new Results();
	String mQuestionID;
	SessionManagement mSession;
	private de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic mApplicationLogicSelection;
	private DatabaseHandler mDbHandler;
	ArrayList<String> mUserAnswers;
	
	//CONSTRUCTOR
	public Data(Activity activity, Context context){
		
		//initialize mActivity
		mActivity = activity;
		
		//INITIALIZE HELPER CLASSES AND DATA
		//gets current questionID
		mQuestionID = mActivity.getIntent().getExtras().getString("currentQuestionId");
		//to get information from the xml file
		mXMLHandler = new XMLDomParserAndHandler(context);
        mQuestionAndAnswers = new ArrayList<String>();
        //gets the questions and Answers for the specific questionID
        mResult = mXMLHandler.getRequiredQuestionAnswersAndCorrectAnswers(mQuestionID);
        //saves questions and answers in a String Array
        mQuestionAndAnswers = mResult.get_list_Question_and_Answers();
        //saves correct answers in a String Array
        mCorrectAnswers = mResult.get_list_correct_answers();
        //to get information from the database 
        mDbHandler = new DatabaseHandler(context);
        //to call the next question with the help of the selection application logic and the method startSingleQuestion
        mApplicationLogicSelection = new de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic();
        //get session for userDetails and cardfileId
        mSession = new SessionManagement(context);
        //to save the answer(s) of the user and work with it/them
        mUserAnswers = new ArrayList<String>();
	}
	
	//GETTERS

	public Activity getActivity() {
		return mActivity;
	}
	
	public ArrayList<String> getQuestionAndAnswers() {
		return mQuestionAndAnswers;
	}

	public ArrayList<String> getCorrectAnswers() {
		return mCorrectAnswers;
	}

	public Results getResult() {
		return mResult;
	}

	public String getQuestionID() {
		return mQuestionID;
	}

	public de.bg.fhdw.bfwi413a.karthago.activities.selection.ApplicationLogic getApplicationLogicSelection() {
		return mApplicationLogicSelection;
	}

	public DatabaseHandler getDbHandler() {
		return mDbHandler;
	}

	public ArrayList<String> getUserAnswers() {
		return mUserAnswers;
	}
	
	public SessionManagement getSession() {
		return mSession;
	}

}
