package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUserDetails extends Fragment {

    private AuthCredential authCredential;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
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
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        txtAdminUserEmailDetails = (TextView) v.findViewById(R.id.txtAdminUserEmailDetails);
        txtAdminUserEmailDetails.setText(AdminUser.strAdminUserEmailDetails);

        txtAdminUserICDetails = (TextView) v.findViewById(R.id.txtAdminUserICDetails);
        txtAdminUserICDetails.setText(AdminUser.strAdminUserICDetails);

        txtAdminUserNameDetails = (TextView) v.findViewById(R.id.txtAdminUserNameDetails);
        txtAdminUserNameDetails.setText(AdminUser.strAdminUserNameDetails);

        txtAdminUserTypeDetails = (TextView) v.findViewById(R.id.txtAdminUserTypeDetails);
        txtAdminUserTypeDetails.setText(AdminUser.strAdminUserTypeDetails);

//        btnDelAdminUserDetails = (Button) v.findViewById(R.id.btnDelAdminUserDetails);
//        btnDelAdminUserDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                authCredential = EmailAuthProvider.getCredential(AdminUser.strAdminUserEmailDetails, AdminUser.strAdminUserPassDetails);
//                Log.d("UID before", fAuth.getCurrentUser().getUid());
//                Log.d("UID before tostr", fAuth.getCurrentUser().getUid().toString());
//                fAuth.getCurrentUser().reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()) {
//                            Log.d("CREDENTIAL", "OK");
//                            Log.d("UID after", fAuth.getCurrentUser().getUid());
//                            Log.d("UID after tostr", fAuth.getCurrentUser().getUid().toString());
//                        } else {
//                            Log.d("CREDENTIAL", "NOT OK");
//                            task.getException();
//                        }
//                    }
//                });
//
////                fAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
////                        if(task.isSuccessful()) {
////                            Log.d("TESTING", "OK");
////                            fDatabase.child(AdminUserType.strAdminUserTypeSelection).child(AdminUser.strAdminUserIDDetails).removeValue();
////                            fDatabase.child("Auth").child(AdminUser.strAdminUserIDDetails).removeValue();
////                        } else {
////                            Log.d("TESTING", "NOT OK");
////                            task.getException();
////                        }
////
////                    }
////                });
//
//            }
//        });
    }
}
