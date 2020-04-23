package com.acamara.blowit.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.acamara.blowit.FirebaseHandler;
import com.acamara.blowit.R;
import com.acamara.blowit.User;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ListView listView;
    private FirebaseHandler fb = new FirebaseHandler();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        listView = root.findViewById(R.id.userList);
        final List<User> subsList = fb.retrieveUsers();
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                subsList
        );
        listView.setAdapter(adapter);
        return root;
    }
}
