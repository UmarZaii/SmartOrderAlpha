package com.davon.smartorderalpha;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class AdminMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar btmBarAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        btmBarAdmin = BottomBar.attach(this, savedInstanceState);
        btmBarAdmin.setItemsFromMenu(R.menu.menu_admin, this);

    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarHome){

        } else if (menuItemId == R.id.btmBarMenu){

            AdminMenuAdd fragmMenu = new AdminMenuAdd();
            transaction.replace(R.id.activity_admin_main, fragmMenu);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarUser){

        } else if (menuItemId == R.id.btmBarSetting){

        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}