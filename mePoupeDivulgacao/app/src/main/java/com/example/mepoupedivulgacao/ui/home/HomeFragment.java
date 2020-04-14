package com.example.mepoupedivulgacao.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mepoupedivulgacao.R;
import com.example.mepoupedivulgacao.ServiceMePoupe;
import com.example.mepoupedivulgacao.model.ModelMePoupe;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView listView;
    private  TextView textView;
    private Button bLink1, bLink2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        bLink1 = root.findViewById(R.id.button1);
        bLink2 = root.findViewById(R.id.button2);


         class ExecutaRequisicao extends AsyncTask<Long, Void, String> {
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
                final List<ModelMePoupe> listaVideos = stringToJson(s);
                updateViewData(listaVideos);


                bLink1.setText(listaVideos.get(0).getTitle());
                bLink1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateViewData(listaVideos);
                        carregarVideoYouTube(bLink1);
                    }
                });

                bLink2.setText(listaVideos.get(1).getTitle());
                bLink2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateViewData(listaVideos);
                        carregarVideoYouTube(bLink2);
                    }
                });
            }

        }

        ExecutaRequisicao executaRequisicao = new ExecutaRequisicao();
        executaRequisicao.execute();

        return root;
    }

    public List<ModelMePoupe> stringToJson(String text) {
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

    public void carregarVideoYouTube(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + view.getTag().toString()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }


    private void updateViewData(List<ModelMePoupe> listaVideos) {
        bLink1.setText(listaVideos.get(0).getTitle());
        bLink1.setTag(listaVideos.get(0).getId());

        bLink2.setText(listaVideos.get(1).getTitle());
        bLink2.setTag(listaVideos.get(1).getId());

    }


}
