package com.c4ltech.eta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c4ltech.eta.model.RegisterModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KayitolActivity extends AppCompatActivity {
//    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    FirebaseAuth auth;
    EditText kayitOlAd1,kayitol_sifre1,k_ad,k_soyad,k_sehir,k_cep;
    Button kayitol_btn1;
    private Object database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitol_activity);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("RegisterModel");



    auth = FirebaseAuth.getInstance();
    kayitOlAd1= findViewById(R.id.kayitOlAd);
    kayitol_sifre1=findViewById(R.id.kayitol_sifre);
    kayitol_btn1=findViewById(R.id.kayıtol_btn);
    k_ad=findViewById(R.id.adi);
    k_soyad = findViewById(R.id.soyadi);
    k_sehir=findViewById(R.id.kayitol_sehir);
    k_cep=findViewById(R.id.kayitol_cep);




    kayitol_btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String email = kayitOlAd1.getText().toString().trim();
            final String pass = kayitol_sifre1.getText().toString().trim();
            if (kayitOlAd1.getText().toString().trim().equals("")) {
                Toast.makeText(KayitolActivity.this, "Kayıt olmak için E-mail adresinizi doldurunuz.",Toast.LENGTH_SHORT).show();
            } else if (kayitol_sifre1.getText().toString().trim().equals("")) {
                Toast.makeText(KayitolActivity.this, "Kayıt olmak için şifrenizi doldurunuz.",Toast.LENGTH_SHORT).show();

            }
            else if (kayitol_sifre1.length()<6){
                Toast.makeText(KayitolActivity.this,"Şifreniz 6 haneden fazla olmalıdır.",Toast.LENGTH_SHORT).show();
            }
            else {

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(KayitolActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Giriş sayfasına email ve sifreyi aktarıyor
                            Bundle kutu = new Bundle();
                            String ad=k_ad.getText().toString().trim();
                            String soyad=k_soyad.getText().toString().trim();
                            String k_adi=kayitOlAd1.getText().toString();
                            String sifre = kayitol_sifre1.getText().toString();
                            String sehir = k_sehir.getText().toString();
                            String cep = k_cep.getText().toString();

                            kutu.putString("eposta",k_adi);
                            kutu.putString("pass",sifre);

                            String id = auth.getCurrentUser().getUid();
                            RegisterModel model = new RegisterModel(ad,soyad,k_adi,sifre,sehir,cep);
                            databaseReference.child(id).setValue(model);

                            //Bitişş
                            Toast.makeText(KayitolActivity.this, "Kayıt Başarılı.", Toast.LENGTH_SHORT).show();
                            Intent aktar = new Intent(KayitolActivity.this,LoginActivity.class);
                            aktar.putExtras(kutu); // aktar aksiyonu yanında kutuyuda almasını söylüyoruz
                            startActivity(aktar);
                            finish();
                        } else {
                            Toast.makeText(KayitolActivity.this, "Kayıt Başarısız.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        }
    });
    }
}


