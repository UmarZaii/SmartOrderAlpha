package com.davon.smartorderalpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUser extends Fragment {

    private RecyclerView rvUser;
    private DatabaseReference databaseReference;
    private Button btnGoToAddUser;

    public static String strAdminUserEmailDetails = "";
    public static String strAdminUserICDetails = "";
    public static String strAdminUserNameDetails = "";
    public static String strAdminUserTypeDetails = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        rvUser = (RecyclerView) v.findViewById(R.id.rvUser);
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnGoToAddUser = (Button) v.findViewById(R.id.btnGoToAddUser);
        btnGoToAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminUserAdd fragUser = new AdminUserAdd();
                transaction.replace(R.id.activity_admin_main, fragUser);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<UserList, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserList, UserViewHolder>(

                UserList.class,
                R.layout.fragment_admin_user_row,
                UserViewHolder.class,
                databaseReference.child(AdminUserType.strAdminUserTypeSelection)
        ) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, final UserList model, int position) {

                viewHolder.setUserEmail(model.getUserEmail());
                viewHolder.setUserIC(model.getUserIC());
                viewHolder.setUserName(model.getUserName());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strAdminUserEmailDetails = model.getUserEmail();
                        strAdminUserICDetails = model.getUserIC();
                        strAdminUserNameDetails = model.getUserName();
                        strAdminUserTypeDetails = model.getUserType();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        AdminUserDetails fragUserDetails = new AdminUserDetails();
                        transaction.replace(R.id.activity_admin_main, fragUserDetails);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });



            }
        };

        rvUser.setAdapter(firebaseRecyclerAdapter);
    }

    public static class  UserViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public UserViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setUserEmail(String userEmail){
            TextView txtUserEmail = (TextView) fView.findViewById(R.id.txtUserEmail);
            txtUserEmail.setText(userEmail);
        }

        public void setUserIC(String userIC){
            TextView txtUserIC = (TextView) fView.findViewById(R.id.txtUserIC);
            txtUserIC.setText(userIC);
        }

        public void setUserName(String userName){
            TextView txtUserName = (TextView) fView.findViewById(R.id.txtUserName);
            txtUserName.setText(userName);
        }
    }
}
