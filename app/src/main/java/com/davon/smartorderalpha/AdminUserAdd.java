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

public class AdminUserAdd extends Fragment {

    private DatabaseReference fDatabase;
    private EditText edtEmail, edtIC, edtSurname;
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

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");
        edtEmail = (EditText) v.findViewById(R.id.edtEmail);
        edtIC = (EditText) v.findViewById(R.id.edtIC);
        edtSurname = (EditText) v.findViewById(R.id.edtSurname);
        btnAdd = (Button) v.findViewById(R.id.btnUserAdd);
        progressDialog = new ProgressDialog(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Storing Data...");
                progressDialog.show();
                String strEmail = edtEmail.getText().toString().trim();
                String strIC = edtIC.getText().toString().trim();
                String strSurname = edtSurname.getText().toString().trim();
                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Email",strEmail);
                dataMap.put("IC",strIC);
                dataMap.put("Surname",strSurname);
                fDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
