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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffOrderViewTable extends Fragment {

    private RecyclerView rvStaffOrderTable;
    private DatabaseReference fDatabase;

    public static String strOrderID = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_view_table,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblTable");

        rvStaffOrderTable = (RecyclerView)v.findViewById(R.id.rvStaffOrderViewTable);
        rvStaffOrderTable.setHasFixedSize(true);
        rvStaffOrderTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStaffOrderTable.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvStaffOrderTable.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableList, TableViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableViewHolder>(

                TableList.class,
                R.layout.fragment_staff_order_view_table_row,
                TableViewHolder.class,
                fDatabase.orderByChild("orderID").startAt("1001").endAt("9999")
//                fDatabase.orderByChild("tableStatus").equalTo("N/A")

        ) {
            @Override
            protected void populateViewHolder(TableViewHolder viewHolder, final TableList model, int position) {

                viewHolder.setTableNo(model.getTableNo());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        strOrderID = model.getOrderID();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        StaffOrderViewOrder fragmStaffOrderViewOrder = new StaffOrderViewOrder();
                        transaction.replace(R.id.activity_staff_main, fragmStaffOrderViewOrder);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }
        };

        rvStaffOrderTable.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public TableViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setTableNo(String tableNo) {

            TextView txtStaffOrderTableNo = (TextView)fView.findViewById(R.id.txtStaffOrderViewTableNo);
            txtStaffOrderTableNo.setText(tableNo);

        }

    }

}
