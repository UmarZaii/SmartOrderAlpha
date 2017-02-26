package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mansoull on 15/2/2017.
 */

public class CustSetting extends Fragment {

    private Button btnChgPasswordCust, btnLogoutCust;
    private DatabaseReference fDatabaseOrder;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListener;
    private ProgressDialog progressDialog;

    public static String strUserID = "";
    public static String strOrderID = "";
    public static String strUserView = "";
    public static String strOrderStatus = "";

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
        fDatabaseOrder = FirebaseDatabase.getInstance().getReference().child("tblOrder");
        progressDialog = new ProgressDialog(getActivity());
        btnChgPasswordCust = (Button) v.findViewById(R.id.btnChgPasswordCust);
        btnLogoutCust = (Button) v.findViewById(R.id.btnLogoutCust);

        strUserID = fAuth.getCurrentUser().getUid().toString();
        Log.v("userID", strUserID);

        fDatabaseOrder.child("userView").child(strUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strOrderID = dataSnapshot.getValue().toString();
                Log.v("OrderID", strOrderID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fDatabaseOrder.child("userView").child(strUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strUserView = dataSnapshot.getValue().toString();
                Log.v("UserView gftvt", "test");
                Log.v("UserView x", strUserView);

                if (!strUserView.equals("empty")) {
                    fDatabaseOrder.child(strUserView).child("orderStatus").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.v("strUserView y",strUserView);
                            if (!strUserView.equals("empty")) {
                                strOrderStatus = dataSnapshot.getValue().toString();
                            }
                            Log.v("OrderStatus", strOrderStatus);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        if (!strUserView.equals("empty")) {
//            fDatabaseOrder.child(strUserView).child("orderStatus").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    strOrderStatus = dataSnapshot.getValue().toString();
//                    Log.v("OrderStatus", strOrderStatus);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }

//        fAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() == null){
//                    startActivity(new Intent(getActivity(),LoginActivity.class));
//                }
//            }
//        };
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
                    getActivity().finish();
                }
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        fAuth.addAuthStateListener(fAuthListener);
//    }
}
