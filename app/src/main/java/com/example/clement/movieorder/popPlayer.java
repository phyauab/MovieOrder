package com.example.clement.movieorder;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

//a class for youtube player
public class popPlayer extends YouTubeBaseActivity {

    private static final String TAG = "Youtube Player";
    private String url = "";

    YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_player);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        url = bundle.getString("url");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, (int) (height*.4));

        Log.d(TAG, "onCreate: Starting.");
        mYoutubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);

         mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
             @Override
             public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url);
             }

             @Override
             public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

             }
         };

         mYoutubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
    }
}
