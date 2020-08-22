package com.example.filemanager;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerViewClick {
    void itemClick(int position, RecyclerView view,String name);
}
