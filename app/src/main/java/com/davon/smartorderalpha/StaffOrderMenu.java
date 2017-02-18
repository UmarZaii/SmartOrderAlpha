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

public class StaffOrderMenu extends Fragment {

    private RecyclerView rvStaffOrderMenu;
    private DatabaseReference fDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_order_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");

        rvStaffOrderMenu = (RecyclerView)v.findViewById(R.id.rvStaffOrderMenu);
        rvStaffOrderMenu.setHasFixedSize(true);
        rvStaffOrderMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStaffOrderMenu.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvStaffOrderMenu.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuViewHolder>(

                MenuList.class,
                R.layout.fragment_staff_order_menu_row,
                MenuViewHolder.class,
                fDatabase.child(StaffOrderMenuType.strMenuType)

        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setMenuName(model.getMenuName());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        StaffOrderTableOrder fragmStaffOrderTableOrder = new StaffOrderTableOrder();
//                        transaction.replace(R.id.activity_staff_main, fragmStaffOrderTableOrder);
//                        transaction.addToBackStack(null);
//                        transaction.commit();

                    }
                });

            }
        };

        rvStaffOrderMenu.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtStaffOrderMenuName = (TextView)fView.findViewById(R.id.txtStaffOrderMenuName);
            txtStaffOrderMenuName.setText(menuName);

        }

    }

}
