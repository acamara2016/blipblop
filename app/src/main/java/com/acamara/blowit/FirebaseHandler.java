package com.acamara.blowit;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirebaseHandler{
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseUser user = mAuth.getCurrentUser();
    //public String uid = user.getUid();


    public String retrieveCurrentUID(){
        return user.getUid();
    }



    public void AddUserInfo(User u)
    {

        ref.child("user").child(u.getUID()).setValue(u);

    }






    public void AddDefaultSubstance(String uid){
        ref.child("user").child(uid).child("counter").setValue(0);
        //ref.child("user").child(uid).child("customList").child("No").setValue(0); //Initialize custom substance table
        //ref.child("user").child(uid).child("consumedList").child(giveDate()).child(String.valueOf(1)).setValue(new Substance(null,null,0.00));
    }


    public void updateUsername(String username, String uid){
        ref.child("user").child(uid).child("username").setValue(username);
    }
    public void updateFirstName(String firstName, String uid){
        ref.child("user").child(uid).child("first_name").setValue(firstName);
    }
    public void updateLastName(String lastName, String uid){
        ref.child("user").child(uid).child("last_name").setValue(lastName);
    }
    public void updateEmail(String username, String uid){
        ref.child("user").child(uid).child("email");
    }


    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(cal.getTime());
    }
    public String giveYear() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(cal.getTime());
    }
    public String giveMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(cal.getTime());
    }
    public String giveDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(cal.getTime());
    }



    public void removeCollection(String path){

    }

    public List<User> retrieveUsers() {
        String uid = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("user").child(uid);
        final List<User> userList = new ArrayList<User>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User u = new User();
                    u.setFirst_name((String) userSnapshot.child("first_name").getValue());
                    u.setUsername((String) userSnapshot.child("username").getValue());
                    userList.add(u);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return userList;
    }

}
