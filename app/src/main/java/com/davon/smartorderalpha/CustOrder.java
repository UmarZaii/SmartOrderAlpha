package com.davon.smartorderalpha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustOrder extends Fragment {

    private RecyclerView rvCustOrder;
    private DatabaseReference fDatabaseTable, fDatabaseOrder;
    private Button btnCustOrderCancel, btnCustOrderAdd, btnCustOrderBook;
    private TextView txtCustOrderStatus;

    public static String strOrderName = "";
    public static String strOrderPrice = "";
    public static String strOrderAmount = "";

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

        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        fDatabaseTable = FirebaseDatabase.getInstance().getReference().child("tblTable");

        rvCustOrder = (RecyclerView)v.findViewById(R.id.rvCustOrder);
        rvCustOrder.setHasFixedSize(true);
        rvCustOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCustOrder.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvCustOrder.setItemAnimator(new DefaultItemAnimator());

        txtCustOrderStatus = (TextView)v.findViewById(R.id.txtCustOrderStatus);

        fDatabaseOrder.child(CustSetting.strOrderID).child("tableNo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strTableNo = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (!CustSetting.strUserView.equals("empty")) {
            fDatabaseOrder.child(CustSetting.strOrderID).child("orderStatus").addValueEventListener(new ValueEventListener() {
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
                if (CustSetting.strUserView.equals("empty")) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    CustOrderTable fragmCustOrderTable = new CustOrderTable();
                    transaction.replace(R.id.activity_cust_main, fragmCustOrderTable);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "You already have an order", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCustOrderAdd = (Button)v.findViewById(R.id.btnCustOrderAdd);
        btnCustOrderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!strTableNo.equals("") || !strTableNo.equals(null)) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    CustOrderMenuType fragmCustOrderMenuType = new CustOrderMenuType();
                    transaction.replace(R.id.activity_cust_main, fragmCustOrderMenuType);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Please choose a table first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCustOrderCancel = (Button)v.findViewById(R.id.btnCustOrderCancel);
        btnCustOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CustSetting.strUserView.equals("empty")) {
                    fDatabaseTable.child(strTableNo).child("orderID").setValue("empty");
                    fDatabaseTable.child(strTableNo).child("staffView").setValue("empty");
                    fDatabaseTable.child(strTableNo).child("tableStatus").setValue("AV");
                    fDatabaseOrder.child(CustSetting.strUserView).removeValue();
                    fDatabaseOrder.child("userView").child(CustSetting.strUserID).setValue("empty");
                } else {
                    Toast.makeText(getActivity(), "You don't have any order yet", Toast.LENGTH_SHORT).show();
                }

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
                fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu")

        ) {
            @Override
            protected void populateViewHolder(TableOrderViewHolder viewHolder, final TableOrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuAmount(model.getMenuAmount());
                viewHolder.setMenuPrice(model.getMenuPrice());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        strOrderName = model.getMenuName();
                        strOrderPrice = model.getMenuPrice();
                        strOrderAmount = model.getMenuAmount();

                        final CharSequence[] dialogitem = {"Update Order", "Delete Order"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Choose");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                        Log.v("Update", model.getMenuName());
                                        Log.v("strOrderStatus", CustSetting.strOrderStatus);
                                        if (CustSetting.strOrderStatus.equals("New Order")) {
                                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            CustOrderOrderDetailsUpd fragmCustOrderOrderDetailsUpd = new CustOrderOrderDetailsUpd();
                                            transaction.replace(R.id.activity_cust_main, fragmCustOrderOrderDetailsUpd);
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                        } else if (CustSetting.strOrderStatus.equals("Preparing")) {
                                            Toast.makeText(getActivity(), "Your orders are being prepared", Toast.LENGTH_SHORT).show();
                                        } else if (CustSetting.strOrderStatus.equals("Serving")) {
                                            Toast.makeText(getActivity(), "Your orders has been served", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1 :
                                        Log.v("Delete", model.getMenuName());
                                        Log.v("strOrderStatus", CustSetting.strOrderStatus);
                                        if (CustSetting.strOrderStatus.equals("New Order")) {
                                            fDatabaseOrder.child(CustSetting.strOrderID).child("orderMenu").child(strOrderName).removeValue();
                                        } else if (CustSetting.strOrderStatus.equals("Preparing")) {
                                            Toast.makeText(getActivity(), "Your orders are being prepared", Toast.LENGTH_SHORT).show();
                                        } else if (CustSetting.strOrderStatus.equals("Serving")) {
                                            Toast.makeText(getActivity(), "Your orders has been served", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                }
                            }
                        });
                        builder.create().show();

                    }
                });

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
