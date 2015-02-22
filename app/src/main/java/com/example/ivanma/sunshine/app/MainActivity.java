package com.example.ivanma.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(LOG_TAG, "onCreate() called");

        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.v(LOG_TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings_main:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_view_location_main:
                openPreferredLocationInMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void openPreferredLocationInMap(){

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String zipCode = sharedPref.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        //No lat and long available, so append query string with zip code
        mapIntent.setData(Uri.parse("geo:0,0?q=" + zipCode));

        //Only start the activity if there is an activity that can handle this intent
        if (mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }else{
            Toast.makeText(this, "No application available", Toast.LENGTH_SHORT).show();
        }
    }
}