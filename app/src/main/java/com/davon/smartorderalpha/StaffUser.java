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

    public static String strStaffUserTypeSelection = "";
    public static String strStaffUserEmailDetails = "";
    public static String strStaffUserICDetails = "";
    public static String strStaffUserNameDetails = "";
    public static String strStaffUserTypeDetails = "";


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
                strStaffUserTypeSelection = "Customer";
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StaffUserAdd fragStaffUser = new StaffUserAdd();
                transaction.replace(R.id.activity_staff_main, fragStaffUser);
                transaction.addToBackStack(null);
                transaction.commit();
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
                databaseReference.child(strStaffUserTypeSelection)
        ) {
            @Override
            protected void populateViewHolder(UserViewHolderStaff viewHolder, final UserList model, int position) {

                viewHolder.setUserName(model.getUserName());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strStaffUserEmailDetails = model.getUserEmail();
                        strStaffUserICDetails = model.getUserIC();
                        strStaffUserNameDetails = model.getUserName();
                        strStaffUserTypeDetails = model.getUserType();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        StaffUserDetails fragStaffUserDetails = new StaffUserDetails();
                        transaction.replace(R.id.activity_staff_main, fragStaffUserDetails);
                        transaction.addToBackStack(null);
                        transaction.commit();
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
