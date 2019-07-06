package com.example.googleclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import io.github.yavski.fabspeeddial.FabSpeedDial;

public class classworkfragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        FabSpeedDial fabSpeedDial = (FabSpeedDial)getView().findViewById(R.id.fabspeed);
//        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
//            @Override
//            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemSelected(MenuItem menuItem) {
//
//                return true;
//            }
//
//            @Override
//            public void onMenuClosed() {
//
//            }
//        });
        return inflater.inflate(R.layout.fragment_classwork, container, false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menuclasswork, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Classess:
                Intent intent = new Intent(getActivity(),firstPage.class) ;
                startActivity(intent);

                return true ;
            case R.id.more_vertt:
                Intent intent1 = new Intent(getActivity(),aboutUs.class) ;
                startActivity(intent1);
                return true ;
            case R.id.setting:
                Intent intent2 = new Intent(getActivity(),aboutUs.class) ;
                startActivity(intent2);

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
