# Events

Für die dynamischen Teile der Statistik wurde eine besondere Tabelle
in der SQLite3-Datenbank festgehalten, sogenannte "Events".  Jedes
Event hat ID, Namen, Zeitstempel, User-ID und Cardfile-ID.  Die
letzten drei Spalten werden aktuell *nicht* verwendet und sind für
zukünftige Zwecke reserviert.

```
| id | name      | timestamp | user_id | cardfile_id |
|  1 | correct   |       123 |       1 |           1 |
|  2 | incorrect |       456 |       1 |           2 |
|  3 | incorrect |       789 |       2 |           1 |
```

Für die programmatische Nutzung dieser Tabelle war es nötig
Helfermethoden anzulegen zum Einfügen eines Events und Auslesen aller
Events nach User:

```java
public void insertEvent(String name, long timestamp, String user, String cardfile_name) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(NAME, name);
    values.put(EVALUATION_TIMESTAMP, timestamp);
    values.put(USER, user);
    values.put(CARDFILE_NAME, cardfile_name);

    db.insert(TB_NAME_EVENTS, null, values);
    db.close();
}

public ArrayList<String> getEventsByAnswer(String user) {
    ArrayList<String> answers = new ArrayList<String>();
    String select_query = "SELECT " + NAME + " FROM " + TB_NAME_EVENTS + " WHERE " + USER + " = '" + user + "'";
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(select_query, null);

    if (cursor.moveToFirst()) {
        do {
            answers.add(cursor.getString(0));
        } while (cursor.moveToNext());
    }

    return answers;
}
```

Zur Integration der Events in den Workflow reichte es die Methode zum
Einfügen des Events einmal pro Fragentyp in die jeweilige Activity zu
setzen.

# activities.statistics

Die Statistik-Activity hat weder betätigbare Buttons, noch verändert
sie ihre Darstellung nach der Initialisierung.  Aufgrund dieser
Tatsache sind die Applikationslogik, Datenhaltung und das Mapping von
Events zu Listenern minimal gehalten:

```java
import android.content.Context;

public class ApplicationLogic {

    private Data mData;
    private Gui mGui;

    public ApplicationLogic(Gui gui, Data data, Context context) {
        //INITIALIZE COMPONENTS
        mGui = gui;
        mData = data;
        initDataToGui();
    }

    private void initDataToGui() {
    }
}
```

```java
import android.app.Activity;
import android.os.Bundle;

public class Data {

    private Activity mActivity;

    public Data(Activity activity, Bundle savedInstanceState){
        mActivity = activity;
    }

    public Activity getmActivity() {
        return mActivity;
    }
}
```

```java
public class EventToListenerMapping {

    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic){
        mApplicationLogic = applicationLogic;
    }
}
```

In der GUI-Klasse hingegen werden alle Textviews deklariert, auf ihre
initialen Werte gesetzt und Getter und Setter angeboten:

```java
import de.bg.fhdw.bfwi413a.karthago.R;
import android.app.Activity;
import android.widget.TextView;

public class Gui {

    TextView mTextviewCountAmountCardfile;
    TextView mTextviewCountQuestAll;
    TextView mTextviewCountQuestMc;
    TextView mTextviewCountQuestFt;
    TextView mTextviewCountQuestG;
    TextView mTextviewCountQuestAnswered;
    TextView mTextviewCountQuestRight;
    TextView mTextviewCountQuestWrong;

    public Gui(Activity activity){
        activity.setContentView(R.layout.activity_statistics);
        this.mTextviewCountAmountCardfile = (TextView) activity.findViewById(R.id.textview_count_amount_cardfile);
        this.mTextviewCountQuestAll = (TextView) activity.findViewById(R.id.textview_count_quest_all);
        this.mTextviewCountQuestMc = (TextView) activity.findViewById(R.id.textview_count_quest_mc);
        this.mTextviewCountQuestFt = (TextView) activity.findViewById(R.id.textview_count_quest_ft);
        this.mTextviewCountQuestG = (TextView) activity.findViewById(R.id.textview_count_quest_g);
        this.mTextviewCountQuestAnswered = (TextView) activity.findViewById(R.id.textview_count_quest_answered);
        this.mTextviewCountQuestRight = (TextView) activity.findViewById(R.id.textview_count_quest_right);
        this.mTextviewCountQuestWrong = (TextView) activity.findViewById(R.id.textview_count_quest_wrong);
    }

    public TextView getTextviewCountAmountCardfile() {
        return mTextviewCountAmountCardfile;
    }

    public void setTextviewCountAmountCardfile(String text) {
        mTextviewCountAmountCardfile.setText(text);
    }

    public TextView getTextviewCountQuestAll() {
        return mTextviewCountQuestAll;
    }

    public void setTextviewCountQuestAll(String text) {
        mTextviewCountQuestAll.setText(text);
    }

    public TextView getTextviewCountQuestMc() {
        return mTextviewCountQuestMc;
    }

    public void setTextviewCountQuestMc(String text) {
        mTextviewCountQuestMc.setText(text);
    }

    public TextView getTextviewCountQuestFt() {
        return mTextviewCountQuestFt;
    }

    public void setTextviewCountQuestFt(String text) {
        mTextviewCountQuestFt.setText(text);
    }

    public TextView getTextviewCountQuestG() {
        return mTextviewCountQuestG;
    }

    public void setTextviewCountQuestG(String text) {
        mTextviewCountQuestG.setText(text);
    }

    public TextView getTextviewCountQuestAnswered() {
        return mTextviewCountQuestAnswered;
    }

    public void setTextviewCountQuestAnswered(String text) {
        mTextviewCountQuestAnswered.setText(text);
    }

    public TextView getTextviewCountQuestRight() {
        return mTextviewCountQuestRight;
    }

    public void setTextviewCountQuestRight(String text) {
        mTextviewCountQuestRight.setText(text);
    }

    public TextView getTextviewCountQuestWrong() {
        return mTextviewCountQuestWrong;
    }

    public void setTextviewCountQuestWrong(String text) {
        mTextviewCountQuestWrong.setText(text);
    }

}
```

