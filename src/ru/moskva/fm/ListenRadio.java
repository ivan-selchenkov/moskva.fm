package ru.moskva.fm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class ListenRadio extends Activity implements OnClickListener {
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
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                startService(new Intent(this, PlayService.class));
                break;
            case R.id.stop_button:
                stopService(new Intent(this, PlayService.class));
                break;
        }
    }
}
