package com.android.medom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class UserDashBoardActivity extends AppCompatActivity {
    private DrawerLayout homeDrawerLayout;
    private ActionBarDrawerToggle homeToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);
        homeDrawerLayout = (DrawerLayout) findViewById(R.id.user_dashboard);
        homeToggle = new ActionBarDrawerToggle(this, homeDrawerLayout, R.string.open, R.string.close);
        homeDrawerLayout.addDrawerListener(homeToggle);
        homeToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (homeToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
