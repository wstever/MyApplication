package com.example.burt.android_sample_study.tablayout_toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.burt.android_sample_study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_toolbar_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTextView = (TextView) findViewById(R.id.textView);
        mImageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        mImageView.setOnClickListener(this);

        ButterKnife.bind(this);

    }

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
        getMenuInflater().inflate(R.menu.tablayout_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String string = null;
        switch (id) {
            case R.id.nav_me:
                string = "我";
                break;
            case R.id.nav_about:
                string = "关于";
                break;
            case R.id.nav_friend:
                string = "好友";
                break;
            case R.id.nav_manage:
                string = "通知";
                break;
            case R.id.nav_message:
                string = "私信";
                break;
            case R.id.nav_night:
                string = "夜间模式";
                break;
            case R.id.nav_notification:
                string = "通知";
                break;
            case R.id.nav_setting:
                string = "设置";
                break;
            case R.id.nav_suggestion:
                string = "意见反馈";
                break;
            case R.id.nav_theme:
                string = "主题风格";
                break;
        }
        if (!TextUtils.isEmpty(string))
            mTextView.setText("你点击了" + string);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivAvatar) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    @OnClick(R.id.snackbar)
    public void goToSnackbar() {
        startActivity(new Intent(this, SnackBarActivity.class));
        Toast.makeText(this, "啦啦啦", Toast.LENGTH_SHORT).show();
    }


}
