package com.example.cagliari2020;

import it.html.androidgui.lezione09.contents.DataProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
	 
    public PagerAdapter(FragmentManager manager) 
    {
        super(manager);
    }
 
    @Override
    public Fragment getItem(int index) {
    	Bundle b=new Bundle();
    	ContentFragment fragment=null;
    	String category=DataProvider.getCategoryByIndex(index);
    	b.putString(DataProvider.CATEGORY,category);
    	fragment=new ContentFragment();
    	fragment.setArguments(b);
 
        return fragment;
    }
 
    @Override
    public int getCount() 
    {
        return DataProvider.getCatoriesCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DataProvider.getCategoryByIndex(position);
    }
}