/**********************************************************************************
 * ----------       GUI-CLASS (LM1)- WRITTEN BY: LEONIE SCHIBURR       ------------
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

package de.bg.fhdw.bfwi413a.karthago.activities.lm1_mc;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES
import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import de.bg.fhdw.bfwi413a.karthago.R;


public class Gui {
	
	//DECLARE GUI-ELEMENTS
	TextView mQuestion;
	TextView mLevelText;
	CheckBox mAnswer1;
	CheckBox mAnswer2;
	CheckBox mAnswer3;
	CheckBox mAnswer4;
	Button mConfirm;
	
	//CONSTRUCTOR
	public Gui(Activity activity){
		//assign layout to activity
		activity.setContentView(R.layout.activity_lm1_mc);
		//initialize gui elements
		this.mQuestion = (TextView) activity.findViewById(R.id.question);
        this.mLevelText = (TextView) activity.findViewById(R.id.textview_level_mc);
		this.mAnswer1 = (CheckBox) activity.findViewById(R.id.answer1);
		this.mAnswer2 = (CheckBox) activity.findViewById(R.id.answer2);
		this.mAnswer3 = (CheckBox) activity.findViewById(R.id.answer3);
		this.mAnswer4 = (CheckBox) activity.findViewById(R.id.answer4);
		this.mConfirm = (Button) activity.findViewById(R.id.confirm);
	}

	//GETTERS AND SETTERS FOR GUI ELEMENTS
	
	public TextView getQuestion() {
		return mQuestion;
	}
	
	//The setter changes not the Object itself, but just the Text (Question text from XML) 
	public void setQuestion(String text) {
		mQuestion.setText(text);
	}

	public TextView getLevelText() {
		return mLevelText;
	}
	
	//The setter changes not the Object itself, but just the Text (Level-Information from Database) 
	public void setLevelText(String text) {
		mLevelText.setText(text);
	}

	public CheckBox getAnswer1() {
		return mAnswer1;
	}

	//The setter changes not the Object itself, but just the Text (Answer text from XML) 
	public void setAnswer1(String text) {
		mAnswer1.setText(text);
	}

	public CheckBox getAnswer2() {
		return mAnswer2;
	}

	//The setter changes not the Object itself, but just the Text (Answer text from XML) 
	public void setAnswer2(String text) {
		mAnswer2.setText(text);
	}

	public CheckBox getAnswer3() {
		return mAnswer3;
	}

	//The setter changes not the Object itself, but just the Text (Answer text from XML) 
	public void setAnswer3(String text) {
		mAnswer3.setText(text);
	}

	public CheckBox getAnswer4() {
		return mAnswer4;
	}

	//The setter changes not the Object itself, but just the Text (Answer text from XML) 
	public void setAnswer4(String text) {
		mAnswer4.setText(text);
	}

	public Button getConfirm() {
		return mConfirm;
	}

	public void setConfirm(Button confirm) {
		this.mConfirm = confirm;
	}	
	
}
