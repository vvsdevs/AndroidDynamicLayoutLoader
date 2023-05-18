package com.dynamiclayout.demolayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.dynamiclayout.loader.DynamicException;
import com.dynamiclayout.loader.DynamicListener;
import com.dynamiclayout.loader.LayoutUpdater;

import java.util.ArrayList;
import java.util.List;

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
    private void InItViews() {
        viewById = (ViewGroup) findViewById(R.id.mainLayout);
    }
    private void RequestData() {
        final String res = "https://raw.githubusercontent.com/vvsdevs/AndroidDynamicLayoutLoader/master/DemoLayout/src/main/assets/";
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


    //Here onImageClick is onClick Method that we are mentioned in json
    // parameters cannot be primitive types
    public static void onImageClick(String name, Integer age, Context context) {
        Toast.makeText(context, "Name: " + name + "\nAge: " + age, Toast.LENGTH_SHORT).show();
    }

    // first argument must be boolean and it will be used as 'checked' flag
    public static void onButtonCheck(boolean isChecked, String message, Context context) {
        Toast.makeText(context, "Checked: " + isChecked + "\nMessage: " + message, Toast.LENGTH_SHORT).show();

    }
}