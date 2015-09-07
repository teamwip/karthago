/***************************************************************************************
 * ----------     STATISTICS-ACTIVITY - WRITTEN BY: VASILIJ SCHNEIDERMANN     ----------
 *
 * Diese Activity zeigt allgemeine Statistiken für den Lernenden an,
 * unterteilt in statische und dynamische Informationen.
 *
 ***************************************************************************************/

package de.bg.fhdw.bfwi413a.karthago.activities.statistics;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import de.bg.fhdw.bfwi413a.karthago.Navigation;
import de.bg.fhdw.bfwi413a.karthago.SessionManagement;
import de.bg.fhdw.bfwi413a.karthago.activities.statistics.Data;
import de.bg.fhdw.bfwi413a.karthago.db.DatabaseHandler;
import de.bg.fhdw.bfwi413a.karthago.xml.Results;
import de.bg.fhdw.bfwi413a.karthago.xml.XMLDomParserAndHandler;
import de.bg.fhdw.bfwi413a.karthago.Util;

public class Init extends Activity {

	Data mData;
	Gui mGui;
	ApplicationLogic mApplicationLogic;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		initData(savedInstanceState);
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();

		XMLDomParserAndHandler xml = new XMLDomParserAndHandler(getApplicationContext());
		int cardFileAmount = xml.getCardFileNames().size();
		mGui.setTextviewCountAmountCardfile(Integer.toString(cardFileAmount));

		Results results = xml.getAllIDs();
		int cardAmount = results.get_list_ids().size();
		mGui.setTextviewCountQuestAll(Integer.toString(cardAmount));

		Map<String, Integer> question_types = Util.frequencies(results.get_list_answer_type());
		int mc_questions = question_types.get("MC");
		int ft_questions = question_types.get("FT");
		int g_questions = question_types.get("G");
		mGui.setTextviewCountQuestMc(Integer.toString(mc_questions));
		mGui.setTextviewCountQuestFt(Integer.toString(ft_questions));
		mGui.setTextviewCountQuestG(Integer.toString(g_questions));

		SessionManagement session = new SessionManagement(getApplicationContext());
		DatabaseHandler db_handler = new DatabaseHandler(getApplicationContext());
		String current_user = session.getUserDetails();
		ArrayList<String> answers = db_handler.getEventsByAnswer(current_user);
		mGui.setTextviewCountQuestAnswered(Integer.toString(answers.size()));

		Map<String, Integer> answer_types = Util.frequencies(answers);
		int right_answers = Util.getOrDefault(answer_types, "correct", 0);
		int wrong_answers = Util.getOrDefault(answer_types, "incorrect", 0);
		mGui.setTextviewCountQuestRight(Integer.toString(right_answers));
		mGui.setTextviewCountQuestWrong(Integer.toString(wrong_answers));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private void initData(Bundle savedInstanceState) {
		mData = new Data(this, savedInstanceState);
	}

	private void initGui() {
		mGui = new Gui(this);
	}

	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mGui, mData, getApplicationContext());
	}

	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui, mApplicationLogic);
	}

	public boolean onKeyDown(int keycode, KeyEvent event){
		  if(keycode==KeyEvent.KEYCODE_BACK){
		   Navigation.startActivityMenu(mData.getmActivity());
		  }
		 return false;
	 }
}
