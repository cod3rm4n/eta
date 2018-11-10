package com.example.user.kaanbaba;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.kaanbaba.model.LoginModel;
import com.example.user.kaanbaba.model.RegisterModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button userKayit;
    Button userLoginBtn;
    FirebaseAuth auth; // FireBase değişkeni
    EditText Email;
    EditText Sifre;
    Button gec;
    CheckBox checkBox1;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* final NavigationMenuItemView kaan = findViewById(R.id.Adminpanel);*/


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("LoginModel");
        auth = FirebaseAuth.getInstance(); //Firebase içindekini alıyor
        Email = findViewById(R.id.emailgiris);
        Sifre = findViewById(R.id.emailpassword);
        gec = findViewById(R.id.kayit_ol);
        checkBox1 = findViewById(R.id.benihatirla);
        userLoginBtn = findViewById(R.id.giris_yap);

        //KAyıt oldan gelen email ve sifre alma yeri
        Bundle kutu = new Bundle();
        kutu = getIntent().getExtras();
        if (kutu != null) {
            String girr = kutu.getString("eposta");
            String pas = kutu.getString("pass");
            Email.setText(girr);
            Sifre.setText(pas);
        }
        //Bitiş

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = Email.getText().toString().trim();
                final String userPass = Sifre.getText().toString().trim();
                if (Email.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "E-mail adresi giriniz.", Toast.LENGTH_SHORT).show();

                } else if (Sifre.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "Şifrenizi giriniz. ", Toast.LENGTH_SHORT).show();
                } else {

                    //Veri tabanına gönderen kısım
                    auth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {//Doğru kaydedildi mi diye sorguluyoruz
//                               String id = databaseReference.push().getKey();
//                               LoginModel model = new LoginModel(userEmail,userPass);
//                               databaseReference.child(id).setValue(model);
                                checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        if(checkBox1.isChecked()){

                                        }
                                    }
                                });
                                Toast.makeText(LoginActivity.this, "Giris Başarılı, Hoşgeldiniz", Toast.LENGTH_SHORT).show();
                                Intent gir = new Intent(LoginActivity.this, Home.class);
                                startActivity(gir);

                            } else {
                                Toast.makeText(LoginActivity.this, "Giris Başarısız", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //Bitiş


                }

            }

        });
        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gir = new Intent(LoginActivity.this, KayitolActivity.class);
                startActivity(gir);

            }
        });
    }

    @Override
    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, Home.class));
            finish();
        }
        super.onResume();

    }
}

