package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class CustMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar btmBarCust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCust);
        setSupportActionBar(toolbar);

        btmBarCust = BottomBar.attach(this, savedInstanceState);
        btmBarCust.setItemsFromMenu(R.menu.menu_customer, this);
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarOrderCust){

            CustOrder fragCustOrder = new CustOrder();
            transaction.replace(R.id.activity_cust_main, fragCustOrder);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarSettingCust){

            CustSetting fragCustSetting = new CustSetting();
            transaction.replace(R.id.activity_cust_main, fragCustSetting);
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
