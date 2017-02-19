package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 20/2/2017.
 */

public class CustOrderMenuDetails extends Fragment {

    private DatabaseReference fDatabaseOrder, fDatabaseTable;

    private TextView txtCustOrderMenuNameDetails, txtCustOrderMenuPriceDetails;
    private EditText edtCustOrderMenuAmountDetails;
    private Button btnCustOrderMenuAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order_menu_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");

        txtCustOrderMenuNameDetails = (TextView)v.findViewById(R.id.txtCustOrderMenuNameDetails);
        txtCustOrderMenuNameDetails.setText(CustOrderMenu.strMenuName);

        txtCustOrderMenuPriceDetails = (TextView)v.findViewById(R.id.txtCustOrderMenuPriceDetails);
        txtCustOrderMenuPriceDetails.setText(CustOrderMenu.strMenuPrice);

        edtCustOrderMenuAmountDetails = (EditText)v.findViewById(R.id.edtCustOrderMenuAmountDetails);

        btnCustOrderMenuAdd = (Button)v.findViewById(R.id.btnCustOrderMenuAdd);
        btnCustOrderMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CustOrderTable.strOrderID == "") {


                } else if(CustOrderTable.strOrderID != "") {
                    Log.v("test", CustOrderTable.strOrderID);

                    String menuAmount = edtCustOrderMenuAmountDetails.getText().toString();

                    fDatabaseOrder.child(CustOrderTable.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(CustOrderTable.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuName").setValue(CustOrderMenu.strMenuName);
                    fDatabaseOrder.child(CustOrderTable.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuPrice").setValue(CustOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(CustOrderTable.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuStatus").setValue("not served");
                    fDatabaseOrder.child(CustOrderTable.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuType").setValue(CustOrderMenuType.strMenuType);

                }

            }
        });

    }
}
