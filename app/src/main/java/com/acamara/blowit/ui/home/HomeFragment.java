package com.acamara.blowit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.acamara.blowit.FirebaseHandler;
import com.acamara.blowit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView counter;
    private FirebaseHandler fb = new FirebaseHandler();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        final long[] value= new long[1];
        counter = root.findViewById(R.id.counter);
        String uid = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("user").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value[0] = (long) dataSnapshot.child("counter").getValue();
                System.out.println("From Firebase yooooo"+value[0]);
                counter.setText(Long.toString(value[0]));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Code to retrieve count from SQLite
            }
        });

        final ConstraintLayout screen = (ConstraintLayout) root.findViewById(R.id.tapArea);
        screen.setOnTouchListener(new View.OnTouchListener() {
            int i = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    value[0] = value[0] +1;
                    ref.child("counter").setValue(value[0]);
                    counter.setText(Long.toString(value[0]));
                }
                return true;
            }
        });



        return root;
    }

}
