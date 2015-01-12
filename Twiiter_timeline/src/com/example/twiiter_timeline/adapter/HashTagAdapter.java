package com.example.twiiter_timeline.adapter;

import java.util.ArrayList;

import com.example.twiiter_timeline.R;
import com.example.twiiter_timeline.model.HashTag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HashTagAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<HashTag> lista;
	
	public HashTagAdapter(Context context, ArrayList<HashTag> lista){
		
		this.context = context;
		this.lista = lista;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		ViewHolder holder = new ViewHolder();
		 
        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hashtags, parent, false);
        }
		
        holder.txtHashTag = (TextView) convertView.findViewById(R.id.txt_hashtag);
        HashTag etiqueta = lista.get(position);
        
        holder.txtHashTag.setText(etiqueta.getHashtag());
        
		return convertView;
	}
	
	private class ViewHolder{
		public TextView txtHashTag;
	}

}
