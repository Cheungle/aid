package com.example.aid;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aid.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id = getIntent().getStringExtra("id");
        if(id!= null)this.id = id;
        Log.i("idi",this.id);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard,
                    R.id.navigation_forum, R.id.navigation_message, R.id.navigation_notifications)
                    .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                Log.v("change","change");
                if(destination.getId() == R.id.navigation_notifications ){
                    if(arguments==null)arguments = new Bundle();
                    arguments.putString("id", MainActivity.this.id);
                    NavArgument nav = new NavArgument.Builder().setDefaultValue(MainActivity.this.id).build();
                    destination.addArgument("id",nav);
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //把当前用户id传给论坛页
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                Log.v("change","change");
                if(destination.getId() == R.id.navigation_forum){
                    if(arguments==null)arguments = new Bundle();
                    arguments.putString("id", MainActivity.this.id);
                    NavArgument nav = new NavArgument.Builder().setDefaultValue(MainActivity.this.id).build();
                    destination.addArgument("id",nav);
                }
            }
        });
        //把当前用户id传给消息页
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                Log.v("change","change");
                if(destination.getId() == R.id.navigation_message){
                    if(arguments==null)arguments = new Bundle();
                    arguments.putString("id", MainActivity.this.id);
                    NavArgument nav = new NavArgument.Builder().setDefaultValue(MainActivity.this.id).build();
                    destination.addArgument("id",nav);
                }
            }
        });

    }
    }
