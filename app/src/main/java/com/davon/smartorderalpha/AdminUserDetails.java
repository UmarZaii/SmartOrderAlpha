package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUserDetails extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtAdminUserEmailDetails, txtAdminUserICDetails, txtAdminUserNameDetails, txtAdminUserTypeDetails;
    private Button btnDelAdminUserDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");

        txtAdminUserEmailDetails = (TextView) v.findViewById(R.id.txtAdminUserEmailDetails);
        txtAdminUserEmailDetails.setText(AdminUser.strAdminUserEmailDetails);

        txtAdminUserICDetails = (TextView) v.findViewById(R.id.txtAdminUserICDetails);
        txtAdminUserICDetails.setText(AdminUser.strAdminUserICDetails);

        txtAdminUserNameDetails = (TextView) v.findViewById(R.id.txtAdminUserNameDetails);
        txtAdminUserNameDetails.setText(AdminUser.strAdminUserNameDetails);

        txtAdminUserTypeDetails = (TextView) v.findViewById(R.id.txtAdminUserTypeDetails);
        txtAdminUserTypeDetails.setText(AdminUser.strAdminUserTypeDetails);

        btnDelAdminUserDetails = (Button) v.findViewById(R.id.btnDelAdminUserDetails);
        btnDelAdminUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
