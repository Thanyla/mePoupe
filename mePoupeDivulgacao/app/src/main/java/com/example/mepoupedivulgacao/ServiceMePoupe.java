package com.example.mepoupedivulgacao;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ServiceMePoupe {
    private  static final String DEVELOPER_KEY = "AIzaSyBtS7JDKzQ7Li1B4CSCj2UZKQORuy01WdQ";
    private static final String APPLICATION_NAME = "MePoupe";

    //Alterei o tipo de variavel
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public  static YouTube getService () throws GeneralSecurityException, IOException{
        final NetHttpTransport httpTransport = new com.google.api.client.http.javanet.NetHttpTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String getVideo(long numResultados)
            throws  GeneralSecurityException, IOException, GoogleJsonResponseException{
        YouTube youTubeService = getService();
        //Define and execute the API request
        YouTube.PlaylistItems.List request = youTubeService.playlistItems().list("snippet");
        PlaylistItemListResponse response = request.setKey(DEVELOPER_KEY)
                .setMaxResults(numResultados)
                .setPlaylistId("UU8mDF5mWNGE-Kpfcvnn0bUg")
                .execute();
        return response.toString();
    }

}
