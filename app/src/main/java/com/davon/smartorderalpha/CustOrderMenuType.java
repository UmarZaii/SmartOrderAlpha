package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CustOrderMenuType extends Fragment {

    private Button btnCustOrderMenuTypeAir, btnCustOrderMenuTypeNasi, btnCustOrderMenuTypeMee, btnCustOrderMenuTypeSup, btnCustOrderMenuTypeLauk;
    private Button btnCustOrderMenuTypeSayur, btnCustOrderMenuTypeTelur, btnCustOrderMenuTypePagi, btnCustOrderMenuTypeTengahari;
    private Button btnCustOrderMenuTypePetang, btnCustOrderMenuTypeMalam;

    public static String strMenuType = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order_menu_type,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        btnCustOrderMenuTypeAir = (Button)v.findViewById(R.id.btnCustOrderMenuTypeAir);
        btnCustOrderMenuTypeAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Air";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeNasi = (Button)v.findViewById(R.id.btnCustOrderMenuTypeNasi);
        btnCustOrderMenuTypeNasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Nasi";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeMee = (Button)v.findViewById(R.id.btnCustOrderMenuTypeMee);
        btnCustOrderMenuTypeMee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Mee";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeSup = (Button)v.findViewById(R.id.btnCustOrderMenuTypeSup);
        btnCustOrderMenuTypeSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Sup";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeLauk = (Button)v.findViewById(R.id.btnCustOrderMenuTypeLauk);
        btnCustOrderMenuTypeLauk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Lauk";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeSayur = (Button)v.findViewById(R.id.btnCustOrderMenuTypeSayur);
        btnCustOrderMenuTypeSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Sayur";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeTelur = (Button)v.findViewById(R.id.btnCustOrderMenuTypeTelur);
        btnCustOrderMenuTypeTelur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Telur";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypePagi = (Button)v.findViewById(R.id.btnCustOrderMenuTypePagi);
        btnCustOrderMenuTypePagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Pagi";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeTengahari = (Button)v.findViewById(R.id.btnCustOrderMenuTypeTengahari);
        btnCustOrderMenuTypeTengahari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Tengahari";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypePetang = (Button)v.findViewById(R.id.btnCustOrderMenuTypePetang);
        btnCustOrderMenuTypePetang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Petang";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderMenuTypeMalam = (Button)v.findViewById(R.id.btnCustOrderMenuTypeMalam);
        btnCustOrderMenuTypeMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMenuType = "Malam";

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenu fragmCustOrderMenu = new CustOrderMenu();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}
