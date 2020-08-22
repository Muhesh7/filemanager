package com.example.filemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends Activity implements RecyclerViewClick {
    private File file;
    private List<String> myList;
    recyclerAdapter adapter;
    RecyclerView recyclerView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview1);
        myList = new ArrayList<String>();
      //Works only for api 28 and below
        String root_sd = Environment.getExternalStorageDirectory().getPath().toString();
        file = new File(root_sd);
        File list[] = file.listFiles();

        for (File file1:file.listFiles()) {
            myList.add(file1.getPath());
        }

         adapter = new recyclerAdapter((ArrayList<String>) myList, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void itemClick(int position,RecyclerView view,String filename ) {
        if(view.getVisibility()!=View.VISIBLE) {
            File sub_file = new File(filename);
            ArrayList<String> subList = new ArrayList<>();
            if (!sub_file.isFile()) {
                File list[] = sub_file.listFiles();
                for (File file1 : sub_file.listFiles()) {
                    subList.add(file1.getPath());
                }
                view.setVisibility(View.VISIBLE);
                recyclerAdapter adapter2 = new recyclerAdapter((ArrayList<String>) subList, this);
                view.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();

            }
        }
        else {
            view.setVisibility(View.GONE);
        }
    }
}
