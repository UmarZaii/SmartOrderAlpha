package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StaffOrderViewOrder extends Fragment {

    private RecyclerView rvStaffOrderViewOrder;
    private DatabaseReference fDatabaseOrder, fDatabaseTable;
    private Button btnStaffPrepareOrder, btnStaffServingOrder;
    private TextView statusPrepareServing;

    public static String strOrderStatus = "";
    public static String strStaffView = "abc";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_view_order,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        rvStaffOrderViewOrder = (RecyclerView)v.findViewById(R.id.rvStaffOrderViewOrder);
        rvStaffOrderViewOrder.setHasFixedSize(true);
        rvStaffOrderViewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStaffOrderViewOrder.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvStaffOrderViewOrder.setItemAnimator(new DefaultItemAnimator());

        fDatabaseOrder.child(StaffOrderViewTable.strOrderID).child("orderStatus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strOrderStatus = dataSnapshot.getValue().toString();
                statusPrepareServing.setText(strOrderStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        statusPrepareServing = (TextView)v.findViewById(R.id.statusPrepareServing);

        btnStaffPrepareOrder = (Button)v.findViewById(R.id.btnStaffPrepareOrder);
        btnStaffPrepareOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fDatabaseOrder.child(StaffOrderViewTable.strOrderID).child("orderStatus").setValue("Preparing");

            }
        });

        btnStaffServingOrder = (Button)v.findViewById(R.id.btnStaffServingOrder);
        btnStaffServingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strStaffView = "xyz";
                fDatabaseOrder.child(StaffOrderViewTable.strOrderID).child("orderStatus").setValue("Serving");
//                fDatabaseTable.child(StaffOrderViewTable.strTableNo).child("staffView").setValue("Serving");

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderViewTable fragmStaffOrderViewTable = new StaffOrderViewTable();
                transaction.replace(R.id.activity_staff_main, fragmStaffOrderViewTable);
                transaction.commit();

            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder>(

                TableOrderList.class,
                R.layout.fragment_staff_order_view_order_row,
                TableOrderViewHolder.class,
                fDatabaseOrder.child(StaffOrderViewTable.strOrderID).child("orderMenu")

        ) {
            @Override
            protected void populateViewHolder(TableOrderViewHolder viewHolder, final TableOrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuAmount(model.getMenuAmount());
                viewHolder.setMenuPrice(model.getMenuPrice());

            }
        };

        rvStaffOrderViewOrder.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TableOrderViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public TableOrderViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtStaffOrderViewOrderName = (TextView)fView.findViewById(R.id.txtStaffOrderViewOrderName);
            txtStaffOrderViewOrderName.setText(menuName);

        }

        public void setMenuAmount(String menuAmount) {

            TextView txtStaffOrderViewOrderAmount = (TextView)fView.findViewById(R.id.txtStaffOrderViewOrderAmount);
            txtStaffOrderViewOrderAmount.setText(menuAmount);

        }

        public void setMenuPrice(String menuPrice) {

            TextView txtStaffOrderViewOrderPrice = (TextView)fView.findViewById(R.id.txtStaffOrderViewOrderPrice);
            txtStaffOrderViewOrderPrice.setText(menuPrice);

        }

    }


}
