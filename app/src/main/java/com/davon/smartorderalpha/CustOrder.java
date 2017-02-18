package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mansoull on 19/2/2017.
 */

public class CustOrder extends Fragment {

    private Button btnCustOrderManageOrder, btnCustOrderViewOrder;

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

        btnCustOrderManageOrder = (Button) v.findViewById(R.id.btnCustOrderManageOrder);
        btnCustOrderManageOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustOrderTable fragCustOrderTable = new CustOrderTable();
                transaction.replace(R.id.activity_cust_main, fragCustOrderTable);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }
}
