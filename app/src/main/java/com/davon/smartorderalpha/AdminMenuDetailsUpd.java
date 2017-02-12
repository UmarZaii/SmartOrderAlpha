package com.davon.smartorderalpha;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMenuDetailsUpd extends Fragment {

    private DatabaseReference fDatabase;

    private static EditText edtMenuNameUpdDetails;
    private static EditText edtMenuPriceUpdDetails;
    private Button btnUpdMenuDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu_details_upd,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");

        edtMenuNameUpdDetails = (EditText) v.findViewById(R.id.edtMenuNameUpdDetails);
        edtMenuNameUpdDetails.setText(AdminMenu.strMenuNameDetails);

        edtMenuPriceUpdDetails = (EditText) v.findViewById(R.id.edtMenuPriceUpdDetails);
        edtMenuPriceUpdDetails.setText(AdminMenu.strMenuPriceDetails);

        btnUpdMenuDetails = (Button)v.findViewById(R.id.btnUpdMenuDetails);
        btnUpdMenuDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMenuNameUpdDetails = edtMenuNameUpdDetails.getText().toString();
                String strMenuPriceUpdDetails = edtMenuPriceUpdDetails.getText().toString();

                fDatabase.child(AdminMenuType.strAdminMenuTypeSelection).child(AdminMenu.strMenuNameDetails).child("menuName").setValue(strMenuNameUpdDetails);
                fDatabase.child(AdminMenuType.strAdminMenuTypeSelection).child(AdminMenu.strMenuNameDetails).child("menuPrice").setValue(strMenuPriceUpdDetails);
            }
        });

    }

}
