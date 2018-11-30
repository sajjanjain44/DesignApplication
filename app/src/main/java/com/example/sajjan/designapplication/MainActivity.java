package com.example.sajjan.designapplication;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ConstraintLayout constraintLayout;
    TextInputLayout textInputLayout;
    AppCompatEditText editText;
    TextInputLayout passTextInputLayout;
    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;
    int i = 0;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar = findViewById(R.id.toolbarCustom);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        constraintLayout = findViewById(R.id.rootLayout);
        constraintLayout.setOnClickListener(null);

        textInputLayout = findViewById(R.id.username_textInputLayout);
        passTextInputLayout = findViewById(R.id.password_textInputLayout);
        editText = findViewById(R.id.usernameEditText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(editText.getText().toString().isEmpty()) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Username is required");

                }else {
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.getText().toString().isEmpty()) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("Username is required");

                }else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passTextInputLayout.setCounterEnabled(true);
        passTextInputLayout.setCounterMaxLength(10);

//        progress bar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(100);
        progressBar.setProgress(0);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
//                timer.cancel();
//                progressBar.setVisibility(View.GONE);
                if(++i <=100)
                {
                    progressBar.setProgress(i);
                    progressBar.setSecondaryProgress(i+10);
                }else {
                    timer.cancel();
                    progressBar.setVisibility(View.GONE);
                }

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);

            }
        },8000,100);



        builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard Draft?");
        builder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Discard", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();



//        bottom navigaiton button

        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id){
                            case R.id.nearby_id :
                                Toast.makeText(MainActivity.this, "Near by selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.favourates_id :
                                Toast.makeText(MainActivity.this, "Favourates selected", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.recents_id :
                                Toast.makeText(MainActivity.this, "Recent selected", Toast.LENGTH_SHORT).show();
                                return true;
                                default: return false;

                }
            }
        });

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemid = item.getItemId();

        switch (itemid) {
            case R.id.item1_id:
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2_id:
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3_id:
                Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_id:
                Toast.makeText(this, "search action selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cart_id :
                Toast.makeText(this, "cart action selected", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home :
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                toggle.syncState();
                return true;
            default:
                return false;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.inbox_id :
                Toast.makeText(this, "Inbox", Toast.LENGTH_SHORT).show();
                break;
 case R.id.starred_id :
                Toast.makeText(this, "Starred clicked", Toast.LENGTH_SHORT).show();
                break;
 case R.id.send_mail_id :
                Toast.makeText(this, "Inbox", Toast.LENGTH_SHORT).show();
                break;
 case R.id.drafts_id :
                Toast.makeText(this, "Drafts", Toast.LENGTH_SHORT).show();
                break;
 case R.id.all_mail_id :
                Toast.makeText(this, "All Mail", Toast.LENGTH_SHORT).show();
                break;
 case R.id.trash_id :
                Toast.makeText(this, "Trash", Toast.LENGTH_SHORT).show();
                break;
 case R.id.spam_id :
                Toast.makeText(this, "Spam", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}
