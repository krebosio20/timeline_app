package com.example.twiiter_timeline;

import java.util.ArrayList;

import com.example.twiiter_timeline.adapter.HashTagAdapter;
import com.example.twiiter_timeline.model.HashTag;
import com.example.twiiter_timeline.utils.ConstantsUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity {
	
	ListView lvHashTag;
	ArrayList<HashTag> etiquetas = new ArrayList<HashTag>();
	HashTag etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5;
	HashTagAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lvHashTag = (ListView) findViewById(R.id.lvHashTag);
        
        etiqueta1 = new HashTag("developer");
        etiquetas.add(etiqueta1);
        etiqueta2 = new HashTag("androidio");
        etiquetas.add(etiqueta2);
        etiqueta3 = new HashTag("MejorandolaLIVE");
        etiquetas.add(etiqueta3);
        etiqueta4 = new HashTag("nodebots");
        etiquetas.add(etiqueta4);
        etiqueta5 = new HashTag("games");
        etiquetas.add(etiqueta5);
        
        
        adapter = new HashTagAdapter(this, etiquetas);
        lvHashTag.setAdapter(adapter);
        
        lvHashTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapter, View view,
                    int position, long arg) {
            	HashTag tag = (HashTag) lvHashTag.getAdapter().getItem(position);
            	ConstantsUtils.tag = tag.getHashtag();
            	Toast.makeText(MainActivity.this, ConstantsUtils.MEJORANDROID_TERM, Toast.LENGTH_SHORT).show();
            	
            	Intent i = new Intent(MainActivity.this, TimelineActivity.class);
            	
            	startActivity(i);
            }
        });
        
    }

}
