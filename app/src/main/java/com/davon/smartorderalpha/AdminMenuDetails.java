package com.davon.smartorderalpha;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdminMenuDetails extends Fragment {

    private TextView txtMenuNameDetails, txtMenuPriceDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        txtMenuNameDetails = (TextView)v.findViewById(R.id.txtMenuNameDetails);
        txtMenuNameDetails.setText(AdminMenu.strMenuNameDetails);

        txtMenuPriceDetails = (TextView)v.findViewById(R.id.txtMenuPriceDetails);
        txtMenuPriceDetails.setText(AdminMenu.strMenuPriceDetails);

    }
}
