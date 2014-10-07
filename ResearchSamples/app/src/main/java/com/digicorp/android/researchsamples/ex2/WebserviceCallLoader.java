package com.digicorp.android.researchsamples.ex2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by kevin.adesara on 05/09/14.
 */
public class WebserviceCallLoader extends AsyncTaskLoader<JSONObject> {

    private static final String TAG = "WCallLoader";

    private String userId;
    private String offset;
    private String limit;

    private JSONObject data;


    public WebserviceCallLoader(Context context, String userId, String offset, String limit) {
        super(context);
        this.userId = userId;
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public JSONObject loadInBackground() {
        Log.v(TAG, "loadInBackground");
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://devapp.imusti.com/dev/androidradiosv1/sync");

        JSONObject jsonRequest = new JSONObject();
        JSONObject jsonResponse = null;
        StringEntity entity;
        try {
            jsonRequest.put("request", "sync");
            jsonRequest.put("para", new JSONObject()
                            .put("userid", userId)
                            .put("apitoken", "7DE7CC59D1D874A63FB995A4D5D1D2FARAS4GY67")
                            .put("serverdatetime", "2000-08-20 05:57:27")
                            .put("offset", offset)
                            .put("limit", limit)
                            .put("udid", Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID))
            );
            Log.d(TAG, "Request: " + jsonRequest.toString());

            entity = new StringEntity(jsonRequest.toString(), "utf-8");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseString = EntityUtils.toString(response.getEntity());
                Log.d(TAG, "Response: " + responseString);
                jsonResponse = new JSONObject(responseString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonResponse;
    }

    @Override
    public void deliverResult(JSONObject data) {
        Log.v(TAG, "deliverResult");

        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (data != null) {
                onReleaseResources(data);
            }
        }
        JSONObject oldData = this.data;
        this.data = data;

        if(isStarted())
            super.deliverResult(this.data);
    }

    @Override
    protected void onStartLoading() {
        Log.v(TAG, "onStartLoading");
        if(this.data != null) {
            deliverResult(this.data);
        }

        if (takeContentChanged() || this.data == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(JSONObject data) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
}
