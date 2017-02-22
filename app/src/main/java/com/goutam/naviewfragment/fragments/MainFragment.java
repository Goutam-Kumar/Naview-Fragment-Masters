package com.goutam.naviewfragment.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goutam.naviewfragment.R;
import com.goutam.naviewfragment.interfaces.SecondaryFragmentSelect;
import com.goutam.naviewfragment.navigationhelper.CustomNavigationView;

import java.util.ArrayList;

/**
 * Created by Goutam Kumar K. on 22/2/17.
 */

public class MainFragment extends Fragment
        implements CustomNavigationView.NavigationItemSelectedListner{
    ArrayList<String> menuList;
    ArrayAdapter<String> menuAdapter;
    CustomNavigationView navView;
    DrawerLayout drawerLayout;
    FragmentTransaction ft;
    private View view = null;
    private ImageView imgMenu = null;

    private SecondaryFragmentSelect secondaryFragmentSelect = null;
    public static MainFragment getInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navView = (CustomNavigationView) view.findViewById(R.id.navView);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        imgMenu = (ImageView) view.findViewById(R.id.imgMenu);

        secondaryFragmentSelect = (SecondaryFragmentSelect) getActivity();
        FirstFragment firstFragment = new FirstFragment();
        ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, firstFragment).commit();

        prepareListItems();
        menuAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuList);
        navView.setAdapter(menuAdapter);
        navView.setHeaderView(getHeader(),20);
        navView.setOnNavigationItemSelectedListner(this);
        navView.setScrollState(CustomNavigationView.MENU_ITEM_SCROLLABLE);
        navView.setSelectionBackGround(getResources().getColor(R.color.colorAccent));

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawerLayout.getChildAt(0).setTranslationX(slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }


    @Override
    public void onItemSelected(View view, int position) {
        switch (position){
            case 0:
                Fragment firstFragment = FirstFragment.getInstance();
                ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, firstFragment).commit();
                break;
            case 1:
                Fragment secondFragment = SecondFragment.getInstance();
                ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, secondFragment).commit();
                break;
            case 2:
                /*Fragment secondaryFragment = SecondaryFragment.getInstance();
                ft = getChildFragmentManager().beginTransaction();
                ft.addToBackStack("MainFragment");
                ft.replace(R.id.frameContainer,secondaryFragment).commit();*/
                secondaryFragmentSelect.onSelectSecondaryFragment();
                break;
        }
        drawerLayout.closeDrawers();
    }

    private View getHeader() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.header, null);
        TextView button = (TextView) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("header", "buttonClicked");
            }
        });
        return view;
    }

    private void prepareListItems() {
        menuList = new ArrayList<>();
        menuList.add("One");
        menuList.add("Two");
        menuList.add("Fragment Without Drawer Menu");
    }
}
