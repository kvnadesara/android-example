package com.digicorp.android.researchsamples.ex2;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.digicorp.android.researchsamples.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomAsyncLoaderActivity extends ListActivity implements LoaderManager.LoaderCallbacks<JSONObject> {

    private static final String TAG = "CustomAsyncLoaderActivity";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_async_loader);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<JSONObject> onCreateLoader(int i, Bundle bundle) {
        Log.v(TAG, "onCreateLoader");
        progressBar.setVisibility(View.VISIBLE);
        WebserviceCallLoader webserviceCallLoader = new WebserviceCallLoader(this, "0", "1", "10");
        return webserviceCallLoader;
    }

    @Override
    public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
        progressBar.setVisibility(View.GONE);
        Log.v(TAG, "onLoadFinished");
        Log.d(TAG, "Data loaded: " + data);

        if(data.has("albums") && data.optJSONArray("albums") != null) {
            JSONArray albums = data.optJSONArray("albums");
            String[] albumNames = new String[albums.length()];

            for(int i=0; i<albums.length(); i++) {
                JSONObject album = albums.optJSONObject(i);
                albumNames[i] = album.optString("album");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, albumNames);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONObject> loader) {
        progressBar.setVisibility(View.GONE);
        Log.v(TAG, "onLoaderReset");
    }
}
