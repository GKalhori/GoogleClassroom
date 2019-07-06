package com.example.googleclassroom;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class classPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);

        BottomNavigationView bottomNav = findViewById(R.id.navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new peoplefragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null ;
        switch (menuItem.getItemId()){
            case R.id.people:
                selectedFragment = new peoplefragment();
                break;
            case R.id.stream:
                selectedFragment = new classworkfragment();
                break;
            case R.id.classwork:
                selectedFragment = new streamfragment();
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit() ;
        return true ;
    }
};
}


