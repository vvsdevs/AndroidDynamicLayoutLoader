package com.dynamiclayout.demolayout;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.dynamiclayout.loader.DynamicException;
import com.dynamiclayout.loader.DynamicListener;
import com.dynamiclayout.loader.LayoutUpdater;

public class MainActivity extends AppCompatActivity {
    ViewGroup viewById;
    private static final String TAG = "Testing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InItViews();
        RequestData();
    }

    private void RequestData() {
        final String res = "https://raw.githubusercontent.com/vvsdevs/Demo/master/app/src/main/assets/";
        Log.d(TAG, "RequestData: ");
        try {
            new LayoutUpdater(res, "login_screen", viewById)
                    .setListener(new DynamicListener() {
                        @Override
                        public void onSuccess(String message) {
                            Log.d(TAG, "onSuccess: ");
                        }

                        @Override
                        public void onError(String message) {
                            Log.d(TAG, "onError: ");
                            Toast.makeText(MainActivity.this, "Something Went Wrong with json....", Toast.LENGTH_SHORT).show();

                        }
                    }).setAsyncPause(500000)
                    .initialize();
        }catch(DynamicException e) {
            finish();
        }
    }

    private void InItViews() {
        viewById = (ViewGroup) findViewById(R.id.mainLayout);
    }
}