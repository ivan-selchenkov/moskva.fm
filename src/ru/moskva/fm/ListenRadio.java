package ru.moskva.fm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import java.io.File;
import java.io.IOException;


public class ListenRadio extends Activity implements OnClickListener {

    private static final String TAG = "Moskva.fm";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View play_button = findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        View stop_button = findViewById(R.id.stop_button);
        stop_button.setOnClickListener(this);

        View test_button = findViewById(R.id.test_button);
        test_button.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                startService(new Intent(this, PlayService.class));
                break;
            case R.id.stop_button:
                stopService(new Intent(this, PlayService.class));
                break;
            case R.id.test_button:
                break;
//                Channel ch = new Channel("4015");
//
//                Track t = ch.getFirstTranslationTrack();
//
//                Log.d(TAG, t.url);
//
//                Log.d(TAG, t.date.toString());
//
//                Track t1 = ch.getNextTrack(t);
//
//                Log.d(TAG, t1.url);
//
//                Log.d(TAG, t1.date.toString());

        }
    }
}
