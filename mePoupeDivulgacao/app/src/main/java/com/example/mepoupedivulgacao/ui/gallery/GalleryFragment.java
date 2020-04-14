package com.example.mepoupedivulgacao.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mepoupedivulgacao.R;

public class GalleryFragment extends Fragment {

    Button button1, button2, button3;
    LottieAnimationView lottieInstragram, lottieFacebook, lottieTwiter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        lottieInstragram = root.findViewById(R.id.lav_android_wave_json);
        lottieInstragram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/nathaliaarcuri/"));
                startActivity(i);
            }
        });

        lottieFacebook = root.findViewById(R.id.lav_facebook);
        lottieFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mepoupenaweb/"));
                startActivity(i);
            }
        });

        lottieTwiter = root.findViewById(R.id.twitter);
        lottieTwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mepoupenaweb"));
                startActivity(i);
            }
        });


        return root;
    }
}
