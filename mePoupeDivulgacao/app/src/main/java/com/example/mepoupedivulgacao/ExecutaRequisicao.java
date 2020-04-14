package com.example.mepoupedivulgacao;

import android.os.AsyncTask;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class ExecutaRequisicao extends AsyncTask<Long, Void, String> {
    private String string;
    @Override
    protected String doInBackground(Long... longs) {
        try {
            string = new ServiceMePoupe().getVideo(2L);
            return string;
        } catch (GoogleJsonResponseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        //Log.i("AsyncTask", s);
        stringToJson(s);
    }

    void stringToJson(String text) {
        JSONObject jobject = null;
        JSONArray jItems = null;
        try {
            jobject = new JSONObject(text);
            jItems = new JSONArray(jobject.getString("items"));

            jobject = jItems.getJSONObject(0);
            jobject = (JSONObject) jobject.get("snippet");

            System.out.println(jobject.getString("title"));
            //System.out.println(jobject.getString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
