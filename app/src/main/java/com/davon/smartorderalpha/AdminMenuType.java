package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminMenuType extends Fragment {

    private Button btnAdminMenuTypeAir, btnAdminMenuTypeNasi, btnAdminMenuTypeMee, btnAdminMenuTypeSup, btnAdminMenuTypeLauk, btnAdminMenuTypeSayur;
    private Button btnAdminMenuTypeTelur, btnAdminMenuTypePagi, btnAdminMenuTypeTengahari, btnAdminMenuTypePetang, btnAdminMenuTypeMalam;
    public static String strAdminMenuTypeSelection = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu_type,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Menu");
        View v = getView();

        btnAdminMenuTypeAir = (Button) v.findViewById(R.id.btnAdminMenuTypeAir);
        btnAdminMenuTypeAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Air";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeNasi = (Button) v.findViewById(R.id.btnAdminMenuTypeNasi);
        btnAdminMenuTypeNasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Nasi";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeMee = (Button) v.findViewById(R.id.btnAdminMenuTypeMee);
        btnAdminMenuTypeMee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Mee";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeSup = (Button) v.findViewById(R.id.btnAdminMenuTypeSup);
        btnAdminMenuTypeSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Sup";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeLauk = (Button) v.findViewById(R.id.btnAdminMenuTypeLauk);
        btnAdminMenuTypeLauk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Lauk";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeSayur = (Button) v.findViewById(R.id.btnAdminMenuTypeSayur);
        btnAdminMenuTypeSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Sayur";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeTelur = (Button) v.findViewById(R.id.btnAdminMenuTypeTelur);
        btnAdminMenuTypeTelur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Telur";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypePagi = (Button) v.findViewById(R.id.btnAdminMenuTypePagi);
        btnAdminMenuTypePagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Pagi";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeTengahari = (Button) v.findViewById(R.id.btnAdminMenuTypeTengahari);
        btnAdminMenuTypeTengahari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Tengahari";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypePetang = (Button) v.findViewById(R.id.btnAdminMenuTypePetang);
        btnAdminMenuTypePetang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Petang";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btnAdminMenuTypeMalam = (Button) v.findViewById(R.id.btnAdminMenuTypeMalam);
        btnAdminMenuTypeMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAdminMenuTypeSelection = "Malam";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragMenu);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }
}
