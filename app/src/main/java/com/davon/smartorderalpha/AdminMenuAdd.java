package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminMenuAdd extends Fragment {

    private DatabaseReference fDatabase;

    private EditText edtMenuName, edtMenuPrice;
    private Button btnAddMenu;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblMenu");
        edtMenuName = (EditText)v.findViewById(R.id.edtMenuName);
        edtMenuPrice = (EditText)v.findViewById(R.id.edtMenuPrice);
        btnAddMenu = (Button)v.findViewById(R.id.btnAddMenu);
        progressDialog = new ProgressDialog(getActivity());

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Storing Data...");
                progressDialog.show();
                String strMenuName = edtMenuName.getText().toString().trim();
                String strMenuPrice = edtMenuPrice.getText().toString().trim();
                HashMap<String, String> dataMap =  new HashMap<String, String>();
                dataMap.put("MenuName", strMenuName);
                dataMap.put("MenuPrice", strMenuPrice);
                fDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
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
