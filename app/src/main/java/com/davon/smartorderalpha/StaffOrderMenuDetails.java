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

public class StaffOrderMenuDetails extends Fragment {

    private FirebaseAuth fAuth;
    private DatabaseReference fDatabaseOrder, fDatabaseTable;

    private TextView txtStaffOrderMenuNameDetails, txtStaffOrderMenuPriceDetails;
    private EditText edtStaffOrderMenuAmountDetails;
    private Button btnStaffOrderMenuAdd;

    public static String strLastOrderID = "";

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


        fAuth = FirebaseAuth.getInstance();
        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");

        txtStaffOrderMenuNameDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuNameDetails);
        txtStaffOrderMenuNameDetails.setText(StaffOrderMenu.strMenuName);

        txtStaffOrderMenuPriceDetails = (TextView)v.findViewById(R.id.txtStaffOrderMenuPriceDetails);
        txtStaffOrderMenuPriceDetails.setText(StaffOrderMenu.strMenuPrice);

        edtStaffOrderMenuAmountDetails = (EditText)v.findViewById(R.id.edtStaffOrderMenuAmountDetails);

        fDatabaseOrder.child("lastOrderID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strLastOrderID = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnStaffOrderMenuAdd = (Button)v.findViewById(R.id.btnStaffOrderMenuAdd);
        btnStaffOrderMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String menuAmount = edtStaffOrderMenuAmountDetails.getText().toString();

                if(StaffOrderTable.strOrderID.equals("empty")) {

                    String userID = fAuth.getCurrentUser().getUid().toString();
                    Integer intNewOrderID = Integer.parseInt(strLastOrderID) + 1;

                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("orderID").setValue(intNewOrderID.toString());

                    fDatabaseOrder.child("lastOrderID").setValue(intNewOrderID.toString());
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderID").setValue(intNewOrderID.toString());
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderStatus").setValue("not paid");
                    fDatabaseOrder.child(intNewOrderID.toString()).child("tableNo").setValue(StaffOrderTable.strTableNo);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("userID").setValue(userID);

                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuName").setValue(StaffOrderMenu.strMenuName);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuPrice").setValue(StaffOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuStatus").setValue("not served");
                    fDatabaseOrder.child(intNewOrderID.toString()).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuType").setValue(StaffOrderMenuType.strMenuType);

                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("orderID").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            StaffOrderTable.strOrderID = dataSnapshot.getValue().toString();
                            Log.v("getIDA", StaffOrderTable.strOrderID);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            StaffOrderTableOrder fragmStaffOrderTableOrder = new StaffOrderTableOrder();
                            transaction.replace(R.id.activity_staff_main, fragmStaffOrderTableOrder);
                            transaction.commit();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else if(!StaffOrderTable.strOrderID.equals("empty")) {

                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuAmount").setValue(menuAmount);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuName").setValue(StaffOrderMenu.strMenuName);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuPrice").setValue(StaffOrderMenu.strMenuPrice);
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuStatus").setValue("not served");
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu").child(StaffOrderMenu.strMenuName).child("menuType").setValue(StaffOrderMenuType.strMenuType);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    StaffOrderTableOrder fragmStaffOrderTableOrder = new StaffOrderTableOrder();
                    transaction.replace(R.id.activity_staff_main, fragmStaffOrderTableOrder);
                    transaction.commit();


                }

            }
        });

    }

}
