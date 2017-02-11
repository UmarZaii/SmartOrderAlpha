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

/**
 * Created by mansoull on 12/2/2017.
 */

public class StaffUser extends Fragment {

    private RecyclerView rvStaffUser;
    private DatabaseReference databaseReference;
    private Button btnStaffGoToAddUser;

    public static String strAdminUserEmailDetails = "";
    public static String strAdminUserICDetails = "";
    public static String strAdminUserNameDetails = "";
    public static String strAdminUserTypeDetails = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_user, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        rvStaffUser = (RecyclerView) v.findViewById(R.id.rvStaffUser);
        rvStaffUser.setHasFixedSize(true);
        rvStaffUser.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnStaffGoToAddUser = (Button) v.findViewById(R.id.btnStaffGoToAddUser);
        btnStaffGoToAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                AdminUserAdd fragUser = new AdminUserAdd();
//                transaction.replace(R.id.activity_admin_main, fragUser);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<UserList, UserViewHolderStaff> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserList, UserViewHolderStaff>(

                UserList.class,
                R.layout.fragment_staff_user_row,
                UserViewHolderStaff.class,
                databaseReference.child(AdminUserType.strAdminUserTypeSelection)
        ) {
            @Override
            protected void populateViewHolder(UserViewHolderStaff viewHolder, UserList model, int position) {

                viewHolder.setUserName(model.getUserName());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        strAdminUserEmailDetails = model.getUserEmail();
//                        strAdminUserICDetails = model.getUserIC();
//                        strAdminUserNameDetails = model.getUserName();
//                        strAdminUserTypeDetails = model.getUserType();

//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        AdminUserDetails fragUserDetails = new AdminUserDetails();
//                        transaction.replace(R.id.activity_staff_main, fragUserDetails);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                    }
                });

            }
        };

        rvStaffUser.setAdapter(firebaseRecyclerAdapter);
    }


    public static class  UserViewHolderStaff extends RecyclerView.ViewHolder{

        View fView;

        public UserViewHolderStaff(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setUserName(String userName){
            TextView txtUserName = (TextView) fView.findViewById(R.id.txtStaffUserName);
            txtUserName.setText(userName);
        }
    }

}
