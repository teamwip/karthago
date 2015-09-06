/**********************************************************************************
 * ----------       APPLICATIONLOGIC-CLASS (SELECTION)- WRITTEN BY: AN-NAM PHAM         -----------
 *
 * The ApplicationLogic Class provides methods for the selection functionality.
 * It reacts to the user input (chosen cardfile) and starts the learn mode.
 * 
 * 
 ************************************************************************************/
package de.bg.fhdw.bfwi413a.karthago.activities.selection;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import de.bg.fhdw.bfwi413a.karthago.SessionManagement;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;

public class ApplicationLogic {
	
	//DECLARE NECESSARY VARIABLES
	private Gui mGui;
	private Data mData;
	
	private DatabaseHandler mDbHandler;
	private long mTstamp;
	private SessionManagement mSession;
	private String mRequiredID;
	private Context mContext;
	private String mAnswerType;
	
	//CONSTRUCTOR TO GENERATE A APPLICATIONLOGIC OBJECT
	public ApplicationLogic(Gui gui, Data data, Context context) {
		//INITIALIZE COMPONENTS
		mGui = gui;
		mData = data;
		mDbHandler = new DatabaseHandler(context);
		mSession = new SessionManagement(context);
		mRequiredID = new String();
		mContext = context;
		
	}
	
	//EMPTY CONSTRUCTOR
	public ApplicationLogic() {
	
	}
	
	//FOR CLICK ON EACH BUTTON (CARDFILE): START THE LEARN MODE WITH THE CHOSEN CARDFILE
	public void onButtonClicked(int i) {
		switch ( i ) {
		case 1:
			//DECLARE THE NAME OF THE CARDFILE
			String cardfile_id1 = "Allgemeinwissen";
			
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id1);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 2:
			//DECLARE THE NAME OF THE CARDFILE
			
			String cardfile_id2 = "Fitness";
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id2);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 3:   
			//DECLARE THE NAME OF THE CARDFILE
			
			String cardfile_id3 = "Musik";
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id3);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 4:   
			//DECLARE THE NAME OF THE CARDFILE
			
			String cardfile_id4 = "Architektur";
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id4);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 5:   
			//DECLARE THE NAME OF THE CARDFILE
			String cardfile_id5 = "Filme";
			
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id5);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 6:   
			//DECLARE THE NAME OF THE CARDFILE
			String cardfile_id6 = "Kunst";
			
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id6);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 7:   
			//DECLARE THE NAME OF THE CARDFILE
			String cardfile_id7 = "Geschichte";
			
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id7);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		case 8:   
			//DECLARE THE NAME OF THE CARDFILE
			String cardfile_id8 = "Fotografie";
			
			//WRITE THE CARDFILENAME IN THE SESSION; IMPORTANT FOR METHOD STARTSINGLEQUESTION
				//writes ID of the chosen cardfile into the session
				//startSingleQuestion prompts the cardfile (now the chosen cardfile)
			mSession.writeCardfileID(cardfile_id8);
			
			//START THE QUESTION
			startSingleQuestion(mContext);
			break;
		}
	}
	
	//METHOD THAT STARTS THE FIRST/NEXT QUESTION
		//this question has to be called after every learn mode activity to start a new question or finish the learn mode
	public void startSingleQuestion(Context context){
		//DECLARE AND INITIALIZE NEEDED VARIABLES
		mTstamp = new Date().getTime();
		mSession = new SessionManagement(context);
		mDbHandler = new DatabaseHandler(context);
		String cardfile_id = mSession.getCardfileID();
		//GET THE ID FOR THE FIRST/NEXT QUESTION
			//if there is no question left or there aren't any questions, the method getRequiredQuestionIDs will return an empty string
		mRequiredID = mDbHandler.getRequiredQuestionIDs(mTstamp, cardfile_id, mSession.getUserDetails().toString());
		//CHECK IF ANY QUESTION HAS TO BE ANSWERED
		if(mRequiredID.equals("")){
		//IF NO QUESTION HAS TO BE ANSWERED THE USER IS PROVIDED WITH A MESSAGE (TOAST)
		Toast toast = Toast.makeText(context, "Es müssen keine weiteren Fragen der Kartei " + cardfile_id + " bearbeitet werden oder die Kartei ist nicht verfügbar!", Toast.LENGTH_LONG);
		toast.show();
		//RETURN TO SELECTION ACTIVITY WHEN COMING FROM LEARN MODE ACTIVITY WITH NO QUESTIONS LEFT
		Intent nextScreen = new Intent(context, de.bg.fhdw.bfwi413a.karthago.activities.selection.Init.class);
        nextScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(nextScreen);
		}else{
			//IF A QUESTION HAST TO BE ANSWERED
			//GET ANSWERTYPE OF QUESTION
			mAnswerType = mDbHandler.getAnswerTypeForCertainQuestionID(mRequiredID);
			//CALLS LEARN MODE ACTIVITY BELONGING TO THE PROMPTED ANSWERTYPE
			if(mAnswerType.equals("MC")){
				//CREATE INTENT
				Intent nextScreen = new Intent(context, de.bg.fhdw.bfwi413a.karthago.activities.lm1_mc.Init.class);
				//SET FLAG BECAUSE CLASS IS NO ACTIVITY (APPLICATIONLOGIC)
				nextScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//PUT DATA TO INTENT
				nextScreen.putExtra("currentQuestionId", mRequiredID);
				context.startActivity(nextScreen);
			}
			else if(mAnswerType.equals("FT")){
				//CREATE INTENT
				Intent nextScreen = new Intent(context, de.bg.fhdw.bfwi413a.karthago.activities.lm2_ft.Init.class);
				//SET FLAG BECAUSE CLASS IS NO ACTIVITY (APPLICATIONLOGIC)
				nextScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//PUT DATA TO INTENT
				nextScreen.putExtra("currentQuestionId", mRequiredID);
				context.startActivity(nextScreen);
			}
			else if(mAnswerType.equals("G")){
				//CREATE INTENT
				Intent nextScreen = new Intent(context, de.bg.fhdw.bfwi413a.karthago.activities.lm3_g.Init.class);
				//SET FLAG BECAUSE CLASS IS NO ACTIVITY (APPLICATIONLOGIC)
				nextScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//PUT DATA TO INTENT
				nextScreen.putExtra("currentQuestionId", mRequiredID);
				context.startActivity(nextScreen);
			}	
		}
	}

}
