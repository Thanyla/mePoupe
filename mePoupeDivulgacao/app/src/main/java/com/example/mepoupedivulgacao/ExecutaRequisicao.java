package com.example.mepoupedivulgacao;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mepoupedivulgacao.ServiceMePoupe;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ExecutaRequisicao extends AsyncTask<Long, Void, String> {
    @Override
    protected String doInBackground(Long... longs) {
        try {
            return new ServiceMePoupe().getVideo(2L);
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
        Log.i("AsyncTask", s);
    }
}
