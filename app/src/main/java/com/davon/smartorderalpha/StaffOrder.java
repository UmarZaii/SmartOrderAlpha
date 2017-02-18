package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StaffOrder extends Fragment{

    private Button btnStaffOrderManageOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        btnStaffOrderManageOrder = (Button) v.findViewById(R.id.btnStaffOrderManageOrder);
        btnStaffOrderManageOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffOrderTable fragStaffOrderTable = new StaffOrderTable();
                transaction.replace(R.id.activity_staff_main, fragStaffOrderTable);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }

}
