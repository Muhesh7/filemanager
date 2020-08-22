package com.example.filemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyHolder> {

    private ArrayList<String> mStrings=new ArrayList<>();
    private ArrayList<String> mSubStrings=new ArrayList<>();
    private RecyclerViewClick mRecyclerViewClick;
    Context mContext;

    public recyclerAdapter(ArrayList<String> strings,RecyclerViewClick recyclerViewClick) {
        mStrings = strings;
        mRecyclerViewClick=recyclerViewClick;

    }

    @NonNull
    @Override
    public recyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.node,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final recyclerAdapter.MyHolder holder, final int position) {
        String[] strings=mStrings.get(position).split("/");
        Log.d("dd",new File(mStrings.get(position)).getParent());
          holder.mTextView.setText(strings[strings.length-1]);
       holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewClick.itemClick(position,holder.mRecyclerView,mStrings.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

   class MyHolder extends RecyclerView.ViewHolder {
       TextView mTextView;
       RecyclerView mRecyclerView;
        MyHolder(@NonNull final View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.text);
            mRecyclerView=itemView.findViewById(R.id.recyclerview);
            mRecyclerView.setVisibility(View.GONE);

        }
    }

}
