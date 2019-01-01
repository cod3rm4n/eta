package com.c4ltech.eta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.c4ltech.eta.model.EtkinlikModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AdminPanel extends AppCompatActivity {
    private static final String TAG = "AdminPanel";
    DatabaseReference databaseReference;
    EditText etkBas, etkIc, etkTar;
    Button etksec, etkekleme;
    String id;
    TextView textTargetUri;
    ImageView imagetarget;
    String urlImage = "";


    private List<EtkinlikModel> etkinlikModels = new ArrayList<EtkinlikModel>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        databaseReference = FirebaseDatabase.getInstance().getReference("events/");
        textTargetUri=(TextView)findViewById(R.id.targeturi);

        imagetarget=(ImageView)findViewById(R.id.targetimage);

        etkBas = findViewById(R.id.etkbaslik);
        etkIc = findViewById(R.id.etkicerik);
        etkTar = findViewById(R.id.etktarih);
        etksec = findViewById(R.id.etkresim);
        etkekleme = findViewById(R.id.etkekle);
        etkekleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etkBas.getText().toString().trim().equals("")){
                    Toast.makeText(AdminPanel.this,"Başlığı boş geçemezsiniz",Toast.LENGTH_SHORT).show();
                }
                else if (etkIc.getText().toString().trim().equals("")){
                    Toast.makeText(AdminPanel.this,"İçeriği boş geçemezsiniz",Toast.LENGTH_SHORT).show();
                }
                else if(etkTar.getText().toString().trim().equals("")){
                    Toast.makeText(AdminPanel.this,"Tarihi boş geçemezsiniz",Toast.LENGTH_SHORT).show();
                }
                else{


                    String baslik = etkBas.getText().toString();
                    String tarih = etkTar.getText().toString();
                    String icerik = etkIc.getText().toString();
                    String id = databaseReference.push().getKey();
                    EtkinlikModel model = new EtkinlikModel(baslik, tarih, icerik,id,urlImage);
                    databaseReference.child(id).setValue(model);
                    Intent yeniden = new Intent(AdminPanel.this,Home.class);
                    startActivity(yeniden);
                    Toast.makeText(AdminPanel.this, "Etkinlik eklenmiştir. :)", Toast.LENGTH_SHORT).show();

                }

            }
        });
        etksec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            Uri targetUri=data.getData();
            urlImage = targetUri.toString();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try{
                bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imagetarget.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();

            }
        }


    }

}
