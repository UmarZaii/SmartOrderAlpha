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
 * Created by mansoull on 18/2/2017.
 */

public class StaffMenu extends Fragment {

    private Button btnStaffMenuManageOrder, btnStaffMenuViewOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        btnStaffMenuManageOrder = (Button) v.findViewById(R.id.btnStaffMenuManageOrder);
//        btnStaffMenuManageOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                StaffMenuManage fragStaffMenuManage = new StaffMenuManage();
//                transaction.replace(R.id.activity_staff_main, fragStaffMenuManage);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//            }
//        });

    }
}
