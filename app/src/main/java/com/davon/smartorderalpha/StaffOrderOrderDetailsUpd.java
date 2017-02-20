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

public class StaffOrderOrderDetailsUpd extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtStaffOrderOrderNameDetails, txtStaffOrderOrderPriceDetails;
    private EditText edtStaffOrderOrderQuantityDetails;
    private Button btnStaffUpdOrderOrderDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_order_details_upd,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        txtStaffOrderOrderNameDetails = (TextView)v.findViewById(R.id.txtStaffOrderOrderNameDetails);
        txtStaffOrderOrderNameDetails.setText(StaffOrderTableOrder.strMenuName);

        txtStaffOrderOrderPriceDetails = (TextView)v.findViewById(R.id.txtStaffOrderOrderPriceDetails);
        txtStaffOrderOrderPriceDetails.setText(StaffOrderTableOrder.strMenuPrice);

        edtStaffOrderOrderQuantityDetails = (EditText)v.findViewById(R.id.edtStaffOrderOrderQuantityDetails);
        edtStaffOrderOrderQuantityDetails.setText(StaffOrderTableOrder.strMenuAmount);

        btnStaffUpdOrderOrderDetails = (Button)v.findViewById(R.id.btnStaffUpdOrderOrderDetails);
        btnStaffUpdOrderOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderQuantity = edtStaffOrderOrderQuantityDetails.getText().toString();
                Log.d("qty", orderQuantity);

                fDatabase.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderTableOrder.strMenuName).child("menu"+
                        "Amount").setValue(orderQuantity);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderTableOrder fragmStaffOrderTableOrder = new StaffOrderTableOrder();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderTableOrder);
                transaction.commit();
            }
        });

    }

}
