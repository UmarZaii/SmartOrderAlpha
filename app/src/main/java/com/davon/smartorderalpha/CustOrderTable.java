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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustOrderTable extends Fragment {

    private RecyclerView rvCustOrderTable;
    private DatabaseReference fDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order_table,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblTable");

        rvCustOrderTable = (RecyclerView)v.findViewById(R.id.rvCustOrderTable);
        rvCustOrderTable.setHasFixedSize(true);
        rvCustOrderTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCustOrderTable.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvCustOrderTable.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableList, TableViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableViewHolder>(

                TableList.class,
                R.layout.fragment_cust_order_table_row,
                TableViewHolder.class,
                fDatabase.orderByChild("tableStatus").equalTo("AV")

        ) {
            @Override
            protected void populateViewHolder(TableViewHolder viewHolder, final TableList model, int position) {

                viewHolder.setTableNo(model.getTableNo());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CustOrder.strTableNo = model.getTableNo();
                        Log.v("tableNo", CustOrder.strTableNo);

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        CustOrder fragmCustOrder = new CustOrder();
                        transaction.replace(R.id.activity_cust_main, fragmCustOrder);
                        transaction.commit();

                    }
                });

            }
        };

        rvCustOrderTable.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public TableViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setTableNo(String tableNo) {

            TextView txtCustOrderTableNo = (TextView)fView.findViewById(R.id.txtCustOrderTableNo);
            txtCustOrderTableNo.setText(tableNo);

        }

    }

}
