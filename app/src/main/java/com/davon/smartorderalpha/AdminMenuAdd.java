package com.davon.smartorderalpha;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class AdminMenuAdd extends Fragment {

    DatabaseReference mDatabase;

    EditText edtMenuName, edtMenuPrice;
    Button btnAddMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_menu_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtMenuName = (EditText)v.findViewById(R.id.edtMenuName);
        edtMenuPrice = (EditText)v.findViewById(R.id.edtMenuPrice);
        btnAddMenu = (Button)v.findViewById(R.id.btnAddMenu);

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMenuName = edtMenuName.getText().toString().trim();
                String strMenuPrice = edtMenuPrice.getText().toString().trim();
                HashMap<String, String> dataMap =  new HashMap<String, String>();
                dataMap.put("MenuName", strMenuName);
                dataMap.put("MenuPrice", strMenuPrice);
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()) {
//                            Toast.makeText(AdminMenuAdd, "Stored", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(AdminMenuAdd, "Unsuccessfull", Toast.LENGTH_LONG).show();
//                        }
                    }
                });
            }
        });

    }

}
