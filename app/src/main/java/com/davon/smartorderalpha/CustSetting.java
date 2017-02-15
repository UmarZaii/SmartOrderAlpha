package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mansoull on 15/2/2017.
 */

public class CustSetting extends Fragment {

    private Button btnChgPasswordCust, btnLogoutCust;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListener;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_setting,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Setting");
        final View v = getView();

        fAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        btnChgPasswordCust = (Button) v.findViewById(R.id.btnChgPasswordCust);
        btnLogoutCust = (Button) v.findViewById(R.id.btnLogoutCust);

        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            }
        };
        btnChgPasswordCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnChgPasswordCust){
                    progressDialog.setMessage("Reset Password. Please Wait...");
                    progressDialog.show();

                    String strEmail = LoginActivity.strEmail;

                    if (TextUtils.isEmpty(strEmail)){
                        Toast.makeText(getActivity(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        fAuth.sendPasswordResetEmail(strEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Your password has been reset. Please check your email!", Toast.LENGTH_LONG).show();

                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Failed to send reset email!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                }
            }
        });
        btnLogoutCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnLogoutCust){
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