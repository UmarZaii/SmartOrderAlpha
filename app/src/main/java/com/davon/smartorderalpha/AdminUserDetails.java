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

/**
 * Created by mansoull on 10/2/2017.
 */

public class AdminUserDetails extends Fragment {

    private DatabaseReference fDatabase;

    private TextView txtUserEmailDetails, txtUserICDetails, txtUserNameDetails;
    private Button btnDelUserDetails;

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

        txtUserEmailDetails = (TextView) v.findViewById(R.id.txtUserEmailDetails);
        txtUserEmailDetails.setText(AdminUser.strUserEmailDetails);

        txtUserICDetails = (TextView) v.findViewById(R.id.txtUserICDetails);
        txtUserICDetails.setText(AdminUser.strUserICDetails);

        txtUserNameDetails = (TextView) v.findViewById(R.id.txtUserNameDetails);
        txtUserNameDetails.setText(AdminUser.strUserNameDetails);

        btnDelUserDetails = (Button) v.findViewById(R.id.btnDelUserDetails);
        btnDelUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
