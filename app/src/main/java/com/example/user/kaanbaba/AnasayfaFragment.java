package com.example.user.kaanbaba;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.kaanbaba.model.EtkinlikModel;
import com.example.user.kaanbaba.model.EventModel;
import com.example.user.kaanbaba.model.RegisterModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AnasayfaFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference oku;
    DatabaseReference ref;
    FirebaseDatabase yen;
    SearchView ara;
    OzelAdaptor eventAdaptor;
    FirebaseAuth auth;
    DatabaseReference referans;
    FirebaseDatabase databasem;
    EditText gir;

    // EditText arama;
    Button buton;
    private ListView listview;
    private ListView listview1;
    private List<EtkinlikModel> etkinlikModels = new ArrayList<EtkinlikModel>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.anasayfa_fragment, container, false);

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview = view.findViewById(R.id.lvEtkinlikler);
        listview1 = view.findViewById(R.id.lvArama);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("events");
        databasem = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ara = view.findViewById(R.id.search_bar);
        gir = view.findViewById(R.id.emailgiris);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    EtkinlikModel etkinlik = userSnapShot.getValue(EtkinlikModel.class);
                    String pictureUrl = etkinlik.getPictureUrl();
                    etkinlikModels.add(etkinlik);


                }
                OzelAdaptor adaptor = new OzelAdaptor(getActivity(), etkinlikModels);
                listview.setAdapter(adaptor);
                Collections.reverse(etkinlikModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (auth.getCurrentUser().getEmail().equalsIgnoreCase("emirtrn13@gmail.com")) {
                    EtkinlikModel etkinlik = etkinlikModels.get(position);
                    String etkinlikId = etkinlik.getId();
                    oku = FirebaseDatabase.getInstance().getReference().child("events").child(etkinlikId);
                    oku.removeValue();
                    Intent yeniden = new Intent(getActivity(), Home.class);
                    startActivity(yeniden);
                    Toast.makeText(getActivity(), "Başarılı bir şekilde silindi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Yetkisiz Erişim", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle kutu = new Bundle();

                kutu.putString("baslik", etkinlikModels.get(position).getEtkinlikBaslik());
                kutu.putString("aciklama", etkinlikModels.get(position).getEtkinlikAciklama());
                kutu.putString("tarih", etkinlikModels.get(position).getEtkinlikTarih());
                kutu.putString("resim", etkinlikModels.get(position).getPictureUrl());
                Intent aktar = new Intent(getActivity(), IcerikActivity.class);
                aktar.putExtras(kutu);
                startActivity(aktar);
            }
        });
    }


}
