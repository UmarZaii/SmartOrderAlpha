package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StaffOrderMenuType extends Fragment {

    private Button btnStaffOrderMenuTypeAir, btnStaffOrderMenuTypeNasi, btnStaffOrderMenuTypeMee, btnStaffOrderMenuTypeSup, btnStaffOrderMenuTypeLauk;
    private Button btnStaffOrderMenuTypeSayur, btnStaffOrderMenuTypeTelur, btnStaffOrderMenuTypePagi, btnStaffOrderMenuTypeTengahari;
    private Button btnStaffOrderMenuTypePetang, btnStaffOrderMenuTypeMalam;

    public static String strMenuType = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_menu_type,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        btnStaffOrderMenuTypeAir = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeAir);
        btnStaffOrderMenuTypeAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Air";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeNasi = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeNasi);
        btnStaffOrderMenuTypeNasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Nasi";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeMee = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeMee);
        btnStaffOrderMenuTypeMee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Mee";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeSup = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeSup);
        btnStaffOrderMenuTypeSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Sup";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeLauk = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeLauk);
        btnStaffOrderMenuTypeLauk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Lauk";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeSayur = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeSayur);
        btnStaffOrderMenuTypeSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Sayur";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeTelur = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeTelur);
        btnStaffOrderMenuTypeTelur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Telur";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypePagi = (Button)v.findViewById(R.id.btnStaffOrderMenuTypePagi);
        btnStaffOrderMenuTypePagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Pagi";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeTengahari = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeTengahari);
        btnStaffOrderMenuTypeTengahari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Tengahari";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypePetang = (Button)v.findViewById(R.id.btnStaffOrderMenuTypePetang);
        btnStaffOrderMenuTypePetang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Petang";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffOrderMenuTypeMalam = (Button)v.findViewById(R.id.btnStaffOrderMenuTypeMalam);
        btnStaffOrderMenuTypeMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Malam";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenu fragmStaffOrderMenu = new StaffOrderMenu();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}
