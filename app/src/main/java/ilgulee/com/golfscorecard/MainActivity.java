package ilgulee.com.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends ListActivity {
    private static final String PREFS_FILE = "ilgulee.com.golfscorecard.preferences";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        int stroke = 0;
        for (int i = 0; i < mHoles.length; i++) {
            stroke=mSharedPreferences.getInt(KEY_STROKECOUNT+i,0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", stroke);
        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }
}
