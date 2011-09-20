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

        updateButtonStates();

    }

    @Override
    public void onResume() {

        super.onResume();

        updateButtonStates();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                startService(new Intent(this, PlayService.class));
                setButtonState(true);
                break;
            case R.id.stop_button:
                stopService(new Intent(this, PlayService.class));
                setButtonState(false);
                break;
            case R.id.test_button:
                break;
        }
    }

    private void updateButtonStates() {

        boolean isStarted = PlayService.isStarted;

        setButtonState(isStarted);

    }

    private void setButtonState(boolean state) {

        View play_button = findViewById(R.id.play_button);

        View stop_button = findViewById(R.id.stop_button);

        play_button.setEnabled(!state);

        stop_button.setEnabled(state);
    }

}
