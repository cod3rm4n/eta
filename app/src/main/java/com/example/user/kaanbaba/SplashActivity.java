package com.example.user.kaanbaba;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.kaanbaba.model.EtkinlikModel;
import com.example.user.kaanbaba.model.EventModel;
import com.example.user.kaanbaba.model.RegisterModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private long ms = 0, splashTime = 2000;
    private boolean splashActive = true, paused = false;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String id;

    private List<EtkinlikModel> events = new ArrayList<EtkinlikModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //  setStatusBarColor(R.color.colorPrimary);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("events");


        /*for (int i = 0; i <= 5; i++) {
            events.add(new EtkinlikModel("BURASI LÜLEBURGAZ", "Konumunun Lüleburgaz Bichess", "25/10/2018"));
            id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(events);

        }*/


        final RelativeLayout cl = findViewById(R.id.cl);

        final Thread thread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if (!paused)
                            ms = ms + 100;
                        sleep(100);
                    }
                } catch (Exception e) {

                } finally {
                    if (!isOnline()) {
                        Snackbar snackbar = Snackbar
                                .make(cl, "Lütfen İnternet Bağlantınızı Kontrol Ediniz", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Tekrar Dene", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        recreate();
                                    }
                                });
                        snackbar.show();

                    } else {
                        goMain();
                    }
                }

            }
        };
        thread.start();
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void setStatusBarColor(@ColorRes int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = ContextCompat.getColor(this, statusBarColor);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    private void goMain() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
