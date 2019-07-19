package com.app.nb.ejemplonavdrawer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.nb.ejemplonavdrawer.R;
import com.app.nb.ejemplonavdrawer.fragments.AlertsFragment;
import com.app.nb.ejemplonavdrawer.fragments.EmailFragment;
import com.app.nb.ejemplonavdrawer.fragments.InfoFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);

        setFragmenByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(getApplicationContext(), "Abierto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(getApplicationContext(), "Cerrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_opcion1:
                        Toast.makeText(getApplicationContext(), "Opcion 1 seleccionada", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }


                if (fragmentTransaction) {
                    changeFragment(fragment, menuItem);
                }

                return true;
            }
        });

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmenByDefault() {
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem menuItem) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fragment).commit();

        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle()); //establecer titulos
        drawerLayout.closeDrawers(); //cerrar drawer
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
