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
    private EditText edtEmail, edtIC, edtName;
    private Button btnUserAdd;
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
        edtEmail = (EditText) v.findViewById(R.id.edtEmail);
        edtIC = (EditText) v.findViewById(R.id.edtIC);
        edtName = (EditText) v.findViewById(R.id.edtName);
        btnUserAdd = (Button) v.findViewById(R.id.btnUserAdd);
        progressDialog = new ProgressDialog(getActivity());

        btnUserAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Storing Data...");
                progressDialog.show();

                String strEmail = edtEmail.getText().toString().trim();
                String strPassword = "abc123";
                String strIC = edtIC.getText().toString().trim();
                String strName = edtName.getText().toString().trim();

                if(TextUtils.isEmpty(strEmail)) {
                    Toast.makeText(getActivity(), "Please enter email address", Toast.LENGTH_LONG).show();
                    return;
                } else if(TextUtils.isEmpty(strIC)) {
                    Toast.makeText(getActivity(), "Please enter your IC number", Toast.LENGTH_LONG).show();
                    return;
                } else if(TextUtils.isEmpty(strName)) {
                    Toast.makeText(getActivity(), "Please enter your full name", Toast.LENGTH_LONG).show();
                    return;
                }

                final HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Email",strEmail);
                dataMap.put("IC",strIC);
                dataMap.put("Name",strName);

                fAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
//                            Toast.makeText(getActivity(), "Sign Up Failed" , Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), "Sign Up failed." + task.getException(), Toast.LENGTH_LONG).show();
                        } else {
                            fDatabase.push().setValue(dataMap);
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
