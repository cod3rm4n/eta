package com.example.user.kaanbaba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.kaanbaba.model.BildirimModel;
import com.example.user.kaanbaba.model.EtkinlikModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BildirimFragment extends Fragment {
    private ListView listview2;
    private List<BildirimModel> usersbildirim = new ArrayList<BildirimModel>();
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    DatabaseReference sil;
    String id;
    String idm1;
    FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bildirim_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();
        listview2 = view.findViewById(R.id.lvBildirimler);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BildirimModel").child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    BildirimModel etkinlik = userSnapShot.getValue(BildirimModel.class);
                    usersbildirim.add(etkinlik);

                }
                OzelAdaptor2 adaptor = new OzelAdaptor2(getActivity(), usersbildirim);
                listview2.setAdapter(adaptor);
                Collections.reverse(usersbildirim);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listview2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                BildirimModel bd = usersbildirim.get(position);
                String etk = bd.getBildiriBaslik();
                idm1 = auth.getCurrentUser().getUid();
                sil = FirebaseDatabase.getInstance().getReference().child("BildirimModel").child(idm1).child(etk);
                sil.removeValue();
                Intent yeniden = new Intent(getActivity(), Home.class);
                startActivity(yeniden);
                Toast.makeText(getActivity(), "Eklediğiniz bildirim başarılı bir şekilde silindi", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


    }
}
