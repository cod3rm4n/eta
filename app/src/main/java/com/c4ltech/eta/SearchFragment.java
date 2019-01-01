package com.c4ltech.eta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.c4ltech.eta.model.EtkinlikModel;

import java.util.List;

public class SearchFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<EtkinlikModel> etkinlikModels;

    EditText theFilter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.arama_fragment, container, false);
        /*theFilter=view.findViewById(R.id.etAramaa);
        listView=view.findViewById(R.id.lvArama);

        ArrayList<String> ekle=new ArrayList<String>();

        ekle.add("Emir");
        ekle.add("Turan");

        adapter=new ArrayAdapter(getContext(),R.layout.arama_fragment,ekle);
        listView.setAdapter(adapter);

        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SearchFragment.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}