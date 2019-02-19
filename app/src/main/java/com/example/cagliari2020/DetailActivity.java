package com.example.cagliari2020;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Intent i=getIntent();
		String title=i.getStringExtra("TITLE");
		String text=i.getStringExtra("TEXT");

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Articolo in dettaglio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txt=(TextView) findViewById(R.id.title);
		txt.setText(title);
		txt=(TextView) findViewById(R.id.text);
		txt.setText(text);
	}
}
