package com.example.user.kaanbaba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.kaanbaba.model.EtkinlikModel;
import com.example.user.kaanbaba.model.RegisterModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfilFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TextView pAd, pSoyad, pPosta, pSehir, pTelefon;
    FirebaseAuth auth;
    Button guncelle;
    String id;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RegisterModel");
        auth = FirebaseAuth.getInstance();
        id=auth.getCurrentUser().getUid();

        Query query3 = FirebaseDatabase.getInstance().getReference("RegisterModel").child(id);
        query3.addListenerForSingleValueEvent(valueEventListener);


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            RegisterModel registe = dataSnapshot.getValue(RegisterModel.class);
           /* Toast.makeText(getActivity(), auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();*/

                RegisterModel registerModel = dataSnapshot.getValue(RegisterModel.class);
                String ad= registerModel.getAd();
                String soyad=registerModel.getSoyad();
                String email=registerModel.geteMail();
                String sehir=registerModel.getSehir();
                String tel=registerModel.getCepNo();

                pAd.setText(ad+" "+soyad);
                pPosta.setText(email);
                pSehir.setText(sehir);
                pTelefon.setText(tel);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_fragment, container, false);

        pAd = view.findViewById(R.id.profilAd);
        pPosta = view.findViewById(R.id.profilEposta);
        pSehir = view.findViewById(R.id.profilSehir);
        pTelefon = view.findViewById(R.id.profilTelefon);

        return view;

    }


    @Override
    public void onStart() {


        super.onStart();
    }
}
