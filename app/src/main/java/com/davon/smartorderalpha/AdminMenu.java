package com.davon.smartorderalpha;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class AdminMenu extends Fragment {

    private RecyclerView rvMenu;
    private DatabaseReference fDatabase;
    private Button btnGoToAddMenu;

    public static String strMenuNameDetails = "";
    public static String strMenuPriceDetails = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");

        rvMenu = (RecyclerView)v.findViewById(R.id.rvMenu);
        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnGoToAddMenu = (Button)v.findViewById(R.id.btnGoToAddMenu);
        btnGoToAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenuAdd fragmMenu = new AdminMenuAdd();
                transaction.replace(R.id.activity_admin_main, fragmMenu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuViewHolder>(

                MenuList.class,
                R.layout.fragment_admin_menu_row,
                MenuViewHolder.class,
                fDatabase

        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuPrice(model.getMenuPrice());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        strMenuNameDetails = model.getMenuName();
                        strMenuPriceDetails = model.getMenuPrice();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        AdminMenuDetails fragmMenuDetails = new AdminMenuDetails();
                        transaction.replace(R.id.activity_admin_main, fragmMenuDetails);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }
        };

        rvMenu.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        View fView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuName(String menuName) {

            TextView txtMenuName = (TextView)fView.findViewById(R.id.txtMenuName);
            txtMenuName.setText(menuName);

        }

        public void setMenuPrice(String menuPrice) {

            TextView txtMenuPrice = (TextView)fView.findViewById(R.id.txtMenuPrice);
            txtMenuPrice.setText(menuPrice);

        }

    }

}
