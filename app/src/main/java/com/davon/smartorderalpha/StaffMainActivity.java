package com.davon.smartorderalpha;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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

        btmBarStaff = BottomBar.attach(this, savedInstanceState);
        btmBarStaff.setItemsFromMenu(R.menu.menu_staff, this);

    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarOrderStaff){

        } else if (menuItemId == R.id.btmBarUserStaff){

//            AdminMenu fragmMenu = new AdminMenu();
//            transaction.replace(R.id.activity_admin_main, fragmMenu);
//            transaction.addToBackStack(null);
//            transaction.commit();

        } else if (menuItemId == R.id.btmBarSettingStaff){

//            AdminUserType fragmUserType = new AdminUserType();
//            transaction.replace(R.id.activity_admin_main, fragmUserType);
//            transaction.addToBackStack(null);
//            transaction.commit();

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
