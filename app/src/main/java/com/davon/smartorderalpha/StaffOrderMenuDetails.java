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

public class StaffOrderMenuDetails extends Fragment {

    private DatabaseReference fDatabaseOrder, fDatabaseTable;

    private TextView txtStaffOrderMenuNameDetails, txtStaffOrderMenuPriceDetails;
    private EditText edtStaffOrderMenuAmountDetails;
    private Button btnStaffOrderMenuAdd;

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

        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");

        txtStaffOrderMenuNameDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuNameDetails);
        txtStaffOrderMenuNameDetails.setText(StaffOrderMenu.strMenuName);

        txtStaffOrderMenuPriceDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuPriceDetails);
        txtStaffOrderMenuPriceDetails.setText(StaffOrderMenu.strMenuPrice);

        edtStaffOrderMenuAmountDetails = (EditText)v.findViewById(R.id.edtStaffOrderMenuAmountDetails);

        btnStaffOrderMenuAdd = (Button)v.findViewById(R.id.btnStaffOrderMenuAdd);
        btnStaffOrderMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaffOrderTable.strOrderID == "") {


                } else if(StaffOrderTable.strOrderID != "") {
                    Log.v("test", StaffOrderTable.strOrderID);

                    String menuAmount = edtStaffOrderMenuAmountDetails.getText().toString();

                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuName").setValue(StaffOrderMenu.strMenuName);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuPrice").setValue(StaffOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuStatus").setValue("not served");
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuType").setValue(StaffOrderMenuType.strMenuType);

                }

            }
        });

    }

}
