package com.c4ltech.eta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.c4ltech.eta.model.EtkinlikModel;
import com.c4ltech.eta.model.RegisterModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.c4ltech.eta.R.id.action_profil;

import static com.c4ltech.eta.R.id.adminpan;
import static com.c4ltech.eta.R.id.search_bar;
import static com.c4ltech.eta.R.id.visible;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String eposta;
    /*private ListView Listview;
    private List<EtkinlikModel> users = new ArrayList<EtkinlikModel>();

*/
    private List<EtkinlikModel> etkinlikModels = new ArrayList<EtkinlikModel>();
    OzelAdaptor adapterim;
    FirebaseAuth auth; // FireBase değişkeni
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private TextView adim, posta;
    EditText ara;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RegisterModel");
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();
        Query query3 = FirebaseDatabase.getInstance().getReference("RegisterModel").child(id);
        query3.addListenerForSingleValueEvent(valueEventListener);

        //Fragment Başlangıcı
        openFragment(new AnasayfaFragment());
        auth = FirebaseAuth.getInstance(); //Firebase içindekini alıyor


        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            RegisterModel registerModel = dataSnapshot.getValue(RegisterModel.class);

            if (registerModel != null && registerModel.getAd() != null && registerModel.getSoyad() != null && registerModel.geteMail() != null){

                String ad = registerModel.getAd();
                String soyad = registerModel.getSoyad();
                String email = registerModel.geteMail();

                TextView emailim = (TextView) findViewById(R.id.epostaal);
                TextView adim = (TextView) findViewById(R.id.adsoyad);


                adim.setText(ad + " " + soyad);
                emailim.setText(email);

            }




        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(arama).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == adminpan) {
            if (auth.getCurrentUser().getEmail().equalsIgnoreCase("c4ltechno@gmail.com")) {
                item.setVisible(true);
                Intent i = new Intent(getApplicationContext(), AdminPanel.class);
                startActivity(i);
            } else {
                item.setVisible(false);

            }


        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.anasayfam) {
//            openFragment(new AnasayfaFragment());
//
//        }
//        else if (id == R.id.ayarlar) {
//
//        }
//        else if (id == R.id.iletisim) {
//
//        }
//        else if (id == R.id.iletisim) {
//           /* Intent adminPanel = new Intent(Home.this,AdminPanel.class);
//            startActivity(adminPanel);*/
//        }

        if (id == R.id.cikisss) {

            FirebaseAuth.getInstance().signOut();
            Intent yen = new Intent(Home.this, LoginActivity.class);
            startActivity(yen);
            finish();

            Toast.makeText(Home.this, "Başarılı bir şekilde çıkış yapıldı.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.kapat) {
            System.exit(1);
            finish();


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
    //FRAGMENT GEÇİŞ KODLARI


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                    switch (menuItem.getItemId()) {
                        case R.id.action_ev:
                            openFragment(new AnasayfaFragment());
                            break;
                        case R.id.action_bildiri:
                            openFragment(new BildirimFragment());
                            break;
                        case R.id.search_bar:
                           /* Intent git= new Intent(getApplicationContext(),SearchFragment.class);
                            startActivity(git);*/
                            openFragment(new SearchFragment());
                            break;
                        case R.id.action_profil:
                            openFragment(new ProfilFragment());
                            break;

                    }
                    return true;
                }
            };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void openFragment(final Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

