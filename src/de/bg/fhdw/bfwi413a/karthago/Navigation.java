/**********************************************************************************
 * ----------       NAVIGATION-CLASS - WRITTEN BY: LEONIE SCHIBURR       ----------
 *********************************************************************************/
/**
 * The Navigation Class controls the data exchange between
 * the different Activities, as well as the navigation between them --> the Activities Call.
 *  
 * In this class all needed persistent and non persistent data
 * is transmitted
 * 
 *  */

package de.bg.fhdw.bfwi413a.karthago;

import android.app.Activity;
import android.content.Intent;

public class Navigation {

	/** DECLARE CLASS VARIABLES
	 * 
	 * Class variables are needed to identify the class, which will be called with the startActivity-Method.
	 * In this Application the Class variables are the Init classes for each Activity, which initialize the
	 * Activities itself.
	 * 
	 * The usage of Key variables is obsolete for our app because the Activities are identified with the userID and especially the 
	 * Learn Mode Activities are identified with the specific questionID, both stored in the database
	 * 
	 */
	
	public static final Class<?> LOGIN_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.login.Init.class;
	
	public static final Class<?> CONFIG_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.config.Init.class;
	
	public static final Class<?> SELECTION_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.selection.Init.class;
	
	public static final Class<?> MENU_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.menu.Init.class;
	
	public static final Class<?> STATISTICS_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.statistics.Init.class;
	
	public static final Class<?> LM1_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.lm1_mc.Init.class;
	
	public static final Class<?> LM2_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.lm2_ft.Init.class;
	
	public static final Class<?> LM3_ACTIVITY_CLASS =
			de.bg.fhdw.bfwi413a.karthago.activities.lm3_g.Init.class;
	
	/** METHOD FOR ACTIVITY CALL
	 * 
	 * This Method calls another Activity.
	 * The only Parameters are
	 * @param activity
	 * @param classOfActivityToStart
	 * because no futher data has to be transmitted.
	 * All other needed data is retrieved from the database.
	 */
	
	private static void startActivity (Activity activity, Class<?> classOfActivityToStart) {
		Intent intent;
		
		intent = new Intent();
		intent.setClass(activity, classOfActivityToStart);
		activity.startActivity(intent);
		activity.finish();
	}
	
	/** METHOD FOR ACTIVITY CALL WITH ADDITIONAL DATA
	 * 
	 * This Method functions the same way as the other startActivity Method.
	 * However in this case some additional data has to be transmitted.
	 * The parameters key and par can save Key - Value pairs.
	 * These are saved in the Intent with intent.putExtra(key,par)
	 * @param activity
	 * @param classOfActivityToStart
	 * @param key
	 * @param par
	 */
	
	private static void startActivity (Activity activity, Class<?> classOfActivityToStart, String key, String par) {
		Intent intent;
		
		intent = new Intent();
		intent.setClass(activity, classOfActivityToStart);
		intent.putExtra(key, par);
		activity.startActivity(intent);
		activity.finish();
	}
	
	/** METHODS FOR SPECIFIC ACTIVITY CALL
	 * 
	 * These Methods utilize the startActivity Method, together with the parameter callingActivity
	 * to call a specific Activity, which is stated with the specific class variable.
	 * @param callingActivity
	 */
	public static void startActivityLogin (Activity callingActivity) {
		startActivity(callingActivity, LOGIN_ACTIVITY_CLASS);
	}
	
	public static void startActivitySelection (Activity callingActivity) {
		startActivity(callingActivity, SELECTION_ACTIVITY_CLASS);
	}
	
	public static void startActivityConfig (Activity callingActivity) {
		startActivity(callingActivity, CONFIG_ACTIVITY_CLASS);
	}
	
	public static void startActivityMenu (Activity callingActivity){
		startActivity(callingActivity, MENU_ACTIVITY_CLASS);
	}

	public static void startActivityStatistics(Activity callingActivity) {
		startActivity(callingActivity, STATISTICS_ACTIVITY_CLASS);		
	}
	
	/** METHODS FOR SPECIFIC ACTIVITY CALL WITH ADDITIONAL DATA
	 * 
	 * These methods call a specific data, like the methods above.
	 * However they also do transfer additional data with the Parameter questionId.
	 * The questionId is needed to load the right, next following question.
	 * Only the Learn Mode Activities need additional data.
	 * All other needed data is retrieved from the database.
	 * @param callingActivity
	 * @param questionId
	 */
	
	public static void startActivityLM1_MC(Activity callingActivity, String questionId) {
		startActivity(callingActivity, LM1_ACTIVITY_CLASS, "currentQuestionId", questionId);		
	}
	
	public static void startActivityLM2_FT(Activity callingActivity, String questionId) {
		startActivity(callingActivity, LM2_ACTIVITY_CLASS, "currentQuestionId", questionId);		
	}
	
	public static void startActivityLM3_G(Activity callingActivity, String questionId) {
		startActivity(callingActivity, LM3_ACTIVITY_CLASS, "currentQuestionId", questionId);		
	}


	
}
