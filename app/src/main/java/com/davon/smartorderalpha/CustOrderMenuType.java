package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mansoull on 20/2/2017.
 */

public class CustOrderMenuType extends Fragment {

    private Button btnCustOrderMenuTypeAir, btnCustOrderMenuTypeNasi, btnCustOrderMenuTypeMee, btnCustOrderMenuTypeSup, btnCustOrderMenuTypeLauk;

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

    }
}
