package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StaffOrderMenuDetails extends Fragment {

    TextView txtStaffOrderMenuNameDetails, txtStaffOrderMenuPriceDetails;
    EditText edtStaffOrderMenuAmountDetails;
    Button btnStaffOrderMenuAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_menu_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        txtStaffOrderMenuNameDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuNameDetails);
        txtStaffOrderMenuNameDetails.setText(StaffOrderMenu.strMenuName);

        txtStaffOrderMenuPriceDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuPriceDetails);
        txtStaffOrderMenuPriceDetails.setText(StaffOrderMenu.strMenuPrice);

        edtStaffOrderMenuAmountDetails = (EditText)v.findViewById(R.id.edtStaffOrderMenuAmountDetails);

        btnStaffOrderMenuAdd = (Button)v.findViewById(R.id.btnStaffOrderMenuAdd);
        btnStaffOrderMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
