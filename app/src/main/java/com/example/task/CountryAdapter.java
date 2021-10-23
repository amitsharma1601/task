package com.example.task;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    LayoutInflater inflater;
    List<CountryData> countryDataList;
    List<CountryData> searchList;
    int[] indexOfAlphabet;

    public CountryAdapter(Context context, List<CountryData> countryDataList, int[] indexOfAlphabet) {
        this.context = context;
        this.countryDataList = countryDataList;
        this.indexOfAlphabet = indexOfAlphabet;
        searchList = new ArrayList<>();
        searchList.addAll(countryDataList);
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new HeaderViewHolder(inflater.inflate(R.layout.list_item_alphabets, parent, false));
        }else{
            return new CountryViewHolder(inflater.inflate(R.layout.items, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CountryData countryData = searchList.get(position);
        if (holder instanceof CountryViewHolder) {
            ((CountryViewHolder) holder).tvCountryName.setText(countryData.countryName);
            ((CountryViewHolder) holder).ivCountryFlag.setImageResource(R.drawable.india);
        }else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) ((HeaderViewHolder)holder).itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.bottomMargin = 5;
            layoutParams.topMargin = 5;
            ((HeaderViewHolder)holder).itemView.setLayoutParams(layoutParams);
            ((HeaderViewHolder)holder).itemView.setBackgroundColor(Color.GRAY);
            ((HeaderViewHolder)holder).tvAlphabet.setText(countryData.countryName);
        }

    }
    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void filter(String s){
        searchList.clear();
        if(s.isEmpty()){
            searchList.addAll(countryDataList);
        }
        for (CountryData data: countryDataList){
            if(data.countryName.toUpperCase().contains(s.toUpperCase())){
                searchList.add(data);
            }
        }

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(indexOfCount(position) != -1){
            return 0; // view type is HEADER
        }else {
            return 1; // view type is COUNTRY
        }
    }

    private int indexOfCount(int position){
        for (int i: indexOfAlphabet) {
            if(i == position){
                return i;
            }
        }

        return -1;
    }
}


class CountryViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivCountryFlag;
    public TextView tvCountryName;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
        tvCountryName = itemView.findViewById(R.id.tvCountryName);
    }
}

class HeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView tvAlphabet;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvAlphabet = itemView.findViewById(R.id.tvAlphabet);
    }
}



