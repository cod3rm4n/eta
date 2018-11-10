package com.example.user.kaanbaba;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.kaanbaba.model.BildirimModel;
import com.example.user.kaanbaba.model.EtkinlikModel;
import com.example.user.kaanbaba.model.EventModel;
import com.example.user.kaanbaba.model.RegisterModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IcerikActivity extends AppCompatActivity {
    String aciklama, baslik, tarih;
    String resim;
    ImageView resimim;
    ImageView resim1;
    TextView textViewaciklama, textViewbaslik, textViewtarih;
    ListView listView;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String id;
    FirebaseDatabase firebaseDatabase;
    private List<BildirimModel> usersBildirims = new ArrayList<BildirimModel>();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icerik_activity);
        ImageView geri = findViewById(R.id.icerikGeri);
        resimim=findViewById(R.id.IcerikFoto);
        auth=FirebaseAuth.getInstance();
        final Button bildiri = findViewById(R.id.IcerikBildirimAc);
        textViewaciklama = findViewById(R.id.IcerikYazi);
        textViewbaslik = findViewById(R.id.baslik1);
        resim1 = findViewById(R.id.blFoto);
        id=auth.getCurrentUser().getUid();
        textViewtarih = findViewById(R.id.tarih1);
        listView = findViewById(R.id.lvEtkinlikler);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BildirimModel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    BildirimModel etkinlik = userSnapShot.getValue(BildirimModel.class);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Bundle b = getIntent().getExtras();

        if (b != null) {
            baslik = b.getString("baslik");
            aciklama = b.getString("aciklama");
            tarih = b.getString("tarih");
            resim=b.getString("resim");
            textViewbaslik.setText(baslik);
            textViewtarih.setText(aciklama);
            textViewaciklama.setText(tarih);
            Picasso.get().load(resim).into(resimim);

        }


        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Geri = new Intent(IcerikActivity.this, Home.class);
                finish();
                startActivity(Geri);


            }
        });


        bildiri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (textViewbaslik != null && textViewtarih != null) {
                    String baslik = textViewbaslik.getText().toString();
                    String tarih = textViewtarih.getText().toString();
                    String idm=databaseReference.push().getKey();
                    BildirimModel model = new BildirimModel(baslik,tarih,idm);
                    databaseReference.child(id).child(baslik).setValue(model);

                    Toast.makeText(IcerikActivity.this, "Bildirimlere eklendi", Toast.LENGTH_SHORT).show();
                  }
            }
        });


    }
}
