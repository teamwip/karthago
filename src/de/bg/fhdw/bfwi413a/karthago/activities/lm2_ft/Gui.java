/**********************************************************************************
 * ----------       GUI-CLASS (LM2)- WRITTEN BY: FRANZISKA PLATE       ------------
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

package de.bg.fhdw.bfwi413a.karthago.activities.lm2_ft;

//IMPORTS FOR NECESSARY CLASSES AND PACKAGES

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.bg.fhdw.bfwi413a.karthago.R;

public class Gui {
	
//	TextView mTextviewQuestionFT;
//	Button mButtonSENDFT;
//	EditText mEditTextFT;
	
	//DECLARE GUI-ELEMENTS
	private TextView question;
	private EditText answer;
	private Button commiting;
	private TextView leveltext;
	
	//CONSTRUCTOR
	public Gui(Activity activity){
		//ASSIGN LAYOUT TO ACTIVITY
		activity.setContentView(R.layout.activity_lm2_ft);
//		this.mTextviewQuestionFT = (TextView) activity.findViewById(R.id.textview_question_ft);
//		this.mButtonSENDFT = (Button) activity.findViewById(R.id.btn_send_ft);
//		this.mEditTextFT = (EditText) activity.findViewById(R.id.edittext_ft);

		//INITIALIZE GUI ELEMENTS
		this.question = (TextView) activity.findViewById(R.id.textview_question_ft);
        this.leveltext = (TextView) activity.findViewById(R.id.textview_level_ft);
        this.answer = (EditText) activity.findViewById(R.id.edittext_ft);
        this.commiting = (Button) activity.findViewById(R.id.btn_send_ft);
	}

	//GETTERS AND SETTERS
	public TextView getQuestion() {
		return question;
	}

	public void setQuestion(String text) {
		question.setText(text);
	}

	public EditText getAnswer() {
		return answer;
	}

	public void setAnswer(EditText answer) {
		this.answer = answer;
	}

	public Button getCommiting() {
		return commiting;
	}

	public void setCommiting(Button commiting) {
		this.commiting = commiting;
	}

	public TextView getLeveltext() {
		return leveltext;
	}

	public void setLeveltext(String text) {
		leveltext.setText(text);
	}

//	public TextView getmTextviewQuestionFT() {
//		return mTextviewQuestionFT;
//	}
//
//	public void setmTextviewQuestionFT(TextView mTextviewQuestionFT) {
//		this.mTextviewQuestionFT = mTextviewQuestionFT;
//	}
//
//	public Button getmButtonSENDFT() {
//		return mButtonSENDFT;
//	}
//
//	public void setmButtonSENDFT(Button mButtonSENDFT) {
//		this.mButtonSENDFT = mButtonSENDFT;
//	}
//
//	public EditText getmEditTextFT() {
//		return mEditTextFT;
//	}
//
//	public void setmEditTextFT(EditText mEditTextFT) {
//		this.mEditTextFT = mEditTextFT;
//	}
	
	
	
}
