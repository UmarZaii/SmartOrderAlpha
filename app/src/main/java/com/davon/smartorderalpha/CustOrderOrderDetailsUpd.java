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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustOrderOrderDetailsUpd extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtCustOrderOrderNameDetailsUpd, txtCustOrderOrderPriceDetailsUpd;
    private EditText edtCustOrderOrderQuantityDetailsUpd;
    private Button btnCustUpdOrderOrderDetailsUpd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order_order_details_upd,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblOrder");

        txtCustOrderOrderNameDetailsUpd = (TextView)v.findViewById(R.id.txtCustOrderOrderNameDetailsUpd);
        txtCustOrderOrderNameDetailsUpd.setText(CustOrder.strOrderName);

        txtCustOrderOrderPriceDetailsUpd = (TextView)v.findViewById(R.id.txtCustOrderOrderPriceDetailsUpd);
        txtCustOrderOrderPriceDetailsUpd.setText(CustOrder.strOrderPrice);

        edtCustOrderOrderQuantityDetailsUpd = (EditText)v.findViewById(R.id.edtCustOrderOrderQuantityDetailsUpd);
        edtCustOrderOrderQuantityDetailsUpd.setText(CustOrder.strOrderAmount);

        Log.v("strUserView", CustSetting.strUserView);

        btnCustUpdOrderOrderDetailsUpd = (Button)v.findViewById(R.id.btnCustUpdOrderOrderDetailsUpd);
        btnCustUpdOrderOrderDetailsUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderQuantity = edtCustOrderOrderQuantityDetailsUpd.getText().toString();
                Log.d("qty", orderQuantity);

                fDatabase.child(CustSetting.strUserView).child("orderMenu").child(CustOrder.strOrderName).child("menu"+
                        "Amount").setValue(orderQuantity);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrder fragmCustOrder = new CustOrder();
                transaction.replace(R.id.activity_cust_main, fragmCustOrder);
                transaction.commit();
            }
        });

    }

}
