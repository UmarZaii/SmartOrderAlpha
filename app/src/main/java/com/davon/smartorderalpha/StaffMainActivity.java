package com.davon.smartorderalpha;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Created by mansoull on 11/2/2017.
 */

public class StaffMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar btmBarStaff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btmBarStaff = BottomBar.attach(this, savedInstanceState);
        btmBarStaff.setItemsFromMenu(R.menu.menu_staff, this);

    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarOrderStaff){

            StaffOrder fragOrderStaff = new StaffOrder();
            transaction.replace(R.id.activity_staff_main, fragOrderStaff);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarUserStaff){

            StaffUser fragmUserStaff = new StaffUser();
            transaction.replace(R.id.activity_staff_main, fragmUserStaff);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarSettingStaff){

            StaffSetting fragmSettingStaff = new StaffSetting();
            transaction.replace(R.id.activity_staff_main, fragmSettingStaff);
            transaction.commit();
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
