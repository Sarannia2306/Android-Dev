package com.example.fitness;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.fitness.databinding.ActivityNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation_bottom extends AppCompatActivity {

    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.navView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.navigation_workout) {
                replaceFragment(new WorkoutFragment());
            } else if (itemId == R.id.navigation_notifications) {
                replaceFragment(new NotificationsFragment());
            } else if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
            }else if (itemId == R.id.navigation_me) {
                replaceFragment(new MeFragment());
            }

            return true;
        });

        // BottomNavigationView navView = findViewById(R.id.nav_view);

        //  AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        // R.id.navigation_home, R.id.navigation_me, R.id.navigation_notifications, R.id.navigation_workout
        //  ).build();

        // Find the NavController associated with the NavHostFragment
        // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navigation);

        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // NavigationUI.setupWithNavController(binding.navView, navController);
        // navController.navigate(R.id.navigation_home);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_navigation, fragment);
        fragmentTransaction.commit();

    }
}
