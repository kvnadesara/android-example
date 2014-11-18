package com.digicorp.android.researchsamples.ex5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digicorp.android.researchsamples.R;

public class TestActivity extends ActionBarActivity {

    private static final String TAG = TestActivity.class.getSimpleName();

    private static final String CLIENT_ID = "cdd5adb056fc31677ea1524c96e59ae956533274";
    private static final String CLIENT_SECRET = "4976d767f1fe348514d07aef67d11f13a75f186f";
    private static final String REDIRECT_URI = "http://digicamp.com/oauth";
    private static final String AUTHORIZATION_URL = "https://launchpad.37signals.com/authorization/new?type=web_server";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "NEW INTENT : " + intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnOAuthClick(View view) {
        String url = AUTHORIZATION_URL + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI;
        Uri oauthUri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, oauthUri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        startActivity(intent);
    }
}
