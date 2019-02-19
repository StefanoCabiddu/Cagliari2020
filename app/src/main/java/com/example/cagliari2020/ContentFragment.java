package com.example.cagliari2020;

import com.example.cagliari2020.Content.DataProvider;
import com.example.cagliari2020.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentFragment extends ListFragment
{  
 private ArrayAdapter<DataProvider.Content> adapter;

@Override  
 public void onListItemClick(ListView lv, View view, int position, long id) 
 {
     DataProvider.Content c=adapter.getItem(position);
     Intent i=new Intent(getActivity(),DetailActivity.class);
     i.putExtra("TITLE", c.getTitle());
     i.putExtra("TEXT", c.getDescription());
     getActivity().startActivity(i);
 }  
 
 @Override  
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
 {
	 Bundle b=getArguments();
	 String category=(String) b.get(DataProvider.CATEGORY);
	 
    adapter = new ArrayAdapter<DataProvider.Content>(
    inflater.getContext(), android.R.layout.simple_list_item_1,  
    DataProvider.getContentsByCategory(category));  
    setListAdapter(adapter);
    return super.onCreateView(inflater, container, savedInstanceState);
 }  
}  