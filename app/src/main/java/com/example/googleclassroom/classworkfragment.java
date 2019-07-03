package com.example.googleclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class classworkfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classwork,container,false);
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
