package com.example.mepoupedivulgacao;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mepoupedivulgacao.model.ModelMePoupe;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


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
        List<ModelMePoupe> listaVideos = stringToJson(s);
        Log.i("", listaVideos.get(1).getUrl());
    }

    List<ModelMePoupe> stringToJson(String text) {
        List<ModelMePoupe> listaVideos = new ArrayList<ModelMePoupe>();
        JSONObject jobject = null;
        JSONObject itemVideo = null;
        JSONArray jItems = null;
        try {
            jobject = new JSONObject(text);
            jItems = new JSONArray(jobject.getString("items"));

            for (int i=0; i<jItems.length(); i++){
                itemVideo = jItems.getJSONObject(i);
                String id = itemVideo.getJSONObject("snippet").getJSONObject("resourceId").getString("videoId");
                String title = itemVideo.getJSONObject("snippet").getString("title");
                String height = itemVideo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("height");
                String url = itemVideo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                String width = itemVideo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("width");

                listaVideos.add(new ModelMePoupe(id, title, height,url, width));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaVideos;
    }

}
