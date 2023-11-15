package com.example.fitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;
import com.nex3z.notificationbadge.NotificationBadgeFactory;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private List<NotificationModel> notificationList;
    private NotificationBadge badge;

    private DatabaseReference databaseReference;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Noti");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize your data and adapter
        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(notificationList);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(notificationAdapter);

        // Fetch data from Firebase
        fetchDataFromFirebase();

        // Initialize the badge
        badge = NotificationBadgeFactory.create(getActivity()).view(view.findViewById(R.id.bottom_nav_notifications));

        return view;
    }

    private void fetchDataFromFirebase() {
        // Add a listener to fetch data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear existing data
                notificationList.clear();

                // Iterate through the dataSnapshot to get notification data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.child("title").getValue(String.class);
                    String message = snapshot.child("message").getValue(String.class);
                    boolean isNew = determineIfNewNotification(snapshot);

                    // Add the notification to the list
                    notificationList.add(new NotificationModel(title, message, isNew));
                }

                // Notify the adapter that the data has changed
                notificationAdapter.notifyDataSetChanged();

                // Update the badge count based on the number of new notifications
                updateBadgeCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private boolean determineIfNewNotification(DataSnapshot snapshot) {
        // Implement your logic here based on timestamp, flags, or any other criteria
        // For simplicity, let's assume all notifications are new
        return true;
    }

    private void updateBadgeCount() {
        // Count the number of new notifications
        int newNotificationCount = 0;
        for (NotificationModel notification : notificationList) {
            if (notification.isNew()) {
                newNotificationCount++;
            }
        }

        // Set the badge count
        badge.setNumber(newNotificationCount);
    }
}