Die Init-Klasse bereitet sowohl den XML-Handler für die statischen
Daten als auch den Datenbank-Handler für die dynamischen Daten vor.
Aus dem XML wird eine Liste aller Karteien extrahiert mithilfe welcher
die Anzahl an Karteien dargestellt werden kann.  Äquivalent dazu
wird eine Liste an Fragen nach Typ extrahiert, von dieser wird
zunächst die Größe eingetragen und durch eine Helfermethode eine
Hashmap erstellt welche die einzigartigen Schlüssel mit ihrer
jeweiligen Häufigkeit enthält.

Aus der Datenbank werden alle Antworten nach Typ extrahiert.  Es wird
daraus eine weitere Hashmap mit den einzigartigen Schlüsseln und den
Häufigkeiten erstellt, jedoch ist es möglich, dass noch keine
Antworten festgehalten wurden und deswegen es eine
Nullpointer-Exception geben kann wenn man nach einem nicht vorhandenen
Schlüssel fragt und diesen in eine Variable vom Typen int speichert.
Aufgrund dessen wird für diese Werte eine Helfermethode verwendet
welche diesen Sonderfall abfängt und bei Bedarf auf einen Rückfallwert
zurückgreift.

```java
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
        if (keycode==KeyEvent.KEYCODE_BACK){
            Navigation.startActivityMenu(mData.getmActivity());
        }
        return false;
    }
}
```

# Utilities

Trotz des beachtlichen Umfanges der Java-Standardbibliothek war es
notwendig einige Helfermethoden zur Erstellung der Statistik zu
schreiben.

Erstere Methode nimmt eine Liste von Strings und transformiert diese
in eine Hashmap in welcher die einzigartigen Werte als Schlüssel mit
ihren jeweiligen Häufigkeiten als Werte eingetragen sind.  Letztere
Methode fragt einen Schlüssel einer solchen Map ab und fällt bei einem
nicht vorhandenen Schlüssel auf einen durch den Programmierer
vorgegebenen Standardwert zurück.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static Map<String, Integer> frequencies (ArrayList<String> list) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String key: list) {
            Integer count = result.get(key);
            if (count == null) {
                result.put(key, 1);
            }
            else {
                result.put(key, count + 1);
            }
        }
        return result;
    }

    public static int getOrDefault(Map<String, Integer> map, String key, int def) {
        Integer result = map.get(key);
        if (result == null) {
            result = def;
        }
        return result;
    }
}
```

# Fazit

Das Projekt war vom Umfang her bewältigbar, jedoch schwierig im Team
umsetzbar, da niemand von uns mit den nötigen Vorkenntnisse für
Androidprogrammierung oder Javakenntnisse jenseits des Unterrichts
auftrumpfen konnte.  Trotz dieser Umstände war ich positiv überrascht
wie gut die Umsetzung einiger Teilgebiete gelungen ist, insbesondere
das UX- und UI-Design.

Meine Teamaufgabe war die Versionierung des Codes mithilfe von Git auf
der Plattform Github.  Die erstmalige Einführung veranschlagte relativ
viel Zeit, hatte sich aber ausgezahlt, da so durch das "Pull
Request"-Modell es einfach war Änderungen nachzuvollziehen, Konflikte
aufzulösen und Fehler schnell zu finden.  Leider konnten wir aufgrund
der knapp ausgefallenen Zeit keine weiteren Features nutzen, dadurch
fiel der Anteil vom Code Review zu kurz.

Trotz aller aufgetretenen Schwierigkeiten war das Projekt für mich
eine lehrreiche Erfahrung gewesen.  Ich wäre an einem zweiten Projekt
dieses Umfangs interessiert, vorausgesetzt Leitung durch Unterricht
und konstantes Feedback sind gegeben.
