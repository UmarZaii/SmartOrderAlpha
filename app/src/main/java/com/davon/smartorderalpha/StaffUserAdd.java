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

/**
 * Created by mansoull on 12/2/2017.
 */

public class StaffUserAdd extends Fragment {

    private FirebaseAuth fAuth;
    private DatabaseReference fDatabase;
    private EditText edtStaffUserEmail, edtStaffUserIC, edtStaffUserName;
    private Button btnStaffUserAdd;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_user_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtStaffUserEmail = (EditText) v.findViewById(R.id.edtStaffUserEmail);
        edtStaffUserIC = (EditText) v.findViewById(R.id.edtStaffUserIC);
        edtStaffUserName = (EditText) v.findViewById(R.id.edtStaffUserName);
        btnStaffUserAdd = (Button) v.findViewById(R.id.btnStaffUserAdd);
        progressDialog = new ProgressDialog(getActivity());

        btnStaffUserAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Storing data ...");
                progressDialog.show();

                final String strStaffUserEmail = edtStaffUserEmail.getText().toString().trim();
                final String strStaffUserPassword = "abc123" ;

                String strStaffUserIC = edtStaffUserIC.getText().toString().trim();
                String strStaffUserName = edtStaffUserName.getText().toString().trim();

                if (TextUtils.isEmpty(strStaffUserEmail)){
                    Toast.makeText(getActivity(), "please enter your email address", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strStaffUserIC)){
                    Toast.makeText(getActivity(), "please enter your IC number", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strStaffUserName)){
                    Toast.makeText(getActivity(), "please enter your Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                final HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("userEmail",strStaffUserEmail);
                dataMap.put("userIC", strStaffUserIC);
                dataMap.put("userName", strStaffUserName);
                dataMap.put("userType", StaffUser.strStaffUserTypeSelection);

                fAuth.createUserWithEmailAndPassword(strStaffUserEmail,strStaffUserPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Sign Up failed." + task.getException(), Toast.LENGTH_LONG).show();

                        } else {

                            String strStaffUserID = "";
                            strStaffUserID = fAuth.getCurrentUser().getUid();

                            fDatabase.child(StaffUser.strStaffUserTypeSelection).child(strStaffUserID).setValue(dataMap);
                            fDatabase.child("Auth").child(strStaffUserID).setValue(StaffUser.strStaffUserTypeSelection);

                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
}
