package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffOrderOrderDetails extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtStaffOrderOrderNameDetails, txtStaffOrderOrderAmountDetails, txtStaffOrderOrderPriceDetails;
    private Button btnStaffDelOrderOrderDetails, btnStaffUpdOrderOrderDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_order_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        txtStaffOrderOrderNameDetails = (TextView)v.findViewById(R.id.txtStaffOrderOrderNameDetails);
        txtStaffOrderOrderNameDetails.setText(StaffOrderTableOrder.strMenuName);

        txtStaffOrderOrderAmountDetails = (TextView)v.findViewById(R.id.txtStaffOrderOrderAmountDetails);
        txtStaffOrderOrderAmountDetails.setText(StaffOrderTableOrder.strMenuAmount);

        txtStaffOrderOrderPriceDetails = (TextView)v.findViewById(R.id.txtStaffOrderOrderPriceDetails);
        txtStaffOrderOrderPriceDetails.setText(StaffOrderTableOrder.strMenuPrice);

        btnStaffDelOrderOrderDetails = (Button)v.findViewById(R.id.btnStaffDelOrderOrderDetails);
        btnStaffDelOrderOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fDatabase.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderTableOrder.strMenuName).removeValue();
            }
        });

        btnStaffUpdOrderOrderDetails = (Button)v.findViewById(R.id.btnStaffUpdOrderOrderDetails);
        btnStaffUpdOrderOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update
            }
        });

    }

}
