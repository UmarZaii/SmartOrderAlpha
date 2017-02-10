package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminUserType extends Fragment{

    private Button btnAdminUserTypeAdmin, btnAdminUserTypeStaff, btnAdminUserTypeCust;
    public static String strAdminUserTypeSelection = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user_type,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        btnAdminUserTypeAdmin = (Button)v.findViewById(R.id.btnAdminUserTypeAdmin);
        btnAdminUserTypeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAdminUserTypeSelection = "Admin";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminUser fragUser = new AdminUser();
                transaction.replace(R.id.activity_admin_main, fragUser);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnAdminUserTypeStaff = (Button)v.findViewById(R.id.btnAdminUserTypeStaff);
        btnAdminUserTypeStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAdminUserTypeSelection = "Staff";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminUser fragUser = new AdminUser();
                transaction.replace(R.id.activity_admin_main, fragUser);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnAdminUserTypeCust = (Button)v.findViewById(R.id.btnAdminUserTypeCust);
        btnAdminUserTypeCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAdminUserTypeSelection = "Customer";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminUser fragUser = new AdminUser();
                transaction.replace(R.id.activity_admin_main, fragUser);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}
