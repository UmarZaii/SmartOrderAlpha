package com.davon.smartorderalpha;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMenuDetails extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtMenuNameDetails, txtMenuPriceDetails;
    private Button btnDelMenuDetails, btnGoToUpdMenuDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");

        txtMenuNameDetails = (TextView)v.findViewById(R.id.txtMenuNameDetails);
        txtMenuNameDetails.setText(AdminMenu.strMenuNameDetails);

        txtMenuPriceDetails = (TextView)v.findViewById(R.id.txtMenuPriceDetails);
        txtMenuPriceDetails.setText(AdminMenu.strMenuPriceDetails);

        btnDelMenuDetails = (Button)v.findViewById(R.id.btnDelMenuDetails);
        btnDelMenuDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fDatabase.child(AdminMenuType.strAdminMenuTypeSelection).child(AdminMenu.strMenuNameDetails).removeValue();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenu fragmAdminMenu = new AdminMenu();
                transaction.replace(R.id.activity_admin_main, fragmAdminMenu);
                transaction.commit();
            }
        });

        btnGoToUpdMenuDetails = (Button)v.findViewById(R.id.btnGoToUpdMenuDetails);
        btnGoToUpdMenuDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminMenuDetailsUpd fragmMenuDetailsUpd = new AdminMenuDetailsUpd();
                transaction.replace(R.id.activity_admin_main, fragmMenuDetailsUpd);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}
