/**********************************************************************************
 * ----------       GUI-CLASS (LM3)- WRITTEN BY: LEONIE SCHIBURR       ------------
 *********************************************************************************/

/**
 * The Gui Class declares variables for the gui elements (TextView, Buttons, ...) for the activity
 * and initializes them with the IDs of the views and widgets implemented in the layout.
 * 
 * It also provides getters and setters to get the gui elements and to set their content to
 * the different questions.
 * 
 * Methods and Variables are commented in the Code.
 * 
 *  */

package de.bg.fhdw.bfwi413a.karthago.activities.lm3_g;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import de.bg.fhdw.bfwi413a.karthago.R;

public class Gui {
	
	//DECLARE GUI-ELEMENTS
	TextView mQuestion;
	TextView mLevelText;
	Button mConfirm;
	
	//CONSTRUCTOR
	public Gui(Activity activity){
		//assign layout to activity
		activity.setContentView(R.layout.activity_lm3_g);
		//initialize gui elements
		this.mQuestion = (TextView) activity.findViewById(R.id.textview_question_g);
		this.mLevelText = (TextView) activity.findViewById(R.id.textview_level_g);
		this.mConfirm = (Button) activity.findViewById(R.id.btn_show_g);
	}
	
	//GETTERS AND SETTERS FOR GUI ELEMENTS

	public TextView getQuestion() {
		return mQuestion;
	}

	//The setter changes not the Object itself, but just the Text (Question text from XML) 
	public void setQuestion(String text) {
		mQuestion.setText(text);
	}
	
	public TextView getLevelText (){
		return mLevelText;
	}
	
	//The setter changes not the Object itself, but just the Text (Question text from XML) 
	public void setLevelText (String text){
		mLevelText.setText(text);
	}

	public Button getConfirm() {
		return mConfirm;
	}

	//The setter changes not the Object itself, but just the Text (Question text from XML) 
	public void setConfirm(String text) {
		mConfirm.setText(text);
	}
	
}
