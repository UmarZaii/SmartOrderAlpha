package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StaffOrderTableOrder extends Fragment {

    private RecyclerView rvStaffOrderTableOrder;
    private DatabaseReference fDatabaseOrder, fDatabaseTable, fDatabaseUser;
    private Button btnStaffCancelOrder, btnStaffPayOrder, btnStaffGoToAddOrder;
    private TextView txtCustOrderTableOrderRM;

    public static String strUserID = "";
    public static String strUserType = "";

    public static String strMenuName = "";
    public static String strMenuPrice = "";
    public static String strMenuAmount = "";

    public static Double dbTotalPriceAll = 0.00;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_table_order,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");
        fDatabaseUser = FirebaseDatabase.getInstance().getReference().child("tblUser");

        txtCustOrderTableOrderRM = (TextView)v.findViewById(R.id.txtCustOrderTableOrderRM);

        rvStaffOrderTableOrder = (RecyclerView)v.findViewById(R.id.rvStaffOrderTableOrder);
        rvStaffOrderTableOrder.setHasFixedSize(true);
        rvStaffOrderTableOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStaffOrderTableOrder.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvStaffOrderTableOrder.setItemAnimator(new DefaultItemAnimator());

        Log.v("strOrderID", StaffOrderTable.strOrderID);
        fDatabaseOrder.child(StaffOrderTable.strOrderID).child("userID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strUserID = dataSnapshot.getValue().toString();
                Log.v("strUserID", strUserID);

                fDatabaseUser.child("Auth").child(strUserID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        strUserType = dataSnapshot.getValue().toString();
                        Log.v("strUserType", strUserType);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnStaffGoToAddOrder = (Button)v.findViewById(R.id.btnStaffGoToAddOrder);
        btnStaffGoToAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderMenuType fragmStaffOrderMenuType = new StaffOrderMenuType();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderMenuType);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStaffCancelOrder = (Button)v.findViewById(R.id.btnStaffCancelOrder);
        btnStaffCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!StaffOrderTable.strOrderID.equals("empty")) {
                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("orderID").setValue("empty");
                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("staffView").setValue("empty");
                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("tableStatus").setValue("AV");
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).removeValue();
                } else {
                    Toast.makeText(getActivity(), "This table does not have any order", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnStaffPayOrder = (Button)v.findViewById(R.id.btnStaffPayOrder);
        btnStaffPayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!StaffOrderTable.strOrderID.equals("empty")) {

                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("orderID").setValue("empty");
                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("staffView").setValue("empty");
                    fDatabaseTable.child(StaffOrderTable.strTableNo).child("tableStatus").setValue("AV");
                    fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderStatus").setValue("Paid");
                    if (strUserType.equals("Customer")) {
                        fDatabaseOrder.child("userView").child(strUserID).setValue("empty");
                    }

                } else {
                    Toast.makeText(getActivity(), "You have no order to pay for...", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder>(

                TableOrderList.class,
                R.layout.fragment_staff_order_table_order_row,
                TableOrderViewHolder.class,
                fDatabaseOrder.child(StaffOrderTable.strOrderID).child("orderMenu")

        ) {
            @Override
            protected void populateViewHolder(TableOrderViewHolder viewHolder, final TableOrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuAmount(model.getMenuAmount());
                viewHolder.setMenuPrice(model.getMenuPrice());

                String menuPrice = model.getMenuPrice();
                String menuAmount = model.getMenuAmount();
                Double dbMenuPrice = Double.parseDouble(menuPrice);
                Double dbMenuAmount = Double.parseDouble(menuAmount);

                Log.v("menuPrice DB2", String.format("%.2f", dbMenuPrice).toString());
                Log.v("menuAmount DB2", String.format("%.2f", dbMenuAmount).toString());

                Double dbTotalPriceEach = dbMenuPrice * dbMenuAmount;
                dbTotalPriceAll = dbTotalPriceAll + dbTotalPriceEach;

                txtCustOrderTableOrderRM.setText(String.format("%.2f", dbTotalPriceAll).toString());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        strMenuName = model.getMenuName();
                        strMenuAmount = model.getMenuAmount();
                        strMenuPrice = model.getMenuPrice();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        StaffOrderOrderDetails fragmStaffOrderOrderDetails = new StaffOrderOrderDetails();
                        transaction.replace(R.id.activity_staff_main, fragmStaffOrderOrderDetails);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }
        };

        rvStaffOrderTableOrder.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TableOrderViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public TableOrderViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtStaffOrderTableOrderName = (TextView)fView.findViewById(R.id.txtStaffOrderTableOrderName);
            txtStaffOrderTableOrderName.setText(menuName);

        }

        public void setMenuAmount(String menuAmount) {

            TextView txtStaffOrderTableOrderAmount = (TextView)fView.findViewById(R.id.txtStaffOrderTableOrderAmount);
            txtStaffOrderTableOrderAmount.setText(menuAmount);

        }

        public void setMenuPrice(String menuPrice) {

            TextView txtStaffOrderTableOrderPrice = (TextView)fView.findViewById(R.id.txtStaffOrderTableOrderPrice);
            txtStaffOrderTableOrderPrice.setText(menuPrice);

        }

    }


}
