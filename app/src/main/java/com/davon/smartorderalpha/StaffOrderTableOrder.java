package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffOrderTableOrder extends Fragment{

    private RecyclerView rvStaffOrderTableOrder;
    private DatabaseReference fDatabase;
    private Button btnStaffPayOrder, btnStaffGoToAddOrder;

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

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        rvStaffOrderTableOrder = (RecyclerView)v.findViewById(R.id.rvStaffOrderTableOrder);
        rvStaffOrderTableOrder.setHasFixedSize(true);
        rvStaffOrderTableOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStaffOrderTableOrder.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvStaffOrderTableOrder.setItemAnimator(new DefaultItemAnimator());

        btnStaffGoToAddOrder = (Button)v.findViewById(R.id.btnStaffGoToAddOrder);
        btnStaffGoToAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                AdminMenuDetails fragmMenuDetails = new AdminMenuDetails();
//                transaction.replace(R.id.activity_admin_main, fragmMenuDetails);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });

        btnStaffPayOrder = (Button)v.findViewById(R.id.btnStaffPayOrder);

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder>(

                TableOrderList.class,
                R.layout.fragment_staff_order_table_order_row,
                TableOrderViewHolder.class,
                fDatabase.child(StaffOrderTable.strOrderID).child("orderMenu")

        ) {
            @Override
            protected void populateViewHolder(TableOrderViewHolder viewHolder, final TableOrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuAmount(model.getMenuAmount());
                viewHolder.setMenuPrice(model.getMenuPrice());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        AdminMenuDetails fragmMenuDetails = new AdminMenuDetails();
//                        transaction.replace(R.id.activity_admin_main, fragmMenuDetails);
//                        transaction.addToBackStack(null);
//                        transaction.commit();

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
