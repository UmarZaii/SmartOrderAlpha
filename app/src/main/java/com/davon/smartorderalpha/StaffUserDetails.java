package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 12/2/2017.
 */

public class StaffUserDetails extends Fragment {

    private AuthCredential authCredential;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private DatabaseReference fDatabase;
    private TextView txtStaffUserEmailDetails, txtStaffUserICDetails, txtStaffUserNameDetails, txtStaffUserTypeDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_user_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        txtStaffUserEmailDetails = (TextView) v.findViewById(R.id.txtStaffUserEmailDetails);
        txtStaffUserEmailDetails.setText(StaffUser.strStaffUserEmailDetails);

        txtStaffUserICDetails = (TextView) v.findViewById(R.id.txtStaffUserICDetails);
        txtStaffUserICDetails.setText(StaffUser.strStaffUserICDetails);

        txtStaffUserNameDetails = (TextView) v.findViewById(R.id.txtStaffUserNameDetails);
        txtStaffUserNameDetails.setText(StaffUser.strStaffUserEmailDetails);

        txtStaffUserTypeDetails = (TextView) v.findViewById(R.id.txtStaffUserTypeDetails);
        txtStaffUserTypeDetails.setText(StaffUser.strStaffUserEmailDetails);
    }
}
