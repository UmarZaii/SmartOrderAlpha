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

/**
 * Created by mansoull on 20/2/2017.
 */

public class CustOrderMenu extends Fragment {

    private RecyclerView rvCustOrderMenu;
    private DatabaseReference fDatabase;

    public static String strMenuName = "";
    public static String strMenuPrice = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_order_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");

        rvCustOrderMenu = (RecyclerView)v.findViewById(R.id.rvCustOrderMenu);
        rvCustOrderMenu.setHasFixedSize(true);
        rvCustOrderMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCustOrderMenu.addItemDecoration(new AllDividerItemRecycleView(getActivity()));
        rvCustOrderMenu.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuViewHolder>(

                MenuList.class,
                R.layout.fragment_cust_order_menu_row,
                MenuViewHolder.class,
                fDatabase.child(CustOrderMenuType.strMenuType)

        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setMenuName(model.getMenuName());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        strMenuName = model.getMenuName();
                        strMenuPrice = model.getMenuPrice();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        CustOrderMenuDetails fragmCustOrderMenuDetails = new CustOrderMenuDetails();
                        transaction.replace(R.id.activity_cust_main, fragmCustOrderMenuDetails);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }
        };

        rvCustOrderMenu.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtCustOrderMenuName = (TextView)fView.findViewById(R.id.txtCustOrderMenuName);
            txtCustOrderMenuName.setText(menuName);

        }

    }
}
