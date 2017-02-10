package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mansoull on 11/2/2017.
 */

public class StaffSetting extends Fragment {

    private Button btnLogoutStaff, btnChgPasswordStaff;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListener;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_setting,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        btnChgPasswordStaff = (Button)v.findViewById(R.id.btnChgPasswordStaff);
        btnLogoutStaff = (Button)v.findViewById(R.id.btnLogoutStaff);

        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        };

        btnLogoutStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnLogoutStaff){
                    progressDialog.setMessage("Log Out. Please Wait...");
                    progressDialog.show();
                    fAuth.signOut();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fAuthListener);
    }
}
