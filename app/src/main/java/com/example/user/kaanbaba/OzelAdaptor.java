package com.example.user.kaanbaba;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.kaanbaba.model.EtkinlikModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OzelAdaptor extends BaseAdapter {
    private LayoutInflater userLayoutInflater;
    private List<EtkinlikModel> userLst;
    TextView userNameTv;
  private  ImageView userImg;
    TextView userAciklama;
    TextView userTarih;


    public OzelAdaptor(FragmentActivity activity, List<EtkinlikModel> users) {
        userLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userLst = users;
    }


  /*  public OzelAdaptor(Activity activity, List<EtkinlikModel> userList) {


    }

    public OzelAdaptor(AnasayfaFragment anasayfaFragment, List<EtkinlikModel> users) {

    }*/

    //Gönderilerin kaç elemanlı olduğunu bildirir ve ona göre layout çizer.
    @Override
    public int getCount() {
        return userLst.size();
    }
    @Override
    public Object getItem(int position) {
        return userLst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Listenin her bir satırını belirtir
        View satirGorunumu = userLayoutInflater.inflate(R.layout.user,null);
        userNameTv = satirGorunumu.findViewById(R.id.lvBaslik);
        userImg = satirGorunumu.findViewById(R.id.lvFoto);
        userAciklama=satirGorunumu.findViewById(R.id.lvAciklama);
        userTarih=satirGorunumu.findViewById(R.id.lvTarih);


        EtkinlikModel userModel = userLst.get(position);


        userNameTv.setText(userModel.getEtkinlikBaslik());
        userAciklama.setText(userModel.getEtkinlikAciklama());
        userTarih.setText(userModel.getEtkinlikTarih());

        //userImg.setImageResource(R.mipmap.etalogo);



       Picasso.get().load(userModel.getPictureUrl()).into(userImg);

        return satirGorunumu;
    }
}
