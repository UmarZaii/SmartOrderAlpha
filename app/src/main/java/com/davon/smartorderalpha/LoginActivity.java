package com.davon.smartorderalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public static String strEmail, strPassword;
    public static String strUserType = "";
    public static String strUserID = "";

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListener;
    private DatabaseReference fDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }
        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){

                    strUserID = fAuth.getCurrentUser().getUid();
                    Log.v("strUserID", strUserID);

                    fDatabase.child("Auth").child(strUserID).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            strUserType = dataSnapshot.getValue().toString();
                            Log.v("strUserType", strUserType);

                            fDatabase.child(strUserType).child(strUserID).child("userPass").setValue(strPassword);
//                            fAuth.removeAuthStateListener();

                            if(strUserType.equals("Admin")) {
                                startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
                            } else if(strUserType.equals("Staff")) {
                                startActivity(new Intent(LoginActivity.this, StaffMainActivity.class));
                            } else if(strUserType.equals("Customer")) {
                                startActivity(new Intent(LoginActivity.this, CustMainActivity.class));
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fAuthListener);
    }

    private void signIn() {

        this.strEmail = edtEmail.getText().toString().trim();
        this.strPassword = edtPassword.getText().toString().trim();

        progressDialog.setMessage("Login. Please Wait...");

        if(TextUtils.isEmpty(strEmail) && TextUtils.isEmpty(strPassword)) {
            Toast.makeText(LoginActivity.this, "Both fields are empty", Toast.LENGTH_LONG).show();
        } else if(TextUtils.isEmpty(strEmail)) {
            Toast.makeText(LoginActivity.this, "Please input your email", Toast.LENGTH_LONG).show();
        } else if(TextUtils.isEmpty(strPassword)) {
            Toast.makeText(LoginActivity.this, "Please input your password", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.show();
            fAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Sign In Error", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    } else if(task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
    }

}
