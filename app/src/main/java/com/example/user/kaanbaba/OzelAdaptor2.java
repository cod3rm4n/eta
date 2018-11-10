package com.example.user.kaanbaba;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.kaanbaba.model.BildirimModel;
import com.example.user.kaanbaba.model.EtkinlikModel;

import java.util.List;

public class OzelAdaptor2 extends BaseAdapter {
    private LayoutInflater userLayoutInflater;
    private List<BildirimModel> userLst2;



    TextView userBaslik;
    ImageView userImg;
    TextView userTarih;

    public OzelAdaptor2(FragmentActivity activity, List<BildirimModel> users) {
        userLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userLst2 = users;
    }


    @Override
    public int getCount() {
        return userLst2.size();
    }
    @Override
    public Object getItem(int position) {
        return userLst2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Listenin her bir satırını belirtir
        View satirGorunumu = userLayoutInflater.inflate(R.layout.user_bildirim,null);
        userBaslik=satirGorunumu.findViewById(R.id.blTitle);

        userTarih=satirGorunumu.findViewById(R.id.blTarih);



        BildirimModel bildirimModelModel = userLst2.get(position);

        userBaslik.setText(bildirimModelModel.getBildiriBaslik());
        userTarih.setText(bildirimModelModel.getBildiriTarih());

        return satirGorunumu;
    }

}
