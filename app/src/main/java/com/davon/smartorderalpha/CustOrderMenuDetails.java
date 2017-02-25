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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustOrderMenuDetails extends Fragment {

    private FirebaseAuth fAuth;
    private DatabaseReference fDatabaseOrder, fDatabaseTable;

    private TextView txtCustOrderMenuNameDetails, txtCustOrderMenuPriceDetails;
    private EditText edtCustOrderMenuAmountDetails;
    private Button btnCustOrderMenuAdd;

    public static String strLastOrderID = "";

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


        fAuth = FirebaseAuth.getInstance();
        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");

        txtCustOrderMenuNameDetails = (TextView)v.findViewById(R.id.txtCustOrderMenuNameDetails);
        txtCustOrderMenuNameDetails.setText(CustOrderMenu.strMenuName);

        txtCustOrderMenuPriceDetails = (TextView)v.findViewById(R.id.txtCustOrderMenuPriceDetails);
        txtCustOrderMenuPriceDetails.setText(CustOrderMenu.strMenuPrice);

        edtCustOrderMenuAmountDetails = (EditText)v.findViewById(R.id.edtCustOrderMenuAmountDetails);

        fDatabaseOrder.child("lastOrderID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strLastOrderID = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnCustOrderMenuAdd = (Button)v.findViewById(R.id.btnCustOrderMenuAdd);
        btnCustOrderMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String menuAmount = edtCustOrderMenuAmountDetails.getText().toString();

                if(CustSetting.strUserView.equals("empty")) {

                    String userID = fAuth.getCurrentUser().getUid().toString();
                    Integer intNewOrderID = Integer.parseInt(strLastOrderID) + 1;

                    fDatabaseTable.child(CustOrder.strTableNo).child("orderID").setValue(intNewOrderID.toString());
                    fDatabaseTable.child(CustOrder.strTableNo).child("staffView").setValue(intNewOrderID.toString());
                    fDatabaseTable.child(CustOrder.strTableNo).child("tableStatus").setValue("N/A");

                    fDatabaseOrder.child("userView").child(userID).setValue(intNewOrderID.toString());
                    fDatabaseOrder.child("lastOrderID").setValue(intNewOrderID.toString());
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderID").setValue(intNewOrderID.toString());
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderStatus").setValue("New Order");
                    fDatabaseOrder.child(intNewOrderID.toString()).child("tableNo").setValue(CustOrder.strTableNo);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("userID").setValue(userID);

                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuName").setValue(CustOrderMenu.strMenuName);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuPrice").setValue(CustOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuType").setValue(CustOrderMenuType.strMenuType);

                    fDatabaseOrder.child("userView").child(userID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            CustSetting.strUserView = dataSnapshot.getValue().toString();
                            Log.v("getUserView", CustSetting.strUserView);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            CustOrder fragmCustOrder = new CustOrder();
                            transaction.replace(R.id.activity_cust_main, fragmCustOrder);
                            transaction.commit();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else if(!CustSetting.strUserView.equals("empty")) {

                    Log.v("strOrderID", CustSetting.strOrderID);

                    fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuName").setValue(CustOrderMenu.strMenuName);
                    fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuPrice").setValue(CustOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu").child(CustOrderMenu.strMenuName).child("menuType").setValue(CustOrderMenuType.strMenuType);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    CustOrder fragmCustOrder = new CustOrder();
                    transaction.replace(R.id.activity_cust_main, fragmCustOrder);
                    transaction.commit();

                }

            }
        });

    }

}
