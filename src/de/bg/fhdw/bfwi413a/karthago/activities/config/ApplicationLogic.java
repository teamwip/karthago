package de.bg.fhdw.bfwi413a.karthago.activities.config;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ApplicationLogic {
	//DECLARE NECESSARY OBJECTS
	Gui mGui;
	Data mData;
	Context mContext;
	
	public ApplicationLogic(Gui gui, Data data, Context context) {
		//INITIALIZE OBJECTS
		mGui = gui;
		mData = data;
		mContext = context;
		
		setAdapterToSpinner();
		setUserlistToSpinner();
	}
	
	//SET ADAOPTER OF USERLIST TO SPINNER
	private void setUserlistToSpinner() {
		mGui.userlist.setAdapter(mData.getDataAdapter());
		
	}
	
	//SET ADAPTER TO SORT SPINNER
	public void setAdapterToSpinner(){
		mGui.spn_sorttyper.setAdapter(mData.getAdapter_sort());
		mGui.spn_sorttyper.setSelection(mData.dbHelper.readConfigOption1());
	}
	
	//UPDATE DATABASE
	public void updateConfigDatabase(int position){
		mData.dbHelper.updateConfigOption1(position);
	}
	
	//CHANGE USER
	public void switchUser(){
		//GET SELECTED USERNAME
		String choosedUser = mGui.getUserlist().getSelectedItem().toString();
		//SWITCH SESSION
		mData.session.changeUserWithRedirectToMenu(choosedUser);	
	}
	
	//DELEATE USER
	public void deleateUser(){
		final String user = mGui.getUserlist().getSelectedItem().toString();
		
		//GET CONFIRMATION OF USER TO DELEATE CHOOSED USER (ONLY IF USERNAME TYPED IN CORRECTLY)
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
    	//SET TITLE OF BUILDER
		builder.setTitle("Bitte Namen des zu löschenden Users eingeben:");

    	// SET UP THE INPUT FIELD
    	final EditText input = new EditText(mContext);
    	// SPECIFY THE TYPE OF INPUT EXPECTED; IN THIS CASE TEXT
    	input.setInputType(InputType.TYPE_CLASS_TEXT);
    	builder.setView(input);

    	//SET UP THE BUTTONS
    	builder.setPositiveButton("Löschen", new DialogInterface.OnClickListener() { 
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	    	//CHECK LENGTH [WHEN NOTHING TYPED IN]
    	    	if (input.getText().toString().length() > 0) {
    	    		//ADMIN MUST NOT BE DELEATED
    	    		if(user.equals("ADMIN")){
    					Toast.makeText(mContext, "User ADMIN kann nicht gelöscht werden", Toast.LENGTH_LONG).show();
    				}else{
    					//IF TYPED USER EQUALS SELECTED USER
        				if (user.equals(input.getText().toString())){
        					//DELEATE ROW IN DATABASE
        					mData.dbHelper.deleateUser(user);
        					Toast.makeText(mContext, "User wurde erfolgreich gelöscht!",
                                    Toast.LENGTH_SHORT).show();
        					if(user.equals(mData.session.getUserDetails())){
        						//"LOGOUT" TO DELEATE SESSION AND USER FINALLY
            					mData.session.logoutUser();
        					}else{
        						//RELOAD SPINNER
            					mData.loadUserlistfromDatabse();
            					setUserlistToSpinner();
        					}
        				}else{
        					Toast.makeText(mContext, "Abweichungen im Namen erkannt. User konnte nicht gelöscht werden!",
                                    Toast.LENGTH_SHORT).show();
        				}
    				}
    	    		
    	    	}else{
    	    		Toast.makeText(mContext, "Keinen User eingegeben. Löschvorgang abgebrochen.",
                            Toast.LENGTH_SHORT).show();
    	    	}
    	    	
    	    }
    	});
    	builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	       //CLOSE DIALOG
    	    	dialog.cancel();
    	    }
    	});

    	builder.show();
	}

	public void info() {
		//DECLARE NEW ALERT
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		//SET TITLE
		builder.setTitle("Über diese App");
		//SET TEXTVIEW
		TextView myMsg = new TextView(mContext);
		//SET TEXT AND POSITION OF TEXTVIEW
		myMsg.setText("Version 1.0.0 \n \n Diese App wurde programmiert von: \n Leonie Schiburr \n Julia Körvers \n Franziska Plate \n An-Nam Pham \n Vasilij Schneidermann \n Pascal Thronicke \n Fynn-Ole Callsen \n Patrick Künzl");
		myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
		//APPEND TEXTVIEW TO BUILDER
		builder.setView(myMsg);
		// ADD THE BUTTON
		builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.cancel();
		           }
		       });
		
		// CREATE THE ALERT DIALOG
		builder.show();
		
	}
}
