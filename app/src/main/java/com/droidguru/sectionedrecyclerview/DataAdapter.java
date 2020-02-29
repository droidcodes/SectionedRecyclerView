package com.droidguru.sectionedrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {

    Context context;
    ArrayList<HashMap<String,String>> list = new ArrayList<>();
    public DataAdapter(Context con,ArrayList<HashMap<String,String>> dataList)
    {
        context = con;
        list = dataList;
    }
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recycler,parent,false);
        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        HashMap<String,String> dataMap = list.get(position);
        holder.tvDesc1.setText(dataMap.get("Desc1"));
        holder.tvDesc2.setText(dataMap.get("Desc2"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder
    {
        TextView tvDesc1,tvDesc2;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc1 = (TextView)itemView.findViewById(R.id.text_desc1);
            tvDesc2 = (TextView)itemView.findViewById(R.id.text_desc2);
        }
    }
}
