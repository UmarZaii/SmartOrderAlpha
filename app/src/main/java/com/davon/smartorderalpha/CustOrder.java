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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustOrder extends Fragment {

    private RecyclerView rvCustOrder;
    private DatabaseReference fDatabase;
    private Button btnCustOrderCancel, btnCustOrderAdd, btnCustOrderBook;
    private TextView txtCustOrderStatus;

    public static String strTableNo = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        rvCustOrder = (RecyclerView)v.findViewById(R.id.rvCustOrder);
        rvCustOrder.setHasFixedSize(true);
        rvCustOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCustOrder.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvCustOrder.setItemAnimator(new DefaultItemAnimator());

        txtCustOrderStatus = (TextView)v.findViewById(R.id.txtCustOrderStatus);

        if (!CustSetting.strUserView.equals("empty")) {
            fDatabase.child(CustSetting.strOrderID).child("orderStatus").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String strOrderStatus = dataSnapshot.getValue().toString();
                    txtCustOrderStatus.setText(strOrderStatus);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            txtCustOrderStatus.setText("No Order");
        }

        btnCustOrderBook = (Button)v.findViewById(R.id.btnCustOrderBook);
        btnCustOrderBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderTable fragmCustOrderTable = new CustOrderTable();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderTable);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderAdd = (Button)v.findViewById(R.id.btnCustOrderAdd);
        btnCustOrderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderMenuType fragmCustOrderMenuType = new CustOrderMenuType();
                transaction.replace(R.id.activity_cust_main, fragmCustOrderMenuType);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCustOrderCancel = (Button)v.findViewById(R.id.btnCustOrderCancel);
        btnCustOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableOrderList, TableOrderViewHolder>(

                TableOrderList.class,
                R.layout.fragment_cust_order_row,
                TableOrderViewHolder.class,
                fDatabase.child(CustSetting.strOrderID).child("orderMenu")

        ) {
            @Override
            protected void populateViewHolder(TableOrderViewHolder viewHolder, final TableOrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuAmount(model.getMenuAmount());
                viewHolder.setMenuPrice(model.getMenuPrice());

            }
        };

        rvCustOrder.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TableOrderViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public TableOrderViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtCustOrderName = (TextView)fView.findViewById(R.id.txtCustOrderName);
            txtCustOrderName.setText(menuName);

        }

        public void setMenuAmount(String menuAmount) {

            TextView txtCustOrderQuantity = (TextView)fView.findViewById(R.id.txtCustOrderQuantity);
            txtCustOrderQuantity.setText(menuAmount);

        }

        public void setMenuPrice(String menuPrice) {

            TextView txtCustOrderPrice = (TextView)fView.findViewById(R.id.txtCustOrderPrice);
            txtCustOrderPrice.setText(menuPrice);

        }

    }

}
