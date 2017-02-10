package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminUserAdd extends Fragment {

    private FirebaseAuth fAuth;
    private DatabaseReference fDatabase;
    private EditText edtAdminUserEmail, edtAdminUserIC, edtAdminUserName;
    private Button btnAdminUserAdd;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");
        edtAdminUserEmail = (EditText) v.findViewById(R.id.edtAdminUserEmail);
        edtAdminUserIC = (EditText) v.findViewById(R.id.edtAdminUserIC);
        edtAdminUserName = (EditText) v.findViewById(R.id.edtAdminUserName);
        btnAdminUserAdd = (Button) v.findViewById(R.id.btnAdminUserAdd);
        progressDialog = new ProgressDialog(getActivity());

        btnAdminUserAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Storing Data...");
                progressDialog.show();

                String strAdminUserEmail = edtAdminUserEmail.getText().toString().trim();
                String strAdminUserPassword = "abc123";

                final String strAdminUserIC = edtAdminUserIC.getText().toString().trim();
                String strAdminUserName = edtAdminUserName.getText().toString().trim();
                final String strAdminUserType = edtAdminUserName.getText().toString().trim();

                if(TextUtils.isEmpty(strAdminUserEmail)) {
                    Toast.makeText(getActivity(), "Please enter email address", Toast.LENGTH_LONG).show();
                    return;
                } else if(TextUtils.isEmpty(strAdminUserIC)) {
                    Toast.makeText(getActivity(), "Please enter your IC number", Toast.LENGTH_LONG).show();
                    return;
                } else if(TextUtils.isEmpty(strAdminUserName)) {
                    Toast.makeText(getActivity(), "Please enter your full name", Toast.LENGTH_LONG).show();
                    return;
                } else if(TextUtils.isEmpty(strAdminUserType)) {
                    Toast.makeText(getActivity(), "Please select user type", Toast.LENGTH_LONG).show();
                    return;
                }

                final HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("userEmail",strAdminUserEmail);
                dataMap.put("userIC",strAdminUserIC);
                dataMap.put("userName",strAdminUserName);
                dataMap.put("userType",strAdminUserType);

                fAuth.createUserWithEmailAndPassword(strAdminUserEmail, strAdminUserPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
//                            Toast.makeText(getActivity(), "Sign Up Failed" , Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), "Sign Up failed." + task.getException(), Toast.LENGTH_LONG).show();
                        } else {
                            fDatabase.child(strAdminUserType).child(strAdminUserIC).setValue(dataMap);
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Sign Up Success" , Toast.LENGTH_LONG).show();
//                            AdminUser fragmUser = new AdminUser();
//                            transaction.replace(R.id.activity_admin_main, fragmUser);
//                            transaction.commit();
                        }
                    }
                });

//                fDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            progressDialog.dismiss();
//                            Toast.makeText(getActivity(), "Stored", Toast.LENGTH_LONG).show();
//                        } else {
//                            progressDialog.dismiss();
//                            Toast.makeText(getActivity(), "Unsuccessfull", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

            }
        });

    }
}
