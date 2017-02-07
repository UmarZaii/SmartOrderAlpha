package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by mansoull on 8/2/2017.
 */

public class AdminUserAdd extends Fragment {

    private DatabaseReference mDatabase;
    private EditText email, ic, surname;
    private Button btnAdd;
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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        email = (EditText) v.findViewById(R.id.etEmail);
        ic = (EditText) v.findViewById(R.id.etIC);
        surname = (EditText) v.findViewById(R.id.etSurname);
        btnAdd = (Button) v.findViewById(R.id.btnUserAdd);
        progressDialog = new ProgressDialog(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Storing Data...");
                progressDialog.show();
                String strEmail = email.getText().toString().trim();
                String strIC = ic.getText().toString().trim();
                String strSurname = surname.getText().toString().trim();
                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Email",strEmail);
                dataMap.put("IC",strIC);
                dataMap.put("Surname",strSurname);
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Stored", Toast.LENGTH_LONG).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}
