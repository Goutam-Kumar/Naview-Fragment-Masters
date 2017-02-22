package com.goutam.naviewfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.goutam.naviewfragment.fragments.MainFragment;
import com.goutam.naviewfragment.fragments.SecondaryFragment;
import com.goutam.naviewfragment.interfaces.SecondaryFragmentSelect;

/**
 * Created by Goutam Kumar K. on 22/2/17.
 */

public class MainActivity extends AppCompatActivity
implements SecondaryFragmentSelect{
    private FrameLayout frameContainer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameContainer = (FrameLayout) findViewById(R.id.frameContainer);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = MainFragment.getInstance();
        if (fragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.replace(R.id.frameContainer,fragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onSelectSecondaryFragment() {
        Fragment secondaryFragment = SecondaryFragment.getInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("MainFragment");
        ft.replace(R.id.frameContainer,secondaryFragment).commit();
    }
}
