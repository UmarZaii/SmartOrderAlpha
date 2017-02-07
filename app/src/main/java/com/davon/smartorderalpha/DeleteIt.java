package com.davon.smartorderalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mansoull on 7/2/2017.
 */

public class DeleteIt extends AppCompatActivity {

    private Button buttonLogOut;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_it);

        fAuth = FirebaseAuth.getInstance();
        buttonLogOut = (Button) findViewById(R.id.btnLogout);

        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(DeleteIt.this, LoginActivity.class));
                }
            }
        };

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonLogOut){
                    fAuth.signOut();
                    finish();
                    startActivity(new Intent(DeleteIt.this, LoginActivity.class));
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fAuthListener);
    }
}
