package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mansoull on 18/2/2017.
 */

public class StaffMenuManage extends Fragment {

    private Button btnStaffMenuChooseTbl, btnStaffMenuPayment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_menu_manage,container,false);
        return view;
    }
}
