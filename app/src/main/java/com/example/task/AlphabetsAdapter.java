package com.example.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlphabetsAdapter extends BaseAdapter {

   List<String> listAlphabets = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
   Callback callback;
   public AlphabetsAdapter(Callback callback){
       this.callback = callback;
   }

    @Override
    public int getCount() {
        return listAlphabets.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alphabets, null);
        }

        TextView textView = convertView.findViewById(R.id.tvAlphabet);
        textView.setText(listAlphabets.get(position));
        convertView.setOnClickListener(v -> {
            callback.onClick(position);
        });
        return convertView;
    }


    public interface Callback{
        void onClick(int position);
    }
}
